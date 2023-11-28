package supermarket.app.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import supermarket.app.dto.UtenteDTOResponse;
import supermarket.app.dto.UtenteDTOSignUp;
import supermarket.app.enums.RoleEnum;
import supermarket.app.models.Negozio;
import supermarket.app.models.Utente;
import supermarket.app.services.NegozioService;

@Component
public class UtenteDTOConverter {
    @Autowired
    ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    private NegozioService negozioService;

    //CONVERTIRE DA UTENTE A UTENTE_DTO_RESPONSE
    public UtenteDTOResponse convertUtenteToUtenteDTOResponse (Utente utente){
        UtenteDTOResponse utenteDTOResponse = modelMapper.map(utente, UtenteDTOResponse.class);
        return utenteDTOResponse;
    }

    public Utente convertUtenteDTOSignUpToUtente(UtenteDTOSignUp utenteDTOSignUp){
        Utente utente = modelMapper.map(utenteDTOSignUp,Utente.class);
        utente.setPassword(passwordEncoder.encode(utenteDTOSignUp.getPassword()));
        Negozio negozio = negozioService.getById(utenteDTOSignUp.getNegozio_id());
        utente.setNegozio(negozio);
        utente.setRole(RoleEnum.ADMIN);
        return utente;
    }
}
