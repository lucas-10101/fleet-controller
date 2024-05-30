package localhostdev.controledefrota.data.repository.identity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import localhostdev.controledefrota.data.entities.identity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Short> {

}
