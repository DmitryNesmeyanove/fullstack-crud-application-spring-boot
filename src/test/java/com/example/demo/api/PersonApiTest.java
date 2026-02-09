package com.example.demo.api;
import com.example.demo.BasePage;
import com.example.demo.model.Person;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PersonApiTest extends BasePage{
    private Person createTestApi(){
        // Пользователь
        Person person = new Person();
        person.getId();
        person.setUsername("ivanov");
        person.setPassword("12342");
        person.setAge(18);
        person.setEmail("testing@te.te");
        return person;
    }

    @Test
    void testFullCrudCycle(){
        Person person = createTestApi();
        String username = person.getUsername();
        // Создаем пользователя
        Response createResponse = given()
                .contentType(ContentType.JSON)
                .body(person)
                .when()
                .post("/api/save/person")
                .then()
                .statusCode(200)
                .body("username", equalTo("ivanov"))
                .body("email", equalTo("testing@te.te"))
                .body("age", equalTo(18))
                .log().all()
                .extract().response();

        // Получаем ID из ответа сервера
        Long createdId = createResponse.jsonPath().getLong("id");
        assertNotNull(createdId, "ID должен быть возвращен сервером");

        // Проверяем, что пользователь создан
        given()
                .when()
                .get("/api/person/search/username?username=" + username)
                .then()
                .statusCode(200) // Добавлена проверка статуса
                .body("username", equalTo("ivanov"))
                .body("email", equalTo("testing@te.te"))
                .body("age", equalTo(18))
                .log().all();

        // Заменяем данные у пользователя
        Person personUpdate = new Person();
        personUpdate.setUsername("naumov");
        personUpdate.setPassword("12342");
        personUpdate.setAge(25);
        personUpdate.setEmail("naumov@te.te");
        String newUsername = personUpdate.getUsername();

        given()
                .contentType(ContentType.JSON)
                .body(personUpdate)
                .when()
                .put("/api/person/" + createdId)
                .then()
                .statusCode(200)
                .body("username", equalTo("naumov"))
                .body("age",equalTo(25))
                .body("email", equalTo("naumov@te.te"))
                .log().all();

        // Проверяем, что у пользователя изменились данные
        given()
                .when()
                .get("/api/person/search/username?username=" + newUsername)
                .then()
                .statusCode(200)
                .body("username", equalTo("naumov"))
                .body("email", equalTo("naumov@te.te"))
                .body("age", equalTo(25))
                .log().all();

        // Удаляем пользователя из базы
        given()
                .when()
                .delete("/api/person/" + createdId)
                .then()
                .statusCode(200)
                .log().all();

        // Проверяем, что пользователя нет в базе
        given()
                .when()
                .get("/api/person/search/username?username=" + newUsername)
                .then()
                .statusCode(404)
                .log().all();
    }
}
