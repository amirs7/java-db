package xyz.softeng.dbscript.dataaccess;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("local")
@RequiredArgsConstructor
public class Initializer {
    private final PersonRepository personRepository;

    @PostConstruct
    public void init() {
        personRepository.deleteAll();

        personRepository.save(new Person("amir", "devops", Role.BACKEND));

        personRepository.save(new Person("hoda", "ui", Role.FRONTEND));

        personRepository.save(new Person("enzo", "devops", Role.FRONTEND));
    }
}
