package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class PostBookingRrequest {

    @Step("Criar uma reserva.")
    public Response criarReserva(JSONObject payload){

        return given()
                .header("Content-Type", "application/json")
                .header("Accepted", "application/json")
                .body(payload)
                .when()
                .post("booking");

    }

    @Step("Criar uma reserva com dois payloads sequenciais.")
    public Response criarReservaSequencial(JSONObject payload, JSONObject payload2, JSONObject payload3 ){

        return given()
                .header("Content-Type", "application/json")
                .header("Accepted", "application/json")
                .body(payload)
                .body(payload2)
                .body(payload3)
                .when()
                .post("booking");

    }

    @Step("Criar uma nova reserva com retorno 500 com payload invalido")
    public Response novaReservaComHeaderInvalido(JSONObject payload){

        return  given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/php")
                .body(payload)
                .when()
                .post("booking");
    }
}
