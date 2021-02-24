package xyz.softeng.dbscript.elector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.softeng.dbscript.dataaccess.Person;
import xyz.softeng.dbscript.dataaccess.PersonRepository;
import xyz.softeng.dbscript.dataaccess.Role;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class ElectorTest {
    @Autowired
    private Elector elector;

    @Autowired
    private PersonRepository personRepository;

    @ParameterizedTest
    @MethodSource({
            "simpleCase",
            "sameTeam"
    })
    void testElection(List<Person> persons, String expectedOfficer) {
        personRepository.saveAll(persons);

        Person officer = elector.selectNextOfficer();

        assertThat(officer.getName()).isEqualTo(expectedOfficer);
    }

    public static List<Arguments> simpleCase() {
        return List.of(arguments(
                List.of(
                        new Person("amir", "A", Role.BACKEND),
                        new Person("hoda", "B", Role.FRONTEND, 0, true)
                ),
                "amir"
        ));
    }

    public static List<Arguments> sameTeam() {
        return List.of(arguments(
                List.of(
                        new Person("amir", "A", Role.BACKEND, 2),
                        new Person("hoda", "B", Role.FRONTEND, 0, true),
                        new Person("sarah", "B", Role.FRONTEND)
                ),
                "amir"
        ));
    }

    @AfterEach
    public void tearDown() {
        personRepository.deleteAll();
    }
}
