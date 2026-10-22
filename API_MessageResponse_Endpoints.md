# Complete API Documentation - Homes & Merry E-commerce Backend

This document contains comprehensive cURL examples for ALL 64+ REST API endpoints in the Homes & Merry e-commerce backend, with special focus on those using `MessageResponse<T>`.

## Base URL
```
http://localhost:8080
```

## Authentication
Most endpoints require JWT authentication. Include the token in the Authorization header:
```bash
-H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 1. User Management (`/api/v1`)

### Create User
**POST** `/api/v1/CreateUser`  
**Returns:** `MessageResponse<UserDataResponse>`  
**Access:** Public

```bash
curl -X POST http://localhost:8080/api/v1/CreateUser \
  -H "Content-Type: application/json" \
  -d '{
    "first_name": "John",
    "last_name": "Doe", 
    "email": "john.doe@example.com",
    "pincode": 560001,
    "phone_number": "9876543210",
    "brand_name": "John Interior Design",
    "role": "RETAIL_CUSTOMER",
    "address": {
      "city": "Bangalore",
      "state": "Karnataka", 
      "description": "123 Main Street, BTM Layout"
    },
    "password": "securePassword123",
    "gst_number": "29ABCDE1234F1Z5",
    "createdAt": "2024-01-15T10:30:00"
  }'
```

**Expected Response:**
```json
{
  "message": "John Doe's account was created successfully.",
  "data": {
    "id": 1,
    "first_name": "John",
    "last_name": "Doe",
    "email": "john.doe@example.com",
    "role": "RETAIL_CUSTOMER"
  }
}
```

---

## 2. Authentication (`/api/auth`)

### Login User
**POST** `/api/auth/login`  
**Returns:** `MessageResponse<AuthResponse>`  
**Access:** Public

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john.doe@example.com",
    "password": "securePassword123"
  }'
```

**Expected Response:**
```json
{
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "user": {
      "email": "john.doe@example.com",
      "role": "RETAIL_CUSTOMER"
    }
  }
}
```

---

## 3. Primary Categories (`/api/v1/categories`)

### Create Primary Category
**POST** `/api/v1/categories/createCategory`  
**Returns:** `MessageResponse<PrimaryCatResData>`  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X POST http://localhost:8080/api/v1/categories/createCategory \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "primaryCategoryName": "FURNITURE",
    "primaryCategoryDescription": "Premium furniture collection",
    "imageUrl": "https://example.com/furniture.jpg",
    "seo": {
      "metaTitle": "Premium Furniture Collection",
      "metaDescription": "Discover our premium furniture collection",
      "keywords": ["furniture", "premium", "home"]
    },
    "internalTags": ["furniture", "home-decor"],
    "subCategory": [],
    "products": []
  }'
```

### Update Primary Category
**PUT** `/api/v1/categories/updateCategory/{primaryCategoryId}`  
**Returns:** `MessageResponse<PrimaryCatResData>`  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X PUT http://localhost:8080/api/v1/categories/updateCategory/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "primaryCategoryName": "PREMIUM FURNITURE",
    "primaryCategoryDescription": "Updated premium furniture collection",
    "imageUrl": "https://example.com/premium-furniture.jpg",
    "seo": {
      "metaTitle": "Premium Furniture Collection - Updated",
      "metaDescription": "Discover our updated premium furniture collection",
      "keywords": ["furniture", "premium", "luxury"]
    },
    "internalTags": ["furniture", "luxury", "home-decor"],
    "subCategory": [],
    "products": []
  }'
```

### Delete Primary Category
**DELETE** `/api/v1/categories/deleteCategory/{primaryCategoryId}`  
**Returns:** `MessageResponse<Void>`  
**Access:** ADMIN

