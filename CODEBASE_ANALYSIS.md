# Airline Management System - Complete Codebase Analysis

## Project Overview
This is a **GUI-based Airline Reservation Management System** built in Java using Swing for the UI and MySQL for the database backend. The application handles flight booking, ticket cancellation, and passenger management for Air India.

---

## 1. DATABASE CONNECTION SETUP

### Conn.java
**Purpose:** Database connection manager - handles all MySQL connectivity

**Key Details:**
- **Driver:** `com.mysql.cj.jdbc.Driver` (MySQL Connector/J 9.2.0)
- **Connection URL:** `jdbc:mysql://localhost:3306/airlinemanagementsystem`
- **Credentials:**
  - Username: `root`
  - Password: `Aryan@123`
- **Implementation:** Singleton pattern with static Connection and Statement objects
- **Usage:** All other classes instantiate `Conn()` to access database through public `Statement s`

**Vulnerabilities Identified:**
- SQL Injection risk: Uses string concatenation for SQL queries throughout the codebase
- Hardcoded credentials in source code
- No connection pooling or resource cleanup

---

## 2. DATABASE SCHEMA (Inferred from Code)

### Tables Used:

#### 1. **login** table
```sql
-- User authentication table
Columns: username, password
Usage: Admin/staff authentication in Login.java
```

#### 2. **passenger** table
```sql
-- Passenger/customer information
Columns: 
  - name (VARCHAR)
  - nationality (VARCHAR)
  - phone (VARCHAR)
  - address (VARCHAR)
  - aadhar (VARCHAR) - Primary identifier
  - gender (VARCHAR - "Male" or "Female")
Usage: Stores customer details added via AddCustomer.java
```

#### 3. **flight** table
```sql
-- Available flights information
Columns:
  - f_name (VARCHAR) - Flight name
  - f_code (VARCHAR) - Flight code
  - source (VARCHAR) - Departure city
  - destination (VARCHAR) - Arrival city
Usage: Provides flight list for booking (populated at system setup)
```

#### 4. **reservation** table
```sql
-- Booking records
Columns:
  - PNR (VARCHAR) - Booking reference (format: "PNR-xxxxxx")
  - Ticket_ID (VARCHAR) - Ticket number (format: "TIC-xxxxx")
  - aadhar (VARCHAR) - Passenger reference to `passenger` table
  - name (VARCHAR)
  - nationality (VARCHAR)
  - flightname (VARCHAR)
  - flightcode (VARCHAR)
  - src (VARCHAR) - Source
  - des (VARCHAR) - Destination
  - ddate (VARCHAR) - Date of travel
Usage: Active flight reservations
```

#### 5. **cancel** table
```sql
-- Cancelled ticket records (audit trail)
Columns:
  - PNR (VARCHAR)
  - name (VARCHAR)
  - cancellation_no (VARCHAR) - Random number generated at cancellation
  - flightcode (VARCHAR)
  - ddate (VARCHAR)
Usage: Records of cancelled bookings
```

---

## 3. JAVA FILES SUMMARY

### 3.1 Login.java
**Purpose:** Application entry point - authentication screen

**Key Features:**
- Simple credential-based login form
- Username and password fields
- Three buttons: Submit, Reset, Close
- Validates credentials against `login` table
- Upon successful login → Opens Home window and closes login
- Upon failed login → Shows error dialog

**UI Components:**
- JFrame with white background
- JTextField for username
- JPasswordField for password
- Size: 400x250 pixels, centered at (600, 250)

**Database Interaction:**
- Queries: `SELECT * FROM login WHERE username = ? AND password = ?`

**Main Method:** Entry point of the application
```bash
java airlinemanagementsystem.Login
```

---

### 3.2 Home.java
**Purpose:** Main application menu - navigation hub

**Key Features:**
- Maximized window with Air India branding
- Background image (Front2.png)
- Red heading: "AIR INDIA WELCOMES YOU"
- Menu bar with two main menus:
  1. **Details Menu:**
     - Flight Details
     - Add Customer Details
     - Book Flight
     - Journey Details
     - Cancel Ticket
  2. **Ticket Menu:**
     - Boarding Pass

