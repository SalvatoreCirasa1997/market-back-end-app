package supermarket.app.services;

import org.springframework.stereotype.Service;
import supermarket.app.models.Rifornitore;
import supermarket.app.repositories.RifornitoreRepo;

import java.util.List;

@Service
public class RifornitoreService {

    private final RifornitoreRepo rifornitoreRepo;

    public RifornitoreService(RifornitoreRepo rifornitoreRepo) {
        this.rifornitoreRepo = rifornitoreRepo;
    }

    public Rifornitore addRifornitore(Rifornitore rifornitore){
        return rifornitoreRepo.save(rifornitore);
    }

    public List<Rifornitore> getAllRifornitori(){
        return rifornitoreRepo.findAll();
    }

    public Rifornitore getRifornitoreById(Long id){
        return rifornitoreRepo.findById(id).orElseThrow(()->
                new IllegalArgumentException("Rifornitore non trovato"));
    }

    public String updateRifornitore(Rifornitore rifornitore,long id){
        if(rifornitoreRepo.existsById(id)){
            Rifornitore rifornitoreModificato = new Rifornitore();
            rifornitoreModificato.setId(id);
            rifornitoreModificato.setNome(rifornitore.getNome());
            rifornitoreModificato.setPartitaIva(rifornitore.getPartitaIva());
            rifornitoreModificato.setTipoFornitura(rifornitore.getTipoFornitura());
            rifornitoreRepo.save(rifornitoreModificato);
            return "rifornitore modificato";
        }
        return "non c'è un utente con quell'id";
    }

    public String deleteRifornitore(long id){
        if(rifornitoreRepo.existsById(id)){
            rifornitoreRepo.deleteById(id);
            return "ho eliminato il rifornitore "+rifornitoreRepo.getById(id).getNome();
        }
        else return "non posso eliminare nessun rifornitore perche non ne esiste uno con quell id";
    }
}
