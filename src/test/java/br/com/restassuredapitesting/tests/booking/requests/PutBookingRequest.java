package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {

    PostAuthRequest postAuthRequest = new PostAuthRequest();


    @Step("Alterar uma reserva com token")
    public Response alterarUmaReservaComToken(int id, JSONObject payload){
        return given().header("Content-Type", "application/json")
                .header("Accept", "application/json" )
                .header("Cookie", postAuthRequest.getToken())
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }

    @Step("Alterar uma reserva com Basic Auth")
    public  Response alterarUmaReservaComBasicAuth(int id, JSONObject payload){
        return given().header("Content-Type", "application/json")
                .header("Accept", "application/json" )
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }

    @Step("Alterar uma reserva com token")
    public Response alterarUmaReservaSemToken(int id, JSONObject payload){
        return given().header("Content-Type", "application/json")
                .header("Accept", "application/json" )
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }

    @Step("Alterar uma reserva com token")
    public Response alterarUmaReservaComTokenInvalido(int id, JSONObject payload){
        return given().header("Content-Type", "application/json")
                .header("Accept", "application/json" )
                .header("Cookie", "token=abc123")
                .when()
                .body(payload.toString())
                .put("booking/" + id);
    }
}
