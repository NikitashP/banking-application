package com.banking;

import com.banking.account.exception.AccountNotFoundExceptionMapper;
import com.banking.account.repository.AccountRepository;
import com.banking.account.resource.AccountResource;
import com.banking.account.service.AccountService;
import com.banking.payment.exception.InsufficientBalanceExceptionMapper;
import com.banking.payment.exception.InvalidPaymentAmountExceptionMapper;
import com.banking.payment.exception.UnableToAcceptPaymentOrderExceptionMapper;
import com.banking.payment.repository.PaymentRepository;
import com.banking.payment.resource.PaymentResource;
import com.banking.payment.service.PaymentService;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;


public class Application {

    public static final String BASE_URI = "http://localhost:8090/";

    public static void main(String[] args) {
        startServer();
    }

    public static HttpServer startServer() {

        AccountRepository accountRepository = new AccountRepository();
        PaymentRepository paymentRepository = new PaymentRepository(paymentOrderQueue);
        AccountService accountService = new AccountService(accountRepository);
        PaymentService paymentService = new PaymentService(paymentRepository, accountRepository);
        AccountResource accountResource = new AccountResource(accountService);
        PaymentResource paymentResource = new PaymentResource(paymentService);
        AccountNotFoundExceptionMapper accountNotFoundExceptionMapper = new AccountNotFoundExceptionMapper();
        InsufficientBalanceExceptionMapper insufficientBalanceExceptionMapper = new InsufficientBalanceExceptionMapper();
        InvalidPaymentAmountExceptionMapper invalidPaymentAmountExceptionMapper = new InvalidPaymentAmountExceptionMapper();
        UnableToAcceptPaymentOrderExceptionMapper unableToAcceptPaymentOrderExceptionMapper = new UnableToAcceptPaymentOrderExceptionMapper();


        ResourceConfig config = new ResourceConfig()
                .register(accountResource)
                .register(paymentResource)
                .register(accountRepository)
                .register(paymentRepository)
                .register(accountService)
                .register(paymentService)
                .register(accountNotFoundExceptionMapper)
                .register(JacksonJsonProvider.class)
                .register(insufficientBalanceExceptionMapper)
                .register(invalidPaymentAmountExceptionMapper)
                .register(unableToAcceptPaymentOrderExceptionMapper);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }
}
