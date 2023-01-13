package ct.vt.zonkyapi.service;

import ct.vt.zonkyapi.entity.controllerentity.Loan;

import java.util.List;

public interface ZonkyApiService {
    public String getAverageRate() throws Exception;
    public String getAverageLoan() throws Exception;
    public List<Loan> getLoans() throws Exception;
}
