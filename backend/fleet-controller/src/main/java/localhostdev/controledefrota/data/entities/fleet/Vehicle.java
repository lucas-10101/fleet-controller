package localhostdev.controledefrota.data.entities.fleet;

import org.hibernate.annotations.TenantId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import localhostdev.controledefrota.data.entities.identity.Realm;
import lombok.Data;

@Data
@Entity
@Table(name = "vehicles", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "plate", "realm_tenant" }, name = "un__vehicles__plate__realm_tenant"),
        @UniqueConstraint(columnNames = { "chassis", "realm_tenant" }, name = "un__vehicles__chassis__realm_tenant")
})
public class Vehicle {

    @Id
    private Integer id;

    @Column(length = 8, nullable = false)
    private String plate;

    @Column(length = 17, nullable = false)
    private String chassis;

    @Column(length = 32)
    private String color;

    @Column
    private Short numberOfAxles;

    @Column
    private Short manufactureYear;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "realm_tenant", nullable = false, updatable = false)
    private Realm realm;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private VehicleModel model;

    @OneToOne(optional = true)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fuel_id", nullable = false)
    private FuelType fuel;

    @TenantId
    public String getTenantIdentifier() {
        return this.realm.getTenant();
    }
}
