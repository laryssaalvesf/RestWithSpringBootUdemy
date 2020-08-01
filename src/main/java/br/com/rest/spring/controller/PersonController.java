package br.com.rest.spring.controller;

import br.com.rest.spring.domain.entities.Person;
import br.com.rest.spring.domain.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {


    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> findAll() {

        return personService.findAll();
    }


    @GetMapping("/{id}")
    public Person findById(@PathVariable("id") String id) {

        return personService.findById(id);
    }


    @PostMapping
    public Person create(@RequestBody Person person) {

        return personService.create(person);
    }


    @PutMapping
    public Person update(@RequestBody Person person) {

        return personService.update(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        personService.delete(id);

        return ResponseEntity.ok().build();
    }




}
