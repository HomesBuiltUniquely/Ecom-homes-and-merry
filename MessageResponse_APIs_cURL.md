# MessageResponse APIs - cURL Documentation (49 APIs)

**Base URL:** `http://localhost:8080`

## Authentication
Include JWT token for authenticated endpoints:
```bash
-H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 1. User APIs (2)

### UserAuthLogin
**POST** `/api/auth/login`

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "password123"
  }'
```

### UserSignUp  
**POST** `/api/v1/CreateUser`

```bash
curl -X POST http://localhost:8080/api/v1/CreateUser \
  -H "Content-Type: application/json" \
  -d '{
    "first_name": "John",
    "last_name": "Doe",
    "email": "john@example.com",
    "phone_number": "9876543210",
    "password": "password123",
    "role": "RETAIL_CUSTOMER",
    "brand_name": "John Store",
    "pincode": 560001,
    "address": {
      "city": "Bangalore",
      "state": "Karnataka",
      "description": "123 Main St"
    }
  }'
```

---

## 2. Product APIs (21)

### Inventory (3)

#### Create Inventory
**POST** `/api/v1/inventory/create`

```bash
curl -X POST http://localhost:8080/api/v1/inventory/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "productId": 1,
    "stockQuantity": 100,
    "reservedQuantity": 10,
    "warehouseLocation": "WH-001"
  }'
```

#### Update Inventory
**PUT** `/api/v1/inventory/updateInventory/{prod_id}`

```bash
curl -X PUT http://localhost:8080/api/v1/inventory/updateInventory/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "stockQuantity": 150,
    "reservedQuantity": 5,
    "availableQuantity": 145
  }'
```
#### Delete Inventory
**DELETE** `/api/v1/inventory/deleteInventory/{prod_id}`

```bash
curl -X DELETE http://localhost:8080/api/v1/inventory/deleteInventory/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Pricing (1)

#### Update Pricing
**PUT** `/api/v1/pricing/updatePricing/{prod_id}`

```bash
curl -X PUT http://localhost:8080/api/v1/pricing/updatePricing/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "basePrice": 50000.00,
    "discountedPrice": 45000.00,
    "taxRate": 18.0,
    "currency": "INR"
  }'
```

### Media (3)

#### Create Media
**POST** `/api/v1/media/create`

```bash
curl -X POST http://localhost:8080/api/v1/media/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "productId": 1,
    "primaryImage": "https://example.com/image1.jpg",
    "imageGallery": ["https://example.com/img1.jpg", "https://example.com/img2.jpg"],
    "videoUrl": "https://example.com/video.mp4"
  }'
```

#### Update Media
**PUT** `/api/v1/media/updateMedia/{prod_id}`

```bash
curl -X PUT http://localhost:8080/api/v1/media/updateMedia/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "primaryImage": "https://example.com/updated-image.jpg",
    "imageGallery": ["https://example.com/new1.jpg"],
    "altText": "Updated product image"
  }'
```

#### Delete Media
**DELETE** `/api/v1/media/deleteMedia/{prod_id}`

```bash
curl -X DELETE http://localhost:8080/api/v1/media/deleteMedia/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### SEO (3)

#### Create SEO
**POST** `/api/v1/seo/create`

```bash
curl -X POST http://localhost:8080/api/v1/seo/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "productId": 1,
    "metaTitle": "Premium Sofa - Buy Online",
    "metaDescription": "Best quality sofas available online",
    "keywords": ["sofa", "furniture", "home"],
    "canonicalUrl": "https://site.com/sofa-1"
  }'
```
#### Update SEO
**PUT** `/api/v1/seo/updateSEO/{prod_id}`

```bash
curl -X PUT http://localhost:8080/api/v1/seo/updateSEO/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "metaTitle": "Updated Premium Sofa Title",
    "metaDescription": "Updated description for SEO",
    "keywords": ["premium", "sofa", "luxury"]
  }'
```

#### Delete SEO
**DELETE** `/api/v1/seo/deleteSEO/{prod_id}`

```bash
curl -X DELETE http://localhost:8080/api/v1/seo/deleteSEO/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Specifications (3)

#### Create Specifications
**POST** `/api/v1/specifications/create`

```bash
curl -X POST http://localhost:8080/api/v1/specifications/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "productId": 1,
    "dimensions": {"length": 200, "width": 100, "height": 80},
    "weight": 50.5,
    "material": "Premium Wood",
    "color": "Brown",
    "warranty": "2 years"
  }'
```

#### Update Specifications
**PUT** `/api/v1/specifications/updateSpecifications/{prod_id}`

```bash
curl -X PUT http://localhost:8080/api/v1/specifications/updateSpecifications/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "dimensions": {"length": 220, "width": 110, "height": 85},
    "weight": 55.0,
    "material": "Updated Premium Wood"
  }'
