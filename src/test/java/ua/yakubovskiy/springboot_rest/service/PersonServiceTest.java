package ua.yakubovskiy.springboot_rest.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ua.yakubovskiy.springboot_rest.dao.PersonRepository;
import ua.yakubovskiy.springboot_rest.entity.Person;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonServiceTest {

    @TestConfiguration
    static class PersonServiceTestContextConfiguration {
        @Bean
        public PersonService personService() {
            return new PersonService();
        }
    }

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @BeforeEach
    public void setUp() {
        Person person = new Person(1, "ivan", "petrov",
                LocalDate.of(1999, 1, 8), 0);
        Mockito.when(personRepository.findById(person.getId()))
                .thenReturn(Optional.of(person));
    }

    @Test
    void whenValidName_thenPersonShouldBeFound() {
        Person found = personService.showById(1);
        assertThat(found.getName()).isEqualTo("ivan");
    }

    @Test
    void calculatingPersonsAge(){
        Person person = new Person(7, "Mike", "Java",
                LocalDate.of(2000, 1, 8), 0);
        int age = Period.between(person.getBirthdate(), LocalDate.now()).getYears();
        personService.setPersonAge(person);
        assertThat(person.getAge()).isEqualTo(age);
    }
}