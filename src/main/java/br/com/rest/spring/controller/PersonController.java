package br.com.rest.spring.controller;

import br.com.rest.spring.data.vo.PersonVO;
import br.com.rest.spring.domain.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


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
        List<PersonVO> personVOList = personService.findAll();
        personVOList.forEach(personVO -> {
            personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());
        });

        return personVOList;
    }


    @GetMapping("/{id}")
    public PersonVO findById(@PathVariable("id") Long id) {
        PersonVO personVO = personService.findById(id);
        personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return personVO;
    }


    @PostMapping
    public PersonVO create(@RequestBody PersonVO person) {
        PersonVO personVO = personService.create(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());

        return personVO;
    }


    @PutMapping
    public PersonVO update(@RequestBody PersonVO person) {
        PersonVO personVO = personService.update(person);
        personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());

        return personVO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        personService.delete(id);

        return ResponseEntity.ok().build();
    }




}
