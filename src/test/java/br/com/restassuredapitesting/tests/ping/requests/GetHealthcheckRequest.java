package br.com.restassuredapitesting.tests.ping.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetHealthcheckRequest {

    @Step("Verificar se API está online")
    public Response verificarStatusApi(){
        return given()
                .when()
                .get("ping");
    }

}
