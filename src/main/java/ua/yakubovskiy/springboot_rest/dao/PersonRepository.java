package ua.yakubovskiy.springboot_rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.yakubovskiy.springboot_rest.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
