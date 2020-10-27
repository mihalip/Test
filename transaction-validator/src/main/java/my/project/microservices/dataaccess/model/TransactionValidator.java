package my.project.microservices.dataaccess.model;

import java.util.logging.Logger;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;

public class TransactionValidator implements ConstraintValidator<TransactionCheck, FinancialTransactionDto> {

    private Logger logger = Logger.getLogger(TransactionValidator.class.getName());

    public void initialize(TransactionCheck constraint) {
        //NOOP
    }

    public boolean isValid(FinancialTransactionDto financialTransactionDto, ConstraintValidatorContext context) {
        return validateElectronicDeviceTheftCase(financialTransactionDto);
    }

    public boolean validateElectronicDeviceTheftCase(FinancialTransactionDto electronicDeviceTheftDto) {
        switch (electronicDeviceTheftDto.getTransactionType()) {
            case IBAN_TO_IBAN:
                return validateIBAN(electronicDeviceTheftDto.getIbanPayer()) && validateIBAN(electronicDeviceTheftDto.getIbanPayee());
            case IBAN_TO_WALLET:
                return validateIBAN(electronicDeviceTheftDto.getIbanPayer()) && validateWallet(electronicDeviceTheftDto.getWalletPayee());
            case WALLET_TO_IBAN:
                return validateWallet(electronicDeviceTheftDto.getWalletPayer()) && validateIBAN(electronicDeviceTheftDto.getIbanPayee());
            case WALLET_TO_WALLET:
                return validateWallet(electronicDeviceTheftDto.getWalletPayer()) && validateWallet(electronicDeviceTheftDto.getWalletPayee());
            default:
                throw new ValidationException("This type of transaction is not supported yet!");
        }
    }

    private boolean validateIBAN(String iban) {
        if (!StringUtils.isBlank(iban) && iban.matches("RO[a-zA-Z0-9]{2}\\s?([a-zA-Z]{4}\\s?)([a-zA-Z0-9]{4}\\s?){4}\\s?")) {
            return true;
        }
        logger.severe("Invalid IBAN!");
        return false;
    }

    private boolean validateWallet(String wallet) {
        if (!StringUtils.isBlank(wallet) && wallet.matches("\\d{8}-\\d{4}-\\d{4}-\\d{4}-\\d{12}")) {
            return true;
        }
        logger.severe("Invalid walled id!");
        return false;
    }
}
