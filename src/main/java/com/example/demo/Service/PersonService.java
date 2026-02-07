package com.example.demo.Service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Person findPersonByUsername(String username){
        return personRepository.findPersonByUsername(username);
    }
    public Person findByEmail(String email){
        return personRepository.findByEmail(email);
    }
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    public void deletePerson(Long id) {
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
