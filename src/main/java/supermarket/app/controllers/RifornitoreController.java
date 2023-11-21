package supermarket.app.controllers;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import supermarket.app.models.Rifornitore;
import supermarket.app.services.RifornitoreService;

import java.util.List;

@RestController
@RequestMapping("/rifornitore")
public class RifornitoreController {
    private final RifornitoreService rifornitoreService;

    public RifornitoreController(RifornitoreService rifornitoreService) {
        this.rifornitoreService = rifornitoreService;
    }

    @PostMapping("/addrifornitore")
    public ResponseEntity<Rifornitore> addRifornitore(@Valid @RequestBody Rifornitore rifornitore){
        try{
            return new ResponseEntity<>(rifornitoreService.addRifornitore(rifornitore),HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getallrifornitore")
    public ResponseEntity<List<Rifornitore>> getAllRifornitore(){
        return new ResponseEntity<>(rifornitoreService.getAllRifornitori(),HttpStatus.OK);
    }

    @GetMapping("/getrifornitorebyid/{id}")
    public ResponseEntity<?> getRifornitoreById(@PathVariable long id){
        try {
            return new ResponseEntity<>(rifornitoreService.getRifornitoreById(id),HttpStatus.OK);
        }catch (NullPointerException n){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updaterifornitorebyid/{id}")
    public ResponseEntity<?> updateRifornitoreById(@PathVariable long id,@Valid @RequestBody Rifornitore rifornitore) {
        try{
            return new ResponseEntity<>(rifornitoreService.updateRifornitore(rifornitore,id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleterifornitorebyid/{id}")
    public ResponseEntity<String> deleteRifornitoreById(@PathVariable long id){
        try{
            return new ResponseEntity<>(rifornitoreService.deleteRifornitore(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
