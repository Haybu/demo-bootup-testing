package io.agilehandy.bootuptesting;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 *  By Haytham Mohamed
 */

@RestController
public class RegistrationController {

    RegistrationService service;

    public RegistrationController(RegistrationService s) {
        service = s;
    }

    @GetMapping("/registrations")
    public Iterable<Registration> getAll() {
        return service.getAllRegistrations();
    }

    @GetMapping("/registrations/{name}")
    public Registration getRegistration(@PathVariable("name") String name) {
        return service.getRegistration(name);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notfoundHandler(RegistrationNotFoundException ex){}

}
