package io.agilehandy.bootuptesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *  By Haytham Mohamed
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoBootupTestingApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	@Autowired
	RegistrationRepository repository;

	@Before
	public void setup() {
		repository.save(new Registration(1L, "Haytham", "Math"));
	}

	@After
	public void teardown() {
		repository.deleteAll();
	}

	@Test
	public void contextLoads() {


		ResponseEntity<Registration> entity =
                testRestTemplate.getForEntity("/registrations/Haytham", Registration.class);

		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

		Registration registration = entity.getBody();

		assertThat(registration).isNotNull();
		assertThat(registration.getId()).isEqualTo(1L);
		assertThat(registration.getName()).isEqualTo("Haytham");
		assertThat(registration.getClassName()).isEqualTo("Math");
	}

}
