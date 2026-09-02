# Homes-merry Backend — Industry Standards Guide

> **📖 Open the styled version:** [`BACKEND_INDUSTRY_STANDARDS.html`](./BACKEND_INDUSTRY_STANDARDS.html) — open in any browser for the full interactive guide with sidebar navigation, color-coded status badges, and code examples.

> Reference document for bringing the Homes-merry e-commerce backend to production-grade quality.
> Last updated: August 2026

---

## Table of Contents

1. [Current State (What You Have)](#1-current-state-what-you-have)
2. [Priority 1 — Production Basics](#2-priority-1--production-basics)
3. [Priority 2 — E-Commerce Features](#3-priority-2--e-commerce-features)
4. [Priority 3 — Security Hardening](#4-priority-3--security-hardening)
5. [Priority 4 — Performance & Scale](#5-priority-4--performance--scale)
6. [Priority 5 — DevOps & Deployment](#6-priority-5--devops--deployment)
7. [Priority 6 — API Design Polish](#7-priority-6--api-design-polish)
8. [Exception Handling (Full Guide)](#8-exception-handling-full-guide)
9. [DTO vs Entity Strategy](#9-dto-vs-entity-strategy)
10. [Elasticsearch Integration](#10-elasticsearch-integration)
11. [Recommended Roadmap](#11-recommended-roadmap)
12. [Quick Wins Checklist](#12-quick-wins-checklist)

---

## 1. Current State (What You Have)

| Area | Status | Notes |
|------|--------|-------|
| Layered architecture | ✅ Done | Controller → Service → Repository |
| DTOs + MapStruct | ✅ Done | Clean separation from entities |
| JWT auth + RBAC | ✅ Done | ADMIN, ENTERPRISE roles |
| Global exception handler | ⚠️ Partial | See [Section 8](#8-exception-handling-full-guide) |
| Request validation (`@Valid`) | ✅ Done | On request DTOs |
| Domain modules | ✅ Done | product, category, user |
| Basic request logging | ✅ Done | `RequestLoggingFilter` |
| API versioning | ✅ Done | `/api/v1/...` |
| Pagination | ❌ Missing | List endpoints return all records |
| API documentation | ❌ Missing | No Swagger/OpenAPI |
| Database migrations | ❌ Missing | Using `ddl-auto: update` |
| Tests | ❌ Missing | Only `contextLoads` smoke test |
| Secrets management | ❌ Missing | Credentials hardcoded in yaml/source |
| Search | ❌ Missing | Elasticsearch planned |

---

## 2. Priority 1 — Production Basics

### 2.1 Environment & Secrets Management

**Problem today:**
- DB password in `application.yaml`
- JWT secret hardcoded in `JwtUtil.java`

**Industry standard:**
- Use Spring profiles: `application-dev.yaml`, `application-prod.yaml`
- Load secrets from environment variables:
  ```bash
  SPRING_DATASOURCE_PASSWORD=...
  JWT_SECRET=...
  ```
- On AWS EC2: use **Secrets Manager** or **Parameter Store**
- Never commit secrets to git

**Example `application-prod.yaml`:**
```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

jwt:
  secret: ${JWT_SECRET}
  expiration-ms: 3600000
```

---

### 2.2 Database Migrations (Flyway / Liquibase)

**Problem today:**
```yaml
jpa:
  hibernate:
    ddl-auto: "update"   # NOT safe for production
```

**Industry standard:** Use **Flyway** or **Liquibase**
- Versioned SQL migration files in `src/main/resources/db/migration/`
- Reproducible schema across dev, staging, prod
- Safe team collaboration and rollbacks

**Example Flyway file:** `V1__create_product_table.sql`

---

### 2.3 Pagination

**Problem today:** `getAllProducts()` returns every record — breaks at scale.

**Industry standard:**
```http
GET /api/v1/products?page=0&size=20&sort=offering_name,asc
```

**Implementation:**
```java
// Controller
public Page<Prod_Data_Res_DTO> getAllProducts(Pageable pageable)

// Repository
Page<ProdData> findAll(Pageable pageable);
```

Apply to: products, primary categories, secondary categories.

---

### 2.4 API Documentation (OpenAPI / Swagger)

**Dependency:**
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```

**Access:** `http://localhost:8080/swagger-ui.html`

Benefits:
- Auto-generated interactive API docs
- Frontend team can test endpoints without guessing payloads
- Required in most professional teams

---

### 2.5 Health & Monitoring (Spring Actuator)

**Dependency:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

**Endpoints (secure in production):**
| Endpoint | Purpose |
|----------|---------|
| `/actuator/health` | Load balancer / AWS health checks |
| `/actuator/metrics` | JVM, HTTP, DB pool stats |
| `/actuator/info` | App version, build info |

---

### 2.6 CORS Configuration

Required when a frontend (React/Next.js) calls the API from a different domain.

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("https://your-frontend.com")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

---

### 2.7 Tests

**Current:** Only 1 smoke test (`contextLoads`).

**Industry standard:**

| Type | Tool | What to test |
|------|------|--------------|
| Unit tests | JUnit 5 + Mockito | Services, mappers |
| Controller tests | `@WebMvcTest` | HTTP status, JSON response |
| Repository tests | `@DataJpaTest` | Custom queries |
| Integration tests | Testcontainers | Full flow with real MySQL |

**Target:** 60–80% coverage on service layer minimum.

---

## 3. Priority 2 — E-Commerce Features

Current backend is a **product catalog + admin API**. Full e-commerce requires:

| Module | Purpose | Status |
|--------|---------|--------|
| **Search** | Elasticsearch product search | Planned |
| **Cart** | Add/remove items, session or user-based | ❌ |
| **Orders** | Checkout flow, order status tracking | ❌ |
| **Payments** | Razorpay / Stripe integration | ❌ |
| **Inventory locking** | Prevent overselling during checkout | ❌ |
| **Wishlist** | Save products for later | ❌ |
| **Reviews & ratings** | Social proof on products | ❌ |
| **Coupons / discounts** | Promo codes | ❌ |
| **Notifications** | Order confirmation email/SMS | ❌ |
| **File upload** | Product images → AWS S3 (not DB blobs) | ❌ |

---

## 4. Priority 3 — Security Hardening

| Item | Why | Status |
|------|-----|--------|
| Secrets in env vars | Prevent credential leaks | ❌ |
| JWT refresh tokens | Short-lived access (15 min) + long refresh | ❌ |
| Rate limiting | Prevent brute-force on `/api/auth/login` | ❌ |
| HTTPS only | Terminate SSL at ALB/nginx on EC2 | ❌ |
| Input sanitization | Prevent XSS in `long_desc`, search queries | ❌ |
| Audit fields | `createdAt`, `updatedAt`, `createdBy` on entities | ❌ |
| Soft delete | `deletedAt` instead of hard delete | ❌ |
| Hide 500 internals | Don't expose `ex.getMessage()` to clients | ❌ |

---

## 5. Priority 4 — Performance & Scale

| Item | When to add |
|------|-------------|
| **Redis caching** | Cache categories, featured products |
| **CDN for images** | CloudFront + S3 for product media |
| **DB indexes** | On `sku_id`, `offering_name`, category FKs |
| **Connection pooling** | HikariCP tuning in prod |
| **Async processing** | `@Async` or SQS for emails, ES indexing |
| **Pagination** | All list endpoints (Priority 1) |

---

## 6. Priority 5 — DevOps & Deployment

| Item | Purpose |
|------|---------|
| **Dockerfile** | Consistent Spring Boot deploys on EC2 |
| **docker-compose.yml** | App + MySQL + Elasticsearch locally |
| **CI/CD (GitHub Actions)** | Auto test + build on every push |
| **Structured logging** | JSON logs → AWS CloudWatch |
| **README** | Setup instructions, env vars, API overview |
| **Environment profiles** | dev / staging / prod configs |

---

## 7. Priority 6 — API Design Polish

### 7.1 RESTful URL Conventions

| Current | REST Standard |
|---------|---------------|
| `POST /createProduct` | `POST /api/v1/products` |
| `GET /getProduct/{id}` | `GET /api/v1/products/{id}` |
| `PUT /updateProduct/{id}` | `PUT /api/v1/products/{id}` |
| `DELETE /deleteProduct/{id}` | `DELETE /api/v1/products/{id}` |

> Note: Current URLs work fine — migrate gradually without breaking existing clients.

### 7.2 Standard Response Wrapper

```json
{
  "success": true,
  "data": { ... },
  "message": "Product created successfully",
  "timestamp": "2026-08-22T15:38:00"
}
```

For paginated lists:
```json
{
  "success": true,
  "data": [ ... ],
  "pagination": {
    "page": 0,
    "size": 20,
    "totalElements": 150,
    "totalPages": 8
  }
}
```

---

## 8. Exception Handling (Full Guide)

### 8.1 Current State

```
Exception/
├── GlobalExceptionHandler.java   ✅
└── ResourceNotFoundException.java ✅
```

| Handler | HTTP Status | Status |
|---------|-------------|--------|
| `ResourceNotFoundException` | 404 | ✅ Done |
| `MethodArgumentNotValidException` | 400 | ✅ Done |
| `IllegalArgumentException` | 400 | ✅ Done |
| `DataIntegrityViolationException` | 400 | ⚠️ Should be 409 |
| `Exception` (catch-all) | 500 | ⚠️ Leaks internal messages |
| Security exceptions (401/403) | — | ❌ Missing |
| Malformed JSON / bad path vars | — | ❌ Missing |

---

### 8.2 Target Exception Hierarchy

```
Exception/
├── GlobalExceptionHandler.java
├── ApiException.java                  (abstract base)
├── ErrorCode.java                     (enum of all error codes)
├── ResourceNotFoundException.java     (404)
├── DuplicateResourceException.java    (409 — SKU already exists)
├── BusinessRuleException.java         (422 — business logic failed)
├── UnauthorizedException.java         (401)
├── ForbiddenException.java            (403)
└── dto/
    ├── ApiErrorResponse.java
    └── ValidationErrorResponse.java
```

---

### 8.3 ErrorCode Enum

```java
public enum ErrorCode {
    // 400
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "VALIDATION_FAILED"),
    INVALID_REQUEST_BODY(HttpStatus.BAD_REQUEST, "INVALID_REQUEST_BODY"),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "INVALID_PARAMETER"),

    // 401 / 403
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "ACCESS_DENIED"),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS"),

    // 404
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "PRODUCT_NOT_FOUND"),
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CATEGORY_NOT_FOUND"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND"),

    // 409
    SKU_DUPLICATE(HttpStatus.CONFLICT, "SKU_DUPLICATE"),
    DATA_INTEGRITY_VIOLATION(HttpStatus.CONFLICT, "DATA_INTEGRITY_VIOLATION"),

    // 422
    BUSINESS_RULE_VIOLATION(HttpStatus.UNPROCESSABLE_ENTITY, "BUSINESS_RULE_VIOLATION"),
    INSUFFICIENT_STOCK(HttpStatus.UNPROCESSABLE_ENTITY, "INSUFFICIENT_STOCK"),

    // 500
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR");

    private final HttpStatus status;
    private final String code;
}
```

---

### 8.4 Base Exception Class

```java
public abstract class ApiException extends RuntimeException {
    private final ErrorCode errorCode;

    protected ApiException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() { return errorCode; }
    public HttpStatus getStatus() { return errorCode.getStatus(); }
    public String getCode() { return errorCode.getCode(); }
}
```

---

### 8.5 Unified Error Response Format

**Standard error response (all non-validation errors):**
```json
{
  "status": 404,
  "error": "Not Found",
  "code": "PRODUCT_NOT_FOUND",
  "message": "Product not found with id: 5",
  "path": "/api/v1/products/getProduct/5",
  "traceId": "a1b2c3d4-e5f6-7890",
  "timestamp": "2026-08-22T15:38:00"
}
```

**Validation error response:**
```json
{
  "status": 400,
  "error": "Bad Request",
  "code": "VALIDATION_FAILED",
  "message": "Validation failed",
  "path": "/api/v1/products/createProduct",
  "traceId": "b2c3d4e5-f6a7-8901",
  "errors": {
    "offering_name": "Offering name cannot be empty",
    "sku_id": "SKU ID cannot be empty"
  },
  "timestamp": "2026-08-22T15:38:00"
}
```

**Response record:**
```java
public record ApiErrorResponse(
    int status,
    String error,
    String code,
    String message,
    String path,
    String traceId,
    LocalDateTime timestamp
) {}
```

---

### 8.6 Complete Handler Map (Target)

| Exception | HTTP Status | Priority |
|-----------|-------------|----------|
| `ResourceNotFoundException` | 404 | ✅ Have |
| `DuplicateResourceException` | 409 | ❌ Add |
| `BusinessRuleException` | 422 | ❌ Add |
| `MethodArgumentNotValidException` | 400 | ✅ Have |
| `ConstraintViolationException` | 400 | ❌ Add |
| `HttpMessageNotReadableException` | 400 | ❌ Add |
| `MethodArgumentTypeMismatchException` | 400 | ❌ Add |
| `MissingServletRequestParameterException` | 400 | ❌ Add |
| `IllegalArgumentException` | 400 | ✅ Have (reduce usage) |
| `BadCredentialsException` | 401 | ❌ Add |
| `AuthenticationException` | 401 | ❌ Add |
| `AccessDeniedException` | 403 | ❌ Add |
| `DataIntegrityViolationException` | 409 | ⚠️ Change from 400 |
| `Exception` (catch-all) | 500 | ⚠️ Fix message leakage |

---

### 8.7 Security Exception Handlers

Add to `GlobalExceptionHandler`:

```java
@ExceptionHandler(BadCredentialsException.class)
public ResponseEntity<ApiErrorResponse> handleBadCredentials(
        BadCredentialsException ex, HttpServletRequest request) {
    return buildError(ErrorCode.INVALID_CREDENTIALS, "Invalid email or password", request);
}

@ExceptionHandler(AccessDeniedException.class)
public ResponseEntity<ApiErrorResponse> handleAccessDenied(
        AccessDeniedException ex, HttpServletRequest request) {
    return buildError(ErrorCode.ACCESS_DENIED, "You do not have permission to access this resource", request);
}
```

Add `AuthenticationEntryPoint` in `SecurityConfig` for unauthenticated requests (returns 401 JSON, not HTML).

---

### 8.8 Logging Rules in Exception Handler

| HTTP Status | Log Level | Client Message |
|-------------|-----------|----------------|
| 400, 404, 409, 422 | `WARN` | Specific, helpful message |
| 401, 403 | `WARN` | Generic auth message |
| 500 | `ERROR` + full stack trace | Generic + traceId only |

**Never do this on 500:**
```java
// BAD — leaks SQL errors, file paths, internal details
"An unexpected error occurred: " + ex.getMessage()
```

**Do this instead:**
```java
// GOOD
log.error("[{}] Unhandled exception at {} {}", traceId, method, path, ex);
return "An unexpected error occurred. Contact support with traceId: " + traceId;
```

---

### 8.9 How to Throw in Service Layer

**Before (current — mixed types):**
```java
throw new IllegalArgumentException("Product with SKU ID already exists.");
.orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + prod_id));
```

**After (standard):**
```java
// Not found
.orElseThrow(() -> new ResourceNotFoundException(
    ErrorCode.PRODUCT_NOT_FOUND, "Product not found with id: " + prod_id));

// Duplicate SKU → 409 Conflict
throw new DuplicateResourceException(
    ErrorCode.SKU_DUPLICATE, "Product with SKU '" + sku + "' already exists");

// Business rule → 422
throw new BusinessRuleException(
    ErrorCode.INSUFFICIENT_STOCK, "Not enough inventory for product " + prod_id);
```

**Rule:** Controllers should have **zero try/catch** — all exceptions bubble to `GlobalExceptionHandler`.

---

### 8.10 RFC 7807 Problem Details (Optional Advanced)

Spring 6+ supports `ProblemDetail` natively for `application/problem+json`:

```json
{
  "type": "https://api.homesmerry.com/errors/product-not-found",
  "title": "Product Not Found",
  "status": 404,
  "detail": "Product with id 5 does not exist",
  "instance": "/api/v1/products/5"
}
```

---

### 8.11 Exception Handling Implementation Order

```
Step 1 → Unified ApiErrorResponse record (status, code, message, path, traceId, timestamp)
Step 2 → ErrorCode enum
Step 3 → ApiException base class
Step 4 → DuplicateResourceException (409) — replace SKU IllegalArgumentException
Step 5 → Security handlers (401, 403) + AuthenticationEntryPoint in SecurityConfig
Step 6 → HttpMessageNotReadableException + MethodArgumentTypeMismatchException
Step 7 → Logging + hide 500 internals + traceId
Step 8 → BusinessRuleException (422)
Step 9 → RFC 7807 ProblemDetail (optional polish)
```

---

## 9. DTO vs Entity Strategy

### Why separate DTOs from entities

| Concern | Entity (`ProdData`) | DTO (`Prod_Data_Req_DTO`) |
|---------|---------------------|---------------------------|
| DB persistence | ✅ | ❌ |
| API validation | ❌ | ✅ (`@NotBlank`, `@Valid`) |
| Hide internal fields | ❌ | ✅ (no `Internal` in response) |
| API stability | ❌ (changes with DB) | ✅ |

### Recommended pattern

| Operation | DTO | Notes |
|-----------|-----|-------|
| Create | Full `Req_DTO` with validation | All required fields |
| Update | Same `Req_DTO` + MapStruct `IGNORE` nulls | Partial update — only send changed fields |
| Response | `Res_DTO` | Hide sensitive/internal fields |
| List | `Res_DTO` (paginated) | Never return full entity |

### When adding a new field to `ProdData`

1. Add field to entity
2. Add to `Req_DTO` and/or `Res_DTO` as needed
3. MapStruct auto-maps if field names match
4. Add validation annotations on DTO if required

### What NOT to do

| Approach | Verdict |
|----------|---------|
| `Map<String, Object>` as request body | ❌ No validation, security risk |
| Use entity directly as `@RequestBody` | ❌ Exposes DB structure, mass assignment risk |
| Generics to avoid DTO updates | ❌ Doesn't work — generics still need concrete types |

### Small win — ignore unknown JSON fields

```java
@JsonIgnoreProperties(ignoreUnknown = true)
public record Prod_Data_Req_DTO(...) {}
```

Extra fields in client requests are silently ignored — prevents breakage when clients send old payloads.

---

## 10. Elasticsearch Integration

### Architecture

```
MySQL (source of truth)
    ↓ sync on create/update/delete
Elasticsearch (search index)
    ↑ query
SearchController → /api/v1/products/search?q=...
```

### Index fields (MVP)

| Field | ES Type | Search Weight |
|-------|---------|---------------|
| `offeringName` | text | Highest (^3) |
| `brand` | text | High (^2) |
| `tags` | text | Medium |
| `shortDesc` | text | Medium |
| `skuId` | keyword | Exact match only |
| `category` | keyword | Filter |
| `sellingPrice` | float | Filter + sort |
| `featuredOffer` | boolean | Boost |
| `primaryCategoryId` | long | Filter |

### Sync strategy

Hook `ProductIndexService` into `ProdDataService`:
```java
// After save in addProduct / updateProduct
productIndexService.indexProduct(saved);

// In deleteProduct
productIndexService.deleteProduct(prod_id);
```

### Search endpoint

```http
GET /api/v1/products/search?q=wooden+table&category=FURNITURE&minPrice=5000&maxPrice=50000&page=0&size=20&sort=relevance
```

### Do NOT also implement MySQL FULLTEXT

Elasticsearch replaces the search layer entirely. MySQL remains for CRUD only.

---

## 11. Recommended Roadmap

```
Phase 1 — Production Basics (do now)
├── Move secrets to environment variables
├── Flyway database migrations
├── Pagination on all list endpoints
├── Swagger / OpenAPI documentation
├── Actuator health check endpoint
├── Fix 500 error message leakage
├── CORS configuration
└── Exception handling upgrade (Section 8)

Phase 2 — E-Commerce Core
├── Elasticsearch search integration
├── Cart + Orders modules
├── AWS S3 image upload
├── Payment gateway (Razorpay / Stripe)
└── Email notifications

Phase 3 — Scale & Quality
├── Redis caching (categories, featured products)
├── Unit + integration tests (60%+ service coverage)
├── CI/CD pipeline (GitHub Actions)
├── JWT refresh tokens
└── Rate limiting on auth endpoints

Phase 4 — Polish
├── RESTful URL cleanup (non-breaking migration)
├── Audit fields (createdAt, updatedAt, createdBy)
├── Soft delete for products and orders
├── Reviews, wishlist, coupons
└── Monitoring (CloudWatch / Grafana)
```

---

## 12. Quick Wins Checklist

Do these this week for the biggest impact with least effort:

- [ ] Move JWT secret to environment variable (`JWT_SECRET`)
- [ ] Move DB credentials to environment variables
- [ ] Add `springdoc-openapi` → Swagger UI at `/swagger-ui.html`
- [ ] Add pagination to `getAllProducts` and `getAllCategories`
- [ ] Add Flyway + first migration script
- [ ] Add Actuator → expose `/actuator/health`
- [ ] Fix 500 handler — log server-side, return generic message + traceId
- [ ] Add `DuplicateResourceException` (409) for SKU conflicts
- [ ] Add 401/403 security exception handlers
- [ ] Add `@JsonIgnoreProperties(ignoreUnknown = true)` on request DTOs

---

## Reference Links

| Resource | URL |
|----------|-----|
| Spring Boot Docs | https://docs.spring.io/spring-boot/docs/current/reference/html/ |
| Spring Security | https://docs.spring.io/spring-security/reference/ |
| Flyway | https://flywaydb.org/documentation/ |
| Springdoc OpenAPI | https://springdoc.org/ |
| Elasticsearch Java Client | https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/ |
| RFC 7807 Problem Details | https://datatracker.ietf.org/doc/html/rfc7807 |
| AWS Secrets Manager | https://docs.aws.amazon.com/secretsmanager/ |

---

*Document maintained for the Homes-merry project. Update as features are implemented.*
