package com.example.demo.Service;

import com.example.demo.dto.PersonDTO;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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

    public PersonDTO findPersonByUsername(String username) {
        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Пользователь с username '" + username + "' не найден"
                ));
        PersonDTO personDTO = new PersonDTO();
        personDTO.setUsername(person.getUsername());
        personDTO.setAge(person.getAge());
        personDTO.setEmail(person.getEmail());
        return personDTO;
    }
    public PersonDTO findByEmail(String email) {
        Person person = personRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Пользователь с email '" + email + "' не найден"
                ));
        PersonDTO personDTO = new PersonDTO();
        personDTO.setUsername(person.getUsername());
        personDTO.setAge(person.getAge());
        personDTO.setEmail(person.getEmail());
        return personDTO;
    }
    public void deleteAllPersons() {
        personRepository.deleteAll();
    }
    public List<PersonDTO> getAllPersons() {
        List<Person> persons = personRepository.findAll();
        List<PersonDTO> personDTOs = new ArrayList<>();

        for (int i = 0; i < persons.size(); i++) {
            PersonDTO dto = new PersonDTO();
            dto.setUsername(persons.get(i).getUsername());
            dto.setEmail(persons.get(i).getEmail());
            dto.setAge(persons.get(i).getAge());
            personDTOs.add(dto);
        }
        return personDTOs;
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
