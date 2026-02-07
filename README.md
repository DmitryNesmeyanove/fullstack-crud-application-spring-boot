# 🚀 Person Management System

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.5-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED)

#### 📄 Полнофункциональная система управления пользователями с современным веб-интерфейсом и REST API.

#### ⚠️ В дальнейшем будут добавлены: Kanban доска в Miro, тест-кейсы и набор автотестов для тестирование BD и API.

## 📖 Описание

Person Management System - это веб-приложение для управления пользователями с полным набором CRUD операций. Приложение состоит из:
- **Backend**: Spring Boot REST API
- **Frontend**: Современный HTML/CSS/JavaScript интерфейс
- **Database**: PostgreSQL в Docker контейнере
- **Инфраструктура**: Docker Compose для оркестрации

## ✨ Особенности

✅ **Полный CRUD функционал** - создание, чтение, обновление, удаление пользователей  
✅ **Современный веб-интерфейс** - адаптивный дизайн с градиентами и анимациями  
✅ **REST API** - чистые RESTful endpoints для интеграции  
✅ **Docker контейнеризация** - легкое развертывание и изоляция  
✅ **PostgreSQL** - надежная реляционная база данных  
✅ **Статистика в реальном времени** - отслеживание пользователей и возраста  
✅ **Поиск пользователей** - по username и email  
✅ **Валидация данных** - на стороне клиента и сервера  
✅ **Уведомления** - интерактивные всплывающие сообщения  

## 🏗️ Архитектура
```bash
┌─────────────────────────────────────────────────────────┐
│ Веб-браузер │
│ http://localhost:8080/index.html │
└───────────────┬─────────────────────────────────────────┘
│ HTTP/HTTPS
▼
┌─────────────────────────────────────────────────────────┐
│ Spring Boot Application │
│ (Port: 8080) │
├─────────────────────────────────────────────────────────┤
│ ┌──────────┐ ┌──────────┐ ┌──────────┐ │
│ │Контроллер│ │ Сервис │ │Репозиторий│ │
│ └──────────┘ └──────────┘ └──────────┘ │
└───────────────┬─────────────────────────────────────────┘
│ JDBC
▼
┌─────────────────────────────────────────────────────────┐
│ PostgreSQL Database │
│ (Port: 5433) │
│ Docker Container │
└─────────────────────────────────────────────────────────┘
```

## 🚀 Быстрый старт

### Предварительные требования
- Java 17+
- Docker & Docker Compose
- Maven 3.6+
- Git

### Клонирование репозитория
```bash
git clone <repository-url>
cd person-management-system
```
### Запуск базы данных (Docker)
```bash
docker-compose up -d
```
### Настройка приложения (properties)
```bash
spring.datasource.url=jdbc:postgresql://localhost:5433/app
spring.datasource.username=app
spring.datasource.password=pass
spring.jpa.hibernate.ddl-auto=update
```
### Доступ к приложению
```bash
Веб-интерфейс: http://localhost:8080/
База данных: localhost:5433 (через DBeaver/psql)
```

## 🔧 Технологии
### Backend
```bash
Java 17 - основной язык программирования

- Spring Boot 3.1.5 - фреймворк для создания приложений
- Spring Data JPA - работа с базой данных
- Spring Web - REST API
- PostgreSQL Driver - подключение к БД
```
### Frontend
```bash
- HTML5 - структура веб-страницы
- CSS3 - стилизация с градиентами и анимациями
```
### Инфраструктура
```bash
- Docker - контейнеризация базы данных
- Docker Compose - оркестрация контейнеров
- PostgreSQL 15 - реляционная база данных
- Maven - управление зависимостями и сборка
```
## 📁 Структура проекта
```bash
src/main/java/com/example/demo/
├── controller/              # REST контроллеры
│   └── PersonController.java
├── model/                   # Сущности JPA
│   └── Person.java
├── repository/              # Spring Data JPA репозитории
│   └── PersonRepository.java
├── service/                 # Бизнес-логика
│   └── PersonService.java
└── DemoApplication.java     # Главный класс Spring Boot

src/main/resources/
├── static/                  # Статические файлы
│   └── index.html          # Веб-интерфейс
├── application.properties   # Конфигурация
└── templates/              # (опционально) Thymeleaf шаблоны

docker-compose.yml           # Docker Compose конфигурация
pom.xml                     # Maven зависимости
```
## 🔌 API Endpoints
```bash
- POST	/save/person	Создать нового пользователя	
- GET	/person/all	Получить всех пользователей	-
- GET	/person/search/username?username={name}	Найти по username	
- GET	/person/search/email?email={email}	Найти по email	
- PUT	/person/{id}	Полное обновление пользователя	
- PATCH	/person/{id}	Частичное обновление	
- DELETE	/person/{id}	Удалить пользователя
```
## 💻 Веб-интерфейс
### 📊 Панель статистики
```bash
- Общее количество пользователей
- Средний возраст
- Время последнего обновления
```
### 👥 Добавление пользователей
```bash
- Форма с валидацией полей
- Автоматическое обновление списка
- Визуальная обратная связь
```
### 🔍 Поиск пользователей
```bash
- По username (имя пользователя)
- По email (электронная почта)
- Подсветка найденных записей
```
### 📋 Список всех пользователей
```bash
- Таблица с сортировкой
- Цветные бейджи возраста
- Аватары пользователей
- Кнопки действий (удаление)
```
### 🎨 Дизайн
```bash
- Современный градиентный дизайн
- Адаптивная верстка для мобильных
- Плавные анимации
- Уведомления в реальном времени
```
## 🐳 Docker конфигурация
```bash
version: '3.8'
services:
  postgres:
    image: postgres:15
    container_name: postgres
    restart: always
    environment:
      POSTGRES_DB: app
      POSTGRES_USER: app
      POSTGRES_PASSWORD: pass
    ports:
      - "5433:5432"  # Внешний порт:5433 → внутренний порт:5432
    volumes:
      - pg_data:/var/lib/postgresql/data
    networks:
      - backend

volumes:
  pg_data:

networks:
  backend:
    driver: bridge
```
## 🔐 Безопасность
```bash
⚠️ Для учебных/тестовых целей - пароли хранятся в открытом виде.
```
## ⭐ Если вам понравился этот проект, поставьте звезду на GitHub!
### Построено с ❤️ используя Spring Boot, PostgreSQL и Docker
## 👨‍💻 Автор

**💼 Dmitry Nesmeyanov**
