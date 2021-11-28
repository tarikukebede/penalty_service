package et.com.act.Logics;
import et.com.act.Models.Payment;
import et.com.act.Models.PaymentContainer;
import et.com.act.Services.PenaltyCalculator;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Stateless
public class Yardstick extends PenaltyCalculator {


     @Override
     public Payment calculatePenalty(PaymentContainer paymentContainer) {
          System.out.println("Penalty Logic Yardstick");
          Payment payment = new Payment();

          Date nextPaymentPeriod = paymentContainer.getNextPaymentStartDate();
          int previousPaymentCount = paymentContainer.getPreviousPaymentsCount();

          payment.setAmount(paymentContainer.getAmount());
          payment.setLogicKey(paymentContainer.getLogicKey());

          if(previousPaymentCount < 2 &&
               !nextPaymentPeriod.before(new Date())){
               payment.setPenalty(paymentContainer.getAmount() * 0.1);
          }else if(previousPaymentCount < 2 &&
                       nextPaymentPeriod.before(new Date())){
                    payment.setPenalty(paymentContainer.getAmount() * 0.15);
          }else if(previousPaymentCount >= 2){
               payment.setPenalty(paymentContainer.getAmount() * 0.2);
          }
          return payment;
    }

     @Override
     public List<Payment> calculatePenalty(List<PaymentContainer> paymentContainers) {
          return paymentContainers.stream().map(this::calculatePenalty).collect(Collectors.toList());
     }
}