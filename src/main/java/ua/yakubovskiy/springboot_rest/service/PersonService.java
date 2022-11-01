package ua.yakubovskiy.springboot_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.yakubovskiy.springboot_rest.dao.PersonRepository;
import ua.yakubovskiy.springboot_rest.entity.Person;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> showAll(){
        List<Person> persons = personRepository.findAll();
        persons.forEach(this::setPersonAge);
        return persons;
    }

    public Person showById(int id){
        Optional<Person> optional = personRepository.findById(id);
        optional.ifPresent(this::setPersonAge);
        return optional.orElse(null);
    }

    public void setPersonAge(Person person){
        if(person.getBirthdate() != null)
            person.setAge(Period.between(person.getBirthdate(), LocalDate.now()).getYears());
    }

}
