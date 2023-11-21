package supermarket.app.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import supermarket.app.models.Tessera;
import supermarket.app.services.TesseraService;

import java.util.List;

@Controller
@RequestMapping("/tessera")
public class TesseraController {
    private final TesseraService tesseraService;

    public TesseraController(TesseraService tesseraService) {
        this.tesseraService = tesseraService;
    }

    //AGGIUNGERE UNA TESSERA
    @PostMapping("/add")
    public ResponseEntity<?> add (@RequestBody Tessera tessera){
        try{
            Tessera new_tessera = this.tesseraService.add(tessera);
            return new ResponseEntity<>(new_tessera, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //OTTENERE I DATI DI UN NEGOZIO IN BASE ALL'ID
    @GetMapping("/getbyid")
    public ResponseEntity<Tessera> getById (@RequestParam Long id){
        Tessera new_tessera = this.tesseraService.get(id);
        return new ResponseEntity<>(new_tessera,HttpStatus.OK);
    }

    //OTTENERE TUTTE LE TESSERE NEL DATABASE
    @GetMapping("/getall")
    public ResponseEntity<?> getAll (){
        List<Tessera> tessere = tesseraService.getAll();
        return new ResponseEntity<>(tessere,HttpStatus.OK);
    }

    //MODIFICARE UNA TESSERA
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Tessera tessera){
        try {
            Tessera new_tessera = this.tesseraService.get(tessera.getId());
            new_tessera.setScadenza(tessera.getScadenza());
            return new ResponseEntity<>(new_tessera,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //CANCELLARE UNA TESSERA
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete (@RequestParam Long id){
        try {
            this.tesseraService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
