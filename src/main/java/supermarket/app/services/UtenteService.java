package supermarket.app.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import supermarket.app.converters.UtenteDTOConverter;
import supermarket.app.dto.UtenteDTOResponse;
import supermarket.app.exceptions.ApiRequestException;
import supermarket.app.models.Utente;
import supermarket.app.repositories.UtenteRepo;

@Service
public class UtenteService implements UserDetailsService {
    private final UtenteRepo utenteRepo;
    private final UtenteDTOConverter utenteDTOConverter;

    public UtenteService(UtenteRepo utenteRepo, UtenteDTOConverter utenteDTOConverter) {
        this.utenteRepo = utenteRepo;
        this.utenteDTOConverter = utenteDTOConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.utenteRepo.findByUsername(username)
                .orElseThrow(()-> new ApiRequestException("Username inesistente"));
    }

    public UtenteDTOResponse save(Utente utente){
        utenteRepo.save(utente);
        UtenteDTOResponse utenteDTOResponse = utenteDTOConverter.convertUtenteToUtenteDTOResponse(utente);
        return utenteDTOResponse;
    }
}
