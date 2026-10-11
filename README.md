# GameApp — Video Game Catalog & Reviews

A Java web application for browsing video games, managing a personal wishlist, and sharing reviews. Built using **Spring Boot**, **Thymeleaf**, **Spring Security**, and **PostgreSQL**.

This project demonstrates a server-rendered web application with user accounts, persistent data, game management, and role-based dashboards.

## Features

### Game catalog
- Browse the list of video games and open individual game detail pages.
- View game information including title, description, release date, genre, platform, developer, and publisher.
- Display optional cover image and YouTube trailer links when provided.
- See reviews on a game detail page, grouped into reviewer and regular user reviews.

### User accounts and wishlist
- Register an account and sign in with a username and password.
- Access a user dashboard.
- Add games to a personal wishlist, view saved games, and remove them.
- Prevent duplicate wishlist entries through an existing-entry check.

### Reviews
- Submit a review for a game.
- View reviews attached to each game and a list of your own reviews.
- Distinguish reviews from `REVIEWER` accounts and regular users.

### Administrative screens
- Add video games to the catalog.
- View the games catalog through an administrative page.
- View user accounts, create users, and promote users to the `REVIEWER` role.

> **Security note:** The code defines `USER`, `REVIEWER`, and `ADMIN` roles and provides dedicated dashboards. However, the current `SecurityConfig` only requires authentication for non-public routes; it does **not** yet restrict `/admin/**` endpoints to administrators. Role-based authorization should be tightened before any public deployment.

## Technology stack

| Area | Technologies |
| --- | --- |
| Language | Java 17 |
| Web framework | Spring Boot 3.5.3, Spring MVC |
| Views | Thymeleaf, HTML, CSS |
| Authentication | Spring Security, BCrypt password hashing, HTTP sessions |
| Persistence | PostgreSQL, Spring Data JPA / Hibernate |
| Build tools | Maven, Maven Wrapper |
| Other | Lombok |

## Project structure

The Spring Boot application is in the [`gameapp/`](gameapp/) directory.

```text
AppForGames/
├── README.md
└── gameapp/
    ├── src/main/java/com/example/gameapp/
    │   ├── controllers/       # HTTP requests and page routing
    │   ├── models/            # Game, User, Review, Wishlist entities and roles
    │   ├── repositories/      # Spring Data JPA interfaces
    │   ├── services/          # Application and user operations
    │   ├── SecurityConfig.java
    │   └── GameappApplication.java
    ├── src/main/resources/
    │   ├── static/css/       # Page styling
    │   ├── templates/        # Thymeleaf HTML views
    │   └── application.properties
    ├── src/test/
    ├── pom.xml
    ├── mvnw
    └── mvnw.cmd
```

### Data model

- **Game:** game metadata, cover image URL, and trailer URL.
- **User:** application account and user role (`USER`, `REVIEWER`, or `ADMIN`).
- **Review:** a user's review of a game, with a marker indicating whether the author has the `REVIEWER` role.
- **Wishlist:** relationship between a user and a saved game.

## Application architecture

```text
Browser
   |
   | HTTP requests
   v
Spring MVC Controllers ----> Thymeleaf templates (HTML)
   |
   v
Services / application logic
   |
   v
Spring Data JPA Repositories
   |
   v
PostgreSQL database
```

Spring Security handles account authentication, while BCrypt hashes passwords before storage. The application renders HTML on the server using Thymeleaf rather than a separate JavaScript frontend.

## Running locally

**Requirements:** Java 17, PostgreSQL, and access to Maven dependencies. Maven Wrapper scripts are included.

1. Clone the repository:

   ```bash
   git clone https://github.com/Ela1009/AppForGames.git
   cd AppForGames/gameapp
   ```

2. Create a local PostgreSQL database named `gameapp`.

3. Open `src/main/resources/application.properties` and update the datasource username and password for your **local development environment**. The current file uses a hard-coded example development password; do not reuse it for any other service or public deployment.

4. Start the application:

   **Windows (PowerShell)**
   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

   **macOS / Linux**
   ```bash
   ./mvnw spring-boot:run
   ```

5. Visit **http://localhost:8081** 


## Project context

A learning project focused on building a database-backed Java web application with Spring MVC, Thymeleaf, Spring Data JPA, authentication, and account-specific functionality.
