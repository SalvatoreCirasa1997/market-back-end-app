package supermarket.app.services;

import org.springframework.stereotype.Service;
import supermarket.app.dto.Dipendente_Request_DTO;
import supermarket.app.dto.Dipendente_Response_DTO;
import supermarket.app.models.Dipendente;
import supermarket.app.models.Negozio;
import supermarket.app.repositories.DipendenteRepo;
import supermarket.app.repositories.NegozioRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class DipendenteService {
    private final DipendenteRepo dipendenteRepo;
    private final NegozioRepo negozioRepo;

    public DipendenteService(DipendenteRepo dipendenteRepo, NegozioRepo negozioRepo) {
        this.dipendenteRepo = dipendenteRepo;
        this.negozioRepo = negozioRepo;
    }

    //SELEZIONARE UN DIPENDENTE IN BASE ALL'ID
    public Dipendente_Request_DTO getByID (Long id){
        Dipendente dipendente = dipendenteRepo.findById(id)
                .orElseThrow(()-> new NullPointerException("ID fornito non valido"));
        Dipendente_Request_DTO dipendenteRequestDTO = new Dipendente_Request_DTO(dipendente.getNome(),dipendente.getCognome(),dipendente.getMansione(),dipendente.getStipendio(),dipendente.getNegozio().getId());
        return dipendenteRequestDTO;

    }


    //RESTITUIRE TUTTI I DIPENDENTI IN BASE AL NOME
    public List<Dipendente_Response_DTO> getByName (String name){

            List<Dipendente> dipendentes = this.dipendenteRepo.findByNome(name);
            List<Dipendente_Response_DTO> dipendenteResponseDTOS = new ArrayList<>();
            for (Dipendente dipendente : dipendentes) {
                Dipendente_Response_DTO dipendenteResponseDTO = new Dipendente_Response_DTO(dipendente.getId(),dipendente.getNome(), dipendente.getCognome(), dipendente.getMansione(), dipendente.getStipendio(), dipendente.getNegozio().getId());
                dipendenteResponseDTOS.add(dipendenteResponseDTO);
            }
            return dipendenteResponseDTOS;

    }

    //AGGIUNGERE UN DIPENDENTE NEL DATABASE
    public Dipendente add (Dipendente_Request_DTO dipendente__dto){

        Negozio negozio = negozioRepo.findById(dipendente__dto.negozio_id())
                .orElseThrow(()->new NullPointerException("Id negozio non trovato"));

        Dipendente dipendente = new Dipendente();
        dipendente.setNome(dipendente__dto.nome());
        dipendente.setCognome(dipendente__dto.cognome());
        dipendente.setMansione(dipendente__dto.mansione());
        dipendente.setStipendio(dipendente__dto.stipendio());
        dipendente.setNegozio(negozio);

        return dipendenteRepo.save(dipendente);
    }

    //OTTENERE TUTTI I DIPENDENTI DI UN NEGOZIO
    public List<Dipendente_Response_DTO> findAllByNegozioId (Long id) {
        List<Dipendente> dipendentes = dipendenteRepo.findAllByNegozioId(id);
        List<Dipendente_Response_DTO> dipendenteResponseDtos = new ArrayList<>();

        for(Dipendente dipendente : dipendentes){
            Dipendente_Response_DTO dipendenteResponseDto = new Dipendente_Response_DTO(dipendente.getId(),dipendente.getNome(),dipendente.getCognome(),dipendente.getMansione(),dipendente.getStipendio(),dipendente.getNegozio().getId());
            dipendenteResponseDtos.add(dipendenteResponseDto);
        }
        return dipendenteResponseDtos;
    }

/*
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

 */


}
