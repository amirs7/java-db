package xyz.softeng.bugofficer.elector;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import xyz.softeng.bugofficer.dataaccess.person.Person;
import xyz.softeng.bugofficer.dataaccess.person.PersonRepository;

import java.util.Comparator;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ElectionService {
    private final PersonRepository personRepository;
    private Set<String> currentSelectedTeams;

    public Person selectNextOfficer() {
        currentSelectedTeams = personRepository.findAll().stream()
                .filter(Person::isOfficer)
                .map(Person::getTeam)
                .collect(Collectors.toSet());

        Pot pot = new Pot(comparator().reversed());

        personRepository.findAll().stream()
                .filter(canBeCandidate())
                .forEach(pot::addCandidate);

        return pot.pick();
    }

    private static Predicate<Person> canBeCandidate() {
        return person -> {
            if (person.isOfficer())
                return person.getNoSelected() < person.getRole().getMaxSelection();
            else
                return person.getNoSelected() <= person.getRole().getMaxSelection();
        };
    }

    public Comparator<Person> comparator() {
        return (p1, p2) -> {
            if (p1.equals(p2))
                return 0;

            if (p1.isOfficer())
                return -1;

            if (currentSelectedTeams.contains(p1.getTeam()))
                return -1;

            return 0;
        };
    }
}
