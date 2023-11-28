package supermarket.app.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import supermarket.app.exceptions.ApiRequestException;
import supermarket.app.models.Utente;
import supermarket.app.repositories.UtenteRepo;

@Service
public class UtenteService implements UserDetailsService {
    private final UtenteRepo utenteRepo;

    public UtenteService(UtenteRepo utenteRepo) {
        this.utenteRepo = utenteRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.utenteRepo.findByUsername(username)
                .orElseThrow(()-> new ApiRequestException("Username inesistente"));
    }

    public Utente save(Utente utente){
        return utenteRepo.save(utente);
    }
}
