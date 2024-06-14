package localhostdev.controledefrota.services.fleet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import localhostdev.controledefrota.data.entities.fleet.Vehicle;
import localhostdev.controledefrota.data.repository.fleet.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	public Page<Vehicle> listInPage(Pageable page) {
		return vehicleRepository.findAll(page);
	}
}
