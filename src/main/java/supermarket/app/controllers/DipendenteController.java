package supermarket.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import supermarket.app.models.Dipendente;
import supermarket.app.models.Negozio;
import supermarket.app.services.DipendenteService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dipendente")
public class DipendenteController {
    private final DipendenteService dipendenteService;

    public DipendenteController(DipendenteService dipendenteService) {
        this.dipendenteService = dipendenteService;
    }

    @GetMapping("/getbyid")
    public ResponseEntity<Dipendente> getById (@RequestParam Long id, @RequestBody Negozio negozio){
       Optional<Dipendente> dipendente = this.dipendenteService.getByID(id,negozio);
        // O un'altra risposta HTTP appropriata
        return dipendente
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getallbyname")
    public ResponseEntity<List<Dipendente>> getAllByName (@RequestParam String name, @RequestBody Negozio negozio){
        List<Dipendente> dipendentes = this.dipendenteService.getByName(name,negozio);
        return new ResponseEntity<>(dipendentes,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add (@RequestBody Dipendente dipendente){
        try{
            Dipendente new_dipendente = this.dipendenteService.add(dipendente);
            return new ResponseEntity<>(new_dipendente,HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getallbynegozio")
    public ResponseEntity<List<Dipendente>> getAllByNegozio (@RequestBody Negozio negozio) {
        List<Dipendente> dipendentes = this.dipendenteService.getAll(negozio);
        return new ResponseEntity<>(dipendentes, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update (@RequestBody Dipendente dipendente, @RequestBody Negozio negozio){
        try {
            Dipendente updated_dipendente = this.dipendenteService.update(dipendente, negozio);
            return new ResponseEntity<>(updated_dipendente, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete (@RequestParam Long id, @RequestBody Negozio negozio){
        try {
            this.dipendenteService.remove(id, negozio);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
