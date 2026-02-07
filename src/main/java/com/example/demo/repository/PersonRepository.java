package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
 public Person findPersonByUsername(String username);
 public Person findPersonByPassword(String password);
 public Person findPersonByAge(int age);
 // p- альяс, таблица моего Person
 @Query("SELECT p FROM Person p WHERE p.email = ?1")
 public Person findByEmail(String email);
}
