package supermarket.app.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import supermarket.app.models.Negozio;
import supermarket.app.services.NegozioService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/negozio")
public class NegozioController {
    private final NegozioService negozioService;

    public NegozioController(NegozioService negozioService) {
        this.negozioService = negozioService;
    }

    //AGGIUNGERE UN NEGOZIO
    @PostMapping("/add")
    public ResponseEntity<?> add (@Valid @RequestBody Negozio negozio){
        try {
            Negozio new_negozio = this.negozioService.add(negozio);
            return new ResponseEntity<>(negozio, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //OTTENERE I DATI DI UN NEGOZIO IN BASE ALL'ID
    @GetMapping("/getbyid")
    public ResponseEntity<Negozio> getById (@RequestParam Long id){
        Negozio new_negozio = this.negozioService.getById(id);
        return new ResponseEntity<>(new_negozio,HttpStatus.OK);
    }

    //OTTENERE I NEGOZI DI UNA DETERMINATA CITTA
    @GetMapping("/getbycity")
    public ResponseEntity<?> getByCity (@RequestParam String citta) {
        try {
            List<Negozio> negozi = this.negozioService.getByCity(citta);
            return new ResponseEntity<>(negozi,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //CANCELLARE UN NEGOZIO TRAMITE ID
    @PutMapping("/delete")
    public ResponseEntity<?> delete (@RequestParam Long id){
        try {
            this.negozioService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //MODIFICARE UN NEGOZIO
    @PutMapping("/update")
    public ResponseEntity<?> update (@Valid @RequestBody Negozio negozio){
        try {
            Negozio new_negozio = this.negozioService.getById(negozio.getId());
            new_negozio.setpIva(negozio.getpIva());
            new_negozio.setCitta(negozio.getCitta());
            new_negozio.setIndirizzo(negozio.getIndirizzo());
            new_negozio.setId(negozio.getId());
            this.negozioService.update(new_negozio);
            return new ResponseEntity<>(new_negozio, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
