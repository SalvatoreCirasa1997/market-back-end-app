package supermarket.app.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import supermarket.app.dto.DipendenteDTORequest;
import supermarket.app.dto.DipendenteDTOResponse;
import supermarket.app.models.Dipendente;
import supermarket.app.models.Negozio;
import supermarket.app.services.NegozioService;

@Component
public class DipendenteDTOConverter {
    @Autowired
    private ModelMapper modelMapper;
    private NegozioService negozioService;

    //CONVERTIRE DA DIPENDENTE A DIPENDENTE_DTO_REQUEST
    public DipendenteDTORequest convertDipendenteToDipendenteDTORequest (Dipendente dipendente){
        DipendenteDTORequest dipendenteDTORequest = modelMapper.map(dipendente, DipendenteDTORequest.class);
        dipendenteDTORequest.setNegozio_id(dipendente.getNegozio().getId());
        return dipendenteDTORequest;
    }

    //CONVERTIRE DA DIPENDENTE A DIPENDENTE_DTO_RESPONSE
    public DipendenteDTOResponse convertDipendenteToDipendenteDTOResponse (Dipendente dipendente){
        DipendenteDTOResponse dipendenteDTOResponse = modelMapper.map(dipendente, DipendenteDTOResponse.class);
        dipendenteDTOResponse.setNegozio_id(dipendente.getNegozio().getId());
        return dipendenteDTOResponse;
    }


    //CONVERTIRE DA DIPENDENTE_DTO_RESPONSE A DIPENDENTE
    public  Dipendente convertDipendenteDTOResponseToDipendente (DipendenteDTOResponse dipendenteDTOResponse){
        Dipendente dipendente = modelMapper.map(dipendenteDTOResponse,Dipendente.class);
        Negozio negozio = negozioService.getById(dipendenteDTOResponse.getNegozio_id());
        dipendente.setNegozio(negozio);
        return dipendente;
    }

}
