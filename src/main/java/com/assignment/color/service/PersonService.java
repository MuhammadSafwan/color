package com.assignment.color.service;

import com.assignment.color.exception.ColorNotFoundException;
import com.assignment.color.exception.InvalidInputException;
import com.assignment.color.exception.PersonNotFoundException;
import com.assignment.color.model.Color;
import com.assignment.color.model.Person;
import com.assignment.color.repository.ColorRepository;
import com.assignment.color.repository.PersonRepository;
import com.assignment.color.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

/**
 * @author Safwan
 */
@Service
public class PersonService {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Value("${person.noRecords}")
    private String noRecords;

    @Value("${color.noColorFound}")
    private String noColorFound;

    @Value("${person.personsWithColorNotFound}")
    private String personsWithColorNotFound;

    @Value("${person.personNotFound}")
    private String personNotFound;

    @Value("${person.personSaved}")
    private String personSaved;

    @Value("${color.invalidColorName}")
    private String invalidColorName;

    @Value("${person.invalidPersonId}")
    private String invalidPersonId;

    @Autowired
    @Qualifier("CSVFileService")
    private FileService fileService;

    @Autowired
    private InputValidator inputValidator;

    public Person findPerson(int personId) {
        if(personId < 1) {
            throw new InvalidInputException(invalidPersonId);
        }
        Optional<Person> person = personRepository.findById(personId);
        if(person.isPresent()) {
            return person.get();
        }
        else {
            throw new PersonNotFoundException(personNotFound + personId);
        }
    }

    public List<Person> findAllPersons() {
        List<Person> personList =  personRepository.findAll();
        if(CollectionUtils.isEmpty(personList)) {
            throw new PersonNotFoundException(noRecords);
        } else {
            return personList;
        }
    }

    public List<Person> findAllPersonsByColor(String colorName) {
        if(!inputValidator.isValidColorName(colorName)) {
            throw new InvalidInputException(invalidColorName);
        }
        Optional<Color> inputColor = colorRepository.getColorByName(colorName);
        if(inputColor.isPresent()) {
            Color color = inputColor.get();
            List<Person> personList = personRepository.findAllPersonsWithSameColor(color);
            if(CollectionUtils.isEmpty(personList)) {
                throw new PersonNotFoundException(personsWithColorNotFound + color.getColorName());
            } else {
                return personList;
            }
        } else {
            throw new ColorNotFoundException(noColorFound + colorName);
        }
    }

    public String addPerson(String firstName, String lastName, String city, int zipCode, String colorName) {
        Optional<Color> inputColor = colorRepository.getColorByName(colorName);
        if(inputColor.isPresent()) {
            Color color = inputColor.get();
            Person person = new Person(firstName, lastName, city, zipCode, color);
            personRepository.save(person);
            return personSaved;
        } else {
            throw new ColorNotFoundException(noColorFound + colorName);
        }
    }

    @PostConstruct
    private void postConstruct() {
        fileService.readFileAndSaveData("classpath:sample-input.csv");
    }
}
