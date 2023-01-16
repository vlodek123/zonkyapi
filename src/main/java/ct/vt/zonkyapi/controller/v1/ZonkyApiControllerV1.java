package ct.vt.zonkyapi.controller.v1;


import ct.vt.zonkyapi.entity.controllerentity.Loan;
import ct.vt.zonkyapi.service.PositiveResponse;
import ct.vt.zonkyapi.service.ZonkyApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zonkyapi")
public class ZonkyApiControllerV1 {

    @Autowired
    ZonkyApiService zonkyAPiService;

    @GetMapping("/v1/averageRate")
    public ResponseEntity<Object> getAverageRate() throws Exception {
        String serviceResponse = zonkyAPiService.getAverageRateV1();
        PositiveResponse positiveResponse = new PositiveResponse(serviceResponse);
        return new ResponseEntity<>(positiveResponse, HttpStatus.OK);
    }

    @GetMapping("/v1/averageLoan")
    public ResponseEntity<Object> getAverageLoan() throws Exception {
        String serviceResponse = zonkyAPiService.getAverageLoanV1();
        PositiveResponse positiveResponse = new PositiveResponse(serviceResponse);
        return new ResponseEntity<>(positiveResponse, HttpStatus.OK);
    }

    @GetMapping("/v1/lastloans")
    public ResponseEntity<List<Loan>> getLastLoans() throws Exception {
        return new ResponseEntity<List<Loan>>(zonkyAPiService.getLoansV1(), HttpStatus.OK);
    }


}
