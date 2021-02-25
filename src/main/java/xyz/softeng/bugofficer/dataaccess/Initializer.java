package xyz.softeng.bugofficer.dataaccess;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import xyz.softeng.bugofficer.dataaccess.person.Person;
import xyz.softeng.bugofficer.dataaccess.person.PersonRepository;
import xyz.softeng.bugofficer.dataaccess.person.Role;

import javax.annotation.PostConstruct;

@Component
@Profile("local")
@RequiredArgsConstructor
public class Initializer {
    private final PersonRepository personRepository;

    @PostConstruct
    public void init() {
        if(personRepository.count() >0 )
            return;

        personRepository.deleteAll();

        personRepository.save(new Person("amir", "devops", Role.BACKEND));

        personRepository.save(new Person("hoda", "ui", Role.FRONTEND));

        personRepository.save(new Person("sarah", "ui", Role.FRONTEND));

        personRepository.save(new Person("ramtin", "data", Role.BACKEND));

        personRepository.save(new Person("john", "data", Role.BACKEND));
    }
}
