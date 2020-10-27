package my.project.microservices.dataaccess.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Validated
@RequestMapping(path = "transaction")
public class TransactionValidatorController {

    private final RestTemplate restTemplate;
    @Value("${transaction.service.url:http://localhost:1111}")
    private String serviceUrl;

    @Autowired
    public TransactionValidatorController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String save(@NotNull @Valid @RequestBody FinancialTransactionDto financialTransactionDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FinancialTransactionDto> request = new HttpEntity<>(financialTransactionDto, headers);
        return restTemplate.postForObject(serviceUrl + "/transaction/save", request, String.class);
    }
}
