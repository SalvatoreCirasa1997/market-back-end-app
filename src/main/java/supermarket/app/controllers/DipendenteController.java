package supermarket.app.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import supermarket.app.dto.DipendenteDTORequest;
import supermarket.app.dto.DipendenteDTOResponse;
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
    public ResponseEntity<DipendenteDTORequest> getById (@RequestParam Long id){
       DipendenteDTORequest dipendenteRequestDTO = this.dipendenteService.getByID(id);
       return new ResponseEntity<>(dipendenteRequestDTO,HttpStatus.OK);
    }

    @GetMapping("/getallbyname")
    public ResponseEntity<List<DipendenteDTOResponse>> getAllByName (@RequestParam String name){
        List<DipendenteDTOResponse> dipendentes = this.dipendenteService.getByName(name);
        return new ResponseEntity<>(dipendentes,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add (@Valid @RequestBody DipendenteDTORequest dipendente__dto){
        try{
            DipendenteDTOResponse dipendente = this.dipendenteService.add(dipendente__dto);
            return new ResponseEntity<>(dipendente,HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getallbynegozioid")
    public ResponseEntity<List<DipendenteDTOResponse>> getAllByNegozioId (@RequestParam Long id) {
        List<DipendenteDTOResponse> dipendentes = this.dipendenteService.findAllByNegozioId(id);
        return new ResponseEntity<>(dipendentes, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update (@Valid @RequestBody DipendenteDTOResponse dipendente){
        try {
            DipendenteDTOResponse updated_dipendente = this.dipendenteService.update(dipendente);
            return new ResponseEntity<>(updated_dipendente, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete (@RequestParam Long id){
        try {
            this.dipendenteService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
