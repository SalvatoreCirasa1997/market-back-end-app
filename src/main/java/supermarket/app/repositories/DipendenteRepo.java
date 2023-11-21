package supermarket.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import supermarket.app.models.Dipendente;
import supermarket.app.models.Negozio;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Repository
public interface DipendenteRepo extends JpaRepository <Dipendente, Long> {


    @Query("SELECT d FROM Dipendente d WHERE d.nome = :nome")
    List<Dipendente> findByNome(String nome);

    public Optional<Dipendente> findByIdAndNegozio (Long id, Negozio negozio);

    public List<Dipendente> findByNomeAndNegozio ( String nome,Negozio negozio);

    public  List<Dipendente> findAllByNegozio (Negozio negozio);
}
