package supermarket.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supermarket.app.models.Utente;

import java.util.Optional;

@Repository
public interface UtenteRepo extends JpaRepository<Utente,Long> {

    public Optional<Utente> findByUsername(String username);
}
