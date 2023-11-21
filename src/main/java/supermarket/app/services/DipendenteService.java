package supermarket.app.services;

import org.springframework.stereotype.Service;
import supermarket.app.models.Dipendente;
import supermarket.app.models.Negozio;
import supermarket.app.repositories.DipendenteRepo;

import java.util.List;
import java.util.Optional;

@Service
public class DipendenteService {
    private final DipendenteRepo dipendenteRepo;

    public DipendenteService(DipendenteRepo dipendenteRepo) {
        this.dipendenteRepo = dipendenteRepo;
    }

    //SELEZIONARE UN DIPENDENTE IN BASE ALL'ID
    public Optional<Dipendente> getByID (Long id, Negozio negozio){
        return dipendenteRepo.findByIdAndNegozio(id,negozio);
    }

    //RESTITUIRE TUTTI I DIPENDENTI IN BASE AL NOME
    public List<Dipendente> getByName (String name, Negozio negozio){
        return dipendenteRepo.findByNomeAndNegozio(name,negozio);
    }

    //AGGIUNGERE UN DIPENDENTE NEL DATABASE
    public Dipendente add (Dipendente dipendente){
        return dipendenteRepo.save(dipendente);
    }

    //OTTENERE TUTTI I DIPENDENTI DI UN NEGOZIO
    public List<Dipendente> getAll (Negozio negozio) {
        return dipendenteRepo.findAllByNegozio(negozio);
    }

    //MODIFICARE I DATI DI UN DIPENDENTE
    public Dipendente update (Dipendente dipendente, Negozio negozio){
        Dipendente new_Dipendente = dipendenteRepo.findByIdAndNegozio(dipendente.getId(), negozio)
                .orElseThrow(() -> new NullPointerException("Dipendente non trovato"));

        new_Dipendente.setCognome(dipendente.getCognome());
        new_Dipendente.setMansione(dipendente.getMansione());
        new_Dipendente.setNome(dipendente.getNome());
        new_Dipendente.setStipendio(dipendente.getStipendio());
        new_Dipendente.setNegozio(negozio);

        return new_Dipendente;
    }

    //CANCELLARE DIPENDENTE TRAMITE ID
    public void remove (Long id, Negozio negozio){
        Dipendente new_Dipendente = dipendenteRepo.findByIdAndNegozio(id, negozio)
                .orElseThrow(() -> new NullPointerException("Dipendente non trovato"));
        dipendenteRepo.deleteById(new_Dipendente.getId());
    }


}
