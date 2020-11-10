package com.assignment.color.repository;

import com.assignment.color.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author Safwan
 */
public interface ColorRepository extends JpaRepository<Color, Integer> {

    @Query("SELECT c FROM Color c WHERE c.colorName = ?1")
    Optional<Color> getColorByName(String name);
}