**Design:**
- Orange background color (224, 93, 25)
- Large window (maximized)
- All menu items are action listeners that open corresponding windows

**Navigation Flow:**
All menu items instantiate their respective classes, creating new windows

---

### 3.3 AddCustomer.java
**Purpose:** Add new passenger to system

**Key Features:**
- Form-based GUI for customer registration
- Input Fields:
  - Name (text field)
  - Nationality (text field)
  - Aadhar Number (text field)
  - Address (text field)
  - Gender (radio buttons: Male/Female)
  - Phone (text field)
- Save button stores data to `passenger` table
- Display customer image icon (emp.png)

**UI Layout:**
- Size: 900x600 pixels, positioned at (300, 150)
- Blue heading with Tahoma font
- White background
- Image displayed on right side

**Database Operation:**
```sql
INSERT INTO passenger VALUES(name, nationality, phone, address, aadhar, gender)
```

---

### 3.4 BookFlight.java
**Purpose:** Book flights for registered passengers

**Key Features:**
1. **Passenger Lookup:**
   - Input Aadhar number
   - "Fetch User" button retrieves passenger details from `passenger` table
   - Auto-populates: Name, Nationality, Address, Gender

2. **Flight Selection:**
   - Source dropdown (loaded from `flight` table)
   - Destination dropdown (loaded from `flight` table)
   - "Fetch Flights" button retrieves flight details
   - Auto-populates: Flight Name, Flight Code

3. **Travel Date:**
   - JDateChooser for selecting date of travel

4. **Booking Confirmation:**
   - "Book Flight" button finalizes reservation
   - Generates random PNR: "PNR-" + random 6-digit number
   - Generates random Ticket ID: "TIC-" + random 5-digit number
   - Inserts into `reservation` table

**UI Layout:**
- Size: 1100x700 pixels, positioned at (200, 50)
- Blue heading
- White background
- Detachable image on right (details.jpg)

**Database Operations:**
```sql
-- Fetch passenger: SELECT * FROM passenger WHERE aadhar = ?
-- Fetch flights: SELECT * FROM flight WHERE source = ? AND destination = ?
-- Book flight: INSERT INTO reservation VALUES(...)
```

---

### 3.5 FlightInfo.java
**Purpose:** Display all available flights in a tabular format

**Key Features:**
- JTable displaying all flights
- Uses **DbUtils library** (rs2xml.jar) to convert ResultSet to TableModel
- Loads data via: `SELECT * FROM flight`
- JScrollPane for scrolling if data exceeds view
- Read-only display

**UI Layout:**
- Size: 800x500 pixels
- Table fills entire frame
- Positioned at (400, 200)

**Dependencies:**
- net.proteanit.sql.DbUtils - for ResultSet to Table conversion

---

### 3.6 JourneyDetails.java
**Purpose:** View booking details for a specific reservation

**Key Features:**
- Input field for PNR number
- "Show Details" button queries reservation
- Displays reservation in table format
- Uses DbUtils library for ResultSet conversion
- Validates if record exists before displaying

**UI Layout:**
- Size: 800x600 pixels
- PNR input with button at top
- Table in center/bottom area
- White background

**Database Operation:**
```sql
SELECT * FROM reservation WHERE PNR = ?
```

---

### 3.7 Cancel.java
**Purpose:** Cancel flight tickets and process refunds

**Key Features:**
1. **Ticket Lookup:**
   - Input PNR number
   - "Show Details" button fetches reservation details
   - Auto-populates: Passenger Name, Flight Code, Date of Travel

2. **Cancellation Process:**
   - Auto-generates random cancellation number
   - "Cancel" button performs:
     - Insert into `cancel` table (audit trail)
     - Delete from `reservation` table
   - Shows confirmation message

**UI Layout:**
- Size: 800x450 pixels
- Positioned at (350, 150)
- Cancel image icon on right
- Fields display data fetched from database

**Database Operations:**
```sql
-- Fetch reservation: SELECT * FROM reservation WHERE PNR = ?
-- Record cancellation: INSERT INTO cancel VALUES(...)
-- Remove booking: DELETE FROM reservation WHERE PNR = ?
```

---

