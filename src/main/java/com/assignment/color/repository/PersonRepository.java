package com.assignment.color.repository;

import com.assignment.color.model.Color;
import com.assignment.color.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Safwan
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Query("SELECT p FROM Person p WHERE p.color= ?1")
    List<Person> findAllPersonsWithSameColor(Color color);
}
