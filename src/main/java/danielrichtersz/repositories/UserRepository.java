package danielrichtersz.repositories;

import danielrichtersz.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}