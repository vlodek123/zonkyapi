package ct.vt.zonkyapi.service;

import ct.vt.zonkyapi.entity.controllerentity.Loan;

import java.util.List;

public interface ZonkyApiService {
    public String getAverageRateV1() throws Exception;
    public String getAverageLoanV1() throws Exception;
    public List<Loan> getLoansV1() throws Exception;
    public String getAverageRateV2(String bearer) throws Exception;
    public String getAverageLoanV2(String bearer) throws Exception;
    public List<Loan> getLoansV2(String bearer) throws Exception;
}
