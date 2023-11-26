package supermarket.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import supermarket.app.converters.DipendenteDTOConverter;
import supermarket.app.dto.DipendenteDTORequest;
import supermarket.app.dto.DipendenteDTOResponse;
import supermarket.app.models.Dipendente;
import supermarket.app.models.Negozio;
import supermarket.app.repositories.DipendenteRepo;
import supermarket.app.repositories.NegozioRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class DipendenteService {
    @Autowired
    DipendenteDTOConverter dipendenteDTOConverter;
    private final DipendenteRepo dipendenteRepo;
    private final NegozioRepo negozioRepo;

    public DipendenteService(DipendenteRepo dipendenteRepo, NegozioRepo negozioRepo) {
        this.dipendenteRepo = dipendenteRepo;
        this.negozioRepo = negozioRepo;
    }

    //SELEZIONARE UN DIPENDENTE IN BASE ALL'ID
    public DipendenteDTORequest getByID (Long id){
        Dipendente dipendente = dipendenteRepo.findById(id)
                .orElseThrow(()-> new NullPointerException("ID fornito non valido"));
        DipendenteDTORequest dipendenteRequestDTO = dipendenteDTOConverter
                .convertDipendenteToDipendenteDTORequest(dipendente);
        return dipendenteRequestDTO;

    }


    //RESTITUIRE TUTTI I DIPENDENTI IN BASE AL NOME
    public List<DipendenteDTOResponse> getByName (String name){

            List<Dipendente> dipendentes = this.dipendenteRepo.findByNome(name);
            List<DipendenteDTOResponse> dipendenteResponseDTOS = new ArrayList<>();
            for (Dipendente dipendente : dipendentes) {
                DipendenteDTOResponse dipendenteResponseDTO = dipendenteDTOConverter
                        .convertDipendenteToDipendenteDTOResponse(dipendente);
                dipendenteResponseDTOS.add(dipendenteResponseDTO);
            }
            return dipendenteResponseDTOS;

    }

    //AGGIUNGERE UN DIPENDENTE NEL DATABASE
    public DipendenteDTOResponse add (DipendenteDTORequest dipendente__dto){

        Negozio negozio = negozioRepo.findById(dipendente__dto.getNegozio_id())
                .orElseThrow(()->new NullPointerException("Id negozio non trovato"));

        Dipendente dipendente = new Dipendente();
        dipendente.setNome(dipendente__dto.getNome());
        dipendente.setCognome(dipendente__dto.getCognome());
        dipendente.setMansione(dipendente__dto.getMansione());
        dipendente.setStipendio(dipendente__dto.getStipendio());
        dipendente.setNegozio(negozio);
        dipendenteRepo.save(dipendente);

       DipendenteDTOResponse dipendenteResponseDto = new DipendenteDTOResponse(dipendente.getId(),
                dipendente.getNome(),dipendente.getCognome(),dipendente.getMansione(),dipendente.getStipendio(),
                dipendente.getNegozio().getId());

       return dipendenteResponseDto;
    }

    //OTTENERE TUTTI I DIPENDENTI DI UN NEGOZIO
    public List<DipendenteDTOResponse> findAllByNegozioId (Long id) {
        List<Dipendente> dipendentes = dipendenteRepo.findAllByNegozioId(id);
        List<DipendenteDTOResponse> dipendenteResponseDtos = new ArrayList<>();

        for(Dipendente dipendente : dipendentes){
            DipendenteDTOResponse dipendenteResponseDto = new DipendenteDTOResponse(dipendente.getId(),dipendente.getNome(),dipendente.getCognome(),dipendente.getMansione(),dipendente.getStipendio(),dipendente.getNegozio().getId());
            dipendenteResponseDtos.add(dipendenteResponseDto);
        }
        return dipendenteResponseDtos;
    }


    //MODIFICARE I DATI DI UN DIPENDENTE
    public DipendenteDTOResponse update (DipendenteDTOResponse dipendente){
        Dipendente new_Dipendente = dipendenteRepo.findById(dipendente.getId())
                .orElseThrow(() -> new NullPointerException("Dipendente non trovato"));

        Negozio negozio = negozioRepo.findById(dipendente.getNegozio_id())
                .orElseThrow(()->new NullPointerException("Id negozio non trovato"));

        new_Dipendente.setNegozio(negozio);
        new_Dipendente.setNome(dipendente.getNome());
        new_Dipendente.setStipendio(dipendente.getStipendio());
        new_Dipendente.setCognome(dipendente.getCognome());
        new_Dipendente.setMansione(dipendente.getMansione());
        dipendenteRepo.save(new_Dipendente);

        return dipendente;
    }

    //CANCELLARE DIPENDENTE TRAMITE ID
    public void remove (Long id){
        Dipendente new_Dipendente = dipendenteRepo.findById(id)
                .orElseThrow(() -> new NullPointerException("Dipendente non trovato"));
        dipendenteRepo.deleteById(new_Dipendente.getId());
    }




}
