package ct.vt.zonkyapi.service;

import ct.vt.zonkyapi.callingservice.CallingClass;
import ct.vt.zonkyapi.entity.controllerentity.Loan;
import ct.vt.zonkyapi.entity.serviceentity.ServiceLoan;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZonkyAPServiceiImpl implements ZonkyApiService {

    @Autowired
    CallingClass callingService;

    List<ServiceLoan> loanList;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public String getAverageRateV1() throws Exception {
        this.loanList = callingService.getZonkyLoans();
        double averageValue = getAverageValue(2);
        return String.format("Průměrný úrok: %s %c posledních %d úvěrů.", df.format(averageValue*100), '%', this.loanList.size() );
    }

    @Override
    public String getAverageLoanV1() throws Exception{
        this.loanList = callingService.getZonkyLoans();
        double averageValue = getAverageValue(3);
        return String.format("Průměrný úver: %s Kč z posledních %d úvěrů.", df.format(averageValue), this.loanList.size() );
    }

    @Override
    public List<Loan> getLoansV1() throws Exception {
        this.loanList = callingService.getZonkyLoans();

        ModelMapper modelMapper = new ModelMapper();
        List<Loan> loans = loanList
                .stream()
                .map(serviceLoan -> modelMapper.map(serviceLoan, Loan.class))
                .collect(Collectors.toList());

        return loans;
    }

    @Override
    public String getAverageRateV2(String bearer) throws Exception {
        this.loanList = callingService.getZonkyLoans(bearer);
        double averageValue = getAverageValue(2);
        return String.format("Průměrný úrok: %s %c posledních %d úvěrů.", df.format(averageValue*100), '%', this.loanList.size() );
    }

    @Override
    public String getAverageLoanV2(String bearer) throws Exception {
        this.loanList = callingService.getZonkyLoans(bearer);
        double averageValue = getAverageValue(3);
        return String.format("Průměrný úver: %s Kč z posledních %d úvěrů.", df.format(averageValue), this.loanList.size() );
    }

    @Override
    public List<Loan> getLoansV2(String bearer) throws Exception {
        this.loanList = callingService.getZonkyLoans(bearer);

        ModelMapper modelMapper = new ModelMapper();
        List<Loan> loans = loanList
                .stream()
                .map(serviceLoan -> modelMapper.map(serviceLoan, Loan.class))
                .collect(Collectors.toList());

        return loans;
    }

    private double getAverageValue(int index) {
        double suma = 0;
        for (int i = 0; i < this.loanList.size(); i++) {
            if (index==2) {
                suma += loanList.get(i).getInterestRate();
            } else {
                suma += loanList.get(i).getAmount();
            }
        }
        return suma/this.loanList.size();
    };
}
