package danielrichtersz.repositories;

import danielrichtersz.models.Charity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CharityRepository extends CrudRepository<Charity, Long> {

    Charity getByName(String name);

    boolean existsByName(String name);
}
