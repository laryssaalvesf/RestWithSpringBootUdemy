package br.com.rest.spring.domain.service;


import br.com.rest.spring.domain.entities.Person;
import br.com.rest.spring.domain.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findById(String id) {

       return personRepository.findById(id);
    }

    public List<Person> findAll() {
      return personRepository.findAll();
    }


    public Person create(Person person) {

        return personRepository.save(person);
    }


    public Person update(Person person) {

        return personRepository.save(person);
    }

    public void delete(String id) {
    }


}
