package com.assignment.color.model;

import javax.persistence.*;

/**
 * @author Safwan
 */
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int personId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private int zipCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color")
    private Color color;

    public Person() {
    }

    public Person(String firstName, String lastName, String city, int zipCode, Color color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.zipCode = zipCode;
        this.color = color;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getColor() {
        return color.getColorName();
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
