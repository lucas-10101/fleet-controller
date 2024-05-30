package localhostdev.controledefrota.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import localhostdev.controledefrota.services.identity.AuthenticationService;

@RestController
@RequestMapping
public class TestController {

    @GetMapping
    public String getMethodName() {
        return AuthenticationService.getCurrentUser().toString();
    }
}
