package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Buscar todas as reservas.")
    public Response allBookings(){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking");
    }

    @Step("Buscar uma reserva específica.")
    public Response buscarReservaEspecifica(int id){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking/" +id);
    }

    @Step("Listar IDs de reservas utilizando o filtro Firstname")
    public Response buscarReservaComFirstname(String firstname){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?firstname=" + firstname);
    }

    @Step("Listar IDs de reservas utilizando o filtro Lastname")
    public Response buscarReservaComLastName(String lastname){

        return given()
                .header("Content-Type", "applicatiion/json")
                .when()
                .get("booking?lastname=" + lastname);
    }

    @Step("Listar IDs de reservas utilizando o filtro checkin")
    public Response buscarReservaComCheckin(String checkinDate){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?bookingdates.checkin=" + checkinDate);
    }

    @Step("Listar IDs de reservas utilizando o filtro checkin")
    public Response buscarReservaComCheckout(String checkoutDate){
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?bookingdates.checkout=" + checkoutDate);
    }

    @Step("Listar IDs de reservas utilizando o filtro checkin e checkout")
    public Response buscarReservaComCheckinECheckout(String checkinDate, String checkoutDate){

        return given()
                .header("Content-Type","application/json")
                .when()
                .get("booking?bookingdates.checkin="
                        + checkinDate
                        + "&bookingdates.checkout="
                        + checkoutDate
                );
    }
    @Step("Listar IDs de reservas utilizando o filtro name, checkin e checkout")
    public Response buscarReservaComFiltroNomeCheckinCheckout(String firstname, String lastname, String checkinDate, String checkoutDate){

        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?firstname="
                        + firstname
                        + "&lastname="
                        + lastname
                        +"&bookingdates.checkin="
                        + checkinDate
                        +"&bookingdates.checkout="
                        + checkoutDate
                );
    }

    @Step("Listar IDs de reservas utilizando o filtro mal formatado")
    public Response buscarReservaComErroNoFiltro(String checkinDate){
        return  given()
                .header("Content-Type", "application/json")
                .when()
                .get("booking?checkin=" + checkinDate);

    }

}
