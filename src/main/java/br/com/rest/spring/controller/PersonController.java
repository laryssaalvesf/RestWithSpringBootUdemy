package br.com.rest.spring.controller;

import br.com.rest.spring.data.vo.PersonVO;
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
    public List<PersonVO> findAll() {

        return personService.findAll();
    }


    @GetMapping("/{id}")
    public PersonVO findById(@PathVariable("id") Long id) {

        return personService.findById(id);
    }


    @PostMapping
    public PersonVO create(@RequestBody PersonVO personVO) {

        return personService.create(personVO);
    }


    @PutMapping
    public PersonVO update(@RequestBody PersonVO personVO) {

        return personService.update(personVO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        personService.delete(id);

        return ResponseEntity.ok().build();
    }




}
