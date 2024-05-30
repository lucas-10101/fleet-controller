package localhostdev.controledefrota.data.repository.identity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import localhostdev.controledefrota.data.entities.identity.Realm;
import localhostdev.controledefrota.data.entities.identity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsernameAndTenant(String username, Realm tenant);
}
