package et.com.act.Services;
import et.com.act.Interfaces.PenaltyLogic;
import et.com.act.Models.Payment;
import et.com.act.Models.PaymentContainer;
import et.com.act.Utility.DateUtil;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PenaltyCalculator implements PenaltyLogic {

    @Inject
    private DateUtil dateUtil;


    @Override
    public Payment fixed(PaymentContainer paymentContainer, double fixedPenalty) {
        float penalty = (float) (paymentContainer.getAmount() + fixedPenalty);
        return new Payment(paymentContainer.getAmount(), penalty, paymentContainer.getLogicKey());
    }

    @Override
    public List<Payment> fixed(List<PaymentContainer> paymentContainers, double fixedPenalty) {
        return paymentContainers.stream().map(paymentContainer -> new Payment(paymentContainer
                .getAmount(), fixedPenalty, paymentContainer.getLogicKey()))
                .collect(Collectors.toList());
    }

    @Override
    public Payment percentage(PaymentContainer paymentContainer, double percentage) {
        float penalty = (float) (paymentContainer.getAmount() + (paymentContainer.getAmount() * percentage));
        return new Payment(paymentContainer.getAmount(), penalty, paymentContainer.getLogicKey());
    }

    @Override
    public List<Payment> percentage(List<PaymentContainer> paymentContainers, double percentage) {
        return paymentContainers.stream()
                .map(paymentContainer -> new Payment(paymentContainer.getAmount(), paymentContainer.getAmount() * percentage, paymentContainer.getLogicKey()))
                .collect(Collectors.toList());
    }

    @Override
    public Payment fixedPerDate(PaymentContainer paymentContainer, double fixedPenalty) {
        int overdueDate = dateUtil.difference(paymentContainer.getPaymentEndDate(), new Date());
        return new Payment(paymentContainer.getAmount(), overdueDate * fixedPenalty, paymentContainer.getLogicKey());
    }

    @Override
    public Payment calculatePenalty(PaymentContainer paymentContainer) {
        return null;
    }

    @Override
    public List<Payment> calculatePenalty(List<PaymentContainer> paymentContainers) {
        return null;
    }
}
