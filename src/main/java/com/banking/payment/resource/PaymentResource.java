package com.banking.payment.resource;

import com.banking.payment.repository.PaymentOrder;
import com.banking.payment.service.PaymentService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("payments")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResource {
    private final PaymentService paymentService;

    public PaymentResource(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @POST
    @Path("/transfer")
    public Response create(PaymentOrder paymentOrder) {
        return Response.status(Response.Status.CREATED).entity(paymentService.acceptPaymentOrder(paymentOrder)).build();
    }
}