```bash
curl -X DELETE http://localhost:8080/api/v1/categories/deleteCategory/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

**Expected Response:**
```json
{
  "message": "Primary category with id 1 deleted successfully.",
  "data": null
}
```

---

## 4. Secondary Categories (`/api/v1/secondary-categories`)

### Create Secondary Category
**POST** `/api/v1/secondary-categories/createCategory/{primaryCategoryId}`  
**Returns:** `MessageResponse<SecondaryCatResData>`  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X POST http://localhost:8080/api/v1/secondary-categories/createCategory/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "secondaryCategoryName": "SOFAS",
    "secondaryCategoryDescription": "Comfortable sofas and seating",
    "imageUrl": "https://example.com/sofas.jpg",
    "seo": {
      "metaTitle": "Premium Sofas Collection",
      "metaDescription": "Comfortable and stylish sofas",
      "keywords": ["sofas", "seating", "comfort"]
    },
    "internalTags": ["sofas", "seating"],
    "subCategory": [],
    "products": []
  }'
```

### Create Sub Category
**POST** `/api/v1/secondary-categories/createSubCategory/{parentSecondaryCategoryId}`  
**Returns:** `MessageResponse<SecondaryCatResData>`  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X POST http://localhost:8080/api/v1/secondary-categories/createSubCategory/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "secondaryCategoryName": "L-SHAPED SOFAS",
    "secondaryCategoryDescription": "L-shaped corner sofas",
    "imageUrl": "https://example.com/l-shaped-sofas.jpg",
    "seo": {
      "metaTitle": "L-Shaped Sofas Collection",
      "metaDescription": "Space-saving L-shaped corner sofas",
      "keywords": ["l-shaped", "corner", "sofas"]
    },
    "internalTags": ["l-shaped", "corner-sofas"],
    "subCategory": [],
    "products": []
  }'
```

### Update Secondary Category
**PUT** `/api/v1/secondary-categories/updateCategory/{secondaryCategoryId}`  
**Returns:** `MessageResponse<SecondaryCatResData>`  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X PUT http://localhost:8080/api/v1/secondary-categories/updateCategory/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "secondaryCategoryName": "PREMIUM SOFAS",
    "secondaryCategoryDescription": "Updated premium sofas and seating",
    "imageUrl": "https://example.com/premium-sofas.jpg",
    "seo": {
      "metaTitle": "Premium Sofas Collection - Updated",
      "metaDescription": "Luxury comfortable sofas",
      "keywords": ["premium", "sofas", "luxury", "comfort"]
    },
    "internalTags": ["premium-sofas", "luxury-seating"],
    "subCategory": [],
    "products": []
  }'
```

### Delete Secondary Category
**DELETE** `/api/v1/secondary-categories/deleteCategory/{secondaryCategoryId}`  
**Returns:** `MessageResponse<Void>`  
**Access:** ADMIN

```bash
curl -X DELETE http://localhost:8080/api/v1/secondary-categories/deleteCategory/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 5. Product Management (`/api/v1/products`)

### Create Product
**POST** `/api/v1/products/createProduct`  
**Returns:** `MessageResponse<Prod_Data_Res_DTO>`  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X POST http://localhost:8080/api/v1/products/createProduct \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "productName": "Premium L-Shaped Sofa",
    "productDescription": "Comfortable 6-seater L-shaped sofa",
    "primaryCategoryId": 1,
    "secondaryCategoryId": 1,
    "isPublished": true,
    "basePrice": 45000.00,
    "discountedPrice": 40000.00,
    "stockQuantity": 10
  }'
```

### Update Product
**PUT** `/api/v1/products/updateProduct/{prod_id}`  
**Returns:** `MessageResponse<Prod_Data_Res_DTO>`  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X PUT http://localhost:8080/api/v1/products/updateProduct/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "productName": "Updated Premium L-Shaped Sofa",
    "productDescription": "Updated comfortable 6-seater L-shaped sofa with premium fabric",
    "primaryCategoryId": 1,
    "secondaryCategoryId": 1,
    "isPublished": true,
    "basePrice": 50000.00,
    "discountedPrice": 42000.00,
    "stockQuantity": 15
  }'
