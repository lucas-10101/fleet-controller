package localhostdev.controledefrota.data.entities.fleet;

import java.math.BigDecimal;

import org.hibernate.annotations.TenantId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import localhostdev.controledefrota.data.entities.identity.Realm;
import lombok.Data;

@Data
@Entity
@Table(name = "fuel_types")
public class FuelType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Size(max = 32)
	@Column(length = 32, nullable = false)
	private String name;

	@Column(precision = 8, scale = 4)
	private BigDecimal unitPrice;

	@JsonIgnore
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "realm_tenant", nullable = false, updatable = false)
	private Realm realm;

	@JsonIgnore
	@TenantId
	public String getTenantIdentifier() {
		return this.realm.getTenant();
	}
}
