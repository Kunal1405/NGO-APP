# Authentication API Endpoints

Base URL: `http://localhost:8080`

---

## 1. Signup (Register)

- **POST** `http://localhost:8080/auth/signup`

### Example Request Body
```json
{
  "email": "kunal@example.com",
  "password": "yourpassword"
}
```

---

## 2. Normal Login

- **POST** `http://localhost:8080/auth/login`

### Example Request Body
```json
{
  "email": "kunal@example.com",
  "password": "yourpassword"
}
```

---

## 3. Google OAuth2 Login

- **POST** `http://localhost:8080/oauth2/authorization/google`

### Example Request Body
```json
{
  "token": "your-google-id-token"
}
```

---

