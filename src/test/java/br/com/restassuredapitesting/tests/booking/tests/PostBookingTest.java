package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.suites.Acceptance;
import br.com.restassuredapitesting.suites.E2e;
import br.com.restassuredapitesting.tests.base.tests.BaseTest;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRrequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.json.simple.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.core.IsEqual.equalTo;

public class PostBookingTest extends BaseTest {

    PostBookingRrequest postBookingRrequest = new PostBookingRrequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(Acceptance.class)
    @DisplayName("Criar uma nova reserva")
    public void criaReserva(){

        postBookingRrequest.criarReserva(Utils.validPayloadBooking())
                .then()
                .assertThat().statusCode(200)
                .body("booking.firstname", equalTo("Ronaldo"));
                //Aqui deveria retornar 201 que indica que algo foi criado,
                // mas o retorno padrão é 200
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 500 quando payload da reserva estiver inválido")
    public void validarReservaCOmPayloadInvalido() throws Exception {

        postBookingRrequest.criarReserva(Utils.invalidPayloadBooking())
                .then()
                .assertThat()
                .statusCode(500);
                //Penso que o retorno deveria ser o 400, pois há um erro de sintaxe
                //No envio do payload
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar a criação de reservas de mais de um livro em sequência")
    public void validarCriarResevasSequenciais(){
        postBookingRrequest.criarReservaSequencial(Utils.validPayloadBooking(), Utils.validPayloadBooking(), Utils.validPayloadBooking())
                .then()
                .assertThat()
                .statusCode(200)
                //Aqui deveria retornar 201 que indica que algo foi criado,
                // mas o retorno padrão é 200
        ;

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Criar uma reserva enviando mais parâmetros no payload da reserva")
    public void criarreservaComPayloadMaior() throws Exception{

        postBookingRrequest.criarReserva(Utils.payloadBookingAlterado())
                .then()
                .assertThat()
                .statusCode(500)
        ;
        //Penso que o retorno deveria ser o 400, pois há um erro de sintaxe
        //No envio do payload

    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category(E2e.class)
    @DisplayName("Validar retorno 418 quando o header Accept for inválido")
    public void validarRetornoComHeaderInvalido() throws Exception{

        postBookingRrequest.novaReservaComHeaderInvalido(Utils.validPayloadBooking()).then()
                .assertThat()
                .statusCode(418);
                //Penso que o retorno deveria ser o 400, pois há um erro de sintaxe
                //No envio do payload, porém aceitei a piada em referência ao 1º de abril
    }

}
