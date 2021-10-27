package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {
    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Atualiza uma reserva especifica com o parametro token")
    public Response upDateBookingToken(int id, String token){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie",token)
                .when()
                .body(bookingPayloads.payloadValidBooking().toString())
                .put("booking/"+ id);

    }
    @Step("Atualiza uma reserva especifica com o parametro token")
    public Response upDateBookingBasicAuth(int id){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .log().all()
                .body(bookingPayloads.payloadInsertBooking().toString())
                .put("booking/"+ id);

    }
}
