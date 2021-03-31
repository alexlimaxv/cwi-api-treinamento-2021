package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRrequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DeleteBookingTest extends BaseTest {

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    PostBookingRrequest postBookingRrequest = new PostBookingRrequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Excluir reserva com sucesso.")
    public  void excluiReserva(){

        int idReservaCriada = postBookingRrequest.criarReserva(Utils.validPayloadBooking()).then().statusCode(200).extract().path("bookingid");

        deleteBookingRequest.excluirReserva(idReservaCriada)
                .then()
                .assertThat()
                .statusCode(201); //O status code aqui também deveria ser o 200, pois não estamos criando e sim deletando
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Excluir reserva inexistente.")
    public void excluirReservaInexistente() throws Exception{
        int idInexistente = 999;
        deleteBookingRequest.excluirReserva(idInexistente).then()
                .statusCode(405);
        //Aqui o retorno esperado seria 404 pois o id 999 não existe sendo assim o retorno seria not found,
        // Porém está retornando 405 indicando que o metodo de busca foi desativado e não pode ser usado.

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Excluir reserva sem autorizaçao.")
    public void excluirReservaSemAutorizacao() throws Exception{

        int idReservaCriada = postBookingRrequest.criarReserva(Utils.validPayloadBooking())
                .then().statusCode(200).extract().path("bookingid");

        deleteBookingRequest.excluirReservaSemAuth(idReservaCriada).then()
                .assertThat()
                .statusCode(403);

    }

}
