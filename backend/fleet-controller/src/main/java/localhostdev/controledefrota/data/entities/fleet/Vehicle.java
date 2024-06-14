package localhostdev.controledefrota.data.entities.fleet;

import org.hibernate.annotations.TenantId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import localhostdev.controledefrota.data.entities.identity.Realm;
import lombok.Data;

@Data
@Entity
@Table(name = "vehicles", uniqueConstraints = { @UniqueConstraint(columnNames = { "plate", "realm_tenant" }, name = "un__vehicles__plate__realm_tenant"),
		@UniqueConstraint(columnNames = { "chassis", "realm_tenant" }, name = "un__vehicles__chassis__realm_tenant") })
public class Vehicle {

	@Id
	private Integer id;

	@NotBlank()
	@Size(min = 8, max = 8)
	@Column(length = 8, nullable = false)
	private String plate;

	@NotBlank()
	@Size(min = 17, max = 17)
	@Column(length = 17, nullable = false)
	private String chassis;

	@Size(max = 32)
	@Column(length = 32)
	private String color;

	@Positive
	@Column
	private Short numberOfAxles;

	@Positive
	@Column
	private Short manufactureYear;

	@JsonIgnore
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "realm_tenant", nullable = false, updatable = false)
	private Realm realm;

	@NotNull
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "model_id", nullable = false)
	private VehicleModel model;

	@OneToOne(optional = true)
	@JoinColumn(name = "driver_id")
	private Driver driver;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "fuel_id", nullable = false)
	private FuelType fuel;

	@JsonIgnore
	@TenantId
	public String getTenantIdentifier() {
		return this.realm.getTenant();
	}
}
