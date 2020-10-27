package orange.exercise.transactionhandler;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class FinancialTransaction {

    @Id
    @GeneratedValue()
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String ibanPayer;

    private String ibanPayee;

    private String walletPayer;

    private String walletPayee;
    @NotNull
    private String cnpPayer;
    @NotNull
    private String cnpPayee;
    @NotNull
    private String namePayer;
    @NotNull
    private String namePayee;
    @NotNull
    private String description;
    @NotNull
    private Long amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIbanPayer() {
        return ibanPayer;
    }

    public void setIbanPayer(String ibanPayer) {
        this.ibanPayer = ibanPayer;
    }

    public String getIbanPayee() {
        return ibanPayee;
    }

    public void setIbanPayee(String ibanPayee) {
        this.ibanPayee = ibanPayee;
    }

    public String getCnpPayer() {
        return cnpPayer;
    }

    public void setCnpPayer(String cnpPayer) {
        this.cnpPayer = cnpPayer;
    }

    public String getCnpPayee() {
        return cnpPayee;
    }

    public void setCnpPayee(String cnpPayee) {
        this.cnpPayee = cnpPayee;
    }

    public String getNamePayer() {
        return namePayer;
    }

    public void setNamePayer(String namePayer) {
        this.namePayer = namePayer;
    }

    public String getNamePayee() {
        return namePayee;
    }

    public void setNamePayee(String namePayee) {
        this.namePayee = namePayee;
    }

    public String getWalletPayer() {
        return walletPayer;
    }

    public void setWalletPayer(String walletPayer) {
        this.walletPayer = walletPayer;
    }

    public String getWalletPayee() {
        return walletPayee;
    }

    public void setWalletPayee(String walletPayee) {
        this.walletPayee = walletPayee;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
