package io.agilehandy.bootuptesting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


/**
 *  By Haytham Mohamed
 */


@RunWith(SpringRunner.class)
@DataJpaTest
public class EntityTest {

    @Autowired
    TestEntityManager tem;

    @Autowired
    RegistrationRepository repository;


    @Test
    public void test() {
        Registration savedRegistration =
                tem.persistFlushFind(new Registration("Haytham", "Math"));

       assertThat(savedRegistration.getId()).isEqualTo(1L);
       assertThat(savedRegistration.getName()).isEqualTo("Haytham");
       assertThat(savedRegistration.getClassName()).isEqualTo("Math");
    }

}
