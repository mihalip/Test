package orange.exercise.transactionhandler;

import io.swagger.annotations.Api;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import orange.exercise.transactionhandler.dataaccess.model.FinancialTransactionDto;
import orange.exercise.transactionhandler.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping(path = "transaction")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @CrossOrigin
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    protected String saveTransaction(@NotNull @Valid @RequestBody FinancialTransactionDto financialTransactionDto) {
        return transactionService.saveTransaction(financialTransactionDto);
    }

    @GetMapping(path = "/{cnp}")
    protected String getReport(@NotNull @PathVariable("cnp") String cnp) {
        return transactionService.getReportForPersonId(cnp);
    }
}
