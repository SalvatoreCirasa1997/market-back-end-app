package supermarket.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import supermarket.app.dto.Dipendente_Request_DTO;
import supermarket.app.dto.Dipendente_Response_DTO;
import supermarket.app.models.Dipendente;
import supermarket.app.services.DipendenteService;

import java.util.List;

@Controller
@RequestMapping("/dipendente")
public class DipendenteController {
    private final DipendenteService dipendenteService;

    public DipendenteController(DipendenteService dipendenteService) {
        this.dipendenteService = dipendenteService;
    }

    @GetMapping("/getbyid")
    public ResponseEntity<Dipendente_Request_DTO> getById (@RequestParam Long id){
       Dipendente_Request_DTO dipendenteRequestDTO = this.dipendenteService.getByID(id);
       return new ResponseEntity<>(dipendenteRequestDTO,HttpStatus.OK);
    }

    @GetMapping("/getallbyname")
    public ResponseEntity<List<Dipendente_Response_DTO>> getAllByName (@RequestParam String name){
        List<Dipendente_Response_DTO> dipendentes = this.dipendenteService.getByName(name);
        return new ResponseEntity<>(dipendentes,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add (@RequestBody Dipendente_Request_DTO dipendente__dto){
        try{
            Dipendente dipendente = this.dipendenteService.add(dipendente__dto);
            return new ResponseEntity<>(dipendente,HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getallbynegozioid")
    public ResponseEntity<List<Dipendente_Response_DTO>> getAllByNegozioId (@RequestParam Long id) {
        List<Dipendente_Response_DTO> dipendentes = this.dipendenteService.findAllByNegozioId(id);
        return new ResponseEntity<>(dipendentes, HttpStatus.OK);
    }
/*
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
*/
}
