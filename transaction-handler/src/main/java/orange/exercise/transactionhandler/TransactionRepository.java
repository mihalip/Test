package orange.exercise.transactionhandler;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<FinancialTransaction, Long> {

    @Query(value = "select ft from FinancialTransaction ft where ft.cnpPayee = :cnp or ft.cnpPayer = :cnp")
    public List<FinancialTransaction> findByCnp(@Param("cnp") String cnp);
}
