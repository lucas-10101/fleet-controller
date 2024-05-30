package localhostdev.controledefrota.data.repository.identity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import localhostdev.controledefrota.data.entities.identity.Realm;

@Repository
public interface RealmRepository extends JpaRepository<Realm, String> {

}
