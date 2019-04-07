package danielrichtersz.repositories;

import danielrichtersz.models.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserAccount, Long> {
    UserAccount findByEmail(String email);
    UserAccount findByUsername(String username);

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
