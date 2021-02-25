package xyz.softeng.bugofficer.presentation;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({
            NoSuchElementException.class
    })
    public String handleNotFoundResource(Exception exception, Model model) {
        model.addAttribute("message", "Requested Resource Not Found!");
        return "error";
    }
}