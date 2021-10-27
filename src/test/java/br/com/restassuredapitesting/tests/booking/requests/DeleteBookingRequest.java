package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Step("Excluir uma reserva")
    public Response excluirReserva(int idr, String token){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie",token)
                .when()
                .delete("booking/"+ idr);

    }
    @Step("Tentar excluir uma reserva nao existente")
    public Response tentarexcluirReservaQueNaoExiste(String token){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie",token)
                .when()
                .delete("booking/1000");


    }

    @Step("Tentar excluir uma reserva sem token")
    public Response tentarexcluirReservaSemToken(int idr ){

        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .delete("booking/"+ idr);
    }

}
