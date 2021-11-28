package et.com.act.Logics;

import et.com.act.Models.Payment;
import et.com.act.Models.PaymentContainer;
import et.com.act.Services.PenaltyCalculator;

public class AddisAbay extends PenaltyCalculator {

    @Override
    public Payment calculatePenalty(PaymentContainer paymentContainer) {
        System.out.println("Penalty Logic AddisAbay");
        return super.calculatePenalty(paymentContainer);
    }
}
