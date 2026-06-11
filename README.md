# Enterprise Hotel Management System

## Project Overview

Enterprise Hotel Management System (HMS) is a console-based Java application designed to simulate real-world hotel operations.

The application follows a layered architecture and uses:

- Java 21
- JPA (Hibernate ORM)
- MySQL Database
- Maven
- JDBC (for selected modules as per PRD)

The system supports room management, customer management, booking operations, payment processing, reporting, and role-based access control.

---

## Technology Stack

| Technology | Version |
|------------|----------|
| Java | 21 |
| Maven | Latest |
| Hibernate ORM | 6.x |
| Jakarta Persistence API | 3.x |
| MySQL | 8.x |
| JDBC | MySQL Connector |

---

## Project Architecture

```text
Console UI
    │
    ▼
Controller Layer
    │
    ▼
Service Layer
    │
    ▼
Repository Layer
    │
 ┌──┴───────────────┐
 ▼                  ▼
JDBC            Hibernate/JPA
 │                  │
 └──────► MySQL ◄───┘
```

---

## Features

### User Management

- Customer Registration
- User Login
- Password Hashing
- Role-Based Access Control
    - Admin
    - Receptionist
    - Customer

### Room Management

- Add Room
- Update Room
- Delete Room
- View Rooms
- Room Availability Tracking

Room Types:

- STANDARD
- DELUXE
- SUITE

Room Status:

- AVAILABLE
- OCCUPIED
- MAINTENANCE

### Booking Management

- Create Booking
- Cancel Booking
- Check Room Availability
- Prevent Double Booking
- Booking Lifecycle Management

Booking Status:

- PENDING
- CONFIRMED
- CANCELLED
- CHECKED_OUT

### Payment Management

- Generate Bill
- Process Payment
- Payment Tracking

Payment Status:

- PENDING
- COMPLETED
- FAILED
- REFUNDED

### Reporting Module

- Daily Revenue Report
- Occupancy Report
- Customer Booking History

---

## Database Schema

### Customers

| Column | Type |
|----------|----------|
| customer_id | BIGINT |
| name | VARCHAR |
| email | VARCHAR |
| phone | VARCHAR |
| password_hash | VARCHAR |

### Rooms

| Column | Type |
|----------|----------|
| room_id | BIGINT |
| room_number | VARCHAR |
| room_type | ENUM |
| price_per_night | DECIMAL |
| status | ENUM |

### Bookings

| Column | Type |
|----------|----------|
| booking_id | BIGINT |
| customer_id | BIGINT |
| room_id | BIGINT |
| check_in_date | DATE |
| check_out_date | DATE |
| booking_status | ENUM |

### Payments

| Column | Type |
|----------|----------|
| payment_id | BIGINT |
| booking_id | BIGINT |
| amount | DECIMAL |
| payment_date | TIMESTAMP |
| payment_status | ENUM |

---

## Entity Relationships

```text
Customer
    │
    │ One-To-Many
    ▼
Booking
    │
    ├── Many-To-One ──► Room
    │
    └── One-To-One ───► Payment
```

---

## Project Structure

```text
src/main/java

org.example

├── config
│
├── controller
│
├── service
│
├── repository
│   ├── jdbc
│   └── jpa
│
├── entity
│   ├── Customer
│   ├── Room
│   ├── Booking
│   └── Payment
│
├── enums
│   ├── RoomType
│   ├── RoomStatus
│   ├── BookingStatus
│   └── PaymentStatus
│
├── exception
│
└── Main
```

---

## Setup Instructions

### 1. Clone Project

```bash
git clone <repository-url>
```

### 2. Create Database

```sql
CREATE DATABASE hotel_db;
```

### 3. Configure Database

Update:

```text
src/main/resources/META-INF/persistence.xml
```

Example:

```xml
<property name="jakarta.persistence.jdbc.url"
          value="jdbc:mysql://localhost:3306/hotel_db"/>

<property name="jakarta.persistence.jdbc.user"
          value="root"/>

<property name="jakarta.persistence.jdbc.password"
          value="root"/>
```

### 4. Build Project

```bash
mvn clean install
```

### 5. Run Application

```bash
mvn exec:java
```

or run

```text
Main.java
```

from IntelliJ IDEA.

---

## Future Enhancements

- Spring Boot Migration
- REST API Support
- JWT Authentication
- Email Notifications
- PDF Invoice Generation
- Dashboard UI
- Docker Deployment

---

## Learning Outcomes

This project demonstrates:

- Core Java
- OOP Principles
- Collections Framework
- Exception Handling
- JDBC
- JPA/Hibernate
- MySQL Database Design
- Layered Architecture
- Concurrency Concepts
- Enterprise Coding Standards

---

## Author

Mohan Babu

Enterprise Hotel Management System
Java + Hibernate + MySQL
