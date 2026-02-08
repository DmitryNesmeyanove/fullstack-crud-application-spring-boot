package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
 public Person findPersonByUsername(String username);
 public Person findPersonByPassword(String password);
 public Person findPersonByAge(int age);
 @Query("SELECT p FROM Person p WHERE p.username = ?1")
 Optional<Person> findByUsername(String username);
 // p- альяс, таблица моего Person
 @Query("SELECT p FROM Person p WHERE p.email = ?1")
 Optional<Person> findByEmail(String email);
}
