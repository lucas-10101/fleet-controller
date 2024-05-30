package localhostdev.controledefrota.data.repository.identity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import localhostdev.controledefrota.data.entities.identity.UserPermission;
import localhostdev.controledefrota.data.entities.identity.UserPermission.UserPermissionPk;

@Repository
public interface UserPermissionRepository extends JpaRepository< UserPermission, UserPermissionPk> {

}
