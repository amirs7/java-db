package xyz.softeng.bugofficer.dataaccess.person;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.softeng.bugofficer.dataaccess.person.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
