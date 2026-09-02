# Cart, Wishlist, Coupon & Inventory Backend API Specification
**Project**: `Ecom-homes-and-merry`  
**Scope**: Backend REST APIs (Client-scoped with `{client_id}`)  
**Status**: Final API Design & Payloads Specification  

---

## 1. Architectural Summary

1. **Client-Scoped Route Strategy**:
   All Backend APIs explicitly route under the `/api/v1/client/{client_id}/` namespace. This allows Frontend / Gateway services to delegate client-specific cart operations directly by passing `{client_id}`.
2. **Unified Cart Calculation & Coupon Breakdown**:
   Every Cart operation returns the full pricing breakdown (`mrp_total`, `item_discount_total`, `sub_total`, `coupon_discount`, `delivery_charges`, `gst_breakdown`, `net_payable_amount`), keeping the frontend synchronized without requiring redundant client-side calculations.
3. **Dedicated Endpoints for Mutating Actions**:
   - Cart removal (`DELETE`) and Move-to-Wishlist (`POST .../move-to-wishlist`) are kept distinct.
   - Moving an item to the wishlist atomically decrements the cart size and increments the wishlist size.
4. **Inventory Stock Lifecycle**:
   - `quantity` in cart is validated against `current_stock` via a soft check during Cart operations.
   - DB `current_stock` deduction is executed transactionally during checkout / payment completion.

---

## 2. Complete API Specification & Payloads

---

### 2.1 Add Item to Cart
- **Method & URL**: `POST /api/v1/client/{client_id}/cart/items`
- **Description**: Adds a product with specified quantity to the client's cart. If the product is already in the cart, its quantity is increased.

#### Path Variables:
| Variable | Type | Description |
| :--- | :--- | :--- |
| `client_id` | `Long` | Unique identifier of the client / user (e.g. `5`) |

#### Request Body:
```json
{
  "product_id": 101,
  "quantity": 2
}
```

#### Response Body (`201 Created` / `200 OK`):
```json
{
  "status": "SUCCESS",
  "message": "Item added to cart successfully",
  "data": {
    "cart_id": 1,
    "client_id": 5,
    "customer_email": "customer@example.com",
    "total_items_count": 2,
    "items": [
      {
        "cart_item_id": 15,
        "product_id": 101,
        "sku_id": "SKU-CHR-001",
        "product_name": "Ergonomic Office Chair",
        "primary_image_url": "https://cdn.homesandmerry.com/products/chair-01.jpg",
        "unit_mrp": 15000.00,
        "unit_selling_price": 12500.00,
        "quantity": 2,
        "item_subtotal": 25000.00,
        "item_discount": 5000.00,
        "in_stock": true,
        "available_stock": 25
      }
    ],
    "pricing_summary": {
      "mrp_total": 30000.00,
      "item_discount_total": 5000.00,
      "sub_total": 25000.00,
      "coupon": null,
      "coupon_discount": 0.00,
      "delivery_charges": 0.00,
      "gst_breakdown": {
        "cgst_amount": 2250.00,
        "sgst_amount": 2250.00,
        "total_tax": 4500.00
      },
      "net_payable_amount": 25000.00
    }
  }
}
```

---

### 2.2 Update Item Quantity in Cart
- **Method & URL**: `PUT /api/v1/client/{client_id}/cart/items/{cart_item_id}`
- **Description**: Updates the quantity of a specific item in the client's cart (`+` / `-` buttons in cart UI).

#### Path Variables:
| Variable | Type | Description |
| :--- | :--- | :--- |
| `client_id` | `Long` | Unique identifier of the client / user |
| `cart_item_id` | `Long` | Unique identifier of the cart item (e.g. `15`) |

#### Request Body:
```json
{
  "quantity": 3
}
```

#### Response Body (`200 OK`):
```json
{
  "status": "SUCCESS",
  "message": "Cart item quantity updated",
  "data": {
    "cart_id": 1,
    "client_id": 5,
    "customer_email": "customer@example.com",
    "total_items_count": 3,
    "items": [
      {
        "cart_item_id": 15,
        "product_id": 101,
        "sku_id": "SKU-CHR-001",
        "product_name": "Ergonomic Office Chair",
        "primary_image_url": "https://cdn.homesandmerry.com/products/chair-01.jpg",
        "unit_mrp": 15000.00,
        "unit_selling_price": 12500.00,
        "quantity": 3,
        "item_subtotal": 37500.00,
        "item_discount": 7500.00,
        "in_stock": true,
        "available_stock": 25
      }
    ],
    "pricing_summary": {
      "mrp_total": 45000.00,
      "item_discount_total": 7500.00,
      "sub_total": 37500.00,
      "coupon": {
        "coupon_code": "MERRY10",
        "is_active": true,
        "discount_type": "PERCENTAGE",
        "discount_percentage": 10,
        "discount_amount": 3750.00
      },
      "coupon_discount": 3750.00,
      "delivery_charges": 0.00,
      "gst_breakdown": {
        "cgst_amount": 3037.50,
        "sgst_amount": 3037.50,
        "total_tax": 6075.00
      },
      "net_payable_amount": 33750.00
    }
  }
}
```

