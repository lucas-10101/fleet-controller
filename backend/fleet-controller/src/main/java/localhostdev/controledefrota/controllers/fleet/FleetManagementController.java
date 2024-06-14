package localhostdev.controledefrota.controllers.fleet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import localhostdev.controledefrota.data.entities.fleet.Vehicle;
import localhostdev.controledefrota.services.fleet.VehicleService;

@RestController
@RequestMapping(path = "/fleet")
public class FleetManagementController {

	@Autowired
	private VehicleService vehicleService;

	@GetMapping("/vehicles")
	@PreAuthorize("hasAuthority('LIST_VEHICLES')")
	public Page<Vehicle> listVehicles(Pageable pageable) {
		return this.vehicleService.listInPage(pageable);
	}

}
