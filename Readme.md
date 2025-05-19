# 🔐 Authentication & Donation API

This project provides a backend REST API for user authentication (email/password and Google OAuth2) and donation tracking. Built with Spring Boot.

---

## 🌐 Base URL

```
http://localhost:8080
```

---

## 📌 Authentication Endpoints

### 1. 📝 Signup (Register)

* **POST** `/api/auth/signup`

#### Example Request Body

```json
{
  "email": "kunal@example.com",
  "password": "yourpassword"
}
```

---

### 2. 🔐 Normal Login

* **POST** `/api/auth/login`

#### Example Request Body

```json
{
  "email": "kunal@example.com",
  "password": "yourpassword"
}
```

---

### 3. 🔓 Google OAuth2 Login

* **POST** `/oauth2/authorization/google`

#### Example Request Body

```json
{
  "token": "your-google-id-token"
}
```

---

## 👤 User Details

* **GET** `/api/users/me`

Retrieves the authenticated user's profile.

#### Headers:

```
Authorization: Bearer <your_jwt_token>
```

#### Example Response:

```json
{
  "id": 1,
  "email": "kunal@example.com",
  "fullName": "Kunal Sangwan",
  "role": "USER"
}
```

---

## 💸 Donation Endpoints

### 1. 📤 Make a Donation

* **POST** `/api/donations`

#### Example Request Body:

```json
{
  "amount": 500,
  "message": "Keep up the good work!",
  "donationType": "FOOD"
}
```

#### Headers:

```
Authorization: Bearer <your_jwt_token>
```

---

### 2. 📥 Get All Donations (User-specific)

* **GET** `/api/donations`

#### Example Response:

```json
[
  {
    "id": 101,
    "amount": 500,
    "message": "Keep up the good work!",
    "donationType": "FOOD",
    "date": "2025-05-18T10:15:30"
  },
  {
    "id": 102,
    "amount": 200,
    "message": "Blessings!",
    "donationType": "MEDICINE",
    "date": "2025-05-19T09:00:00"
  }
]
```

---

## 📬 Postman Collection

Access all these endpoints quickly via the Postman collection:

🔗 [Postman Workspace](https://postman.co/workspace/My-Workspace~e6ca4964-033c-44b2-8e56-b2a082ca4a9b/collection/40917538-9807b6c0-74cd-45d6-a9ee-92386de20623?action=share&creator=40917538)

> 💡 Make sure to set your JWT token in the **Authorization** header for protected routes.

---

## 🏗 Tech Stack

* Java 17
* Spring Boot
* Spring Security
* OAuth2 (Google)
* MySQL
* JWT for stateless auth

---

## 📂 Project Structure (Highlights)

```
├── controller/
│   └── AuthController.java
│   └── DonationController.java
│   └── UserController.java
├── service/
│   └── AuthService.java
│   └── DonationService.java
├── model/
│   └── User.java
│   └── Donation.java
├── repository/
│   └── UserRepository.java
│   └── DonationRepository.java
```

---

## 🚀 Getting Started

1. Clone the repo
2. Configure your database in `application.properties`
3. Run the Spring Boot app
4. Use Postman to test APIs

---

## 🤝 Contributing

Feel free to fork this repo, raise issues, or open pull requests for improvements or bug fixes.

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
