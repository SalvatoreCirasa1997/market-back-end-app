package supermarket.app.dto;

import supermarket.app.enums.Mansione;

public record Dipendente_Request_DTO(
    String nome,
    String cognome,
    Mansione mansione,
    double stipendio,
    Long negozio_id
){

}
