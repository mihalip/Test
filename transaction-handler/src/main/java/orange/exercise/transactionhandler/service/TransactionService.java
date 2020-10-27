package orange.exercise.transactionhandler.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import orange.exercise.transactionhandler.dataaccess.model.FinancialTransaction;
import orange.exercise.transactionhandler.dataaccess.model.FinancialTransactionDto;
import orange.exercise.transactionhandler.dataaccess.model.TransactionMapper;
import orange.exercise.transactionhandler.dataaccess.model.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    public String saveTransaction(FinancialTransactionDto financialTransactionDto) {
        return transactionRepository.save(transactionMapper.dtoToEntity(financialTransactionDto)).getId().toString();
    }

    public String getReportForPersonId(String cnp) {
        List<FinancialTransaction> financialTransactions = transactionRepository.findByCnp(cnp);
        if (financialTransactions != null && !financialTransactions.isEmpty()) {
            FinancialTransaction firstTransaction = financialTransactions.get(0);
            boolean isPayer = isPayer(cnp, firstTransaction.getCnpPayer());
            String name = isPayer ? firstTransaction.getNamePayer() : firstTransaction.getNamePayee();
            List<String> ibanList = financialTransactions.stream()
                                                         .map(financialTransaction -> isPayer(cnp, financialTransaction.getCnpPayer())
                                                                 ? financialTransaction.getIbanPayer()
                                                                 : financialTransaction.getIbanPayee())
                                                         .filter(Objects::nonNull)
                                                         .collect(Collectors.toList());
            List<String> walletList = financialTransactions.stream()
                                                         .map(financialTransaction -> isPayer(cnp, financialTransaction.getCnpPayer())
                                                                 ? financialTransaction.getWalletPayer()
                                                                 : financialTransaction.getWalletPayee())
                                                         .filter(Objects::nonNull)
                                                         .collect(Collectors.toList());

            String newLine = System.getProperty("line.separator");
            StringBuilder report = new StringBuilder(
                    "Nume: " + name + newLine + "CNP: " + cnp + newLine + "IBAN: " + (ibanList.size() > 0 ? ibanList.get(0) : "") + newLine + "Wallet: " + (walletList.size() > 0 ? walletList.get(0) : "") +
                    newLine + newLine + "Tranzactii:" + newLine);

            int totalTransactions = 0;
            long totalValue = 0L;

            for (TransactionType type : TransactionType.values()) {
                List<FinancialTransaction> typeTransactions =
                        financialTransactions.stream().filter(financialTransaction -> financialTransaction.getTransactionType().equals(type)).collect(Collectors.toList());
                int nrOfTransactions = typeTransactions.size();
                totalTransactions = totalTransactions + nrOfTransactions;
                Long totalAmount = typeTransactions.stream()
                                                         .map(financialTransaction -> isPayer(cnp, financialTransaction.getCnpPayee())
                                                                 ? financialTransaction.getAmount()
                                                                 : -financialTransaction.getAmount())
                                                         .reduce(0L, Long::sum);
                totalValue = totalValue + totalAmount;

                report.append(type).append(" | ").append(totalTransactions).append(" tranzactii | ").append(totalAmount).append(" RON").append(newLine);
                for (int i = 1; i <= typeTransactions.size(); i++) {
                    FinancialTransaction currentTransaction = typeTransactions.get(i-1);
                    report.append("\t")
                          .append(i)
                          .append(") De la ")
                          .append(currentTransaction.getCnpPayer())
                          .append(" catre ")
                          .append(currentTransaction.getCnpPayee())
                          .append(". Suma: ")
                          .append(currentTransaction.getAmount())
                          .append(" Descrierea: ")
                          .append(currentTransaction.getDescription()).append(newLine);
                }
            }
            System.out.print(report.toString());
            return report.toString();
        } else {
            System.out.print("NU exista nici o tranzactie pentru CNP-ul furnizat...");
            return "NU exista nici o tranzactie pentru CNP-ul furnizat...";
        }
    }

    private boolean isPayer(String cnp, String cnpPayer) {
        return cnpPayer.equals(cnp);
    }
}
