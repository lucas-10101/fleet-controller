package localhostdev.controledefrota.data.entities.identity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "realms")
public class Realm {

    @Id
    @Column(length = 12)
    private String tenant;

    @Column(length = 256, nullable = false, unique = true)
    private String name;
}
