package orange.exercise.transactionhandler.dataaccess.model;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel
public class FinancialTransactionDto {

    private Long id;

    @NotNull
    private TransactionType transactionType;

    private String ibanPayer;

    private String walletPayer;
    @NotNull
    private String cnpPayer;
    @NotBlank(message = "Please provide a name")
    private String namePayer;

    private String ibanPayee;

    private String walletPayee;
    @NotNull
    private String cnpPayee;
    @NotBlank(message = "Please provide a name")
    private String namePayee;
    @NotBlank(message = "Please provide a description")
    private String description;
    @NotBlank(message = "Please provide an amount")
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

    public String getIbanPayer() {
        return ibanPayer;
    }

    public void setIbanPayer(String ibanPayer) {
        this.ibanPayer = ibanPayer;
    }

    public String getWalletPayer() {
        return walletPayer;
    }

    public void setWalletPayer(String walletPayer) {
        this.walletPayer = walletPayer;
    }

    public String getCnpPayer() {
        return cnpPayer;
    }

    public void setCnpPayer(String cnpPayer) {
        this.cnpPayer = cnpPayer;
    }

    public String getNamePayer() {
        return namePayer;
    }

    public void setNamePayer(String namePayer) {
        this.namePayer = namePayer;
    }

    public String getIbanPayee() {
        return ibanPayee;
    }

    public void setIbanPayee(String ibanPayee) {
        this.ibanPayee = ibanPayee;
    }

    public String getWalletPayee() {
        return walletPayee;
    }

    public void setWalletPayee(String walletPayee) {
        this.walletPayee = walletPayee;
    }

    public String getCnpPayee() {
        return cnpPayee;
    }

    public void setCnpPayee(String cnpPayee) {
        this.cnpPayee = cnpPayee;
    }

    public String getNamePayee() {
        return namePayee;
    }

    public void setNamePayee(String namePayee) {
        this.namePayee = namePayee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
