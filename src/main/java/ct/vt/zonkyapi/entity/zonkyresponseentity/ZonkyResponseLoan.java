package ct.vt.zonkyapi.entity.zonkyresponseentity;

import ct.vt.zonkyapi.entity.zonkyresponseentity.InsuranceHistory;
import ct.vt.zonkyapi.entity.zonkyresponseentity.Photo;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ZonkyResponseLoan {

    private int id;
    private String url;
    private String name;
    private String story;
    private String purpose;
    private ArrayList<Photo> photos;
    private int userId;
    private String borrowerNo;
    private String publicIdentifier;
    private String privateIdentifier;
    private String nickName;
    private int termInMonths;
    private double interestRate;
    private double revenueRate;
    private double annuity;
    private int premium;
    private String rating;
    private boolean topped;
    private double amount;
    private double remainingInvestment;
    private double investmentRate;
    private boolean covered;
    private double reservedAmount;
    private double zonkyPlusAmount;
    private Date datePublished;
    private boolean published;
    private Date deadline;
    private int investmentsCount;
    private String region;
    private String mainIncomeType;
    private String mainIncomeIndustry;
    private int activeLoansCount;
    private boolean insuranceActive;
    private boolean additionallyInsured;
    private ArrayList<Object> flags;
    private ArrayList<InsuranceHistory> insuranceHistory;
    private double annuityWithInsurance;

}


