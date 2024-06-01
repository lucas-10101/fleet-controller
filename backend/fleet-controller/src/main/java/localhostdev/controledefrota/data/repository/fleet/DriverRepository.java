package localhostdev.controledefrota.data.repository.fleet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import localhostdev.controledefrota.data.entities.fleet.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {

}
