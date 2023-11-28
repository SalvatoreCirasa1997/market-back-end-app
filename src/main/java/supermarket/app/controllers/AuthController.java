package supermarket.app.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import supermarket.app.dto.UtenteDTOLogin;
import supermarket.app.dto.UtenteDTOSignUp;
import supermarket.app.models.AuthenticationResponse;
import supermarket.app.services.AuthService;
import supermarket.app.services.JwtService;
import supermarket.app.services.UtenteService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;
    private final UtenteService utenteService;

    public AuthController(AuthService authService, JwtService jwtService, UtenteService utenteService) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.utenteService = utenteService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@Valid @RequestBody UtenteDTOLogin utenteDTOLogin){
        try {
            AuthenticationResponse authenticationResponse = this.authService.login(utenteDTOLogin);
            if(authenticationResponse != null && authenticationResponse.getToken() != null && jwtService.isTokenValid(authenticationResponse.getToken(),utenteService.loadUserByUsername(utenteDTOLogin.getUsername()))){
                return  new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }catch (BadCredentialsException e){
            return new ResponseEntity<>("Credenziali errate!",HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UtenteDTOSignUp utenteDTOSignUp){
        try {
            return new ResponseEntity<>(this.authService.signUp(utenteDTOSignUp),HttpStatus.CREATED);
            }catch (Exception e){
            return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