### 3.8 BoardingPass.java
**Purpose:** Generate and display boarding pass for confirmed bookings

**Key Features:**
- Input PNR number
- "Enter" button fetches reservation details
- Displays boarding pass-like format with:
  - Passenger Name
  - Nationality
  - Source and Destination
  - Flight Name and Code
  - Travel Date
  - Air India branding and logo

**UI Layout:**
- Size: 1000x450 pixels
- Positioned at (300, 150)
- Air India logo displayed (airindia.png)
- Professional boarding pass styling
- Blue subheading "Boarding Pass"

**Database Operation:**
```sql
SELECT * FROM reservation WHERE PNR = ?
```

---

## 4. APPLICATION WORKFLOW & FEATURES

### Startup Flow:
```
1. Run: java airlinemanagementsystem.Login
2. Login screen appears
3. User enters credentials (username/password)
4. Upon success → Home window opens → Login closes
```

### Main Features:

#### Feature 1: Flight Booking System
- **Users:** Registered passengers or new ones
- **Steps:**
  1. Add Customer (if new)
  2. Book Flight (select source/destination/date)
  3. System generates PNR and Ticket ID
  4. Confirmation message shown

#### Feature 2: Ticket Management
- View flight details (FlightInfo)
- View specific booking (JourneyDetails)
- View boarding pass (BoardingPass)

#### Feature 3: Ticket Cancellation
- Lookup reservation by PNR
- Generate cancellation reference
- Cancel ticket with audit trail in `cancel` table

#### Feature 4: Passenger Management
- Add new passenger with personal details
- Store Aadhar as unique identifier
- Support gender classification

---

## 5. COMPILATION & EXECUTION

### Build Batch File: compile_and_run.bat

**Classpath Configuration:**
```
.;.\lib\rs2xml.jar;.\lib\jcalendar-tz-1.3.3-4.jar;.\lib\mysql-connector-j-9.2.0\mysql-connector-j-9.2.0.jar
```

**Libraries Used:**
1. **mysql-connector-j-9.2.0.jar** - MySQL JDBC driver
2. **rs2xml.jar** - DbUtils library (ResultSet to TableModel conversion)
3. **jcalendar-tz-1.3.3-4.jar** - Date picker component (JDateChooser)

**Execution Steps:**
1. Batch file compiles all Java files: `javac airlinemanagementsystem\*.java`
2. If compilation successful → Runs: `java airlinemanagementsystem.Login`
3. If compilation fails → Shows error message

---

## 6. SYSTEM DEPENDENCIES

### External Libraries:
1. **MySQL Connector/J 9.2.0** - Database driver
2. **rs2xml (DbUtils)** - Converts SQL ResultSet to Swing JTable model
3. **JCalendar-tz 1.3.3-4** - Provides JDateChooser component for date selection

### Database:
- MySQL Server
- Database name: `airlinemanagementsystem`
- Default credentials: root / Aryan@123

### Java Version:
- Java 8+ (uses Swing, JDBC, standard libraries)

---

## 7. KNOWN ISSUES & SECURITY CONCERNS

### Critical Issues:
1. **SQL Injection Vulnerability:**
   - All queries use string concatenation
   - Example: `"select * from login where username = '"+username+"'..."`
   - Mitigation: Use PreparedStatement

2. **Hardcoded Credentials:**
   - Database password visible in source code
   - Mitigation: Use properties file or environment variables

3. **No Input Validation:**
   - No sanitization of user input
   - No null checks

4. **No Error Handling:**
   - Generic exception catching with printStackTrace
   - No rollback on transaction failures

5. **Session Management:**
   - No user session tracking
   - No logout functionality
   - No role-based access control

### UI Issues:
1. Hard-coded image paths that may fail if files missing
2. No file existence validation for images
3. Potential ClassCastException in BookFlight.java when accessing JDateChooser component

---

## 8. CLASS RELATIONSHIPS DIAGRAM

