package com.example.springdemo.dto;

import java.util.Objects;

/**
 * example
 */
public class PersonDTO {

    private Integer id;
    private String name;
    private String email;
    private String iban;
    private String address;

    public PersonDTO() {
    }

    public PersonDTO(Integer id, String name, String email, String iban, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.iban = iban;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(id, personDTO.id) &&
                Objects.equals(name, personDTO.name) &&
                Objects.equals(email, personDTO.email) &&
                Objects.equals(iban, personDTO.iban) &&
                Objects.equals(address, personDTO.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, email, iban, address);
    }
}
