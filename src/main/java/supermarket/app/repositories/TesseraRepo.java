package supermarket.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supermarket.app.models.Tessera;
@Repository
public interface TesseraRepo extends JpaRepository<Tessera,Long> {

}
