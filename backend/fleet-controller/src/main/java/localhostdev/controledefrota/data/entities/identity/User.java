package localhostdev.controledefrota.data.entities.identity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
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
        "username", "tenant"
    }, name = "un__users__username__tenant")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 256, nullable = false)
    private String username;

    @ManyToOne(optional = false, targetEntity = Realm.class)
    @JoinColumn(name = "tenant", nullable = false)
    private String tenant;

    @Column(length = 72, nullable = false, unique = true)
    private String password;

    @Column(nullable = false)
    private boolean active;

}
