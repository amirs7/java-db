package xyz.softeng.bugofficer.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import xyz.softeng.bugofficer.dataaccess.sprint.Sprint;
import xyz.softeng.bugofficer.dataaccess.sprint.SprintRepository;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sprints")
public class SprintController {
    private final SprintRepository sprintRepository;

    @GetMapping
    public ModelAndView list(Model model) {
        ModelAndView view = new ModelAndView("sprint/list");
        view.addObject("sprints", sprintRepository.findAll());
        return view;
    }

    @PostMapping
    public String create(@ModelAttribute Sprint sprint) {
        sprintRepository.save(sprint);
        return "redirect:/sprints";
    }

    @GetMapping("/current")
    public ModelAndView currentSprint() {
        Sprint sprint = sprintRepository.findAll().stream()
                .filter(Sprint::isInProgress)
                .findAny().orElseThrow();
        ModelAndView view = new ModelAndView("sprint/update");
        view.addObject("sprint", sprint);
        return view;
    }

    @GetMapping("/new")
    public ModelAndView newSprint() {
        ModelAndView view = new ModelAndView("sprint/new");
        Sprint sprint = new Sprint();
        sprint.setStart(LocalDate.now());
        view.addObject("sprint", sprint);
        return view;
    }

    @GetMapping("/{id}")
    public ModelAndView read(@PathVariable Long id) {
        ModelAndView view = new ModelAndView("sprint/update");
        Sprint sprint = sprintRepository.findById(id).orElseThrow();
        view.addObject("sprint", sprint);
        return view;
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Sprint sprint) {
        sprintRepository.save(sprint);
        return "redirect:/sprints/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        sprintRepository.deleteById(id);
        return "redirect:/sprints";
    }
}

