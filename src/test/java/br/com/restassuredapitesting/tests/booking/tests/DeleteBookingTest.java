package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AccptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.E2eTests;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.greaterThan;


public class DeleteBookingTest extends BaseTest {
    PostBookingRequest postBookingRequest = new PostBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AccptanceTests.class})
    @DisplayName("Excluir uma reserva")
    public void excluirUmaReservaComToken() {
    int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        deleteBookingRequest.excluirReserva(idReserva, postAuthRequest.getToken())

                .then()
                .statusCode(201)
                .log().all();

    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Tentar excluir uma reserva que nao existe")
    public void tentarExcluirUmaReservaQueNaoExisteComToken() {
        deleteBookingRequest.tentarexcluirReservaQueNaoExiste(postAuthRequest.getToken())

                .then()
                .statusCode(404)
                .log().all();
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Excluir uma reserva Sem Token")
    public void tentarExcluirUmaReservaSemToken() {
        int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        deleteBookingRequest.tentarexcluirReservaSemToken(idReserva)
                .then()
                .statusCode(403)
                .log().all();
    }
}


