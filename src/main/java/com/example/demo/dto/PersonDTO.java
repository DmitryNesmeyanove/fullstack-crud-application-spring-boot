package com.example.demo.dto;

import com.example.demo.model.Person;

public class PersonDTO{
    String username;
    int age;
    String email;

    public PersonDTO(String username, int age, String email) {
        this.username = username;
        this.age = age;
        this.email = email;
    }

    public PersonDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
