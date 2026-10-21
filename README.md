# Workout Tracker API

A REST API for logging workouts, built with Java and Spring Boot. You can create, view, update and delete workouts, each with a title, date, muscle group and notes.

## Tech stack

- Java 17
- Spring Boot 4 (Spring Web MVC, Spring Data JPA)
- H2 in-memory database
- JUnit and Mockito for testing
- Maven

## Project structure

I split the application into layers so that each part has one job.

The controller (`WorkoutController`) handles the incoming requests and passes them to the service. The service (`WorkoutService`) holds the logic, such as checking a workout exists before it gets updated or deleted. The repository (`WorkoutRepository`) extends Spring Data JPA's `JpaRepository`, which gives me the database methods like `save` and `findById` without having to write them myself. Finally, the entity (`Workout`) defines what a workout is: an ID, title, date, muscle group and notes.

## Running the project

Requirements: Java 17 or later and Maven.

```bash
mvn spring-boot:run
```

If you don't have Maven installed, use the included wrapper instead: `./mvnw spring-boot:run`.

The API runs at `http://localhost:8080`. The database is in memory, so data is cleared when the application stops.

## Endpoints

| Method | Endpoint         | Description             |
|--------|------------------|-------------------------|
| GET    | `/workouts`      | List all workouts       |
| GET    | `/workouts/{id}` | Get a workout by ID     |
| POST   | `/workouts`      | Create a workout        |
| PUT    | `/workouts/{id}` | Update a workout        |
| DELETE | `/workouts/{id}` | Delete a workout        |

You can try the endpoints with an API tool such as Postman.

### Example: create a workout

Send a `POST` request to `http://localhost:8080/workouts` with this JSON body:

```json
{
  "title": "Push day",
  "date": "2026-10-20",
  "muscleGroup": "Chest",
  "notes": "Bench press 3x8"
}
```

The API returns the saved workout, including the ID it was given:

```json
{
  "id": 1,
  "title": "Push day",
  "date": "2026-10-20",
  "muscleGroup": "Chest",
  "notes": "Bench press 3x8"
}
```

## Running the tests

```bash
mvn test
```

## Planned improvements

- Create an exercise entity using a relational database model including weights, sets and reps

## Licence

MIT