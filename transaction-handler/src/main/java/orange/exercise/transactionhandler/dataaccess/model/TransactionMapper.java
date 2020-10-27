package orange.exercise.transactionhandler.dataaccess.model;

import orange.exercise.transactionhandler.dataaccess.model.FinancialTransaction;
import orange.exercise.transactionhandler.dataaccess.model.FinancialTransactionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    FinancialTransaction dtoToEntity(FinancialTransactionDto financialTransaction);
}
