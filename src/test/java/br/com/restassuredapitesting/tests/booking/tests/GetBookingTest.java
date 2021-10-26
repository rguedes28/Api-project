package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AccptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.ContractTests;
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
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","bookings"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class,ContractTests.class})
    @DisplayName("Garantir o schema de retorno da listagem de reserva")
    public void validaSchemaDeReservaEspecifica(){

        getBookingRequest.bookingReturbIdPeloPrimeiroNome_Checkin_Checkout("firstname", "Reserters",
                        "checkin", "2021-10-22", "checkout", "2021-10-31")
                .then()
                .statusCode(200)
                .log().all()
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","bookings"))));
    }


    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AccptanceTests.class})
    @DisplayName("Validar pelo id especifico")
    public void validaIdPeloidEspecificoDaReserva( ){
        int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        String fName = getBookingRequest.bookingReturnIdsEspecifico(idReserva).then().statusCode(200).extract().path("firstname");
        getBookingRequest.bookingReturnIdsEspecifico(idReserva)
                .then()
                .statusCode(200)
                .log().all()
                .body("firstname", is(fName));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AccptanceTests.class})
    @DisplayName("Listar ids pelo primeiro nome")
    public void validaIdPeloPrimeiroNomeDaReserva(){
        int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        String fName = getBookingRequest.bookingReturnIdsEspecifico(idReserva).then()
                        .statusCode(200).extract().path("firstname");

        getBookingRequest.bookingReturbIdPeloPrimeiroNome(fName)
                .then()
                .statusCode(200)
                .log().all()
                .body("bookingid", notNullValue());

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AccptanceTests.class})
    @DisplayName("Listar ids pelo Ultimo nome")
    public void validaIdPeloUltimoNomeDaReserva(){
        int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        String lName = getBookingRequest.bookingReturnIdsEspecifico(idReserva).then().extract().path("lastname");

        getBookingRequest.bookingReturbIdPeloUltimoNome(lName)
                .then()
                .statusCode(200)
                .log().all()
                .body("bookingid", notNullValue());

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AccptanceTests.class})
    @DisplayName("Listar ids pela data de checkin")
    public void validaidPelaDataDeChekinDaReserva(){
        int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        String chkin = getBookingRequest.bookingReturnIdsEspecifico(idReserva).then().extract().path("bookingdates.checkin");
        getBookingRequest.bookingReturbIdPeloCheckin(chkin)
                .then()
                .statusCode(200)
                .log().all()
                .body("[0].bookingid", notNullValue());


    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AccptanceTests.class})
    @DisplayName("Listar ids pela data de checkout")
    public void validaidPelaDataDeChekoutDaReserva(){
        int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        String chkout = getBookingRequest.bookingReturnIdsEspecifico(idReserva).then().extract().path("bookingdates.checkout");
        getBookingRequest.bookingReturbIdPeloCheckout(chkout)
                .then()
                .statusCode(200)
                .log().all()
                .body("[0].bookingid", notNullValue());


    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AccptanceTests.class})
    @DisplayName("Listar ids pela data de checkin e checkout")
    public void validaidPelaDataDeCheckin_CheckoutDaReserva(){
        int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        String chkin = getBookingRequest.bookingReturnIdsEspecifico(idReserva).then().extract().path("bookingdates.checkin");
        String chkout = getBookingRequest.bookingReturnIdsEspecifico(idReserva).then().extract().path("bookingdates.checkout");

        getBookingRequest.bookingReturbIdPeloCheckin_Checkout("checkin", chkin,
                        "checkout", chkout)
                .then()
                .statusCode(200)
                .log().all()
                .body("bookingid", notNullValue());


    }
   /* @Test
    public void testtester(){
       //RestAssured.baseURI ="https://treinamento-api.herokuapp.com/booking";
        RequestSpecification request = RestAssured.given();

        Response response = request.queryParam("checkin", "2018-01-01")
                .queryParam("checkout", "2019-01-01")
                .log().all()
                .get("https://treinamento-api.herokuapp.com/booking");


        String jsonString = response.asString();
        System.out.println(response.getStatusCode());
        Assert.assertEquals(jsonString.contains("bookingid"), true);
    }*/

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AccptanceTests.class})
    @DisplayName("Listar ids pelo Primeiro nome, data de checkin e checkout")
    public void validaidPeloPrimeiroNome_DataDeCheckin_CheckoutDaReserva(){
        int idReserva = postBookingRequest.insertBooking().then().statusCode(200).extract().path("bookingid");
        String chkin = getBookingRequest.bookingReturnIdsEspecifico(idReserva).then().extract().path("bookingdates.checkin");
        String chkout = getBookingRequest.bookingReturnIdsEspecifico(idReserva).then().extract().path("bookingdates.checkout");
        String fName = getBookingRequest.bookingReturnIdsEspecifico(idReserva).then().extract().path("firstname");
        getBookingRequest.bookingReturbIdPeloPrimeiroNome_Checkin_Checkout("firstname", fName,
                        "checkin", chkin, "checkout", chkout)
                .then()
                .statusCode(200)
                .log().all()
                .body("bookingid", notNullValue());


    }

}
