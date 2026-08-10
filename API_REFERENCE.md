# Product Domain — API Reference

Base URL: `http://localhost:8080`

---

## 1. Product (General)

> Base path: `/api/v1/products`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/products/createProduct` | Create a new product |
| GET | `/api/v1/products/getAllProducts` | Get all products |
| GET | `/api/v1/products/getProduct/{prod_id}` | Get product by ID |
| PUT | `/api/v1/products/updateProduct/{prod_id}` | Update product by ID |
| DELETE | `/api/v1/products/deleteProduct/{prod_id}` | Delete product by ID |

---

## 2. Pricing

> Base path: `/api/v1/pricing`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/pricing/createPricing` | Create a pricing record |
| GET | `/api/v1/pricing/getAllPricing` | Get all pricing records |
| GET | `/api/v1/pricing/getPricing/{price_id}` | Get pricing by ID |
| PUT | `/api/v1/pricing/updatePricing/{price_id}` | Update pricing by ID |
| DELETE | `/api/v1/pricing/deletePricing/{price_id}` | Delete pricing by ID |

---

## 3. Inventory

> Base path: `/api/v1/inventory`
> Key: `sku_Id` (natural business key)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/inventory/createInventory` | Create an inventory record |
| GET | `/api/v1/inventory/getAllInventory` | Get all inventory records |
| GET | `/api/v1/inventory/getInventory/{sku_Id}` | Get inventory by SKU |
| PUT | `/api/v1/inventory/updateInventory/{sku_Id}` | Update inventory by SKU |
| DELETE | `/api/v1/inventory/deleteInventory/{sku_Id}` | Delete inventory by SKU |

---

## 4. Sourcing & Logistics

> Base path: `/api/v1/sourcing`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/sourcing/createSourcing` | Create a sourcing record |
| GET | `/api/v1/sourcing/getAllSourcing` | Get all sourcing records |
| GET | `/api/v1/sourcing/getSourcing/{sl_id}` | Get sourcing record by ID |
| PUT | `/api/v1/sourcing/updateSourcing/{sl_id}` | Update sourcing record by ID |
| DELETE | `/api/v1/sourcing/deleteSourcing/{sl_id}` | Delete sourcing record by ID |

---

## 5. Media

> Base path: `/api/v1/media`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/media/createMedia` | Create a media record |
| GET | `/api/v1/media/getAllMedia` | Get all media records |
| GET | `/api/v1/media/getMedia/{media_id}` | Get media by ID |
| PUT | `/api/v1/media/updateMedia/{media_id}` | Update media by ID |
| DELETE | `/api/v1/media/deleteMedia/{media_id}` | Delete media by ID |

---

## 6. Specifications

> Base path: `/api/v1/specifications`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/specifications/createSpecifications` | Create a specifications record |
| GET | `/api/v1/specifications/getAllSpecifications` | Get all specifications |
| GET | `/api/v1/specifications/getSpecifications/{spec_id}` | Get specifications by ID |
| PUT | `/api/v1/specifications/updateSpecifications/{spec_id}` | Update specifications by ID |
| DELETE | `/api/v1/specifications/deleteSpecifications/{spec_id}` | Delete specifications by ID |

---

## 7. SEO

> Base path: `/api/v1/seo`
> Key: `url_slug` (natural business key)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/seo/createSEO` | Create an SEO record |
| GET | `/api/v1/seo/getAllSEO` | Get all SEO records |
| GET | `/api/v1/seo/getSEO/{url_slug}` | Get SEO record by slug |
| PUT | `/api/v1/seo/updateSEO/{url_slug}` | Update SEO record by slug |
| DELETE | `/api/v1/seo/deleteSEO/{url_slug}` | Delete SEO record by slug |

---

## 8. Internal

> Base path: `/api/v1/internal`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/internal/createInternal` | Create an internal record |
| GET | `/api/v1/internal/getAllInternal` | Get all internal records |
| GET | `/api/v1/internal/getInternal/{internal_id}` | Get internal record by ID |
| PUT | `/api/v1/internal/updateInternal/{internal_id}` | Update internal record by ID |
| DELETE | `/api/v1/internal/deleteInternal/{internal_id}` | Delete internal record by ID |

---

## Response Codes

| Code | Meaning |
|------|---------|
| 201 Created | Resource created successfully (POST) |
| 200 OK | Request successful (GET, PUT, DELETE) |
| 400 Bad Request | Validation failed — response body contains field-level errors |
| 404 Not Found | Resource not found for the given ID / key |
| 500 Internal Server Error | Unexpected server error |
