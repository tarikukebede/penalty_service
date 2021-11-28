package et.com.act.Controllers;

import et.com.act.Models.Payment;
import et.com.act.Models.PaymentContainer;
import et.com.act.Services.PenaltyCalculator;
import et.com.act.Utility.HttpResponses;
import et.com.act.Utility.PenaltyLogicProvider;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
public class PenaltyController {

    @Inject
    private PenaltyLogicProvider penaltyLogicProvider;


    public Response calculatePenalty(PaymentContainer paymentContainer){

        if(paymentContainer == null){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(HttpResponses.BAD_REQUEST)
                    .build();
        }

        PenaltyCalculator calculator = penaltyLogicProvider
                .getPenaltyService(paymentContainer.getLogicKey());

        Payment payment = calculator.calculatePenalty(paymentContainer);

        if(payment == null){
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(HttpResponses.INTERNAL_SERVER_ERROR)
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(payment)
                .build();
    }

    public Response calculatePenalty(List<PaymentContainer> paymentContainers){

        if(paymentContainers == null || paymentContainers.isEmpty()){
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(HttpResponses.BAD_REQUEST)
                    .build();
        }

        String logicKey = paymentContainers.get(0).getLogicKey();
        PenaltyCalculator calculator = penaltyLogicProvider
                .getPenaltyService(logicKey);

        List<Payment> payments = calculator.calculatePenalty(paymentContainers);

        if(payments == null){
            Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(HttpResponses.INTERNAL_SERVER_ERROR)
                    .build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(payments)
                .build();
    }
}
