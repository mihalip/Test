package orange.exercise.transactionhandler;

import net.minidev.json.JSONObject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    FinancialTransaction dtoToEntity(FinancialTransactionDto financialTransaction);
}
