package ua.yakubovskiy.springboot_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.yakubovskiy.springboot_rest.entity.Person;
import ua.yakubovskiy.springboot_rest.service.PersonService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public List<Person> showAll() {
        return personService.showAll();
    }

    @GetMapping("/persons/{id}")
    public Person showById(@PathVariable("id") int id) {
        return personService.showById(id);
    }
}
