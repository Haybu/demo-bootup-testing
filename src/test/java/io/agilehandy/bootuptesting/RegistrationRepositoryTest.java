package io.agilehandy.bootuptesting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


/**
 *  By Haytham Mohamed
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class RegistrationRepositoryTest {

    @Autowired
    RegistrationRepository repository;

    @Test
    public void test() {
        Registration savedRegistration =
                repository.save(new Registration(1L, "Haytham", "Math"));

        Registration registration = repository.findByName("Haytham");
        assertThat(registration).isNotNull();
        assertThat(registration.getId()).isEqualTo(savedRegistration.getId());
        assertThat(registration.getName()).isEqualTo(savedRegistration.getName());
        assertThat(registration.getClassName()).isEqualTo(savedRegistration.getClassName());
    }

}
