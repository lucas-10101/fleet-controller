package localhostdev.controledefrota.data.entities.identity;

import org.hibernate.annotations.TenantId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username", "realm_tenant" }, name = "un__users__username__realm_tenant") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@Email
	@Column(length = 256, nullable = false)
	private String username;

	@JsonIgnore
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "realm_tenant", nullable = false, updatable = false)
	private Realm realm;

	@NotBlank
	@Size(min = 8, max = 32)
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(length = 72, nullable = false)
	private String password;

	@Column(nullable = false)
	private boolean active;

	@JsonIgnore
	@TenantId
	public String getTenantIdentifier() {
		return this.realm.getTenant();
	}

}
