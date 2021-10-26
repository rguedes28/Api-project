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
    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Step("Retorna os Id's da listagem de reservas")
    public Response bookingReturnIds(){

        return given()
                .when()
                .get("booking");



    }
    @Step("Retorna os Id's da especifico de uma reserva")
    public Response bookingReturnIdsEspecifico( int idr){

        return given()
                .when()
                .get("booking/"+idr);



    }


    @Step("Retorna os Id's pelo filtro de primeiro nome")
   public Response bookingReturbIdPeloPrimeiroNome(String name1){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .log().all()
                .get("booking?firstname=" + name1);

    }
    @Step("Retorna os Id's pelo filtro de ultimo nome")
    public Response bookingReturbIdPeloUltimoNome(String name2){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .log().all()
                .get("booking?lastname="+name2);

    }
    @Step("Retorna os Id's pela data de checkin")
    public Response bookingReturbIdPeloCheckin(String date1){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .log().all()
                .get("booking?checkin="+date1);

    }
    @Step("Retorna os Id's pela data de checkput")
    public Response bookingReturbIdPeloCheckout(String date2){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .log().all()
                .get("booking?checkout="+date2);

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
    @Step("Retorna os Id's pelo primeiro nome e pelas datas de checkin e checkout")
    public Response bookingReturbIdPeloPrimeiroNome_Checkin_Checkout(String primeiroNome, String pnome, String chkin, String data1,
                                                        String chkout, String data2) {
        return given()
                .queryParams(primeiroNome, pnome, chkin, data1, chkout, data2).log().all()
                .header("Content-Type", "application/json")
                .when()
                .get("booking");
    }





}
