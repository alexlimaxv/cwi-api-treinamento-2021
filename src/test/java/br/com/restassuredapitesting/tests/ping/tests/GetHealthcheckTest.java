package br.com.restassuredapitesting.tests.ping.tests;

import br.com.restassuredapitesting.suites.Healthcheck;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.ping.requests.GetHealthcheckRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.matcher.ResponseAwareMatcher;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

@Feature("Healthcheck")
public class GetHealthcheckTest extends BaseTest {

    GetHealthcheckRequest health = new GetHealthcheckRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Healthcheck.class)
    @DisplayName("Verificar se api está online.")
    public void validarVerificarStatusApiOnline() throws Exception{

        health.verificarStatusApi().then()
                .statusCode(201)
                // Penso que o StatusCode 200 deveria ser o esperado aqui porém ele retorna o status 201.
                // A operação de ping funciona como teste de conectividade.
                // Assim sendo se está conectado(online) deveria retornar OK statusCode 200
                .assertThat()
                .statusLine(containsString("Created"))
                .time(lessThan(2L), TimeUnit.SECONDS);
    }
}
