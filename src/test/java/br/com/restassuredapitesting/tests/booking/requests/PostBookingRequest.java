package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PostBookingRequest {
    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Cria uma reserva com o parametro token")
    public Response insertBooking(){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(BookingPayloads.payloadInsertBooking().toString())
                .post("booking");
    }

    @Step("Cria uma reserva com mais itens no payload")
    public Response insertBookingPayloadMoreItens() {

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(BookingPayloads.payloadMoreItensBooking().toString())
                .post("booking");
    }

    @Step("Validar status code 500 por criacao de uma reserva com o payload invalido")
    public Response insertBookingPayloadInvalido() {

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(BookingPayloads.payloadInvalidBooking().toString())
                .post("booking");
    }

    @Step("Tentar criar uma reserva com o header accept invalido")
    public Response insertBookingHeaderAcceptInvalido(){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "ddd")
                .when()
                .body(BookingPayloads.payloadInsertBooking().toString())
                .post("booking");
    }

}
