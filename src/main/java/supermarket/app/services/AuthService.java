package supermarket.app.services;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import supermarket.app.converters.UtenteDTOConverter;
import supermarket.app.dto.UtenteDTOLogin;
import supermarket.app.dto.UtenteDTOResponse;
import supermarket.app.dto.UtenteDTOSignUp;
import supermarket.app.exceptions.ApiRequestException;
import supermarket.app.models.AuthenticationResponse;
import supermarket.app.models.Utente;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UtenteService utenteService;
    private final UtenteDTOConverter utenteDTOConverter;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, PasswordEncoder passwordEncoder, UtenteService utenteService, UtenteDTOConverter utenteDTOConverter) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.utenteService = utenteService;
        this.utenteDTOConverter = utenteDTOConverter;
    }

    public AuthenticationResponse login(UtenteDTOLogin utenteDTOLogin){
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        utenteDTOLogin.getUsername(),
                        utenteDTOLogin.getPassword()
                )
        );
        UserDetails utente = this.utenteService.loadUserByUsername(utenteDTOLogin.getUsername());
        String jwtToken = this.jwtService.generateToken(utente);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse signUp(UtenteDTOSignUp utenteDTOSignUp){
        Utente utente = utenteDTOConverter.convertUtenteDTOSignUpToUtente(utenteDTOSignUp);
        UtenteDTOResponse utente_new;
        try{
            utente_new = this.utenteService.save(utente);
        }catch (DataIntegrityViolationException ex){
            throw new ApiRequestException("Dati già presenti nel database");
        }
        if(utente_new == null){
            throw new ApiRequestException("Dati utente mancanti!");
        }
        String jwtToken = this.jwtService.generateToken((UserDetails) utente_new);
        return new AuthenticationResponse(jwtToken);

    }
}
