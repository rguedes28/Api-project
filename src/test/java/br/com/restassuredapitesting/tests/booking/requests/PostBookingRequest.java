package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {
    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Cria uma reserva especifica com o parametro token")
    public Response insertBooking(){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .log().all()
                .body(bookingPayloads.payloadInsertBooking().toString())
                .post("booking");

    }

}