---

### 2.3 View My Cart
- **Method & URL**: `GET /api/v1/client/{client_id}/cart`
- **Description**: Fetches current cart contents for the client, recalculates applied coupons, taxes, and verifies real-time stock availability.

#### Path Variables:
| Variable | Type | Description |
| :--- | :--- | :--- |
| `client_id` | `Long` | Unique identifier of the client / user |

#### Request Body:
*(None)*

#### Response Body (`200 OK`):
```json
{
  "status": "SUCCESS",
  "message": "Cart fetched successfully",
  "data": {
    "cart_id": 1,
    "client_id": 5,
    "customer_email": "customer@example.com",
    "total_items_count": 2,
    "items": [
      {
        "cart_item_id": 15,
        "product_id": 101,
        "sku_id": "SKU-CHR-001",
        "product_name": "Ergonomic Office Chair",
        "primary_image_url": "https://cdn.homesandmerry.com/products/chair-01.jpg",
        "unit_mrp": 15000.00,
        "unit_selling_price": 12500.00,
        "quantity": 2,
        "item_subtotal": 25000.00,
        "item_discount": 5000.00,
        "in_stock": true,
        "available_stock": 25
      },
      {
        "cart_item_id": 18,
        "product_id": 204,
        "sku_id": "SKU-TBL-009",
        "product_name": "Solid Teak Wood Dining Table",
        "primary_image_url": "https://cdn.homesandmerry.com/products/table-09.jpg",
        "unit_mrp": 42000.00,
        "unit_selling_price": 35000.00,
        "quantity": 1,
        "item_subtotal": 35000.00,
        "item_discount": 7000.00,
        "in_stock": true,
        "available_stock": 4
      }
    ],
    "pricing_summary": {
      "mrp_total": 72000.00,
      "item_discount_total": 12000.00,
      "sub_total": 60000.00,
      "coupon": {
        "coupon_code": "HOMES5000",
        "is_active": true,
        "discount_type": "FLAT",
        "discount_percentage": null,
        "discount_amount": 5000.00
      },
      "coupon_discount": 5000.00,
      "delivery_charges": 0.00,
      "gst_breakdown": {
        "cgst_amount": 4950.00,
        "sgst_amount": 4950.00,
        "total_tax": 9900.00
      },
      "net_payable_amount": 55000.00
    }
  }
}
```

---

### 2.4 Remove Single Item from Cart
- **Method & URL**: `DELETE /api/v1/client/{client_id}/cart/items/{cart_item_id}`
- **Description**: Removes an individual item from the client's cart.

#### Path Variables:
| Variable | Type | Description |
| :--- | :--- | :--- |
| `client_id` | `Long` | Unique identifier of the client / user |
| `cart_item_id` | `Long` | Unique identifier of the cart item to remove |

#### Request Body:
*(None)*

#### Response Body (`200 OK`):
```json
{
  "status": "SUCCESS",
  "message": "Item removed from cart successfully",
  "data": {
    "cart_id": 1,
    "client_id": 5,
    "total_items_count": 1,
    "removed_cart_item_id": 15,
    "pricing_summary": {
      "mrp_total": 42000.00,
      "item_discount_total": 7000.00,
      "sub_total": 35000.00,
      "coupon": null,
      "coupon_discount": 0.00,
      "delivery_charges": 500.00,
      "gst_breakdown": {
        "cgst_amount": 3150.00,
        "sgst_amount": 3150.00,
        "total_tax": 6300.00
      },
      "net_payable_amount": 35500.00
    }
  }
}
```

---

### 2.5 Move Item from Cart to Wishlist
- **Method & URL**: `POST /api/v1/client/{client_id}/cart/items/{cart_item_id}/move-to-wishlist`
- **Description**: Atomically removes the item from the client's Cart and adds it to their Wishlist.

#### Path Variables:
| Variable | Type | Description |
| :--- | :--- | :--- |
| `client_id` | `Long` | Unique identifier of the client / user |
| `cart_item_id` | `Long` | Unique identifier of the cart item to move |

#### Request Body:
*(None)*

#### Response Body (`200 OK`):
```json
{
  "status": "SUCCESS",
  "message": "Item moved from cart to wishlist successfully",
  "data": {
    "cart_id": 1,
    "client_id": 5,
    "moved_product_id": 101,
    "cart_total_items": 3,
    "wishlist_total_items": 4,
    "pricing_summary": {
      "mrp_total": 42000.00,
      "item_discount_total": 7000.00,
      "sub_total": 35000.00,
      "coupon": null,
      "coupon_discount": 0.00,
      "delivery_charges": 500.00,
      "gst_breakdown": {
        "cgst_amount": 3150.00,
        "sgst_amount": 3150.00,
        "total_tax": 6300.00
      },
      "net_payable_amount": 35500.00
    }
  }
}
```

