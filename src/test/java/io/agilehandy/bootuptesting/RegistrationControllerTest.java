package io.agilehandy.bootuptesting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;


/**
 *  By Haytham Mohamed
 */

@RunWith(SpringRunner.class)
@WebMvcTest(RegistrationController.class)
public class RegistrationControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    RegistrationService service;

    @Test
    public void test_api_all_registrations() throws Exception {
        given(service.getAllRegistrations()).willReturn(
                Arrays.asList(
                        new Registration(1L, "Haytham", "Math")
                        ,new Registration(2L, "Hisham", "History")
                )
        );

        mvc.perform(MockMvcRequestBuilders.get("/registrations"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].name").value("Haytham"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].className").value("Math"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[1].name").value("Hisham"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[1].className").value("History"))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @Test
    public void test_api_registration_by_name() throws Exception {
        given(service.getRegistration(anyString())).willReturn(
                        new Registration(1L, "Haytham", "Math")
        );

        mvc.perform(MockMvcRequestBuilders.get("/registrations/somename"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("@.name").value("Haytham"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.className").value("Math"))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
    }

    @Test
    public void test_api_registration_by_name_failure() throws Exception {
        given(service.getRegistration(anyString())).willThrow(new RegistrationNotFoundException());

        mvc.perform(MockMvcRequestBuilders.get("/registrations/somename"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
        ;
    }

}