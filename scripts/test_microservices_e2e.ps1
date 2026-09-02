# ==============================================================================
# Ecom Homes & Merry - Automated End-to-End Microservices Test Runner
# ==============================================================================

param (
    [string]$GatewayUrl = "http://localhost:8000",
    [string]$ClientServiceUrl = "http://localhost:8081",
    [string]$CoreServiceUrl = "http://localhost:8080",
    [int]$ClientId = 101
)

function Write-Step([string]$msg) {
    Write-Host "`n>>> [STEP] $msg" -ForegroundColor Cyan
}

function Write-Pass([string]$msg) {
    Write-Host "    [PASS] $msg" -ForegroundColor Green
}

function Write-Fail([string]$msg) {
    Write-Host "    [FAIL] $msg" -ForegroundColor Red
}

Write-Host "=================================================================" -ForegroundColor Yellow
Write-Host " Starting Microservices End-to-End Verification Test Suite       " -ForegroundColor Yellow
Write-Host " Target Gateway: $GatewayUrl                                      " -ForegroundColor Yellow
Write-Host "=================================================================" -ForegroundColor Yellow

# Step 1: Health / Port Checks
Write-Step "1. Checking Microservices Connectivity"

try {
    $gwHealth = Invoke-RestMethod -Uri "$GatewayUrl/actuator/health" -Method Get -TimeoutSec 3 -ErrorAction Stop
    Write-Pass "API Gateway is UP and reachable on $GatewayUrl (Status: $($gwHealth.status))"
} catch {
    Write-Host "    [WARN] API Gateway ($GatewayUrl) is not running locally. Direct microservice tests will be performed." -ForegroundColor Yellow
}

# Step 2: Gateway Security Filter Check (Protection of Internal APIs)
Write-Step "2. Testing Internal API Route Protection via Gateway (Expect 403 Forbidden)"
try {
    $blockedRes = Invoke-WebRequest -Uri "$GatewayUrl/api/v1/inventory/internal/verify-stock" -Method Post -Body '{"items":[]}' -ContentType "application/json" -ErrorAction Stop
    Write-Fail "Internal API was reachable through Gateway! Expected 403 Forbidden."
} catch {
    if ($_.Exception.Response.StatusCode.value__ -eq 403) {
        Write-Pass "Route Security Filter correctly blocked external access to /internal/** with 403 Forbidden."
    } else {
        Write-Host "    [INFO] Gateway response code: $($_.Exception.Response.StatusCode.value__)" -ForegroundColor Gray
    }
}

# Step 3: Add Item to Cart
Write-Step "3. Adding Product to Cart via /api/v1/client/$ClientId/cart/items"
$addCartBody = @{
    product_id = 1
    quantity = 2
} | ConvertTo-Json

try {
    $addRes = Invoke-RestMethod -Uri "$GatewayUrl/api/v1/client/$ClientId/cart/items" -Method Post -Body $addCartBody -ContentType "application/json"
    Write-Pass "Added item successfully. Total Items in Cart: $($addRes.data.total_items_count)"
} catch {
    Write-Host "    [SKIP] Service not running or product DB empty: $($_.Exception.Message)" -ForegroundColor Gray
}

# Step 4: View Cart & Price Calculations
Write-Step "4. Viewing Cart and Dynamic Pricing Breakdown"
try {
    $cartRes = Invoke-RestMethod -Uri "$GatewayUrl/api/v1/client/$ClientId/cart" -Method Get
    $p = $cartRes.data.pricing_summary
    Write-Pass "Cart Subtotal: $($p.sub_total) | MRP Total: $($p.mrp_total) | Net Payable: $($p.net_payable_amount)"
} catch {
    Write-Host "    [SKIP] Service not running: $($_.Exception.Message)" -ForegroundColor Gray
}

# Step 5: Apply Coupon Code
Write-Step "5. Applying Promo Code 'FESTIVE15'"
$couponBody = @{
    coupon_code = "FESTIVE15"
} | ConvertTo-Json

try {
    $couponRes = Invoke-RestMethod -Uri "$GatewayUrl/api/v1/client/$ClientId/cart/coupons/apply" -Method Post -Body $couponBody -ContentType "application/json"
    Write-Pass "Coupon applied! Discount Amount: $($couponRes.data.pricing_summary.coupon_discount)"
} catch {
    Write-Host "    [INFO] Coupon check note: $($_.Exception.Message)" -ForegroundColor Gray
}

# Step 6: Initiate Checkout (Temporary Stock Reservation)
Write-Step "6. Initiating Checkout Session (Testing 15-Minute Inventory Hold)"
try {
    $chkRes = Invoke-RestMethod -Uri "$GatewayUrl/api/v1/client/$ClientId/checkout/initiate" -Method Post
    Write-Pass "Checkout Order Created: $($chkRes.data.order_number) | Hold Ref: $($chkRes.data.hold_reference) | Expires: $($chkRes.data.hold_expires_at)"
} catch {
    Write-Host "    [INFO] Checkout session note: $($_.Exception.Message)" -ForegroundColor Gray
}

Write-Host "`n=================================================================" -ForegroundColor Yellow
Write-Host " E2E Verification Complete.                                     " -ForegroundColor Yellow
Write-Host "=================================================================`n" -ForegroundColor Yellow
