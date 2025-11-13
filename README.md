# Store Management API

A simple **Spring Boot (Java 17)** backend for managing products in a store.

---

## Features
- Add, list, find, and delete products  
- In-memory **H2 database**  
- Layered structure:
  ```
  common/
  config/
  service/
  controller/
  data/
      ├─ entity/
      └─ repository/
  ```

---

##  Run the application
```bash
./mvnw spring-boot:run
```

By default, it runs on **http://localhost:8080**

---

## API Endpoints
| Method | Endpoint | Description |
|:--|:--|:--|
| **POST** | `/api/products` | Create a new product |
| **GET** | `/api/products` | List all products |
| **GET** | `/api/products/{id}` | Get product by ID |
| **GET** | `/api/products/name/{name}` | Get product by name |
| **DELETE** | `/api/products/{id}` | Delete product |

---

## Quick Test (PowerShell)
```powershell
$Base = "http://localhost:8080/api/products"
$body = @{ name="Laptop"; price=2500.00; quantity=5 }
Invoke-RestMethod -Uri $Base -Method Post -ContentType "application/json" -Body ($body | ConvertTo-Json)
Invoke-RestMethod -Uri $Base -Method Get
```

---

## Database
- Embedded **H2**, auto-created on startup.  


---

## Tech stack
- Java 17  
- Spring Boot 3.x  
- Spring Web / Data JPA / Validation  
- Lombok (`@Slf4j`, `@RequiredArgsConstructor`)  
- H2 (in-memory)

---

## Next Steps
- Add `config/` package for **security + role-based access**  
- Add `common/` for **global error handling**  
- Add **unit tests**
