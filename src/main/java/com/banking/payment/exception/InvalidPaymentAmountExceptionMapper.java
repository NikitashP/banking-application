package com.banking.payment.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidPaymentAmountExceptionMapper implements ExceptionMapper<InvalidPaymentAmountException> {
    @Override
    public Response toResponse(InvalidPaymentAmountException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}