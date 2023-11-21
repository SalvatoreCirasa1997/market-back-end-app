package supermarket.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import supermarket.app.models.Rifornitore;

@Repository
public interface RifornitoreRepo extends JpaRepository<Rifornitore,Long> {

}
