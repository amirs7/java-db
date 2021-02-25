package xyz.softeng.bugofficer.elector;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.softeng.bugofficer.dataaccess.person.Person;
import xyz.softeng.bugofficer.dataaccess.person.PersonRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ElectionService {
    private final PersonRepository personRepository;

    public List<Person> getCandidates(int count) {
        Pot pot = new Pot(getComparator());

        List<Person> candidates = personRepository.findAll()
                .stream()
                .filter(canBeCandidate())
                .collect(Collectors.toList());

        log.info("Candidates: {}", candidates.stream().map(Person::getName).collect(Collectors.joining(",")));

        Collections.shuffle(candidates);
        pot.addCandidates(candidates);

        return pot.pick(count);
    }

    private static Predicate<Person> canBeCandidate() {
        return person -> {
            if (person.isOfficer())
                return person.getNoSelected() < person.getRole().getMaxSelection() - 1;
            else
                return person.getNoSelected() < person.getRole().getMaxSelection();
        };
    }

    private static Function<Person, Priority> getPriority(Set<String> currentTeams) {
        return person -> {
            if (person.isOfficer())
                return Priority.OFFICER;
            else if (currentTeams.contains(person.getTeam()))
                return Priority.SAME_TEAM;
            else
                return Priority.NONE;
        };
    }

    private Comparator<Person> getComparator() {
        Set<String> currentTeams = personRepository.findAll().stream()
                .filter(Person::isOfficer)
                .map(Person::getTeam)
                .collect(Collectors.toSet());

        return Comparator.comparing(getPriority(currentTeams))
                .thenComparing(Person::getNoSelected);
    }
}
