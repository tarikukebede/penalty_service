package et.com.act.Resources;

import et.com.act.Controllers.PenaltyController;
import et.com.act.Models.PaymentContainer;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/calculatePenalty")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PenaltyResource{

    @Inject
    private PenaltyController penaltyController;

    @POST
    @Path("/single")
    public Response calculate(PaymentContainer paymentContainer){
        System.out.println("incoming request =====>");
        return penaltyController.calculatePenalty(paymentContainer);
    }

    @POST
    @Path("/batch")
    public Response calculate(List<PaymentContainer> paymentContainers){
        return penaltyController.calculatePenalty(paymentContainers);
    }
}
