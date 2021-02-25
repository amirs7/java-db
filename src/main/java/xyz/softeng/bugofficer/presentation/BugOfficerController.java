package xyz.softeng.bugofficer.presentation;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.softeng.bugofficer.dataaccess.person.Person;
import xyz.softeng.bugofficer.dataaccess.person.PersonRepository;
import xyz.softeng.bugofficer.elector.ElectionService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bug-officers")
public class BugOfficerController {
    private final PersonRepository personRepository;
    private final ElectionService electionService;

    @GetMapping
    public ModelAndView list(Model model) {
        List<Person> currentOfficers = personRepository.findAllByIsOfficerTrue();
        ModelAndView view = new ModelAndView("bug-officers/index");
        view.addObject("persons", currentOfficers);
        return view;
    }

    @Data
    static class Form {
        List<Person> persons;

        public Form(List<Person> persons) {
            this.persons = persons;
        }
    }

    @GetMapping("/candidates")
    public ModelAndView candidates(@RequestParam(defaultValue = "1") Integer count) {
        List<Person> candidates = electionService.getCandidates(count);
        ModelAndView view = new ModelAndView("bug-officers/candidates");
        view.addObject("persons", candidates);
        view.addObject("form", new Form(candidates));
        return view;
    }

    @GetMapping("/retire")
    public String retire() {
        retireCurrentOfficers();

        return "redirect:/bug-officers";
    }

    @PostMapping
    public String assignNewOfficers(@ModelAttribute Form form) {
        retireCurrentOfficers();

        List<Person> newOfficers = form.getPersons();
        newOfficers.forEach(Person::becomeOfficer);
        personRepository.saveAll(newOfficers);

        return "redirect:/bug-officers";
    }

    private void retireCurrentOfficers() {
        List<Person> currentOfficers = personRepository.findAllByIsOfficerTrue();
        currentOfficers.forEach(Person::retire);
        personRepository.saveAll(currentOfficers);
    }
}

