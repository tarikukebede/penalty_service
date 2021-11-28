package et.com.act.Interfaces;

import et.com.act.Models.Payment;
import et.com.act.Models.PaymentContainer;

import java.util.List;

public interface PenaltyLogic {
    Payment fixed(PaymentContainer paymentContainer, double fixedPenalty);
    List<Payment> fixed(List<PaymentContainer> paymentContainers, double fixedPenalty);
    Payment percentage(PaymentContainer paymentContainer, double percentage);
    List<Payment> percentage(List<PaymentContainer> paymentContainers, double percentage);
    Payment fixedPerDate(PaymentContainer paymentContainer, double fixedPenalty);
    Payment calculatePenalty(PaymentContainer paymentContainer);
    List<Payment> calculatePenalty(List<PaymentContainer> paymentContainers);
}
