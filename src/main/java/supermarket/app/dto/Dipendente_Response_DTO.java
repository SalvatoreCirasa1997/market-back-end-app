package supermarket.app.dto;

import supermarket.app.enums.Mansione;

public record Dipendente_Response_DTO (
        Long id,
        String nome,
        String cognome,
        Mansione mansione,
        double stipendio,
        Long negozio_id
){
}
