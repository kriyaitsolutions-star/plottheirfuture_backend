# PlotTheirFuture — REST API Documentation

- **Base URL:** `http://localhost:8080`
- **API Version:** `v1`
- **Default Format:** `application/json`
- **CORS:** Enabled for all origins (`*`)

---

## Table of Contents
1. [User Management (`/api/v1/users`)](#1-user-management)
2. [Location Management (`/api/v1/locations`)](#2-location-management)
3. [Web App & Branding (`/api/v1/web-apps`)](#3-web-app--branding)
4. [Wedding Campaigns (`/api/v1/weddings`)](#4-wedding-campaigns)
5. [Ceremony Itinerary (`/api/v1/ceremonies`)](#5-ceremony-itinerary)
6. [Guest Roster & Invitations (`/api/v1/invitations`)](#6-guest-roster--invitations)
7. [Floor Plan Zones (`/api/v1/floor-plan-zones`)](#7-floor-plan-zones)
8. [Floor Plan Tiles & Cart Hold (`/api/v1/floor-plan-tiles`)](#8-floor-plan-tiles--cart-hold)
9. [Contributions & Escrow Checkout (`/api/v1/contributions`)](#9-contributions--escrow-checkout)
10. [Section 56(2)(x) Tax Receipts (`/api/v1/tax-receipts`)](#10-section-562x-tax-receipts)
11. [Standard Error Responses](#11-standard-error-responses)

---

## 1. User Management

### 1.1 List All Users
- **Method:** `GET`
- **Endpoint:** `/api/v1/users`
- **Response:** `200 OK`
```json
[
  {
    "id": 1,
    "name": "Sanvi Reddy",
    "mobileNumber": "+919848012345",
    "role": "ADMIN",
    "otp": "123456",
    "roleId": 1,
    "registered": true,
    "image": "https://images.unsplash.com/photo-1534528741775-53994a69daeb",
    "locationId": 1
  }
]
```

### 1.2 Get User by ID
- **Method:** `GET`
- **Endpoint:** `/api/v1/users/{id}`
- **Response:** `200 OK`

### 1.3 Find User by Mobile Number
- **Method:** `GET`
- **Endpoint:** `/api/v1/users/by-mobile?mobileNumber=+919848012345`
- **Response:** `200 OK`

### 1.4 Create User
- **Method:** `POST`
- **Endpoint:** `/api/v1/users`
- **Request Body:**
```json
{
  "name": "Ananya Rao",
  "password": "SecurePassword@123",
  "mobileNumber": "+919123456780",
  "role": "GUEST",
  "otp": "456789",
  "roleId": 3,
  "registered": false,
  "image": "https://example.com/avatar.jpg",
  "locationId": 1
}
```
> **Security Note:** Passwords sent in the request body are automatically hashed using **BCrypt** before database persistence. Outgoing user responses omit the password field (`WRITE_ONLY`).

- **Response:** `201 Created`

### 1.5 Update User
- **Method:** `PUT`
- **Endpoint:** `/api/v1/users/{id}`
- **Request Body:** (Partial or full fields)
- **Response:** `200 OK`

### 1.6 Delete User
- **Method:** `DELETE`
- **Endpoint:** `/api/v1/users/{id}`
- **Response:** `204 No Content`

---

## 2. Location Management

### 2.1 List All Locations
- **Method:** `GET`
- **Endpoint:** `/api/v1/locations`
- **Response:** `200 OK`

### 2.2 Get Location by ID
- **Method:** `GET`
- **Endpoint:** `/api/v1/locations/{id}`
- **Response:** `200 OK`

### 2.3 Get Prioritized Locations for a User
- **Method:** `GET`
- **Endpoint:** `/api/v1/locations/user/{userId}`
- **Response:** `200 OK`
```json
[
  {
    "id": 1,
    "userId": 1,
    "city": "Hyderabad",
    "mandal": "Gachibowli",
    "district": "Rangareddy",
    "constituency": "Serilingampally",
    "pincode": "500032",
    "active": true,
    "priority": 1
  }
]
```

### 2.4 Create Location
- **Method:** `POST`
- **Endpoint:** `/api/v1/locations`
- **Request Body:**
```json
{
  "userId": 1,
  "city": "Hyderabad",
  "mandal": "Madhapur",
  "district": "Rangareddy",
  "constituency": "Serilingampally",
  "pincode": "500081",
  "active": true,
  "priority": 2
}
```
- **Response:** `201 Created`

### 2.5 Update Location
- **Method:** `PUT`
- **Endpoint:** `/api/v1/locations/{id}`
- **Response:** `200 OK`

### 2.6 Delete Location
- **Method:** `DELETE`
- **Endpoint:** `/api/v1/locations/{id}`
- **Response:** `204 No Content`

---

## 3. Web App & Branding

> **Note:** The `type` field strictly accepts only `"app"` or `"website"`.

### 3.1 List All Web Apps
- **Method:** `GET`
- **Endpoint:** `/api/v1/web-apps`
- **Response:** `200 OK`

### 3.2 Get Web App by ID
- **Method:** `GET`
- **Endpoint:** `/api/v1/web-apps/{id}`
- **Response:** `200 OK`

### 3.3 Get Active Web Apps
- **Method:** `GET`
- **Endpoint:** `/api/v1/web-apps/active`
- **Response:** `200 OK`

### 3.4 Filter Web Apps by Type
- **Method:** `GET`
- **Endpoint:** `/api/v1/web-apps/by-type?type=app` *(or `website`)*
- **Response:** `200 OK`

### 3.5 Create Web App Configuration
- **Method:** `POST`
- **Endpoint:** `/api/v1/web-apps`
- **Request Body:**
```json
{
  "name": "PlotTheirFuture Portal",
  "type": "website",
  "active": true,
  "primaryColour": "#1E3A8A",
  "secondaryColour": "#D97706",
  "themeColour": "#F8FAFC",
  "heroImages": [
    "https://example.com/banner1.jpg",
    "https://example.com/banner2.jpg"
  ]
}
```
- **Response:** `201 Created`

### 3.6 Update Web App
- **Method:** `PUT`
- **Endpoint:** `/api/v1/web-apps/{id}`
- **Response:** `200 OK`

### 3.7 Add Hero Image
- **Method:** `POST`
- **Endpoint:** `/api/v1/web-apps/{id}/hero-images`
- **Request Body:**
```json
{
  "imageUrl": "https://example.com/new-hero-banner.jpg"
}
```
- **Response:** `200 OK`

### 3.8 Remove Hero Image
- **Method:** `DELETE`
- **Endpoint:** `/api/v1/web-apps/{id}/hero-images`
- **Request Body:**
```json
{
  "imageUrl": "https://example.com/new-hero-banner.jpg"
}
```
- **Response:** `200 OK`

### 3.9 Delete Web App
- **Method:** `DELETE`
- **Endpoint:** `/api/v1/web-apps/{id}`
- **Response:** `204 No Content`

---

## 4. Wedding Campaigns

### 4.1 List All Weddings
- **Method:** `GET`
- **Endpoint:** `/api/v1/weddings`
- **Response:** `200 OK`

### 4.2 Get Wedding by ID
- **Method:** `GET`
- **Endpoint:** `/api/v1/weddings/{id}`
- **Response:** `200 OK`

### 4.3 Look Up Wedding by Slug (Microsite Route)
- **Method:** `GET`
- **Endpoint:** `/api/v1/weddings/slug/{slug}` (e.g. `/api/v1/weddings/slug/sanvi-santosh`)
- **Response:** `200 OK`
```json
{
  "id": 1,
  "slug": "sanvi-santosh",
  "brideName": "Sanvi Reddy",
  "groomName": "Santosh Varma",
  "targetSft": 1200,
  "rateSft": 4500.0,
  "videoStreamUrl": "https://cdn.plottheirfuture.in/stream/sanvi-santosh/master.m3u8",
  "coverImageUrl": "https://images.unsplash.com/photo-1583939003579-730e3918a45a",
  "status": "ACTIVE",
  "builderReraId": "PRM/KA/RERA/1251/310/PR/210312/004018",
  "escrowAccountNumber": "ESC-HDFC-99201948102"
}
```

### 4.4 Filter by Campaign Status
- **Method:** `GET`
- **Endpoint:** `/api/v1/weddings/status?status=ACTIVE` *(Options: `ACTIVE`, `PAUSED`, `COMPLETED`)*
- **Response:** `200 OK`

### 4.5 Create Wedding Campaign
- **Method:** `POST`
- **Endpoint:** `/api/v1/weddings`
- **Request Body:**
```json
{
  "slug": "kavya-karthik",
  "brideName": "Kavya Sharma",
  "groomName": "Karthik Iyer",
  "targetSft": 1200,
  "rateSft": 4800.0,
  "videoStreamUrl": "https://example.com/video.m3u8",
  "coverImageUrl": "https://example.com/cover.jpg",
  "status": "ACTIVE",
  "builderReraId": "PRM/KA/RERA/2026/001928",
  "escrowAccountNumber": "ESC-ICICI-4819283719"
}
```
- **Response:** `201 Created`

### 4.6 Update Wedding Campaign
- **Method:** `PUT`
- **Endpoint:** `/api/v1/weddings/{id}`
- **Response:** `200 OK`

### 4.7 Delete Wedding Campaign
- **Method:** `DELETE`
- **Endpoint:** `/api/v1/weddings/{id}`
- **Response:** `204 No Content`

---

## 5. Ceremony Itinerary

### 5.1 List All Ceremonies
- **Method:** `GET`
- **Endpoint:** `/api/v1/ceremonies`
- **Response:** `200 OK`

### 5.2 Get Itinerary for a Wedding (Sorted Chronologically)
- **Method:** `GET`
- **Endpoint:** `/api/v1/ceremonies/wedding/{weddingId}`
- **Response:** `200 OK`
```json
[
  {
    "id": 1,
    "weddingId": 1,
    "name": "Haldi & Mehendi",
    "eventDate": "2026-11-14",
    "startTime": "10:00:00",
    "endTime": "14:00:00",
    "venueName": "The Palm Meadows, Hyderabad",
    "venueMapUrl": "https://maps.google.com/?q=Palm+Meadows+Hyderabad"
  },
  {
    "id": 2,
    "weddingId": 1,
    "name": "Wedding Muhurtham",
    "eventDate": "2026-11-15",
    "startTime": "09:45:00",
    "endTime": "12:30:00",
    "venueName": "Grand Palace Convention Hall, Hyderabad",
    "venueMapUrl": "https://maps.google.com/?q=Grand+Palace+Hyderabad"
  }
]
```

### 5.3 Create Ceremony
- **Method:** `POST`
- **Endpoint:** `/api/v1/ceremonies`
- **Request Body:**
```json
{
  "weddingId": 1,
  "name": "Sangeet Night",
  "eventDate": "2026-11-14",
  "startTime": "18:30:00",
  "endTime": "23:00:00",
  "venueName": "Grand Ballroom, Hyderabad",
  "venueMapUrl": "https://maps.google.com"
}
```
- **Response:** `201 Created`

### 5.4 Update Ceremony
- **Method:** `PUT`
- **Endpoint:** `/api/v1/ceremonies/{id}`
- **Response:** `200 OK`

### 5.5 Delete Ceremony
- **Method:** `DELETE`
- **Endpoint:** `/api/v1/ceremonies/{id}`
- **Response:** `204 No Content`

---

## 6. Guest Roster & Invitations

### 6.1 List Wedding Guest Roster
- **Method:** `GET`
- **Endpoint:** `/api/v1/invitations/wedding/{weddingId}`
- **Response:** `200 OK`

### 6.2 Invitee Dynamic OTP Validation Endpoint
- **Method:** `GET`
- **Endpoint:** `/api/v1/invitations/wedding/{weddingId}/by-phone?phone=+919988776655`
- **Response:** `200 OK`
```json
{
  "id": 1,
  "weddingId": 1,
  "phoneE164": "+919988776655",
  "guestName": "Malli Karjuna",
  "cohort": "FAMILY",
  "ceremonies": [
    "Haldi & Mehendi",
    "Wedding Muhurtham",
    "Grand Reception"
  ],
  "refLead": "uncle",
  "rsvpStatus": "ATTENDING",
  "headcount": 2
}
```

### 6.3 Create Invitation Entry
- **Method:** `POST`
- **Endpoint:** `/api/v1/invitations`
- **Request Body:**
```json
{
  "weddingId": 1,
  "phoneE164": "+919876543210",
  "guestName": "Pooja Mehta",
  "cohort": "FRIENDS",
  "ceremonies": ["Grand Reception"],
  "refLead": "college",
  "rsvpStatus": "PENDING",
  "headcount": 1
}
```
- **Response:** `201 Created`

### 6.4 1-Click RSVP Update
- **Method:** `PATCH`
- **Endpoint:** `/api/v1/invitations/{id}/rsvp`
- **Request Body:**
```json
{
  "rsvpStatus": "ATTENDING",
  "headcount": 3
}
```
- **Response:** `200 OK`

### 6.5 Update Invitation
- **Method:** `PUT`
- **Endpoint:** `/api/v1/invitations/{id}`
- **Response:** `200 OK`

### 6.6 Delete Invitation
- **Method:** `DELETE`
- **Endpoint:** `/api/v1/invitations/{id}`
- **Response:** `204 No Content`

---

## 7. Floor Plan Zones

### 7.1 List Rooms for a Wedding
- **Method:** `GET`
- **Endpoint:** `/api/v1/floor-plan-zones/wedding/{weddingId}`
- **Response:** `200 OK`
```json
[
  {
    "id": 1,
    "weddingId": 1,
    "roomName": "Living Room",
    "totalSft": 400,
    "fundedSft": 45,
    "svgElementId": "svg_living_room"
  },
  {
    "id": 2,
    "weddingId": 1,
    "roomName": "Master Bedroom",
    "totalSft": 250,
    "fundedSft": 20,
    "svgElementId": "svg_master_bed"
  }
]
```

### 7.2 Create Room Zone
- **Method:** `POST`
- **Endpoint:** `/api/v1/floor-plan-zones`
- **Request Body:**
```json
{
  "weddingId": 1,
  "roomName": "Balcony",
  "totalSft": 80,
  "fundedSft": 0,
  "svgElementId": "svg_balcony"
}
```
- **Response:** `201 Created`

### 7.3 Update Room Zone
- **Method:** `PUT`
- **Endpoint:** `/api/v1/floor-plan-zones/{id}`
- **Response:** `200 OK`

### 7.4 Delete Room Zone
- **Method:** `DELETE`
- **Endpoint:** `/api/v1/floor-plan-zones/{id}`
- **Response:** `204 No Content`

---

## 8. Floor Plan Tiles & Cart Hold

### 8.1 List All Tiles for a Wedding
- **Method:** `GET`
- **Endpoint:** `/api/v1/floor-plan-tiles/wedding/{weddingId}`
- **Response:** `200 OK`

### 8.2 Get Tiles by Room Zone
- **Method:** `GET`
- **Endpoint:** `/api/v1/floor-plan-tiles/wedding/{weddingId}/zone/{zoneId}`
- **Response:** `200 OK`

### 8.3 Get Available Tiles
- **Method:** `GET`
- **Endpoint:** `/api/v1/floor-plan-tiles/wedding/{weddingId}/status?status=OPEN`
- **Response:** `200 OK`

### 8.4 10-Minute Temporary Cart Hold
- **Method:** `POST`
- **Endpoint:** `/api/v1/floor-plan-tiles/{id}/lock`
- **Request Body:**
```json
{
  "phone": "+919988776655",
  "minutes": 10
}
```
- **Response:** `200 OK`
```json
{
  "id": 2,
  "weddingId": 1,
  "zoneId": 1,
  "sftSize": 10,
  "status": "HOLD",
  "lockedUntil": "2026-10-27T13:20:00",
  "lockedByPhone": "+919988776655",
  "donorId": null
}
```

### 8.5 Mark Tile Funded (Post-Payment)
- **Method:** `POST`
- **Endpoint:** `/api/v1/floor-plan-tiles/{id}/fund`
- **Request Body:**
```json
{
  "donorId": 1
}
```
- **Response:** `200 OK`

---

## 9. Contributions & Escrow Checkout

### 9.1 List All Contributions for a Wedding
- **Method:** `GET`
- **Endpoint:** `/api/v1/contributions/wedding/{weddingId}`
- **Response:** `200 OK`

### 9.2 List Successful Contributions & Stage Blessings
- **Method:** `GET`
- **Endpoint:** `/api/v1/contributions/wedding/{weddingId}/successful`
- **Response:** `200 OK`
```json
[
  {
    "id": 1,
    "weddingId": 1,
    "invitationId": 1,
    "unitsSft": 5,
    "amount": 22500.0,
    "txnId": "TXN_UPI_992019482",
    "bankRef": "HDFC0001892837",
    "paymentStatus": "SUCCESS",
    "isAnonymous": false,
    "blessingMessage": "May your new home be filled with endless joy and laughter! - Malli",
    "createdAt": "2026-10-27T12:00:00"
  }
]
```

### 9.3 Initiate Escrow Contribution
- **Method:** `POST`
- **Endpoint:** `/api/v1/contributions`
- **Request Body:**
```json
{
  "weddingId": 1,
  "invitationId": 1,
  "unitsSft": 5,
  "amount": 22500.0,
  "paymentStatus": "INITIATED",
  "isAnonymous": false,
  "blessingMessage": "Wishing you both a lifetime of happiness in your dream home!"
}
```
- **Response:** `201 Created`

### 9.4 Confirm Payment Webhook / Callback
- **Method:** `POST`
- **Endpoint:** `/api/v1/contributions/{id}/confirm`
- **Request Body:**
```json
{
  "txnId": "UPI_REF_892189028",
  "bankRef": "HDFC000981928"
}
```
- **Response:** `200 OK` (Updates status to `SUCCESS`)

---

## 10. Section 56(2)(x) Tax Receipts

### 10.1 Generate Tax-Exempt Certificate
- **Method:** `POST`
- **Endpoint:** `/api/v1/tax-receipts/generate`
- **Request Body:**
```json
{
  "contributionId": 1,
  "pdfUrl": "https://plottheirfuture.in/receipts/PTF-SEC-892104.pdf"
}
```
- **Response:** `201 Created`
```json
{
  "id": 1,
  "contributionId": 1,
  "receiptNo": "PTF-SEC-892104",
  "exemptClause": "Section 56(2)(x) of Indian Income Tax Act",
  "digitalSignatureHash": "9a8fbc83d91e84a92c0182b810948ac019",
  "pdfUrl": "https://plottheirfuture.in/receipts/PTF-SEC-892104.pdf",
  "generatedAt": "2026-10-27T12:00:00"
}
```

### 10.2 Look Up Receipt by Receipt Number
- **Method:** `GET`
- **Endpoint:** `/api/v1/tax-receipts/by-number?receiptNo=PTF-SEC-892104`
- **Response:** `200 OK`

### 10.3 Look Up Receipt by Contribution ID
- **Method:** `GET`
- **Endpoint:** `/api/v1/tax-receipts/by-contribution/{contributionId}`
- **Response:** `200 OK`

---

## 11. Standard Error Responses

All endpoints return predictable JSON error payloads managed by [GlobalExceptionHandler.java](file:///c:/Users/malli/OneDrive/Desktop/PLOTTHEIRFUTURE/plottheirfuture_backend/src/main/java/com/plottheirfuture/plottheirfuture_backend/exception/GlobalExceptionHandler.java):

### 404 Not Found
```json
{
  "timestamp": "2026-10-27T13:10:00",
  "status": 404,
  "error": "Not Found",
  "message": "Wedding not found with slug: invalid-slug"
}
```

### 400 Bad Request / Validation Failure
```json
{
  "timestamp": "2026-10-27T13:10:00",
  "status": 400,
  "error": "Validation Failed",
  "details": {
    "type": "Type is required and must be either 'app' or 'website'",
    "mobileNumber": "must not be blank"
  }
}
```