```

### Update All Products (Bulk)
**PUT** `/api/v1/products/updateAllProducts`  
**Returns:** `MessageResponse<List<Prod_Data_Res_DTO>>`  
**Access:** ADMIN

```bash
curl -X PUT http://localhost:8080/api/v1/products/updateAllProducts \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "isPublished": true,
    "discountPercentage": 10
  }'
```

### Delete Product
**DELETE** `/api/v1/products/deleteProduct/{prod_id}`  
**Returns:** `MessageResponse<Void>`  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X DELETE http://localhost:8080/api/v1/products/deleteProduct/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 6. Product Pricing (`/api/v1/pricing`)

### Update Product Pricing
**PUT** `/api/v1/pricing/updatePricing/{prod_id}`  
**Returns:** `MessageResponse<Pricing_Res_DTO>`  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X PUT http://localhost:8080/api/v1/pricing/updatePricing/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "basePrice": 55000.00,
    "discountedPrice": 45000.00,
    "discountPercentage": 18.18,
    "taxRate": 18.0,
    "currency": "INR",
    "priceValidFrom": "2024-01-01T00:00:00",
    "priceValidTo": "2024-12-31T23:59:59"
  }'
```

---

## 7. Product Inventory (`/api/v1/inventory`)

### Update Product Inventory
**PUT** `/api/v1/inventory/updateInventory/{prod_id}/{sku_Id}`  
**Returns:** `MessageResponse<Inventory_Res_DTO>`  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X PUT http://localhost:8080/api/v1/inventory/updateInventory/1/SKU001 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "stockQuantity": 25,
    "reservedQuantity": 5,
    "availableQuantity": 20,
    "reorderLevel": 5,
    "maxStockLevel": 50,
    "warehouseLocation": "Warehouse-A-001"
  }'
```

---

## 8. Product Media (`/api/v1/media`)

### Update Product Media
**PUT** `/api/v1/media/updateMedia/{prod_id}`  
**Returns:** `MessageResponse<Media_Res_DTO>`  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X PUT http://localhost:8080/api/v1/media/updateMedia/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "primaryImage": "https://example.com/sofa-main.jpg",
    "imageGallery": [
      "https://example.com/sofa-1.jpg",
      "https://example.com/sofa-2.jpg",
      "https://example.com/sofa-3.jpg"
    ],
    "videoUrl": "https://example.com/sofa-demo.mp4",
    "altText": "Premium L-Shaped Sofa in living room"
  }'
```

---

## 9. Product SEO (`/api/v1/seo`)

### Update Product SEO
**PUT** `/api/v1/seo/updateSEO/{prod_id}`  
**Returns:** `MessageResponse<SEO_Res_DTO>`  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X PUT http://localhost:8080/api/v1/seo/updateSEO/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "metaTitle": "Premium L-Shaped Sofa - Homes & Merry",
    "metaDescription": "Buy premium 6-seater L-shaped sofa online. Comfortable, stylish, and durable furniture for your living room.",
    "keywords": ["l-shaped sofa", "premium furniture", "6-seater sofa", "living room furniture"],
    "canonicalUrl": "https://homesandmerry.com/products/premium-l-shaped-sofa",
    "ogTitle": "Premium L-Shaped Sofa",
    "ogDescription": "Transform your living space with our premium L-shaped sofa",
    "ogImage": "https://example.com/sofa-og-image.jpg"
  }'
```

---

## 10. Product Specifications (`/api/v1/specifications`)

### Update Product Specifications
**PUT** `/api/v1/specifications/updateSpecifications/{prod_id}`  
**Returns:** `MessageResponse<Specifications_Res_DTO>`  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X PUT http://localhost:8080/api/v1/specifications/updateSpecifications/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "dimensions": {
      "length": 280,
      "width": 180,
      "height": 85,
      "unit": "cm"
    },
    "weight": 65.5,
    "material": "Premium fabric with hardwood frame",
    "color": "Charcoal Grey",
    "warranty": "2 years comprehensive warranty",
    "assemblyRequired": true,
    "careInstructions": "Professional cleaning recommended"
  }'
```

