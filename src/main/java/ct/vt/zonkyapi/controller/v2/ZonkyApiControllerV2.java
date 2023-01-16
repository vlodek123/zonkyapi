package ct.vt.zonkyapi.controller.v2;

import ct.vt.zonkyapi.entity.controllerentity.Loan;
import ct.vt.zonkyapi.service.PositiveResponse;
import ct.vt.zonkyapi.service.ZonkyApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zonkyapi")
public class ZonkyApiControllerV2 {

    @Autowired
    ZonkyApiService zonkyAPiService;

    @GetMapping("/v2/averageRate")
    public ResponseEntity<Object> getAverageRateBearer(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearer) throws Exception {
        String serviceResponse = zonkyAPiService.getAverageRateV2(bearer);
        PositiveResponse positiveResponse = new PositiveResponse(serviceResponse);
        return new ResponseEntity<>(positiveResponse, HttpStatus.OK);
    }

    @GetMapping("/v2/averageLoan")
    public ResponseEntity<Object> getAverageLoanBearer(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearer) throws Exception  {
        String serviceResponse = zonkyAPiService.getAverageLoanV2(bearer);
        PositiveResponse positiveResponse = new PositiveResponse(serviceResponse);
        return new ResponseEntity<>(positiveResponse, HttpStatus.OK);
    }

    @GetMapping("/v2/lastloans")
    public ResponseEntity<List<Loan>> getLastLoansBearer(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearer) throws Exception {
        return new ResponseEntity<List<Loan>>(zonkyAPiService.getLoansV2(bearer), HttpStatus.OK);
    }


}
