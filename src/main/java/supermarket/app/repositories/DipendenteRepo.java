package supermarket.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import supermarket.app.models.Dipendente;

import java.util.List;

@Repository
public interface DipendenteRepo extends JpaRepository <Dipendente, Long> {


    @Query("SELECT d FROM Dipendente d WHERE d.nome = :nome")
    List<Dipendente> findByNome(String nome);

    List<Dipendente> findAllByNegozioId(Long id);
}
