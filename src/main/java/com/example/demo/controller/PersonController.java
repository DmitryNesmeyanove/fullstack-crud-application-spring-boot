package com.example.demo.controller;
import com.example.demo.Service.PersonService;
import com.example.demo.dto.PersonDTO;
import com.example.demo.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PersonController {
    PersonService personService;
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/save/person")
    public Person saveUser(@RequestBody Person person) {
        return personService.saveUser(person);
    }
    @GetMapping("/person/all")
    public List<PersonDTO> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/person/search/username")
    public PersonDTO findPersonByUsername(@RequestParam String username) {
        return personService.findPersonByUsername(username);
    }

    @GetMapping("/person/search/email")
    public PersonDTO findByEmail(@RequestParam String email) {
        return personService.findByEmail(email);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok("Пользователь с ID " + id + " удален");
    }
    @DeleteMapping("/person/all")
    public ResponseEntity<String> deleteAllPersons() {
        personService.deleteAllPersons();
        return ResponseEntity.ok("Все пользователи удалены");
    }

    @PutMapping("/person/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        return personService.updatePerson(id, updatedPerson);
    }

    @PatchMapping("/person/{id}")
    public Person patchPerson(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return personService.patchPerson(id, updates);
    }
}

