# Spring Boot + PostgreSQL Project

This project is a **backend service** built with **Spring Boot** and **PostgreSQL** as the relational database. It leverages modern Java (targeting Java 23) and aims to deliver scalable APIs, with Docker support for containerized deployment.

## 🚀 Getting Started

These instructions will help you run the project locally or within a Docker container.

### Prerequisites

Make sure you have the following tools installed:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- Java 23 (JDK)

### ⚙️ Run the Project

To run the application using Docker:

```bash
docker-compose up
```

This command will:

1. Spin up the **Spring Boot application**.
2. Start a **PostgreSQL database** as a container.
3. Automatically link the application to the database.

---

## 🗂 ER Diagram

Below is the **ER diagram** representing the relational structure of the PostgreSQL database used in this project:

![ER Diagram](https://github.com/user-attachments/assets/9ece30eb-e2b3-4b6a-862b-13840186f240)

---

## 📂 Project Structure

```
/src
  ├── main
  │   ├── java
  │   │   └── com.example            # Java source code
  │   │       ├── controller         # API Controllers
  │   │       ├── service            # Business Logic
  │   │       └── repository         # Database Access Layer
  │   └── resources
  │       ├── application.yml        # Application Configuration
  │       └── schema.sql             # Database Schema Initialization
/docker-compose.yml                  # Docker Compose Configuration
```

---

## 🔧 Configuration

- **Database**: PostgreSQL  
  Default configuration is provided in `application.yml`. Modify if needed.

- **Port**:  
  The application runs on `http://localhost:8080`.

---

## 📜 API Documentation

- **Base URL**: `http://localhost:8080`
- Example API Routes:
  - `GET /shools` - Retrieve all School
  - `POST /schools` - Create a new School
  - `GET /Students` - Retrieve all Students
  - `POST /students` - Create a new Student
  - `PUT /students/{id}` - Update an item
  - `DELETE /students/{id}` - Delete a existing student

---

## 🤝 Contributing

Contributions are welcome! If you'd like to contribute, follow these steps:

1. Fork the repository.
2. Create a feature branch: `git checkout -b feature-name`.
3. Commit changes: `git commit -m 'Add some feature'`.
4. Push to the branch: `git push origin feature-name`.
5. Open a Pull Request.

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 🛠 Technologies Used

- **Spring Boot**
- **PostgreSQL**
- **Java 23**
- **Docker & Docker Compose**