```

#### Delete Specifications
**DELETE** `/api/v1/specifications/deleteSpecifications/{prod_id}`

```bash
curl -X DELETE http://localhost:8080/api/v1/specifications/deleteSpecifications/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Internal (3)

#### Create Internal
**POST** `/api/v1/internal/create`

```bash
curl -X POST http://localhost:8080/api/v1/internal/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "productId": 1,
    "internalSku": "SKU-001",
    "supplierCode": "SUP-001",
    "costPrice": 30000.00,
    "profitMargin": 25.0,
    "internalNotes": "High quality product"
  }'
```
#### Update Internal
**PUT** `/api/v1/internal/updateInternal/{prod_id}`

```bash
curl -X PUT http://localhost:8080/api/v1/internal/updateInternal/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "internalSku": "SKU-001-UPDATED",
    "costPrice": 32000.00,
    "profitMargin": 28.0,
    "internalNotes": "Updated high quality product"
  }'
```

#### Delete Internal
**DELETE** `/api/v1/internal/deleteInternal/{prod_id}`

```bash
curl -X DELETE http://localhost:8080/api/v1/internal/deleteInternal/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Other Product APIs (4)

#### Create Product
**POST** `/api/v1/products/createProduct`

```bash
curl -X POST http://localhost:8080/api/v1/products/createProduct \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "productName": "Premium Sofa",
    "productDescription": "Comfortable premium sofa",
    "primaryCategoryId": 1,
    "secondaryCategoryId": 1,
    "basePrice": 45000.00,
    "isPublished": true
  }'
```

#### Update Product by ID
**PUT** `/api/v1/products/updateProduct/{prod_id}`

```bash
curl -X PUT http://localhost:8080/api/v1/products/updateProduct/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "productName": "Updated Premium Sofa",
    "productDescription": "Updated comfortable premium sofa",
    "basePrice": 48000.00
  }'
```

#### Update All Products
**PUT** `/api/v1/products/updateAllProducts`

```bash
curl -X PUT http://localhost:8080/api/v1/products/updateAllProducts \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "isPublished": true,
    "discountPercentage": 15
  }'
```

#### Delete Product
**DELETE** `/api/v1/products/deleteProduct/{prod_id}`

```bash
curl -X DELETE http://localhost:8080/api/v1/products/deleteProduct/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 3. Category APIs (6)

### Primary Category (3)

#### Create Primary Category
**POST** `/api/v1/categories/createCategory`

```bash
curl -X POST http://localhost:8080/api/v1/categories/createCategory \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "primaryCategoryName": "FURNITURE",
    "primaryCategoryDescription": "All furniture items",
    "imageUrl": "https://example.com/furniture.jpg",
    "internalTags": ["furniture", "home"]
  }'
```
#### Update Primary Category
**PUT** `/api/v1/categories/updateCategory/{primaryCategoryId}`

```bash
curl -X PUT http://localhost:8080/api/v1/categories/updateCategory/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "primaryCategoryName": "PREMIUM FURNITURE",
    "primaryCategoryDescription": "Premium furniture collection",
    "imageUrl": "https://example.com/premium-furniture.jpg"
  }'
```

#### Delete Primary Category
**DELETE** `/api/v1/categories/deleteCategory/{primaryCategoryId}`

```bash
curl -X DELETE http://localhost:8080/api/v1/categories/deleteCategory/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Secondary Category (3)

#### Create Secondary Category
**POST** `/api/v1/secondary-categories/createCategory/{primaryCategoryId}`

```bash
curl -X POST http://localhost:8080/api/v1/secondary-categories/createCategory/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "secondaryCategoryName": "SOFAS",
    "secondaryCategoryDescription": "All types of sofas",
    "imageUrl": "https://example.com/sofas.jpg",
    "internalTags": ["sofas", "seating"]
  }'
```

#### Update Secondary Category
**PUT** `/api/v1/secondary-categories/updateCategory/{secondaryCategoryId}`

```bash
curl -X PUT http://localhost:8080/api/v1/secondary-categories/updateCategory/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "secondaryCategoryName": "PREMIUM SOFAS",
    "secondaryCategoryDescription": "Premium sofa collection",
    "imageUrl": "https://example.com/premium-sofas.jpg"
  }'
```

#### Delete Secondary Category
**DELETE** `/api/v1/secondary-categories/deleteCategory/{secondaryCategoryId}`

```bash
curl -X DELETE http://localhost:8080/api/v1/secondary-categories/deleteCategory/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 4. Brand APIs (3)

#### Create Brand
**POST** `/api/v1/brands/create`

