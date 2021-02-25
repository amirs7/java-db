package xyz.softeng.bugofficer.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.softeng.bugofficer.dataaccess.person.Person;
import xyz.softeng.bugofficer.dataaccess.person.PersonRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/persons")
public class PersonController {
    private final PersonRepository personRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("persons", personRepository.findAll());
        return "person/list";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "person/new";
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
        return "person/update";
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
