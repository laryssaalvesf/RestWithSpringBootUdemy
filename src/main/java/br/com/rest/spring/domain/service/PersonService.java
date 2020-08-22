package br.com.rest.spring.domain.service;


import br.com.rest.spring.converter.DozerConverter;
import br.com.rest.spring.data.vo.PersonVO;
import br.com.rest.spring.domain.entities.Person;
import br.com.rest.spring.domain.repositories.PersonRepository;
import br.com.rest.spring.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonVO findById(Long id) {

        return DozerConverter.parseObject(findByExistingPerson(id), PersonVO.class);
    }

    public List<PersonVO> findAll() {

        return DozerConverter.parseListObjects(personRepository.findAll(), PersonVO.class);
    }


    public PersonVO create(PersonVO personVO) {

        Person person = DozerConverter.parseObject(personVO, Person.class);

        PersonVO vo = DozerConverter.parseObject(personRepository.save(person), PersonVO.class);

        return vo;
    }


    public PersonVO update(PersonVO personVO) {

        Person foundedPerson = personRepository.findById(personVO.getKey());

        if(Objects.isNull(personVO)) {
            throw new ResourceNotFoundException("No records found for this ID");
        }

        foundedPerson.setAddress(personVO.getAddress());
        foundedPerson.setFirstName(personVO.getFirstName());
        foundedPerson.setGender(personVO.getGender());
        foundedPerson.setLastName(personVO.getLastName());

        return DozerConverter.parseObject(personRepository.save(foundedPerson), PersonVO.class);
    }

    public void delete(Long id) {
        Person person = findByExistingPerson(id);

        personRepository.delete(person);
    }


    private Person findByExistingPerson(Long id) {

        Person person = personRepository.findById(id);

        if(Objects.isNull(person)) {
            throw new ResourceNotFoundException("No records found for this ID");
        }

        return person;
    }


}
