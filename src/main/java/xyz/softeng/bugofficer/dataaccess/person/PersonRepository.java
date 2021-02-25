package xyz.softeng.bugofficer.dataaccess.person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByIsOfficerTrue();
}