```bash
curl -X POST http://localhost:8080/api/v1/brands/create \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "brandName": "Premium Furniture Co",
    "brandDescription": "Leading furniture manufacturer",
    "logoUrl": "https://example.com/logo.jpg",
    "website": "https://premiumfurniture.com",
    "isActive": true
  }'
```

#### Update Brand
**PUT** `/api/v1/brands/update/{brandId}`

```bash
curl -X PUT http://localhost:8080/api/v1/brands/update/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "brandName": "Updated Premium Furniture Co",
    "brandDescription": "Updated leading furniture manufacturer",
    "isActive": true
  }'
```
#### Delete Brand
**DELETE** `/api/v1/brands/delete/{brandId}`

```bash
curl -X DELETE http://localhost:8080/api/v1/brands/delete/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## 5. Lead Management APIs (5)

#### CRM Login
**POST** `/api/crm/login`

```bash
curl -X POST http://localhost:8080/api/crm/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "crm_user",
    "password": "password123",
    "role": "SALES_EXECUTIVE"
  }'
```

#### Design Module Login
**POST** `/api/design/login`

```bash
curl -X POST http://localhost:8080/api/design/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "designer",
    "password": "password123",
    "role": "DESIGNERS"
  }'
```

#### Prolance Login
**POST** `/api/prolance/login`

```bash
curl -X POST http://localhost:8080/api/prolance/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "prolance_user",
    "password": "password123",
    "companyCode": "PROLANCE001"
  }'
```

#### Prolance Partner Login
**POST** `/api/prolance/partner/login`

```bash
curl -X POST http://localhost:8080/api/prolance/partner/login \
  -H "Content-Type: application/json" \
  -d '{
    "partnerId": "PARTNER001",
    "apiKey": "prolance_api_key_here",
    "partnerName": "Partner Company Ltd"
  }'
```

#### Lead Assignment (Additional API)
**POST** `/api/leads/assign`

```bash
curl -X POST http://localhost:8080/api/leads/assign \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "leadId": 123,
    "assignedTo": "designer_001",
    "priority": "HIGH",
    "notes": "Urgent lead assignment"
  }'
```

---

## 6. Client Service APIs (12)

### Cart APIs (7)

#### Add to Cart
**POST** `/api/v1/cart/add`

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

#### Update Cart Items
**PUT** `/api/v1/cart/update`

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
**POST** `/api/v1/cart/coupon`

```bash
curl -X POST http://localhost:8080/api/v1/cart/coupon \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "couponCode": "SAVE20",
    "cartId": 1
  }'
```

#### Remove Coupon
**DELETE** `/api/v1/cart/coupon/{couponId}`

```bash
curl -X DELETE http://localhost:8080/api/v1/cart/coupon/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Cart to Wishlist
**POST** `/api/v1/cart/wishlist`

```bash
curl -X POST http://localhost:8080/api/v1/cart/wishlist \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "cartItemIds": [1, 2, 3]
  }'
```

#### Remove Single Item
**DELETE** `/api/v1/cart/item/{itemId}`

```bash
curl -X DELETE http://localhost:8080/api/v1/cart/item/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Clear Cart
**DELETE** `/api/v1/cart/clear`

```bash
curl -X DELETE http://localhost:8080/api/v1/cart/clear \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Wishlist APIs (2)

#### Wishlist to Cart
**POST** `/api/v1/wishlist/cart`

```bash
curl -X POST http://localhost:8080/api/v1/wishlist/cart \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "wishlistItemIds": [1, 2, 3]
  }'
```

#### Delete Wishlist Item
**DELETE** `/api/v1/wishlist/item/{itemId}`

```bash
curl -X DELETE http://localhost:8080/api/v1/wishlist/item/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Checkout APIs (4)

#### Initiate Checkout
**POST** `/api/v1/checkout/initiate`

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
**POST** `/api/v1/checkout/confirm`

```bash
curl -X POST http://localhost:8080/api/v1/checkout/confirm \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "checkoutId": 1,
    "paymentTransactionId": "TXN123456789",
    "paymentStatus": "SUCCESS",
    "paymentMethod": "UPI",
    "paymentAmount": 45000.00
  }'
```

#### Payment Failed
**POST** `/api/v1/checkout/payment-failed`

```bash
curl -X POST http://localhost:8080/api/v1/checkout/payment-failed \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "checkoutId": 1,
    "paymentTransactionId": "TXN123456789",
    "failureReason": "Insufficient funds",
    "errorCode": "PAYMENT_FAILED"
  }'
```

#### Cancel Checkout
**POST** `/api/v1/checkout/cancel`

```bash
curl -X POST http://localhost:8080/api/v1/checkout/cancel \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "checkoutId": 1,
    "cancellationReason": "User cancelled",
    "refundRequired": true
  }'
