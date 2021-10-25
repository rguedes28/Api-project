package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Step("Retorna os Id's da listagem de reservas")
    public Response bookingReturnIds(){

        return given()
                .when()
                .get("booking");



    }
    @Step("Retorna os Id's da especifico de uma reserva")
    public Response bookingReturnIdsEspecifico(){

        return given()
                .when()
                .get("booking/26");



    }


    @Step("Retorna os Id's pelo filtro de primeiro nome")
   public Response bookingReturbIdPeloPrimeiroNome(){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?firstname=Sally");

    }
    @Step("Retorna os Id's pelo filtro de ultimo nome")
    public Response bookingReturbIdPeloUltimoNome(){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?lastname=Brown");

    }
    @Step("Retorna os Id's pela data de checkin")
    public Response bookingReturbIdPeloCheckin(){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?checkin=2018-04-21");

    }
    @Step("Retorna os Id's pela data de checkput")
    public Response bookingReturbIdPeloCheckout(){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?checkout=2018-04-21");

    }
    @Step("Retorna os Id's pela data de checkin e checkout")
    public Response bookingReturbIdPeloCheckin_Checkout(String chkin, String data1,
                                                    String chkout, String data2) {
        return given()
                .queryParams(chkin, data1, chkout, data2).log().all()
                .header("Content-Type", "application/json")
                .when()
                .get("booking");
    }
    @Step("Retorna os Id's prlo primeiro nome e pelas datas de checkin e checkout")
    public Response bookingReturbIdPeloPrimeiroNome_Checkin_Checkout(String primeiroNome, String pnome, String chkin, String data1,
                                                        String chkout, String data2) {
        return given()
                .queryParams(primeiroNome, pnome, chkin, data1, chkout, data2).log().all()
                .header("Content-Type", "application/json")
                .when()
                .get("booking");
    }





}
