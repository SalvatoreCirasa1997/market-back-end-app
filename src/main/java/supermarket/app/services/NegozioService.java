package supermarket.app.services;

import org.springframework.stereotype.Service;
import supermarket.app.models.Negozio;
import supermarket.app.repositories.NegozioRepo;

import java.util.List;
import java.util.Optional;
@Service
public class NegozioService {
    private final NegozioRepo negozioRepo;

    public NegozioService(NegozioRepo negozioRepo) {
        this.negozioRepo = negozioRepo;
    }

    //AGGIUNGERE UN NEGOZIO NEL DATABASE
    public Negozio add (Negozio negozio){
        return negozioRepo.save(negozio);
    }

    //OTTENERE I DATI DI UN NEGOZIO IN BASE ALL'ID
    public Negozio getById (Long id){
        return negozioRepo.findById(id)
                .orElseThrow(()-> new NullPointerException(("Nessun negozio trovato")));
    }

    //OTTENERE TUTTI I NEGOZI DI UNA DETERMINATA CITTA
    public List<Negozio> getByCity (String citta){
        return negozioRepo.findByCitta(citta);
    }

    //CANCELLARE UNA NEGOZIO FORNENDO L'ID
    public void remove (Long id){
        Negozio new_Negozio = negozioRepo.findById(id)
                .orElseThrow(()-> new NullPointerException("Nessun negozio trovato"));
        negozioRepo.delete(new_Negozio);
    }

    //MODIFICARE UN NEGOZIO
    public Negozio update (Negozio negozio){
        Negozio new_negozio = negozioRepo.findById(negozio.getId())
                .orElseThrow(()->new NullPointerException("Nessun negozio trovato"));
        new_negozio.setCitta(negozio.getCitta());
        new_negozio.setIndirizzo(negozio.getIndirizzo());
        new_negozio.setpIva(negozio.getpIva());
        return new_negozio;
    }
}
