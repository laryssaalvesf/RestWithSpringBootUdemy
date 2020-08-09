package br.com.rest.spring.domain.repositories;

import br.com.rest.spring.domain.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

    Person findById(Long id);

}