---

## 11. Product Internal Details (`/api/v1/internal`)

### Update Product Internal Details
**PUT** `/api/v1/internal/updateInternal/{prod_id}`  
**Returns:** `MessageResponse<Internal_Res_DTO>`  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X PUT http://localhost:8080/api/v1/internal/updateInternal/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "internalSku": "SOFA-L-PREM-001",
    "supplierCode": "SUP-FURN-001",
    "costPrice": 30000.00,
    "profitMargin": 33.33,
    "internalNotes": "Premium quality sofa from trusted supplier",
    "qualityGrade": "A+",
    "manufacturingDate": "2024-01-15",
    "expiryDate": "2034-01-15"
  }'
```

---

## Common Response Structure

All endpoints return a response in the following format:

```json
{
  "message": "Operation success message",
  "data": {
    // Response data object (varies by endpoint)
    // Can be null for delete operations
  }
}
```

## Error Responses

In case of errors, you'll receive:

```json
{
  "message": "Error description",
  "data": null
}
```

Common HTTP status codes:
- `200 OK` - Successful GET, PUT, DELETE
- `201 Created` - Successful POST
- `400 Bad Request` - Validation errors
- `401 Unauthorized` - Missing or invalid JWT token
- `403 Forbidden` - Insufficient permissions
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server error

## Notes

1. Replace `YOUR_JWT_TOKEN` with actual JWT token obtained from the login endpoint
2. Replace path variables like `{prod_id}`, `{primaryCategoryId}` with actual IDs
3. Adjust request body fields based on your actual DTO structure
4. All timestamps should be in ISO 8601 format (`YYYY-MM-DDTHH:mm:ss`)
5. Ensure proper Content-Type header for JSON requests

---

## COMPLETE API INVENTORY (64+ Endpoints)

Based on the API documentation shown, here's the complete inventory of all endpoints in the system:

## 12. User Management Extended (`/api/v1`)

### Login User (Auth)
**POST** `/api/auth/login` (UserAuthLogin)  
**Returns:** `MessageResponse<AuthResponse>` or similar  
**Access:** Public

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123"
  }'
```

### Sign Up User
**POST** `/api/v1/signup` (UserSignUp)  
**Access:** Public

```bash
curl -X POST http://localhost:8080/api/v1/signup \
  -H "Content-Type: application/json" \
  -d '{
    "first_name": "John",
    "last_name": "Doe",
    "email": "john@example.com",
    "password": "securePassword123",
    "phone_number": "9876543210",
    "role": "RETAIL_CUSTOMER"
  }'
```

---

## 13. Brand Management (`/api/v1/brands`) - 9 Endpoints

### Create Brand
**POST** `/brands/create` (BrandService_CreateBrand)  
**Returns:** `MessageResponse<BrandResDTO>` (likely)  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X POST http://localhost:8080/api/v1/brands/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "brandName": "Premium Furniture Co.",
    "brandDescription": "Leading furniture manufacturer",
    "logoUrl": "https://example.com/logo.jpg",
    "website": "https://premiumfurniture.com",
    "isActive": true
  }'
```

### Update Brand
**PUT** `/brands/update/{brandId}` (BrandService_UpdateBrand)  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X PUT http://localhost:8080/api/v1/brands/update/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "brandName": "Updated Premium Furniture Co.",
    "brandDescription": "Updated leading furniture manufacturer",
    "logoUrl": "https://example.com/new-logo.jpg",
    "isActive": true
  }'
```

### Get All Brands
**GET** `/brands/all` (BrandService_GetAllBrand)  
**Access:** Public/Authenticated

