package ua.yakubovskiy.springboot_rest.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ua.yakubovskiy.springboot_rest.entity.Person;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PersonRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void whenFindById_thenReturnPerson() {
        Person person = new Person();
        person.setName("Mike");
        person.setSurname("Java");
        person.setBirthdate(LocalDate.of(2001, 5, 9));
        entityManager.persist(person);
        entityManager.flush();

        Person found = personRepository.findById(person.getId()).get();

        assertThat(found.getName()).isEqualTo(person.getName());
    }
}