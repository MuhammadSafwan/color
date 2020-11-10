package com.assignment.color.controller;

import com.assignment.color.repository.PersonRepository;
import com.assignment.color.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Safwan
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Mock
    org.springframework.web.servlet.View mockView;

    private MockMvc mockMvc;

    @InjectMocks
    private PersonController personController;

    @MockBean
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

   @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(personController).setSingleView(mockView).build();
    }

    @Test
    public void shallReturnOkResponseForPersonList() throws Exception {
        mockMvc.perform(get("/persons")).andExpect(status().isOk());
    }

    @Test
    public void shallReturnOkResponseForPerson() throws Exception {
        mockMvc.perform(get("/persons/1")).andExpect(status().isOk());
    }

    @Test
    public void shallReturnNotFoundForPerson() throws Exception {
        mockMvc.perform(get("/person/1")).andExpect(status().isNotFound());
    }

    @Test
    public void shallReturnOkResponseForPersonWithColor() throws Exception {
        mockMvc.perform(get("/persons/color/blau")).andExpect(status().isOk());
    }

    @Test
    public void shallReturnOkForAddingPerson() throws Exception {
        mockMvc.perform(post("/addPerson?first_name=Harry&last_name=Potter&city=Belfast&zip_code=70176&color=blau")).andExpect(status().isOk());
    }
}
