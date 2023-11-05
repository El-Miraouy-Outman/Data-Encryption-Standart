package com.miraouy.dataencrptionstandart;

import jakarta.persistence.Column;

import java.util.List;

public interface PersonService {
    public PersonDto persist(PersonDto personDto);
    public boolean authenticate(String email,String password);
    public List<PersonDto> getAll();


}