---

### 2.6 Move Item from Wishlist to Cart (Reverse Action)
- **Method & URL**: `POST /api/v1/client/{client_id}/wishlist/items/{wishlist_item_id}/move-to-cart`
- **Description**: Moves an item from the client's Wishlist back into their Cart.

#### Path Variables:
| Variable | Type | Description |
| :--- | :--- | :--- |
| `client_id` | `Long` | Unique identifier of the client / user |
| `wishlist_item_id` | `Long` | Unique identifier of the wishlist item |

#### Request Body:
```json
{
  "quantity": 1
}
```

#### Response Body (`200 OK`):
```json
{
  "status": "SUCCESS",
  "message": "Item moved from wishlist to cart successfully",
  "data": {
    "client_id": 5,
    "cart_total_items": 4,
    "wishlist_total_items": 3,
    "cart_item_id": 19,
    "product_id": 101
  }
}
```

---

### 2.7 Apply Coupon Code to Cart
- **Method & URL**: `POST /api/v1/client/{client_id}/cart/coupons/apply`
- **Description**: Validates the promo code against cart subtotal, client eligibility, and product rules, then calculates and applies the discount.

#### Path Variables:
| Variable | Type | Description |
| :--- | :--- | :--- |
| `client_id` | `Long` | Unique identifier of the client / user |

#### Request Body:
```json
{
  "coupon_code": "FESTIVE15"
}
```

#### Response Body (`200 OK`):
```json
{
  "status": "SUCCESS",
  "message": "Coupon FESTIVE15 applied successfully. You saved ₹5,250.00!",
  "data": {
    "cart_id": 1,
    "client_id": 5,
    "applied_coupon": {
      "coupon_code": "FESTIVE15",
      "is_active": true,
      "discount_type": "PERCENTAGE",
      "discount_percentage": 15,
      "discount_amount": 5250.00,
      "min_order_value": 20000.00,
      "max_discount_cap": 6000.00
    },
    "pricing_summary": {
      "sub_total": 35000.00,
      "coupon_discount": 5250.00,
      "delivery_charges": 0.00,
      "gst_breakdown": {
        "cgst_amount": 2677.50,
        "sgst_amount": 2677.50,
        "total_tax": 5355.00
      },
      "net_payable_amount": 29750.00
    }
  }
}
```

#### Error Response Example (If Coupon is Inactive or Expired - `400 Bad Request`):
```json
{
  "status": "ERROR",
  "error_code": "COUPON_INACTIVE",
  "message": "Coupon FESTIVE15 is currently inactive or has expired"
}
```

---

### 2.8 Remove Coupon Code from Cart
- **Method & URL**: `DELETE /api/v1/client/{client_id}/cart/coupons/remove`
- **Description**: Removes the currently applied coupon from the client's cart and recalculates net totals.

#### Path Variables:
| Variable | Type | Description |
| :--- | :--- | :--- |
| `client_id` | `Long` | Unique identifier of the client / user |

#### Request Body:
*(None)*

#### Response Body (`200 OK`):
```json
{
  "status": "SUCCESS",
  "message": "Coupon removed successfully",
  "data": {
    "cart_id": 1,
    "client_id": 5,
    "applied_coupon": null,
    "pricing_summary": {
      "sub_total": 35000.00,
      "coupon_discount": 0.00,
      "delivery_charges": 500.00,
      "gst_breakdown": {
        "cgst_amount": 3150.00,
        "sgst_amount": 3150.00,
        "total_tax": 6300.00
      },
      "net_payable_amount": 35500.00
    }
  }
}
```

---

### 2.9 Clear Entire Cart
- **Method & URL**: `DELETE /api/v1/client/{client_id}/cart/clear`
- **Description**: Clears all items and applied coupons from the client's cart.

#### Path Variables:
| Variable | Type | Description |
| :--- | :--- | :--- |
| `client_id` | `Long` | Unique identifier of the client / user |

#### Request Body:
*(None)*

#### Response Body (`200 OK`):
```json
{
  "status": "SUCCESS",
  "message": "Cart cleared successfully",
  "data": {
    "cart_id": 1,
    "client_id": 5,
    "total_items_count": 0,
    "items": [],
    "pricing_summary": {
      "mrp_total": 0.00,
      "item_discount_total": 0.00,
      "sub_total": 0.00,
      "coupon": null,
      "coupon_discount": 0.00,
      "delivery_charges": 0.00,
      "gst_breakdown": {
        "cgst_amount": 0.00,
        "sgst_amount": 0.00,
        "total_tax": 0.00
      },
      "net_payable_amount": 0.00
    }
  }
}
```
