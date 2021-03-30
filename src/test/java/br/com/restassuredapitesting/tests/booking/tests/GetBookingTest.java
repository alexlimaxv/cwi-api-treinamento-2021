package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.Contract;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.*;

@Feature("Reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Listar IDs das Reservas.")
    public void validarIdsDasReservas() throws Exception{
        getBookingRequest.allBookings()
                .then().statusCode(200).time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Contract.class)
    @DisplayName("Garantir o contrato do retorno da lista de reservas ")
    public void garantirContratoListaReserva() throws Exception{
        getBookingRequest.allBookings().then()
                .statusCode(200)
                .assertThat()
                .body(
                        matchesJsonSchema(
                                new File(
                                        Utils.getContractsBasePath("booking", "bookings")
                                )
                        )
                );
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Contract.class)
    @DisplayName("Garantir contrato de uma reserva específica.")
    public void garantirContratoReservaEspecifica() throws Exception{
        int idDaReserva = getBookingRequest.allBookings().then().statusCode(200).extract().path("[2].bookingid");

        getBookingRequest.buscarReservaEspecifica(idDaReserva).then()
                .statusCode(200)
                .assertThat()
                .body(matchesJsonSchema(new File(Utils.getContractsBasePath("booking", "bookingsid"))));

    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category(Acceptance.class)
    @DisplayName("Validar uma reserva específica.")
    public void validarReservaEspecifica() throws Exception{

        int idDaReserva = getBookingRequest.allBookings().then().statusCode(200).extract().path("[10].bookingid");
        getBookingRequest.buscarReservaEspecifica(idDaReserva).then()
                .assertThat()
                .statusCode(200)
        ;

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Validar reservas com Firstname")
    public void validarReservaComFiltroFirstname()  throws Exception{
        int idDaReserva = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");
        String firstnameReserva = getBookingRequest.buscarReservaEspecifica(idDaReserva).then().extract().path("firstname");

        getBookingRequest.buscarReservaComFirstname(firstnameReserva)
                .then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));


    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Validar reservas com Lastname")
    public void validarReservaComFiltroLastname() throws Exception{
        int idDaReserva = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");
        String lastnameReserva = getBookingRequest.buscarReservaEspecifica(idDaReserva).then().extract().path("lastname");
        getBookingRequest.buscarReservaComLastName(lastnameReserva)
                .then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Validar reserva com filtro checkin")
    public void validarReservaComFiltroCheckin() throws Exception{
        int idDaReserva = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");
        String checkinDate = getBookingRequest.buscarReservaEspecifica(idDaReserva).then().extract().path("checkin");
        getBookingRequest.buscarReservaComCheckin(checkinDate).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Validar reserva com filtro checkout")
    public void validarReservaComFiltroCheckout() throws Exception{
        int idDaReserva = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");
        String checkoutDate = getBookingRequest.buscarReservaEspecifica(idDaReserva).then().extract().path("checkout");
        getBookingRequest.buscarReservaComCheckout(checkoutDate).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Validar reserva com filtro checkin e checkout")
    public void validarReservaComFiltroCheckinCheckout()throws Exception{
        int idDaReserva = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");
        String checkoutDate = getBookingRequest.buscarReservaEspecifica(idDaReserva).then().extract().path("checkout");
        String checkinDate = getBookingRequest.buscarReservaEspecifica(idDaReserva).then().extract().path("checkin");

        getBookingRequest.buscarReservaComCheckinECheckout(checkinDate,checkoutDate).then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Validar reserva com filtro nome, checkin e checkout")
    public void validarReservaComFiltroNomeCheckinCheckout() throws Exception{
        int idDaReserva = getBookingRequest.allBookings().then().statusCode(200).extract().path("[3].bookingid");
        String firstnameReserva = getBookingRequest.buscarReservaEspecifica(idDaReserva).then().extract().path("firstname");
        String lastnameReserva = getBookingRequest.buscarReservaEspecifica(idDaReserva).then().extract().path("lastname");
        String checkoutDate = getBookingRequest.buscarReservaEspecifica(idDaReserva).then().extract().path("checkout");
        String checkinDate = getBookingRequest.buscarReservaEspecifica(idDaReserva).then().extract().path("checkin");

        getBookingRequest.buscarReservaComFiltroNomeCheckinCheckout(firstnameReserva, lastnameReserva, checkinDate, checkoutDate)
                .then()
                .statusCode(200)
                .time(lessThan(2L), TimeUnit.SECONDS)
                .body("size()", greaterThan(0));

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Visualizar erro da reserva com filtro")
    public void visualizarErroDeServidor() throws Exception{
        int idDaReserva = getBookingRequest.allBookings().then().statusCode(200).extract().path("[0].bookingid");
        String checkinDate = getBookingRequest.buscarReservaEspecifica(idDaReserva).then().extract().path("checkin");

        getBookingRequest.buscarReservaComErroNoFiltro(checkinDate).then()
                .statusCode(500)
                .log()
                .all()
        ;

    }



}
