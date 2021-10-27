package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AccptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.ContractTests;
import br.com.restassuredapitesting.suites.E2eTests;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;

import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;


@Feature("Feature - Retorno de Reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar ids reserva")
    public void validaListagemDeIdsDasReservas(){

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));


    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class,ContractTests.class})
    @DisplayName("Garantir o schema de retorno da listagem de reserva")
    public void validaSchemaDaListagemDeReservas(){

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .log().all()
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","bookings"))));
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class,ContractTests.class})
    @DisplayName("Garantir o schema de retorno da listagem de reserva")
    public void validaSchemaDaListagemDeReservaEspecifica(){
        int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        String fName = getBookingRequest.bookingIdEspecifico(idReserva).then().statusCode(200).extract().path("firstname");

        getBookingRequest.bookingIdEspecifico(idReserva)
                .then()
                .statusCode(200)
                .log().all()
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","bookingsesp"))));

    }



    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AccptanceTests.class})
    @DisplayName("Validar uma reserva especifica")
    public void validarUmaReservaEspeciifica( ){
        int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        String fName = getBookingRequest.bookingIdEspecifico(idReserva).then().statusCode(200).extract().path("firstname");
        getBookingRequest.bookingReturnReservaEspecifica(fName)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AccptanceTests.class})
    @DisplayName("Listar ids pelo primeiro nome")
    public void validaIdPeloPrimeiroNomeDaReserva(){
        int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        String fName = getBookingRequest.bookingIdEspecifico(idReserva).then()
                        .statusCode(200).extract().path("firstname");

        getBookingRequest.bookingReturbIdPeloPrimeiroNome("firstname", fName)
                .then()
                .statusCode(200)
                .log().all()
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AccptanceTests.class})
    @DisplayName("Listar ids pelo Ultimo nome")
    public void validaIdPeloUltimoNomeDaReserva(){
        int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        String lName = getBookingRequest.bookingIdEspecifico(idReserva).then().extract().path("lastname");

        getBookingRequest.bookingReturbIdPeloUltimoNome("lastname", lName)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));


    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AccptanceTests.class})
    @DisplayName("Listar ids pela data de checkin")
    public void validaidPelaDataDeChekinDaReserva(){
        int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        String chkin = getBookingRequest.bookingIdEspecifico(idReserva).then().extract().path("bookingdates.checkin");
        getBookingRequest.bookingReturbIdPeloCheckin(chkin)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));


    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AccptanceTests.class})
    @DisplayName("Listar ids pela data de checkout")
    public void validaidPelaDataDeChekoutDaReserva(){
        int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        String chkout = getBookingRequest.bookingIdEspecifico(idReserva).then().extract().path("bookingdates.checkout");
        getBookingRequest.bookingReturbIdPeloCheckout(chkout)
                .then()
                .statusCode(200)
                .log().all()
                .body("size()", greaterThan(0));


    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AccptanceTests.class})
    @DisplayName("Listar ids pela data de checkin e checkout")
    public void validaidPelaDataDeCheckot_CheckoutDaReserva(){
        int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        String chkout1 = getBookingRequest.bookingIdEspecifico(idReserva).then().extract().path("bookingdates.checkout");


        getBookingRequest.bookingReturbIdPeloCheckout_Checkout(chkout1)
                .then()
                .statusCode(500);





    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AccptanceTests.class})
    @DisplayName("Listar ids pelo Primeiro nome, data de checkin e checkout")
    public void validaidPeloPrimeiroNome_DataDeCheckin_CheckoutDaReserva(){
        int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        String chkin = getBookingRequest.bookingIdEspecifico(idReserva).then().extract().path("bookingdates.checkin");
        String chkout = getBookingRequest.bookingIdEspecifico(idReserva).then().extract().path("bookingdates.checkout");
        String fName = getBookingRequest.bookingIdEspecifico(idReserva).then().extract().path("firstname");
        getBookingRequest.bookingReturbIdPeloPrimeiroNome_Checkin_Checkout("firstname", fName,
                        "checkin", chkin, "checkout", chkout)
                .then()
                .statusCode(200)
                .log().all()
                .body("size()", greaterThan(0));



    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, E2eTests.class})
    @DisplayName("Visyalizar um 500 por filtro mal formatado")
    public void validarstatuscode500PorfiltroMalFormatado(){

        getBookingRequest.statusCode500porFiltroMalFormayado("20140225")
                .then()
                .statusCode(500);




    }

}
