package io.agilehandy.bootuptesting;

import org.assertj.core.api.Assertions;
import org.junit.Test;


/**
 *  By Haytham Mohamed
 */

public class PlainTest {

    @Test
    public void testRegistrationDomain() {
        Registration reg = new Registration(1L, "Haytham", "Math");
        Assertions.assertThat(reg.getName()).isNotBlank();
        Assertions.assertThat(reg.getName()).isEqualTo("Haytham");
        Assertions.assertThat(reg.getClassName()).isEqualTo("Math");

    }
}

