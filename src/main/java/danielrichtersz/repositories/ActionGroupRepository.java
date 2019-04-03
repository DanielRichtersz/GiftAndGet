package danielrichtersz.repositories;

import danielrichtersz.models.ActionGroup;
import org.springframework.data.repository.CrudRepository;

public interface ActionGroupRepository extends CrudRepository<ActionGroup, Long> {
    boolean existsByCharity_NameAndIsClosedFalse(String name);
}
