package localhostdev.controledefrota.controllers.fleet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/fleet")
public class FleetManagementController {

    @GetMapping("path")
    public String listVehicles(@RequestParam String param) {
        return new String();
    }
    
}
