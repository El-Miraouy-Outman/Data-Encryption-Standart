package com.miraouy.dataencrptionstandart;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/person")
public class PersonController {
    private final PersonService personService;
    @PostMapping
    public PersonDto persist(@RequestBody PersonDto personDto){
       return personService.persist(personDto);
    }
    @PutMapping
    public boolean authenticate(@RequestParam("email") String email,@RequestParam("password") String password){
        return  personService.authenticate(email,password);
    }
    @GetMapping
    public List<PersonDto> getAll(){
        return personService.getAll();
    }

}
