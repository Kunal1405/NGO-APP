# Authentication API Endpoints

Base URL: `http://localhost:8080`

---

## 1. Signup (Register)

- **POST** `http://localhost:8080/api/auth/signup`

### Example Request Body
```json
{
  "email": "kunal@example.com",
  "password": "yourpassword"
}
```

---

## 2. Normal Login

- **POST** `http://localhost:8080/api/auth/login`

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

## Postman link
`https://.postman.co/workspace/My-Workspace~e6ca4964-033c-44b2-8e56-b2a082ca4a9b/collection/40917538-9807b6c0-74cd-45d6-a9ee-92386de20623?action=share&creator=40917538`

