package io.agilehandy.bootuptesting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;


/**
 *  By Haytham Mohamed
 */

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {

    @Mock
    RegistrationRepository repository;

    RegistrationService service;

    @Before
    public void test() {
        service = new RegistrationService(repository);
    }

    @Test
    public void service_returns_all_registration() {
        given(repository.findAll()).willReturn(
                Arrays.asList(
                    new Registration(1L, "Haytham", "Math")
                    ,new Registration(2L, "Hisham", "History")
                )
        );

        Iterable<Registration> registrations = service.getAllRegistrations();

        assertThat(registrations).isNotNull();

        Iterator itr = registrations.iterator();
        Registration reg1 = (Registration)itr.next();
        Registration reg2 = (Registration)itr.next();

        assertThat(reg1.getId()).isEqualTo(1L);
        assertThat(reg2.getId()).isEqualTo(2L);
        assertThat(reg1.getName()).isEqualTo("Haytham");
        assertThat(reg2.getName()).isEqualTo("Hisham");
    }

    @Test
    public void service_returns_registration_by_name() {
        given(repository.findByName(anyString())).willReturn(
                new Registration(1L, "Haytham", "Math")
        );

        Registration reg = service.getRegistration("");

        assertThat(reg).isNotNull();
        assertThat(reg.getId()).isEqualTo(1L);
        assertThat(reg.getName()).isEqualTo("Haytham");
    }

    @Test (expected = RegistrationNotFoundException.class)
    public void service_returns_exception() {
        given(repository.findByName(anyString())).willReturn(null);
        Registration reg = service.getRegistration("");
    }
}