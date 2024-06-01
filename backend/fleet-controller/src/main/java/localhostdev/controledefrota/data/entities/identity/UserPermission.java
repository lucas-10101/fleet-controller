package localhostdev.controledefrota.data.entities.identity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user_permissions")
public class UserPermission {

    @EmbeddedId
    private UserPermissionPk id;

    @Embeddable
    public static class UserPermissionPk {

        @ManyToOne(targetEntity = User.class, optional = false)
        @JoinColumn(name = "user_id", nullable = false)
        private Integer userId;

        @ManyToOne(targetEntity = Permission.class)
        @JoinColumn(name = "permission_id", nullable = false)
        private Permission permissionId;
    }

}
