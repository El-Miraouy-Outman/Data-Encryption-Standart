package com.miraouy.dataencrptionstandart;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements  PersonService{
    private final PersonRepository personRepository;
    private final DesAlgorithm desAlgorithm;
    @Override
    public PersonDto persist(PersonDto personDto) {
        String pss= desAlgorithm.des(personDto.getPassword());
        Person person=new Person(personDto.getFirstName(), personDto.getLastName(),
                pss,personDto.getEmail());
        Person save = personRepository.save(person);
        PersonDto personDto1=new PersonDto(save.getId(),
                save.getFirstName(), save.getLastName(),null, save.getEmail());
        return personDto1;
    }
    @Override
    public boolean authenticate(String email, String password) {
        String pass=desAlgorithm.des(password);
        Person byEmail = personRepository.findByEmail(email);
        if(byEmail.getPassword().equals(pass))
            return true;
        return false;
    }
    @Override
    public List<PersonDto> getAll() {
        List<Person> all = personRepository.findAll();
        List<PersonDto> allPersonDto =new ArrayList<>();
        all.forEach(person -> {
            PersonDto personDto=PersonDto.builder().build();

            BeanUtils.copyProperties(person,personDto);
            allPersonDto.add(personDto);

        });
        return allPersonDto;
    }
}
