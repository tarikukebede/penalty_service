package et.com.act.Models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentContainer implements Serializable {
    @NotNull
    private String logicKey;
    @NonNull
    private float amount;
    @NonNull
    private int previousPaymentsCount;
    private Date nextPaymentStartDate;
    private Date paymentEndDate;
}
