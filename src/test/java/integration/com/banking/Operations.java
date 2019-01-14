package integration.com.banking;

import com.banking.Application;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.UUID;

import static javax.ws.rs.core.Response.Status.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Operations {


    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() {
        server = Application.startServer();
        Client c = ClientBuilder.newClient();
        target = c.target(Application.BASE_URI);
    }

    @After
    public void tearDown() {
        server.shutdownNow();
    }


    @Test
    public void shouldReturnCreatedStatusOnAccountCreation() {
        UUID accountId = createAccount();
        assertNotNull(accountId);
    }


    @Test
    public void shouldReturnErrorMessageWhenAccountDoesNotExist() {

        String accountId = UUID.randomUUID().toString();
        Response response = target.path("accounts/")
                .path(accountId)
                .path("/balance")
                .request()
                .method("GET");

        assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
        String accountNotFoundExceptionMessage = response.readEntity(String.class);
        assertEquals("Account " + accountId + " not found", accountNotFoundExceptionMessage);
    }


    @Test
    public void shouldReturnAccountBalanceWhenAccountExists() {

        UUID accountId = createAccount();

        Response response = target.path("accounts/")
                .path(accountId.toString())
                .path("/balance")
                .request()
                .method("GET");

        assertEquals(OK.getStatusCode(), response.getStatus());
        assertEquals("Initial balance is Zero", "0", response.readEntity(String.class));

    }


    @Test
    public void shouldReturnErrorMessageWhenPaymentIsMadeAgainstInvalidPayeeAccount() {


        Response response = target.path("payments/")
                .path("/transfer")
                .request()
                .post(Entity.json("{\"payeeAccountId\":\"" + UUID.randomUUID().toString() + "\",\"beneficiaryAccountId\":\"" + UUID.randomUUID().toString() + "\",\"amount\":20}"));

        assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
    }


    @Test
    public void shouldReturnErrorMessageWhenPaymentIsMadeAgainstInvalidBeneficiaryAccount() {

        UUID payeeAccountId = createAccount();

        Response response = target.path("payments/")
                .path("/transfer")
                .request()
                .post(Entity.json("{\"payeeAccountId\":\"" + payeeAccountId.toString() + "\",\"beneficiaryAccountId\":\"" + UUID.randomUUID().toString() + "\",\"amount\":20}"));

        assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void shouldReturnErrorMessageWhenPaymentIsMadeAgainstPayeeAccountWithInsufficientBalance() {

        UUID payeeAccountId = createAccount();

        UUID beneficiaryAccountId = createAccount();

        Response response = target.path("payments/")
                .path("/transfer")
                .request()
                .post(Entity.json("{\"payeeAccountId\":\"" + payeeAccountId.toString() + "\",\"beneficiaryAccountId\":\"" + beneficiaryAccountId.toString() + "\",\"amount\":20}"));

        assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("Insufficient Balance for account " + payeeAccountId.toString(), response.readEntity(String.class));
    }


    @Test
    public void shouldReturnErrorMessageWhenPaymentIsWithInvalidAmount() {

        UUID payeeAccountId = createAccount();

        UUID beneficiaryAccountId = createAccount();

        Response response = target.path("payments/")
                .path("/transfer")
                .request()
                .post(Entity.json("{\"payeeAccountId\":\"" + payeeAccountId.toString() + "\",\"beneficiaryAccountId\":\"" + beneficiaryAccountId.toString() + "\",\"amount\":-20}"));

        assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("Amount should be non-negative", response.readEntity(String.class));
    }

    private UUID createAccount() {
        Response response = target.path("accounts/")
                .request()
                .method("POST");

        assertEquals(CREATED.getStatusCode(), response.getStatus());
        return response.readEntity(UUID.class);
    }
}
