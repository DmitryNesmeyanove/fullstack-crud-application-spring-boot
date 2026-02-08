package com.example.demo.Service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
public class PersonService {
    @Autowired
    public PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public Person saveUser(Person person){
        return personRepository.save(person);
    }

    public Person findPersonByUsername(String username) {
        return personRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Пользователь с username '" + username + "' не найден"
                ));
    }
    public Person findByEmail(String email) {
        return personRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Пользователь с email '" + email + "' не найден"
                ));
    }
    public void deleteAllPersons() {
        personRepository.deleteAll();
    }
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    public void deletePerson(Long id) {
        if (!personRepository.existsById(id)) {
            throw new EntityNotFoundException("Пользователь с ID " + id + " не найден");
        }
        personRepository.deleteById(id);
    }
    public Person updatePerson(Long id, Person updatedPerson) {
        Person existingPerson = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingPerson.setUsername(updatedPerson.getUsername());
        existingPerson.setPassword(updatedPerson.getPassword());
        existingPerson.setAge(updatedPerson.getAge());
        existingPerson.setEmail(updatedPerson.getEmail());

        return personRepository.save(existingPerson);
    }

    public Person patchPerson(Long id, Map<String, Object> updates) {
        Person existingPerson = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "username":
                    existingPerson.setUsername((String) value);
                    break;
                case "password":
                    existingPerson.setPassword((String) value);
                    break;
                case "age":
                    existingPerson.setAge((Integer) value);
                    break;
                case "email":
                    existingPerson.setEmail((String) value);
                    break;
            }
        });

        return personRepository.save(existingPerson);
    }
}
