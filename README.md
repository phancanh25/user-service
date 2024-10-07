# Messaging Service Application

A Spring Boot application for managing chat rooms (individual and group), importing messages, handling user accounts, and ensuring message persistence for auditing and compliance purposes.

## Features
- **Create individual and group chat rooms**.
- **Import messages and attachments** into chat rooms.
- **Handle automatic chat room creation** if they do not exist upon message import.
- **Manage user accounts**: filter users by phone numbers, create new users.
- **Message persistence** for auditing and compliance purposes.

## Technologies
- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **MySQL** 
- **JUnit 5**
- **Mockito**
- **Lombok**

## Prerequisites
- **Java 11+**
- **Maven 2+**
- **PostgreSQL** (for production environment)

## Getting Started

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/phancanh25/user-service
   cd messaging-service
   git checkout master

 
