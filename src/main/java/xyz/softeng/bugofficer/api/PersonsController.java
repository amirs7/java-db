package xyz.softeng.bugofficer.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.softeng.bugofficer.dataaccess.Person;
import xyz.softeng.bugofficer.dataaccess.PersonRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonsController {
    private final PersonRepository personRepository;

    @GetMapping
    public String listPersons(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        return "persons/list";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "persons/new";
    }

    @PostMapping
    public String create(@ModelAttribute Person person) {
        personRepository.save(person);
        return "redirect:/persons";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable Long id, Model model) {
        Person person = personRepository.findById(id).orElseThrow();
        model.addAttribute("person", person);
        return "persons/update";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Person person) {
        personRepository.save(person);
        return "redirect:/persons/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        personRepository.deleteById(id);
        return "redirect:/persons";
    }
}
