package com.assignment.color.service;

import com.assignment.color.exception.ColorNotFoundException;
import com.assignment.color.exception.InvalidInputException;
import com.assignment.color.exception.PersonNotFoundException;
import com.assignment.color.model.Color;
import com.assignment.color.model.Person;
import com.assignment.color.repository.ColorRepository;
import com.assignment.color.repository.PersonRepository;
import com.assignment.color.validator.InputValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * @author Safwan
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private ColorRepository colorRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private InputValidator inputValidator;

    @Value("${person.personSaved}")
    private String personSaved;

    List<Person> personList = new ArrayList<>();
    Color color = new Color();
    Person firstTestPerson;
    Person secondTestPerson;

    @Before
    public void setUp() throws Exception {
        firstTestPerson = new Person("Michael", "Jackson", "Chicago", 12345, color);
        secondTestPerson = new Person("Harry", "Potter", "Belfast", 12345, color);
        personList.add(firstTestPerson);
        personList.add(secondTestPerson);
    }

    @Test
    public void shallReturnListOfPersons() {
        when(personRepository.findAll()).thenReturn(personList);
        List<Person> list = personService.findAllPersons();
        assertEquals(list.size(), 2);
    }

    @Test
    public void shallReturnPerson() {
        when(personRepository.findById(anyInt())).thenReturn(Optional.ofNullable(firstTestPerson));
        Person person = personService.findPerson(1);
        assertEquals(person, firstTestPerson);
    }

    @Test
    public void shallReturnPersonListWithSpecificColor() {
        when(inputValidator.isValidColorName(anyString())).thenReturn(true);
        when(colorRepository.getColorByName(anyString())).thenReturn(Optional.of(color));
        when(personRepository.findAllPersonsWithSameColor(any())).thenReturn(personList);

        personService.findAllPersonsByColor("blau");
    }

    @Test(expected = PersonNotFoundException.class)
    public void shallThrowPersonNotFoundExceptionForList() {
        when(personRepository.findAll()).thenReturn(new ArrayList<>());
        personService.findAllPersons();
    }

    @Test(expected = PersonNotFoundException.class)
    public void shallThrowPersonNotFoundExceptionForSinglePerson() {
        when(personRepository.findById(anyInt())).thenReturn(Optional.empty());
        personService.findPerson(2);
    }

    @Test(expected = PersonNotFoundException.class)
    public void shallThrowPersonNotFoundExceptionForPersonsWithColor() {
        when(inputValidator.isValidColorName(anyString())).thenReturn(true);
        when(colorRepository.getColorByName(anyString())).thenReturn(Optional.of(color));
        when(personRepository.findAllPersonsWithSameColor(any())).thenReturn(new ArrayList<>());

        personService.findAllPersonsByColor(anyString());
    }

    @Test(expected = ColorNotFoundException.class)
    public void shallThrowColorNotFoundExceptionForPersonsWithColor() {
        when(inputValidator.isValidColorName(anyString())).thenReturn(true);
        when(colorRepository.getColorByName(anyString())).thenReturn(Optional.empty());

        personService.findAllPersonsByColor(anyString());
    }

    @Test
    public void shallReturnSuccessMessageForAddingPerson() {
        when(colorRepository.getColorByName(anyString())).thenReturn(Optional.of(color));

        String message = personService.addPerson("Daniel", "Radcliffe", "Belfast", 12345, "Black");
        assertEquals(personSaved, message);
    }

    @Test(expected = ColorNotFoundException.class)
    public void shallThrowExceptionForAddingPerson() {
        when(colorRepository.getColorByName(anyString())).thenReturn(Optional.empty());

        String message = personService.addPerson("Daniel", "Radcliffe", "Belfast", 12345, "Black");
        assertEquals(personSaved, message);
    }

    @Test(expected = InvalidInputException.class)
    public void shallThrowInvalidInputExceptionForWrongColorName() {
        when(inputValidator.isValidColorName(anyString())).thenReturn(false);

        personService.findAllPersonsByColor(anyString());
    }

    @Test(expected = InvalidInputException.class)
    public void shallThrowInvalidInputExceptionForZeroPersonId() {
        personService.findPerson(0);
    }

    @Test(expected = InvalidInputException.class)
    public void shallThrowInvalidInputExceptionForNegativePersonId() {
        personService.findPerson(-9);
    }
}
