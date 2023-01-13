package ct.vt.zonkyapi.controller;


import ct.vt.zonkyapi.entity.controllerentity.Loan;
import ct.vt.zonkyapi.exception.ErrorResponse;
import ct.vt.zonkyapi.service.PositiveResponse;
import ct.vt.zonkyapi.service.ZonkyApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zonkyapi")
public class ZonkyApiController {

    @Autowired
    ZonkyApiService zonkyAPiService;

    @GetMapping("/averageRate")
    public ResponseEntity<Object> getAverageRate() throws Exception {
        String serviceResponse = zonkyAPiService.getAverageRate();
        PositiveResponse positiveResponse = new PositiveResponse(serviceResponse);
        return new ResponseEntity<>(positiveResponse, HttpStatus.OK);
    }

    @GetMapping("/averageLoan")
    public ResponseEntity<Object> getAverageLoan() throws Exception {
        String serviceResponse = zonkyAPiService.getAverageLoan();
        PositiveResponse positiveResponse = new PositiveResponse(serviceResponse);
        return new ResponseEntity<>(positiveResponse, HttpStatus.OK);
    }

    @GetMapping("/lastloans")
    public ResponseEntity<List<Loan>> getLastLoans() throws Exception {
        return new ResponseEntity<List<Loan>>(zonkyAPiService.getLoans(), HttpStatus.OK);
    }
}
