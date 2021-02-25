package xyz.softeng.bugofficer.presentation;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class HomeController {

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/bar")
    public int bar(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        System.out.println(date);
        return 2;
    }
}
