package supermarket.app.services;

import org.springframework.stereotype.Service;
import supermarket.app.models.Tessera;
import supermarket.app.repositories.TesseraRepo;

import java.util.List;
import java.util.Optional;

@Service
public class TesseraService {
    private final TesseraRepo tesseraRepo;

    public TesseraService(TesseraRepo tesseraRepo) {
        this.tesseraRepo = tesseraRepo;
    }

    //AGGIUNGERE UNA TESSERA
    public Tessera add (Tessera tessera){
        return tesseraRepo.save(tessera);
    }

    //OTTENERE I DATI DI UNA TESSERA IN BASE ALL'ID
    public Tessera get (Long id){
        return tesseraRepo.findById(id)
                .orElseThrow(()-> new NullPointerException("Tessera non trovata"));
    }

    //OTTENERE TUTTE LE TESSERE NEL DATABASE
    public List<Tessera> getAll (){
        return tesseraRepo.findAll();
    }


    //MODIFICARE I DATI DI UNA TESSERA
    public Tessera update (Tessera tessera){
        Tessera new_tessera = this.tesseraRepo.findById(tessera.getId())
                .orElseThrow(()-> new NullPointerException("Tessera non trovata"));
        new_tessera.setScadenza(tessera.getScadenza());
        return new_tessera;
    }

    //CANCELLARE UNA TESSERA FORNENDO L'ID
    public void remove (Long id){
        Tessera new_tessera = tesseraRepo.findById(id)
                .orElseThrow(() -> new NullPointerException("Tessera non trovata"));
        tesseraRepo.delete(new_tessera);
    }
}