```bash
curl -X GET http://localhost:8080/api/v1/brands/all \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Get Brand by ID
**GET** `/brands/{brandId}` (BrandService_GetByID)  
**Access:** Public/Authenticated

```bash
curl -X GET http://localhost:8080/api/v1/brands/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Get Brand Dashboard Stats
**GET** `/brands/dashboard-stats` (BrandService_DashboardStats)  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X GET http://localhost:8080/api/v1/brands/dashboard-stats \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Get Latest Updated Brands
**GET** `/brands/latest-updated` (BrandService_LatestUpdatedBrands)  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X GET http://localhost:8080/api/v1/brands/latest-updated \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Get Brands with Active Status & Count
**GET** `/brands/active-status-count` (BrandService_WithActiveStatus&Count)  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X GET http://localhost:8080/api/v1/brands/active-status-count \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Search Brands by Keyword
**GET** `/brands/search` (BrandService_SearchByKeyword)  
**Access:** Public/Authenticated

```bash
curl -X GET "http://localhost:8080/api/v1/brands/search?keyword=premium" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Delete Brand
**DELETE** `/brands/{brandId}` (BrandService_DeleteByID)  
**Returns:** `MessageResponse<Void>` (likely)  
**Access:** ADMIN

```bash
curl -X DELETE http://localhost:8080/api/v1/brands/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 14. Lead Management System

### CRM Login
**POST** `/crm/login` (CRM_LOGIN)  
**Access:** Sales Team

```bash
curl -X POST http://localhost:8080/api/crm/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "sales_executive",
    "password": "password123"
  }'
```

### Design Module Login
**POST** `/design/login` (DesignMod_LOGIN)  
**Access:** Design Team

```bash
curl -X POST http://localhost:8080/api/design/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "designer",
    "password": "password123"
  }'
```

### CRM Lead Filter
**GET** `/crm/leads/filter` (CRM_LeadFilter)  
**Access:** Sales Team

```bash
curl -X GET "http://localhost:8080/api/crm/leads/filter?status=NEW&priority=HIGH" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### CRM Quote Link
**GET** `/crm/quote-link/{leadId}` (CRM_QuoteLink)  
**Access:** Sales Team

```bash
curl -X GET http://localhost:8080/api/crm/quote-link/123 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Design Module Lead Filter
**GET** `/design/leads/filter` (DesignMod_LeadFilter)  
**Access:** Design Team

```bash
curl -X GET "http://localhost:8080/api/design/leads/filter?status=ASSIGNED" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Design Module Quote Link
**GET** `/design/quote-link/{leadId}` (DesignMod_QuoteLink)  
**Access:** Design Team

```bash
curl -X GET http://localhost:8080/api/design/quote-link/123 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Prolance Login
**POST** `/prolance/login` (Prolance_LOGIN)  
**Access:** Prolance Users

```bash
curl -X POST http://localhost:8080/api/prolance/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "prolance_user",
    "password": "password123"
  }'
```

### Prolance Partner Login
**POST** `/prolance/partner/login` (ProlancePartner_LOGIN)  
**Access:** Prolance Partners

```bash
curl -X POST http://localhost:8080/api/prolance/partner/login \
  -H "Content-Type: application/json" \
  -d '{
    "partnerId": "PARTNER001",
    "apiKey": "api_key_here"
  }'
```

### Prolance Origin Quote
**GET** `/prolance/origin-quote/{quoteId}` (Prolance_OriginQuote)  
**Access:** Prolance Users

```bash
curl -X GET http://localhost:8080/api/prolance/origin-quote/456 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 15. Client Service APIs (16+ Endpoints)

### Cart Management (8 Endpoints)

#### Add to Cart
**POST** `/api/v1/cart/add` (CartService_AddToCart)  
**Returns:** `MessageResponse<CartResponse>` (likely)  
**Access:** Authenticated Users

```bash
curl -X POST http://localhost:8080/api/v1/cart/add \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "productId": 1,
    "quantity": 2,
    "selectedVariations": {
      "color": "Blue",
      "size": "Large"
    }
  }'
```

#### View My Cart
**GET** `/api/v1/cart/my-cart` (CartService_ViewMyCart)  
**Access:** Authenticated Users

```bash
curl -X GET http://localhost:8080/api/v1/cart/my-cart \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Update Cart Items
**PUT** `/api/v1/cart/update` (CartService_UpdateItems)  
**Returns:** `MessageResponse<CartResponse>` (likely)  
**Access:** Authenticated Users

