package localhostdev.controledefrota.data.entities.identity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username", "realm_tenant"
        }, name = "un__users__username__realm_tenant")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 256, nullable = false)
    private String username;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "realm_tenant", nullable = false)
    private Realm realm;

    @Column(length = 72, nullable = false, unique = true)
    private String password;

    @Column(nullable = false)
    private boolean active;

}
