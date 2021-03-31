package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@Feature("Reservas")
public class PutBookingTest extends BaseTest {

    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva utilizando token.")
    public void validarAlterarUmaReservaUtilizandoToken() throws Exception{
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");


        putBookingRequest.alterarUmaReservaComToken(primeiroId, Utils.validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Alterar uma reserva utilizando basic auth.")
    public void validarAlterarUmaReservaUtilizandoBasicAuth() throws Exception{
        int segundoId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[1].bookingid");

        putBookingRequest.alterarUmaReservaComBasicAuth(segundoId, Utils.validPayloadBooking()).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Alterar Uma Reserva Sem Token")
    public void validarAlterarReservaSemToken() throws Exception{

        int segundoId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[1].bookingid");

        putBookingRequest.alterarUmaReservaSemToken(segundoId,Utils.validPayloadBooking()).then()
                .assertThat()
                .statusCode(403);

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Alterar Uma Reserva Com Token Invalido")
    public void validarAlterarReservaComTokenIvalido() throws Exception{
        int primeiroId = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");

        putBookingRequest.alterarUmaReservaComTokenInvalido(primeiroId, Utils.validPayloadBooking()).then()
                .assertThat()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Alterar Uma Reserva inexistente")
    public void validarAlterarReservaInexistente() throws Exception{

        int idInexistent = 999;

        putBookingRequest.alterarUmaReservaComToken(idInexistent, Utils.validPayloadBooking()).then()
                .assertThat()
                .statusCode(405);

                //Aqui o retorno de erro esperado seria o 404 (Not Found), pois a reserva não existe.
                //Porém está retornando 405 indicando que o método foi desativado e não pode ser usado.
    }

}
