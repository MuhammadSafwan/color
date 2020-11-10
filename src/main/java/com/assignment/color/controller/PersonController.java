package com.assignment.color.controller;

import com.assignment.color.model.Person;
import com.assignment.color.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Safwan
 */
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons/{id}")
    public Person getPerson(@PathVariable int id) {
        return personService.findPerson(id);
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return personService.findAllPersons();
    }

    @GetMapping("/persons/color/{color}")
    public List<Person> getAllPersonsWithSameColor(@PathVariable String color) {
        return personService.findAllPersonsByColor(color);
    }

    @PostMapping("/addPerson")
    public String postPerson(@RequestParam(value = "first_name") String firstName,
                                   @RequestParam(value = "last_name") String lastName,
                                   @RequestParam(value = "city") String city,
                                   @RequestParam(value = "zip_code") int zipCode,
                                   @RequestParam(value = "color") String colorName) {
        return personService.addPerson(firstName, lastName, city, zipCode, colorName);
    }

}