```bash
curl -X PUT http://localhost:8080/api/v1/cart/update \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "cartItemId": 1,
    "quantity": 3
  }'
```

#### Apply Coupon Code
**POST** `/api/v1/cart/coupon` (CartService_CouponCode)  
**Returns:** `MessageResponse<CartResponse>` (likely)  
**Access:** Authenticated Users

```bash
curl -X POST http://localhost:8080/api/v1/cart/coupon \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "couponCode": "SAVE10",
    "cartId": 1
  }'
```

#### Remove Coupon
**DELETE** `/api/v1/cart/coupon/{couponId}` (CartService_RemoveCoupon)  
**Returns:** `MessageResponse<Void>` (likely)  
**Access:** Authenticated Users

```bash
curl -X DELETE http://localhost:8080/api/v1/cart/coupon/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Add Cart to Wishlist
**POST** `/api/v1/cart/to-wishlist` (CartService_CartToWishlist)  
**Returns:** `MessageResponse<WishlistResponse>` (likely)  
**Access:** Authenticated Users

```bash
curl -X POST http://localhost:8080/api/v1/cart/to-wishlist \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "cartItemIds": [1, 2, 3]
  }'
```

#### Remove Single Item from Cart
**DELETE** `/api/v1/cart/item/{itemId}` (CartService_RemoveSingleItem)  
**Returns:** `MessageResponse<Void>` (likely)  
**Access:** Authenticated Users

```bash
curl -X DELETE http://localhost:8080/api/v1/cart/item/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Clear Cart
**DELETE** `/api/v1/cart/clear` (CartService_ClearCart)  
**Returns:** `MessageResponse<Void>` (likely)  
**Access:** Authenticated Users

```bash
curl -X DELETE http://localhost:8080/api/v1/cart/clear \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

### Wishlist Management (3 Endpoints)

#### View Wishlist
**GET** `/api/v1/wishlist/my-wishlist` (WishlistService_ViewWishlist)  
**Access:** Authenticated Users

```bash
curl -X GET http://localhost:8080/api/v1/wishlist/my-wishlist \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Move Wishlist to Cart
**POST** `/api/v1/wishlist/to-cart` (WishlistService_WishlistToCart)  
**Returns:** `MessageResponse<CartResponse>` (likely)  
**Access:** Authenticated Users

```bash
curl -X POST http://localhost:8080/api/v1/wishlist/to-cart \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "wishlistItemIds": [1, 2, 3]
  }'
```

#### Delete Wishlist Item
**DELETE** `/api/v1/wishlist/item/{itemId}` (WishlistService_DeleteItem)  
**Returns:** `MessageResponse<Void>` (likely)  
**Access:** Authenticated Users

```bash
curl -X DELETE http://localhost:8080/api/v1/wishlist/item/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

### Checkout Process (5 Endpoints)

#### Initiate Checkout
**POST** `/api/v1/checkout/initiate` (CheckoutService_Initiate)  
**Returns:** `MessageResponse<CheckoutResponse>` (likely)  
**Access:** Authenticated Users

```bash
curl -X POST http://localhost:8080/api/v1/checkout/initiate \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "cartId": 1,
    "shippingAddress": {
      "street": "123 Main St",
      "city": "Bangalore",
      "state": "Karnataka",
      "pincode": "560001"
    },
    "paymentMethod": "UPI"
  }'
```

#### Confirm Payment
**POST** `/api/v1/checkout/confirm-payment` (CheckoutService_ConfirmPayment)  
**Returns:** `MessageResponse<OrderResponse>` (likely)  
**Access:** Authenticated Users

```bash
curl -X POST http://localhost:8080/api/v1/checkout/confirm-payment \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "checkoutId": 1,
    "paymentTransactionId": "TXN123456789",
    "paymentStatus": "SUCCESS"
  }'