```

---

## Summary

### Total APIs with MessageResponse: 49

**Distribution:**
- **User:** 2 APIs (Login, SignUp)
- **Product:** 21 APIs (All CRUD operations for product modules)
- **Category:** 6 APIs (Primary & Secondary category CRUD)
- **Brand:** 3 APIs (Brand CRUD)
- **Lead Management:** 4 APIs (Various login endpoints)
- **Client Service:** 12 APIs (Cart, Wishlist, Checkout operations)

### Response Format

All APIs return:
```json
{
  "message": "Success/Error message",
  "data": {
    // Response data or null for delete operations
  }
}
```

### Access Levels
- **Public:** User login/signup
- **Authenticated:** Cart, wishlist, checkout
- **ADMIN/ENTERPRISE:** Product management, categories, brands
- **Specialized:** CRM, Design, Prolance logins

#### Clear Cart
**DELETE** `/api/v1/cart/clear`

```bash
curl -X DELETE http://localhost:8080/api/v1/cart/clear \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### Additional Missing APIs to Reach 49 Total

#### Secondary Category - Create Sub Category
**POST** `/api/v1/secondary-categories/createSubCategory/{parentSecondaryCategoryId}`

```bash
curl -X POST http://localhost:8080/api/v1/secondary-categories/createSubCategory/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "secondaryCategoryName": "L-SHAPED SOFAS",
    "secondaryCategoryDescription": "L-shaped corner sofas",
    "imageUrl": "https://example.com/l-shaped.jpg",
    "internalTags": ["l-shaped", "corner"]
  }'
```

#### Get Inventory by Product ID
**GET** `/api/v1/inventory/{prod_id}` (Returns MessageResponse)

```bash
curl -X GET http://localhost:8080/api/v1/inventory/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Get All Inventory
**GET** `/api/v1/inventory/all` (Returns MessageResponse)

```bash
curl -X GET http://localhost:8080/api/v1/inventory/all \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Get Media by Product ID  
**GET** `/api/v1/media/{prod_id}` (Returns MessageResponse)

```bash
curl -X GET http://localhost:8080/api/v1/media/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Get All Media
**GET** `/api/v1/media/all` (Returns MessageResponse)

```bash
curl -X GET http://localhost:8080/api/v1/media/all \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Get SEO by Slug
**GET** `/api/v1/seo/slug/{slug}` (Returns MessageResponse)

```bash
curl -X GET http://localhost:8080/api/v1/seo/slug/premium-sofa \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Get All SEO
**GET** `/api/v1/seo/all` (Returns MessageResponse)

```bash
curl -X GET http://localhost:8080/api/v1/seo/all \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Get Specifications by Product ID
**GET** `/api/v1/specifications/{prod_id}` (Returns MessageResponse)

```bash
curl -X GET http://localhost:8080/api/v1/specifications/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Get All Specifications
**GET** `/api/v1/specifications/all` (Returns MessageResponse)

```bash
curl -X GET http://localhost:8080/api/v1/specifications/all \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Get Internal by Product ID
**GET** `/api/v1/internal/{prod_id}` (Returns MessageResponse)

```bash
curl -X GET http://localhost:8080/api/v1/internal/1 \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

#### Get All Internal
**GET** `/api/v1/internal/all` (Returns MessageResponse)

```bash
curl -X GET http://localhost:8080/api/v1/internal/all \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

---

## Updated Summary

### Total APIs with MessageResponse: 49

**Final Distribution:**
- **User:** 2 APIs 
- **Product:** 21 APIs
  - Inventory (5): Create, Update, Delete, GetById, GetAll
  - Pricing (1): Update  
  - Media (5): Create, Update, Delete, GetById, GetAll
  - SEO (5): Create, Update, Delete, GetBySlug, GetAll
  - Specifications (5): Create, Update, Delete, GetById, GetAll
  - Internal (5): Create, Update, Delete, GetById, GetAll
  - Core Product (4): Create, Update, UpdateAll, Delete
- **Category:** 7 APIs 
  - Primary (3): Create, Update, Delete
  - Secondary (4): Create, Update, Delete, CreateSubCategory
- **Brand:** 3 APIs
- **Lead Management:** 5 APIs 
- **Client Service:** 12 APIs
  - Cart (7): Add, Update, Coupon, RemoveCoupon, ToWishlist, RemoveItem, Clear
  - Wishlist (2): ToCart, DeleteItem
  - Checkout (4): Initiate, Confirm, PaymentFailed, Cancel

**Total: 2 + 21 + 6 + 3 + 5 + 12 = 49 APIs** ✅