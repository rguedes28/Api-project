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

   public Response bookingReturbIdPeloPrimeiroNome(){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?firstname=Sally");

    }

    public Response bookingReturbIdPeloUltimoNome(){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?lastname=Brown");

    }

    public Response bookingReturbIdPeloCheckin(){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?checkin=2018-04-21");

    }

    public Response bookingReturbIdPeloCheckout(){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?checkout=2018-04-21");

    }

    public Response bookingReturbIdPeloCheckin_Checkout(String chkin, String data1,
                                                    String chkout, String data2) {
        return given()
                .queryParams(chkin, data1, chkout, data2).log().all()
                .header("Content-Type", "application/json")
                .when()
                .get("booking");
    }

    public Response bookingReturbIdPeloPrimeiroNome_Checkin_Checkout(String primeiroNome, String pnome, String chkin, String data1,
                                                        String chkout, String data2) {
        return given()
                .queryParams(primeiroNome, pnome, chkin, data1, chkout, data2).log().all()
                .header("Content-Type", "application/json")
                .when()
                .get("booking");
    }





}