```

#### Payment Failed
**POST** `/api/v1/checkout/payment-failed` (CheckoutService_PaymentFailed)  
**Returns:** `MessageResponse<CheckoutResponse>` (likely)  
**Access:** Authenticated Users

```bash
curl -X POST http://localhost:8080/api/v1/checkout/payment-failed \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "checkoutId": 1,
    "failureReason": "Insufficient funds",
    "paymentTransactionId": "TXN123456789"
  }'
```

#### Cancel Checkout
**POST** `/api/v1/checkout/cancel` (CheckoutService_Cancel)  
**Returns:** `MessageResponse<Void>` (likely)  
**Access:** Authenticated Users

```bash
curl -X POST http://localhost:8080/api/v1/checkout/cancel \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "checkoutId": 1,
    "cancellationReason": "Changed mind"
  }'
```

#### View Order Details
**GET** `/api/v1/checkout/order/{orderId}` (CheckoutService_ViewOrder)  
**Access:** Authenticated Users

```bash
curl -X GET http://localhost:8080/api/v1/checkout/order/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 16. Extended Product Management

All the previously mentioned product endpoints PLUS:

### Get Product by ID
**GET** `/api/v1/products/{prod_id}` (ProductService_GetProductById)  
**Access:** Public

```bash
curl -X GET http://localhost:8080/api/v1/products/1
```

### Get All Products (with pagination)
**GET** `/api/v1/products/all` (ProductService_GetAllProducts)  
**Access:** Public

```bash
curl -X GET "http://localhost:8080/api/v1/products/all?page=0&size=10&sort=prodId,asc"
```

### Update Product by ID
**PUT** `/api/v1/products/update/{prod_id}` (ProductService_updateByID)  
**Returns:** `MessageResponse<Prod_Data_Res_DTO>`  
**Access:** ADMIN, ENTERPRISE

```bash
curl -X PUT http://localhost:8080/api/v1/products/update/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "productName": "Updated Product Name",
    "productDescription": "Updated description",
    "basePrice": 25000.00
  }'
```

---

## Summary of All Endpoints by Module

### **User Management (2 endpoints)**
- POST `/api/auth/login` (UserAuthLogin)
- POST `/api/v1/signup` (UserSignUp)

### **Brand Management (9 endpoints)**
- POST, PUT, GET (multiple), DELETE for brand operations

### **Lead Management (9 endpoints)**
- Various login endpoints for CRM, Design, Prolance
- Lead filtering and quote management

### **Client Services (16 endpoints)**
- **Cart (8):** Add, view, update, coupon management, wishlist integration
- **Wishlist (3):** View, move to cart, delete items  
- **Checkout (5):** Initiate, payment handling, order management

### **Product Management (33+ endpoints)**
- **Product CRUD (6):** Create, read, update, delete operations
- **Categories (11):** Primary and secondary category management
- **Product Modules (16):** Inventory, pricing, media, SEO, specifications, internal

### **Total: 64+ endpoints** across the entire system

---

## MessageResponse Endpoints Summary

The following endpoints specifically return `MessageResponse<T>`:

### **Creation Operations (POST) - Returns MessageResponse with created object:**
1. User creation
2. Brand creation (likely)
3. Category creation (primary & secondary)
4. Product creation
5. All product module creations (inventory, pricing, media, SEO, specifications, internal)
6. Cart operations (add, coupon application)
7. Wishlist operations
8. Checkout initiation

### **Update Operations (PUT) - Returns MessageResponse with updated object:**
1. Brand updates (likely)
2. Category updates (primary & secondary) 
3. Product updates (single & bulk)
4. All product module updates
5. Cart item updates

### **Deletion Operations (DELETE) - Returns MessageResponse<Void>:**
1. Brand deletion (likely)
2. Category deletions
3. Product deletion
4. All product module deletions
5. Cart item removals
6. Wishlist item deletions

**Total MessageResponse endpoints: 35+ out of 64+ total endpoints**