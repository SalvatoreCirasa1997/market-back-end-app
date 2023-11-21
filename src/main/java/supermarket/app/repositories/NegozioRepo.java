package supermarket.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supermarket.app.models.Negozio;

import java.util.List;
@Repository
public interface NegozioRepo extends JpaRepository<Negozio,Long> {

    public List<Negozio> findByCitta(String citta);
}
