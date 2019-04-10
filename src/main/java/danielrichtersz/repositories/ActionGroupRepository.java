package danielrichtersz.repositories;

import danielrichtersz.models.ActionGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActionGroupRepository extends CrudRepository<ActionGroup, Long> {
    boolean existsByCharity_NameAndIsClosedFalse(String name);

    List<ActionGroup> findAllByIsClosedFalse();
}
