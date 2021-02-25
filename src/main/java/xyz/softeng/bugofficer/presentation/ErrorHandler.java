package xyz.softeng.bugofficer.presentation;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler({
            NoSuchElementException.class
    })
    public ModelAndView handleNotFoundResource(Exception exception) {
        ModelAndView view = new ModelAndView("error");
        view.addObject("message", "Requested Resource Not Found!");
        return view;
    }

    @ExceptionHandler({
            ConstraintViolationException.class
    })
    public ModelAndView handleConstraintViolation(Exception exception) {
        ModelAndView view = new ModelAndView("error");
        view.addObject("message", "Bad Request!");
        return view;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler(Exception exception) {
        ModelAndView view = new ModelAndView("error");
        view.addObject("message", "Internal Error!");
        log.error("Unhandled Exception:", exception);
        return view;
    }
}