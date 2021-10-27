package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AccptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.E2eTests;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature - Criacao de Reservas")
public class PostBookingTest extends BaseTest {
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    //Inicio dos testes
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AccptanceTests.class})
    @DisplayName("Criar uma reserva nova")
    public void criarUmaReservaNova() {
        postBookingRequest.insertBooking()
                .then()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Criar uma reserva com mais itens no payload")
    public void criarUmaReservaNovaComPayloadComMaisItens() {
        postBookingRequest.insertBookingPayloadMoreItens()
                .then()
                .statusCode(400);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Tentar criar uma reserva com patload invalido")
    public void criarUmaReservaNovaComPayloadInvalido() {
        postBookingRequest.insertBookingPayloadInvalido()
                .then()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Validar a criacao de duas reservas em sequencia")
    public void ValidarAcriacaodeDuasReservaEmSequencia() {
        postBookingRequest.insertBooking()
                .then()
                .statusCode(200);

        postBookingRequest.insertBooking()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(1));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Validar status code 418 com header accept invalido")
    public void validarStatusCode418comHeaderAccptInvalido() {
        postBookingRequest.insertBookingHeaderAcceptInvalido()
                .then()
                .statusCode(418);
    }

}
