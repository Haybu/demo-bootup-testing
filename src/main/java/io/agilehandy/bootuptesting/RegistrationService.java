package io.agilehandy.bootuptesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  By Haytham Mohamed
 */

@Service
public class RegistrationService {

    @Autowired
    RegistrationRepository repository;

    public RegistrationService(RegistrationRepository r) {
        this.repository = r;
    }

    public Iterable<Registration> getAllRegistrations() {
        return repository.findAll();
    }

    public Registration getRegistration(String name) {
        Registration reg = repository.findByName(name);

        if (reg == null) {
            throw new RegistrationNotFoundException();
        }

        return reg;
    }

}
