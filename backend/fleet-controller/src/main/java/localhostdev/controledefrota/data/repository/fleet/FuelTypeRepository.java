package localhostdev.controledefrota.data.repository.fleet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import localhostdev.controledefrota.data.entities.fleet.FuelType;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, Integer> {

}
