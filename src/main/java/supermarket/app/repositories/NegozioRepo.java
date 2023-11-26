package supermarket.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supermarket.app.models.Negozio;

import java.util.List;
import java.util.Optional;

@Repository
public interface NegozioRepo extends JpaRepository<Negozio,Long> {

    public List<Negozio> findByCitta(String citta);

}
