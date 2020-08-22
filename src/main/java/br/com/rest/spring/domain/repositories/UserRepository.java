package br.com.rest.spring.domain.repositories;

import br.com.rest.spring.domain.entities.Person;
import br.com.rest.spring.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, String> {

    User findByUserName(String userName);

}
