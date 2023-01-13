package ct.vt.zonkyapi.entity.controllerentity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Loan {

    private int id;
    private double interestRate;
    private double amount;
}
