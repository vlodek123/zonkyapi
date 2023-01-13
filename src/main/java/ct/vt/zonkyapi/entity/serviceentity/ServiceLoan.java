package ct.vt.zonkyapi.entity.serviceentity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ServiceLoan {

    private int id;
    private double interestRate;
    private double amount;
}
