package ua.yakubovskiy.springboot_rest.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class PersonTest {

    @Autowired
    private JacksonTester<Person> json;

    @Test
    void testSerialize() throws Exception {
        Person person = new Person(3, "Mike", "Java",
                LocalDate.of(2000, 1, 1), 22);

        JsonContent<Person> result = json.write(person);

        assertThat(result).hasJsonPathStringValue("$.name");
        assertThat(result).extractingJsonPathStringValue("$.name").isEqualTo("Mike");
        assertThat(result).extractingJsonPathStringValue("$.surname").isEqualTo("Java");
        assertThat(result).extractingJsonPathNumberValue("$.age").isEqualTo(22);
    }

    @Test
    public void testDeserialize() throws Exception {

        String jsonContent = "{\n" +
                "        \"id\": 3,\n" +
                "        \"name\": \"Mike\",\n" +
                "        \"surname\": \"Java\",\n" +
                "        \"age\": 22\n" +
                "    }";

        Person result = this.json.parse(jsonContent).getObject();

        assertThat(result.getName()).isEqualTo("Mike");
        assertThat(result.getSurname()).isEqualTo("Java");
        assertThat(result.getId()).isEqualTo(3);
        assertThat(result.getAge()).isEqualTo(22);
    }
}