```
Login.java (Entry Point)
   ↓
Home.java (Navigation Hub)
   ├→ AddCustomer.java (Customer Registration)
   ├→ BookFlight.java (Reservation)
   │   └→ Retrieves: passenger, flight tables
   │   └→ Inserts: reservation table
   ├→ FlightInfo.java (View all flights)
   │   └→ Reads: flight table
   ├→ JourneyDetails.java (View booking details)
   │   └→ Reads: reservation table
   ├→ Cancel.java (Ticket cancellation)
   │   └→ Reads: reservation table
   │   └→ Inserts: cancel table
   │   └→ Deletes: reservation table
   └→ BoardingPass.java (View boarding pass)
       └→ Reads: reservation table

All classes → Conn.java (Database Connection)
```

---

## 9. DATABASE SETUP REQUIREMENTS

### Prerequisite SQL Commands:

```sql
-- Create database
CREATE DATABASE airlinemanagementsystem;
USE airlinemanagementsystem;

-- Create login table
CREATE TABLE login (
    username VARCHAR(50),
    password VARCHAR(50)
);

-- Create passenger table
CREATE TABLE passenger (
    name VARCHAR(100),
    nationality VARCHAR(50),
    phone VARCHAR(20),
    address VARCHAR(200),
    aadhar VARCHAR(20) PRIMARY KEY,
    gender VARCHAR(10)
);

-- Create flight table
CREATE TABLE flight (
    f_name VARCHAR(100),
    f_code VARCHAR(20),
    source VARCHAR(50),
    destination VARCHAR(50)
);

-- Create reservation table
CREATE TABLE reservation (
    PNR VARCHAR(50) PRIMARY KEY,
    Ticket_ID VARCHAR(50),
    aadhar VARCHAR(20),
    name VARCHAR(100),
    nationality VARCHAR(50),
    flightname VARCHAR(100),
    flightcode VARCHAR(20),
    src VARCHAR(50),
    des VARCHAR(50),
    ddate VARCHAR(50),
    FOREIGN KEY (aadhar) REFERENCES passenger(aadhar)
);

-- Create cancel table
CREATE TABLE cancel (
    PNR VARCHAR(50),
    name VARCHAR(100),
    cancellation_no VARCHAR(50),
    flightcode VARCHAR(20),
    ddate VARCHAR(50)
);

-- Insert test login
INSERT INTO login VALUES ('admin', 'password');
```

---

## 10. FILE STRUCTURE

```
Java_project/
├── compile_and_run.bat                    [Build and run script]
├── airlinemanagementsystem/               [Source package]
│   ├── Home.java                          [Main menu]
│   ├── Login.java                         [Authentication]
│   ├── Conn.java                          [DB Connection]
│   ├── AddCustomer.java                   [Customer registration]
│   ├── BookFlight.java                    [Flight booking]
│   ├── FlightInfo.java                    [View all flights]
│   ├── JourneyDetails.java                [View booking details]
│   ├── Cancel.java                        [Ticket cancellation]
│   ├── BoardingPass.java                  [Boarding pass display]
│   ├── *.class                            [Compiled files]
│   └── icons/                             [UI images]
│       ├── Front2.png                     [Home screen background]
│       ├── emp.png                        [Employee/Customer avatar]
│       ├── details.jpg                    [Flight details image]
│       ├── cancel.jpg                     [Cancellation screen image]
│       └── airindia.png                   [Airline logo]
├── lib/                                   [External libraries]
│   ├── rs2xml.jar                         [DbUtils library]
│   ├── jcalendar-tz-1.3.3-4.jar          [Date picker library]
│   └── mysql-connector-j-9.2.0/           [MySQL driver]
└── images/                                [Project assets]
    └── front.jpg
```

---

## 11. SUMMARY

This is a **complete airline reservation system** with:
- ✅ Multi-window GUI application using Swing
- ✅ MySQL database backend
- ✅ Passenger management
- ✅ Flight booking with PNR generation
- ✅ Ticket cancellation with audit trail
- ✅ Boarding pass generation
- ✅ Flight information display
- ⚠️ Basic authentication (needs improvement)
- ⚠️ No real-time availability management
- ⚠️ No payment integration
- ⚠️ Security vulnerabilities (SQL injection, hardcoded credentials)

**Intended Users:** Airlines staff for managing bookings and passenger information

**Production Readiness:** Low - needs security fixes and improvements before deployment
