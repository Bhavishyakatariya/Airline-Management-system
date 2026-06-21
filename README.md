# ✈️ Airline Management System

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-00758F?style=for-the-badge&logo=java&logoColor=white)
![Status](https://img.shields.io/badge/Status-Active-brightgreen?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

A comprehensive desktop-based airline reservation system built with Java Swing and MySQL database. Provides end-to-end flight booking, reservation management, and passenger services.

[Features](#features) • [Tech Stack](#tech-stack) • [Installation](#installation--setup) • [Usage](#usage-guide) • [Database](#database-design) • [Contributing](#contributing)

</div>

---

## 📋 Overview

The **Airline Management System** is a full-featured desktop application designed to streamline airline operations and enhance passenger experience. It provides a user-friendly interface for passengers to book flights, manage reservations, and access boarding passes, while allowing administrators to manage flight information and passenger records.

The system was built to demonstrate proficiency in database design, GUI development, and object-oriented programming principles. It showcases real-world application development with authentication, CRUD operations, and complex business logic.

---

## 🎯 Problem Statement

Airlines face operational challenges in managing flight bookings, passenger information, and reservations efficiently:

- **Manual Processing:** Traditional booking systems require extensive paperwork and manual data entry
- **Inefficient Search:** Passengers struggle to find available flights based on their requirements
- **Poor Record Management:** Difficult to maintain accurate passenger and reservation records
- **No Centralized System:** Lack of integrated platform for bookings, cancellations, and boarding passes
- **Errors & Delays:** Manual systems lead to errors, overbooking, and processing delays

---

## 💡 Solution

The Airline Management System provides:

✅ **Integrated Booking Platform** - Single system for all reservation needs  
✅ **Real-time Flight Search** - Instantly find available flights by route and date  
✅ **Automated PNR Generation** - Unique booking reference numbers auto-generated  
✅ **Passenger Management** - Centralized database for all passenger information  
✅ **Ticket Management** - Book, view, and cancel tickets seamlessly  
✅ **Boarding Passes** - Digital boarding passes for confirmed bookings  
✅ **Secure Authentication** - Login credentials protect user data  
✅ **Audit Trail** - Complete cancellation history for compliance  

---

## ✨ Features

### Core Functionality

| Feature | Description |
|---------|-------------|
| 🔐 **User Authentication** | Secure login system with username/password validation |
| 👤 **Passenger Management** | Register and manage passenger profiles with Aadhar details |
| ✈️ **Flight Search** | Browse all available flights with source, destination, and date filters |
| 🎫 **Flight Booking** | Book flights with automatic PNR and Ticket ID generation |
| 📋 **Journey Details** | View booking information by searching PNR number |
| ❌ **Ticket Cancellation** | Cancel bookings with automatic refund tracking |
| 🎟️ **Boarding Pass** | Generate and display digital boarding passes for confirmed bookings |
| 📊 **Flight Information** | View complete flight details in structured table format |

### Advanced Features

- **Automatic PNR Generation:** Unique booking reference (Format: `PNR-XXXXXX`)
- **Ticket ID Generation:** Auto-generated ticket numbers (Format: `TIC-XXXXX`)
- **Date Picker:** Intuitive calendar interface for date selection
- **Search & Filter:** Find flights and bookings quickly
- **Validation:** Input validation for Aadhar and other passenger details
- **Audit Trail:** Cancellation logs for compliance and record-keeping
- **GUI-Based Operations:** No command-line required; fully graphical interface

---

## 🛠️ Tech Stack

| Layer | Technology | Purpose |
|-------|-----------|---------|
| **Frontend** | Java Swing | Desktop GUI framework |
| **Backend** | Java (Core) | Business logic and application server |
| **Database** | MySQL 5.7+ | Data persistence and management |
| **JDBC Driver** | MySQL Connector/J 9.2.0 | Database connectivity |
| **UI Components** | JDateChooser 1.3.3 | Date picker widget |
| **Utilities** | rs2xml | ResultSet to JTable conversion |
| **Language** | Java 8+ | Primary programming language |
| **Build Tool** | Batch Scripts | Compilation and execution |
| **IDE** | Any Java IDE (NetBeans, Eclipse, IntelliJ) | Development environment |

---

## 🏗️ System Architecture

### Application Workflow

```
┌─────────────────────────────────────────────────────────────┐
│                   AIRLINE MANAGEMENT SYSTEM                 │
│                    Desktop Application                      │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
            ┌──────────────────────┐
            │   Login Module       │
            │ (Authentication)     │
            └──────────┬───────────┘
                       │
                       ▼
            ┌──────────────────────┐
            │   Home Menu Hub      │
            │ (Central Navigation) │
            └──────────┬───────────┘
                       │
        ┌──────────────┼──────────────┬──────────────┐
        ▼              ▼              ▼              ▼
    Add Customer  Book Flight   Flight Info   Journey Details
    ▼              ▼              ▼              ▼
 [DB Write]    [DB Write+Read]  [DB Read]    [DB Read]
        │              │              │              │
        └──────────────┼──────────────┴──────────────┘
                       │
                       ▼
            ┌──────────────────────┐
            │   Cancel Ticket      │
            │ (DB Write+Delete)    │
            └──────────┬───────────┘
                       │
                       ▼
            ┌──────────────────────┐
            │   Boarding Pass      │
            │ (DB Read)            │
            └──────────────────────┘
                       │
                       ▼
            ┌──────────────────────┐
            │  MySQL Database      │
            │  (Persistent Store)  │
            └──────────────────────┘
```

### Component Interaction

```
┌─────────────────────────────────────────────────────────────┐
│                     User Interface Layer                     │
│  (Swing Components: JFrame, JPanel, JTable, JDateChooser)   │
└────────────────────────┬────────────────────────────────────┘
                         │
┌────────────────────────▼────────────────────────────────────┐
│                   Business Logic Layer                       │
│         (Event Handlers & Data Processing)                  │
│  • BookFlight.java    • Cancel.java                         │
│  • AddCustomer.java   • BoardingPass.java                   │
│  • FlightInfo.java    • JourneyDetails.java                 │
└────────────────────────┬────────────────────────────────────┘
                         │
┌────────────────────────▼────────────────────────────────────┐
│                  Data Access Layer                           │
│        (Conn.java - Database Connection Manager)            │
│              JDBC - SQL Query Execution                     │
└────────────────────────┬────────────────────────────────────┘
                         │
┌────────────────────────▼────────────────────────────────────┐
│               MySQL Database Layer                           │
│   (5 Tables: login, passenger, flight, reservation, cancel) │
│            Persistent Data Storage                          │
└─────────────────────────────────────────────────────────────┘
```

### Data Flow

1. **User Interaction** → Swing GUI components capture user input
2. **Event Processing** → Action listeners trigger business logic
3. **Validation** → Input is validated before database operations
4. **Database Query** → JDBC executes SQL through Conn.java
5. **Result Processing** → Results converted to UI components
6. **Display** → Updated GUI reflects database state

---

## 📁 Project Structure

```
Java_project_Airline_Management/
│
├── README.md                           # Project documentation
├── compile_and_run.bat                # Build and execution script
│
├── airlinemanagementsystem/            # Source code directory
│   ├── Login.java                     # Entry point - User authentication
│   ├── Home.java                      # Main menu hub - Navigation
│   ├── Conn.java                      # Database connection manager
│   ├── AddCustomer.java               # Passenger registration module
│   ├── BookFlight.java                # Flight booking engine
│   ├── FlightInfo.java                # Flight information display
│   ├── JourneyDetails.java            # Booking details lookup
│   ├── Cancel.java                    # Ticket cancellation system
│   ├── BoardingPass.java              # Boarding pass generation
│   └── icons/                         # UI images and branding
│       ├── AirIndia_logo.png
│       └── background_images/
│
├── lib/                                # External libraries
│   ├── mysql-connector-j-9.2.0/       # MySQL JDBC driver
│   ├── rs2xml.jar                     # ResultSet to JTable converter
│   └── jcalendar-tz-1.3.3-4.jar      # Date picker component
│
└── images/                             # Documentation images
    └── screenshots/                    # Application screenshots
```

### File Descriptions

| File | Lines | Purpose |
|------|-------|---------|
| **Login.java** | ~150 | Entry point; validates user credentials against login table |
| **Home.java** | ~200 | Main menu interface with 6 navigation buttons |
| **Conn.java** | ~30 | Singleton connection manager; initializes MySQL driver |
| **AddCustomer.java** | ~200 | Form-based passenger registration with Aadhar |
| **BookFlight.java** | ~250 | Flight search and booking with PNR generation |
| **FlightInfo.java** | ~150 | Displays all flights in JTable format |
| **JourneyDetails.java** | ~200 | Query passenger bookings by PNR |
| **Cancel.java** | ~200 | Cancellation with audit trail logging |
| **BoardingPass.java** | ~180 | Generate passenger boarding pass |

---

## 🗄️ Database Design

### Database: `airlinemanagementsystem`

#### 1. **login** Table
User authentication credentials
```sql
CREATE TABLE login (
  username VARCHAR(50) PRIMARY KEY,
  password VARCHAR(50) NOT NULL
);
```
| Column | Type | Constraint |
|--------|------|-----------|
| username | VARCHAR(50) | PRIMARY KEY |
| password | VARCHAR(50) | NOT NULL |

**Purpose:** Store admin/user login credentials for system authentication

---

#### 2. **passenger** Table
Passenger personal information
```sql
CREATE TABLE passenger (
  name VARCHAR(100) NOT NULL,
  nationality VARCHAR(50),
  phone VARCHAR(15),
  address VARCHAR(200),
  aadhar VARCHAR(20) PRIMARY KEY,
  gender CHAR(1)
);
```
| Column | Type | Constraint |
|--------|------|-----------|
| name | VARCHAR(100) | NOT NULL |
| nationality | VARCHAR(50) | - |
| phone | VARCHAR(15) | - |
| address | VARCHAR(200) | - |
| aadhar | VARCHAR(20) | PRIMARY KEY |
| gender | CHAR(1) | - |

**Purpose:** Store permanent passenger profile data; aadhar is unique identifier

---

#### 3. **flight** Table
Available flight information
```sql
CREATE TABLE flight (
  f_name VARCHAR(100),
  f_code VARCHAR(20) PRIMARY KEY,
  source VARCHAR(50),
  destination VARCHAR(50)
);
```
| Column | Type | Constraint |
|--------|------|-----------|
| f_name | VARCHAR(100) | - |
| f_code | VARCHAR(20) | PRIMARY KEY |
| source | VARCHAR(50) | - |
| destination | VARCHAR(50) | - |

**Purpose:** Store flight master data including routes and flight codes

---

#### 4. **reservation** Table
Active flight bookings (main business data)
```sql
CREATE TABLE reservation (
  PNR VARCHAR(50) PRIMARY KEY,
  Ticket_ID VARCHAR(50) UNIQUE,
  aadhar VARCHAR(20),
  name VARCHAR(100),
  nationality VARCHAR(50),
  flightname VARCHAR(100),
  flightcode VARCHAR(20),
  src VARCHAR(50),
  des VARCHAR(50),
  ddate DATE,
  FOREIGN KEY (aadhar) REFERENCES passenger(aadhar)
);
```
| Column | Type | Constraint |
|--------|------|-----------|
| PNR | VARCHAR(50) | PRIMARY KEY |
| Ticket_ID | VARCHAR(50) | UNIQUE |
| aadhar | VARCHAR(20) | FOREIGN KEY |
| name | VARCHAR(100) | - |
| nationality | VARCHAR(50) | - |
| flightname | VARCHAR(100) | - |
| flightcode | VARCHAR(20) | - |
| src | VARCHAR(50) | - |
| des | VARCHAR(50) | - |
| ddate | DATE | - |

**Purpose:** Core table storing all active flight bookings with PNR and Ticket IDs

---

#### 5. **cancel** Table
Audit trail for cancelled bookings
```sql
CREATE TABLE cancel (
  PNR VARCHAR(50),
  name VARCHAR(100),
  cancellation_no VARCHAR(50) PRIMARY KEY,
  flightcode VARCHAR(20),
  ddate DATE
);
```
| Column | Type | Constraint |
|--------|------|-----------|
| PNR | VARCHAR(50) | - |
| name | VARCHAR(100) | - |
| cancellation_no | VARCHAR(50) | PRIMARY KEY |
| flightcode | VARCHAR(20) | - |
| ddate | DATE | - |

**Purpose:** Log all cancellations for compliance and audit purposes

---

### Database Relationships

```
passenger (aadhar) ─────────► reservation (aadhar FK)
                                     │
                                     │ PNR reference
                                     ▼
                              cancel (PNR)

flight (f_code) ───────────► reservation (flightcode)
                                     │
                                     │ flightcode reference
                                     ▼
                              cancel (flightcode)
```

---

## 🔌 API Endpoints / Module Operations

### User Authentication Module

| Operation | Method | Parameters | Returns |
|-----------|--------|-----------|---------|
| Authenticate User | `Login.validateLogin()` | username, password | boolean |

---

### Passenger Management Module

| Operation | Method | Parameters | Returns |
|-----------|--------|-----------|---------|
| Add Passenger | `AddCustomer.addPassenger()` | name, nationality, phone, address, aadhar, gender | void / boolean |
| Fetch Passenger | `BookFlight.fetchPassenger()` | aadhar | ResultSet |

---

### Flight Search Module

| Operation | Method | Parameters | Returns |
|-----------|--------|-----------|---------|
| View All Flights | `FlightInfo.displayFlights()` | None | Populates JTable |
| Search Flights | `BookFlight.fetchFlights()` | source, destination | ResultSet |

---

### Booking Management Module

| Operation | Method | Parameters | Returns |
|-----------|--------|-----------|---------|
| Book Flight | `BookFlight.bookFlight()` | aadhar, flight details, date | PNR, Ticket_ID |
| Generate PNR | `BookFlight.generatePNR()` | None | String (PNR-XXXXXX) |
| Generate Ticket ID | `BookFlight.generateTicketID()` | None | String (TIC-XXXXX) |

---

### Journey Details Module

| Operation | Method | Parameters | Returns |
|-----------|--------|-----------|---------|
| Get Booking Details | `JourneyDetails.getDetails()` | PNR | ResultSet |
| Display Journey Info | `JourneyDetails.displayJourney()` | PNR | Populates JTable |

---

### Cancellation Module

| Operation | Method | Parameters | Returns |
|-----------|--------|-----------|---------|
| Cancel Booking | `Cancel.cancelBooking()` | PNR | cancellation_no |
| Generate Cancel No | `Cancel.generateCancellationNo()` | None | String |
| Log Cancellation | `Cancel.logCancellation()` | PNR details | void |

---

### Boarding Pass Module

| Operation | Method | Parameters | Returns |
|-----------|--------|-----------|---------|
| Generate Boarding Pass | `BoardingPass.generatePass()` | PNR | void (displays pass) |
| Fetch Booking | `BoardingPass.fetchBooking()` | PNR | ResultSet |

---

## 📦 Installation & Setup

### Prerequisites

- **Java Development Kit (JDK):** Java 8 or higher
- **MySQL Server:** Version 5.7 or later
- **MySQL Connector/J:** 9.2.0 (included in lib/)
- **Git:** For cloning the repository

### Step 1: Clone the Repository

```bash
git clone https://github.com/yourusername/airline-management-system.git
cd airline-management-system
```

### Step 2: Database Setup

1. **Start MySQL Server**
   ```bash
   # Windows
   net start MySQL80
   
   # Linux/Mac
   sudo systemctl start mysql
   ```

2. **Create Database and Tables**
   ```sql
   -- Open MySQL command line
   mysql -u root -p
   
   -- Create database
   CREATE DATABASE airlinemanagementsystem;
   USE airlinemanagementsystem;
   
   -- Create login table
   CREATE TABLE login (
     username VARCHAR(50) PRIMARY KEY,
     password VARCHAR(50) NOT NULL
   );
   
   -- Create passenger table
   CREATE TABLE passenger (
     name VARCHAR(100) NOT NULL,
     nationality VARCHAR(50),
     phone VARCHAR(15),
     address VARCHAR(200),
     aadhar VARCHAR(20) PRIMARY KEY,
     gender CHAR(1)
   );
   
   -- Create flight table
   CREATE TABLE flight (
     f_name VARCHAR(100),
     f_code VARCHAR(20) PRIMARY KEY,
     source VARCHAR(50),
     destination VARCHAR(50)
   );
   
   -- Create reservation table
   CREATE TABLE reservation (
     PNR VARCHAR(50) PRIMARY KEY,
     Ticket_ID VARCHAR(50) UNIQUE,
     aadhar VARCHAR(20),
     name VARCHAR(100),
     nationality VARCHAR(50),
     flightname VARCHAR(100),
     flightcode VARCHAR(20),
     src VARCHAR(50),
     des VARCHAR(50),
     ddate DATE,
     FOREIGN KEY (aadhar) REFERENCES passenger(aadhar)
   );
   
   -- Create cancel table
   CREATE TABLE cancel (
     PNR VARCHAR(50),
     name VARCHAR(100),
     cancellation_no VARCHAR(50) PRIMARY KEY,
     flightcode VARCHAR(20),
     ddate DATE
   );
   
   -- Insert your own login credentials (use strong passwords in production)
   INSERT INTO login (username, password) VALUES ('admin', 'CHANGE_THIS_PASSWORD_123');
   INSERT INTO login (username, password) VALUES ('user', 'CHANGE_THIS_PASSWORD_456');
   
   -- Insert sample flights
   INSERT INTO flight VALUES
   ('Air India AI101', 'AI101', 'Delhi', 'Mumbai'),
   ('Air India AI102', 'AI102', 'Delhi', 'Bangalore'),
   ('Air India AI103', 'AI103', 'Mumbai', 'Delhi'),
   ('Air India AI104', 'AI104', 'Bangalore', 'Hyderabad');
   ```

3. **Set Database Configuration**
   - Database credentials are loaded from environment variables (secure approach)
   - Copy `.env.example` to `.env` in the project root
   - Update the `.env` file with your database credentials:
   ```
   DB_HOST=localhost
   DB_PORT=3306
   DB_USER=root
   DB_PASSWORD=your_password
   ```
   - **Important:** Never commit `.env` file to Git (it's in `.gitignore`)

### Step 3: Configure Database Connection

Database credentials are loaded from environment variables for security:

**Option A: Using .env File (Recommended for Development)**

1. Copy `.env.example` to `.env`:
   ```bash
   cp .env.example .env
   ```

2. Edit `.env` with your actual database credentials:
   ```
   DB_HOST=localhost
   DB_PORT=3306
   DB_USER=root
   DB_PASSWORD=your_secure_password
   ```

3. `.env` is in `.gitignore` - it won't be committed to Git

**Option B: Using System Environment Variables (Recommended for Production)**

Set environment variables before running the application:

```bash
# Windows (Command Prompt)
set DB_HOST=localhost && set DB_PORT=3306 && set DB_USER=root && set DB_PASSWORD=your_password

# Windows (PowerShell)
$env:DB_HOST="localhost"; $env:DB_PORT="3306"; $env:DB_USER="root"; $env:DB_PASSWORD="your_password"

# Linux/Mac (Bash)
export DB_HOST=localhost
export DB_PORT=3306
export DB_USER=root
export DB_PASSWORD=your_password
```

### Step 4: Compile the Project

**Option A: Using Batch Script (Windows)**
```batch
compile_and_run.bat
```

**Option B: Manual Compilation**
```bash
# Navigate to project directory
cd airlinemanagementsystem

# Compile all Java files
javac -cp ../lib/*:. *.java

# Run the application
java -cp ../lib/*:. Login
```

**Option C: Using IDE**
- Open project in NetBeans, Eclipse, or IntelliJ IDEA
- Add JAR files from `lib/` to build path
- Run `Login.java` as main class

### Step 4: Run the Application

```bash
java -cp lib/*:. airlinemanagementsystem.Login
```

### Step 5: Run the Application

**Important:** Make sure environment variables are set (DB_HOST, DB_PORT, DB_USER, DB_PASSWORD)

Before running, set your database credentials:

```bash
# Windows (Command Prompt)
set DB_HOST=localhost && set DB_PORT=3306 && set DB_USER=root && set DB_PASSWORD=your_password && java -cp lib/*:. airlinemanagementsystem.Login

# Linux/Mac (Bash)
export DB_HOST=localhost DB_PORT=3306 DB_USER=root DB_PASSWORD=your_password
java -cp lib/*:. airlinemanagementsystem.Login
```

The login window will appear. Use the credentials you configured in your database:

**ℹ️ Default Example Credentials (Change these in production!):**
- **Username:** admin
- **Password:** CHANGE_THIS_PASSWORD_123

**⚠️ Security Note:** Always use strong, unique passwords in production. Change default credentials immediately after setup.

---

## 🎮 Usage Guide

### 1. **Login to System**
   - Launch the application
   - Enter your configured username
   - Enter your configured password
   - Click "Login" button
   - **Note:** Default example credentials are shown in `.env.example` file

### 2. **Access Home Menu**
   - After successful login, main menu appears with 6 options

### 3. **Register Passenger**
   - Click "Add Customer Details" button
   - Fill passenger information:
     - Full Name
     - Nationality
     - Phone Number
     - Address
     - Aadhar Number (12 digits)
     - Gender
   - Click "Add" to register

### 4. **Search and Book Flight**
   - Click "Book Flight" button
   - Enter passenger Aadhar number
   - Click "Fetch User" to load passenger details
   - Select source city from dropdown
   - Select destination city from dropdown
   - Click "Fetch Flights" to view available flights
   - Select desired flight from table
   - Choose travel date using date picker
   - Click "Book Flight" to confirm reservation
   - **System generates and displays:**
     - Unique PNR (e.g., PNR-123456)
     - Ticket ID (e.g., TIC-12345)

### 5. **View Flight Information**
   - Click "Flight Information" button
   - Browse all available flights in table format
   - View details: Flight Name, Code, Source, Destination

### 6. **View Journey Details**
   - Click "Journey Details" button
   - Enter PNR number from booking confirmation
   - Click "Show Details" to display:
     - Passenger name and nationality
     - Flight details
     - Travel date and route
     - Ticket information

### 7. **Cancel Ticket**
   - Click "Cancel" button
   - Enter PNR number of booking to cancel
   - Click "Show Details" to verify booking
   - Click "Confirm Cancel" to proceed
   - **System generates:**
     - Cancellation number
     - Records cancellation in audit log

### 8. **View Boarding Pass**
   - Click "Boarding Pass" button
   - Enter PNR number of confirmed booking
   - Click "Generate" to display:
     - Passenger details
     - Flight information
     - Boarding time
     - Seat information
     - Gate number

### 9. **Logout**
   - Close the application window
   - System automatically logs out user

---

## 📸 Screenshots

> **Note:** Screenshots should be added here for demonstration purposes.

| Screenshot | Location | Description |
|-----------|----------|-------------|
| Login Screen | `/images/01_login.png` | User authentication interface |
| Home Menu | `/images/02_home_menu.png` | Main navigation dashboard |
| Add Customer | `/images/03_add_customer.png` | Passenger registration form |
| Book Flight | `/images/04_book_flight.png` | Flight search and booking interface |
| Flight List | `/images/05_flight_list.png` | Available flights table |
| Journey Details | `/images/06_journey_details.png` | Booking information display |
| Cancellation | `/images/07_cancellation.png` | Ticket cancellation interface |
| Boarding Pass | `/images/08_boarding_pass.png` | Digital boarding pass display |

### How to Add Screenshots

To enhance the README, place screenshots in the `/images/` directory:

```bash
mkdir -p images/screenshots
# Copy your screenshots here
```

Then update the markdown links above with actual image paths.

---

## 🔒 Security Features

### Authentication & Authorization
- ✅ User login with username/password validation
- ✅ Session management for authenticated users
- ✅ Logout functionality to clear user session
- ✅ Database credentials loaded from environment variables (not hardcoded)

### Data Protection
- ✅ **Environment-based Configuration** - Credentials stored in environment variables, not in source code
- ✅ **Configuration Flexibility** - Easy database migration without code changes
- ⚠️ **TO BE IMPROVED:** Passwords stored in plaintext (should use hashing)
- ⚠️ **TO BE IMPROVED:** SQL injection prevention (use PreparedStatements)

### Transaction Safety
- ✅ Foreign key constraints maintain referential integrity
- ✅ Auto-generated IDs prevent manual ID conflicts
- ⚠️ **TO BE IMPROVED:** No transaction rollback on failures

### Audit & Compliance
- ✅ Cancellation audit trail maintained in `cancel` table
- ✅ Complete booking history preserved
- ✅ Cancellation numbers auto-generated for tracking

### Input Validation
- ✅ Aadhar number format validation
- ✅ Phone number format validation
- ⚠️ **TO BE IMPROVED:** SQL injection prevention (use PreparedStatements)

---

## ⚠️ Known Issues & Challenges

### Technical Challenges Addressed

1. **Database Connectivity**
   - **Challenge:** Multiple simultaneous database connections
   - **Solution:** Implemented Singleton pattern in Conn.java for shared connection

2. **PNR Generation**
   - **Challenge:** Ensuring unique PNR for each booking
   - **Solution:** Random number generation with database uniqueness constraint

3. **GUI Threading**
   - **Challenge:** Preventing UI freezing during database queries
   - **Solution:** Used Event Dispatch Thread for long operations (can be improved with SwingWorker)

4. **Date Handling**
   - **Challenge:** Date picker compatibility across platforms
   - **Solution:** Used JDateChooser library for consistent behavior

5. **ResultSet to Table Conversion**
   - **Challenge:** Displaying database results in JTable
   - **Solution:** Used rs2xml library for efficient conversion

### Security Issues Found

| Issue | Severity | Status | Recommendation |
|-------|----------|--------|-----------------|
| SQL Injection Risk | 🔴 CRITICAL | **Not Fixed** | Use PreparedStatements |
| Hardcoded Credentials | 🟠 HIGH | **Not Fixed** | Move to config file or environment variables |
| No Input Sanitization | 🟠 HIGH | **Not Fixed** | Implement input validation layer |
| Plaintext Passwords | 🟡 MEDIUM | **Not Fixed** | Use bcrypt/MD5 hashing |
| No Transaction Management | 🟡 MEDIUM | **Not Fixed** | Implement ACID transactions |
| Weak Error Handling | 🟡 MEDIUM | **Not Fixed** | Use proper logging framework |

---

## 🚀 Future Enhancements

### Phase 1: Security & Stability (High Priority)
1. **SQL Injection Prevention**
   - Migrate all queries to PreparedStatements
   - Implement parameterized query builder
   - Add query validation layer

2. **Password Security**
   - Implement bcrypt or Argon2 hashing
   - Add password strength validation
   - Implement password reset functionality

3. **Configuration Management**
   - Move database credentials to external config file
   - Support environment-based configuration
   - Add database connection pooling (HikariCP)

4. **Transaction Management**
   - Implement ACID compliance
   - Add rollback mechanisms
   - Implement transaction logging

### Phase 2: User Experience & Features (Medium Priority)
5. **Web-Based Interface**
   - Convert desktop app to Spring Boot + React web application
   - Mobile-responsive design
   - REST API endpoints

6. **Payment Integration**
   - Online payment gateway integration (Razorpay, Stripe)
   - Digital wallet support
   - Invoice generation and email

7. **Notification System**
   - Email notifications for bookings/cancellations
   - SMS alerts for flight delays
   - Push notifications for boarding reminders

8. **Advanced Search Filters**
   - Filter by airline, time, price
   - Multi-city trip planning
   - Saved searches and preferences

### Phase 3: Analytics & Operations (Medium Priority)
9. **Admin Dashboard**
   - Revenue analytics
   - Flight occupancy reports
   - Passenger statistics
   - Route profitability analysis

10. **Reporting System**
    - Generate booking reports
    - Export to PDF/Excel
    - Scheduled automated reports
    - Custom report builder

### Phase 4: Scalability & Integration (Lower Priority)
11. **Microservices Architecture**
    - Separate booking, payment, and notification services
    - Docker containerization
    - Kubernetes orchestration

12. **Third-party Integrations**
    - Hotel booking partnerships
    - Travel insurance integration
    - Loyalty program management
    - GDS (Global Distribution System) connectivity

13. **Performance Optimization**
    - Database query optimization and indexing
    - Caching layer (Redis)
    - Load testing and optimization
    - CDN for static assets (web version)

14. **AI/ML Features**
    - Personalized flight recommendations
    - Dynamic pricing engine
    - Predictive maintenance for flights
    - Chatbot for customer support

---

## 🤝 Contributing

Contributions are welcome! This is an active project accepting improvements, bug fixes, and new features.

### How to Contribute

1. **Fork the Repository**
   ```bash
   git clone https://github.com/yourusername/airline-management-system.git
   cd airline-management-system
   ```

2. **Create a Feature Branch**
   ```bash
   git checkout -b feature/your-feature-name
   # Example: git checkout -b feature/payment-integration
   ```

3. **Make Changes**
   - Keep code clean and well-documented
   - Follow Java naming conventions
   - Add comments for complex logic
   - Test thoroughly before committing

4. **Commit Changes**
   ```bash
   git add .
   git commit -m "feat: description of your changes"
   # Examples:
   # git commit -m "feat: add email notifications"
   # git commit -m "fix: resolve SQL injection vulnerability"
   # git commit -m "docs: update README with new features"
   ```

5. **Push to Your Fork**
   ```bash
   git push origin feature/your-feature-name
   ```

6. **Create Pull Request**
   - Go to GitHub repository
   - Click "New Pull Request"
   - Describe your changes in detail
   - Reference any related issues
   - Wait for code review

### Contribution Guidelines

- **Code Quality:** Follow Java conventions and use meaningful variable names
- **Documentation:** Document new features and update README if needed
- **Testing:** Test all changes thoroughly before submitting
- **Security:** Highlight any security improvements
- **Commits:** Use clear, descriptive commit messages
- **Issues:** Report bugs with detailed reproduction steps

### Areas Looking for Contributors

- 🔒 Security improvements and vulnerability fixes
- 📱 Web UI development (Spring Boot/React)
- 🧪 Unit tests and integration tests
- 📊 Analytics and reporting features
- 🌐 Internationalization (multi-language support)
- 📖 Documentation improvements

---

## 📄 License

This project is licensed under the **MIT License** - see the LICENSE file for details.

### MIT License Summary

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

- The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
- THE SOFTWARE IS PROVIDED "AS IS", WITHOUT IS", WITHOUT WARRANTY OF ANY KIND

You are free to use this project for:
- ✅ Commercial purposes
- ✅ Personal projects
- ✅ Educational use
- ✅ Modifications
- ✅ Distribution

Just include the original copyright notice and license text.

---

## 👨‍💻 Author

<div align="center">

### **Bhavishya Katariya**

**Final Year Student | Full Stack Developer | Java Enthusiast**

[![GitHub](https://img.shields.io/badge/GitHub-000?style=for-the-badge&logo=github)](https://github.com/yourusername)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/yourprofile)
[![Email](https://img.shields.io/badge/Email-EA4335?style=for-the-badge&logo=gmail)](mailto:your.email@example.com)
[![Portfolio](https://img.shields.io/badge/Portfolio-FF6B35?style=for-the-badge&logo=web)](https://yourportfolio.com)

**Location:** India  
**Year:** 2024-2026 (Final Year)  

### About Me

I am a passionate Java developer with expertise in:
- 💻 Desktop & Web Application Development
- 🗄️ Database Design & SQL Optimization
- 🎨 GUI Development with Swing & Modern Frameworks
- 🔧 Full-stack development and system design
- 📚 Problem-solving and algorithm development

This project demonstrates my ability to develop complete business applications from concept to deployment.

</div>

---

## 🙏 Acknowledgements

### Libraries & Frameworks Used

| Library | Purpose | Link |
|---------|---------|------|
| **MySQL Connector/J** | JDBC driver for MySQL connectivity | [mysql.com](https://mysql.com) |
| **rs2xml** | Convert ResultSet to JTable format | [GitHub](https://github.com) |
| **JDateChooser** | Date picker component | [sourceforge.net](https://sourceforge.net) |
| **Java Swing** | GUI framework | [oracle.com](https://oracle.com) |

### Resources & Learning Materials

- **Java Documentation:** [docs.oracle.com/javase](https://docs.oracle.com/javase)
- **MySQL Tutorials:** [mysql.com/tutorials](https://mysql.com/tutorials)
- **JDBC Programming:** [baeldung.com](https://baeldung.com)
- **Database Design:** [Mode Analytics SQL Tutorial](https://mode.com/sql-tutorial)
- **GUI Development:** [oracle.com/swing](https://oracle.com/swing)

### Special Thanks

- To my mentors and professors for guidance
- To the open-source community for excellent libraries
- To the MySQL and Java communities for support
- To all contributors and users of this project

---

## 📞 Support & Contact

### Getting Help

- 🐛 **Bug Reports:** Open an issue on GitHub
- 💡 **Feature Requests:** Create a GitHub discussion
- ❓ **Questions:** Email the author directly
- 📧 **Email:** your.email@example.com

### Quick Links

- [GitHub Repository](https://github.com/yourusername/airline-management-system)
- [Report an Issue](https://github.com/yourusername/airline-management-system/issues)
- [View Discussions](https://github.com/yourusername/airline-management-system/discussions)
- [Pull Requests](https://github.com/yourusername/airline-management-system/pulls)

---

<div align="center">

### ⭐ If you find this project useful, please consider giving it a star!

**Happy Coding!** ✈️

---

*Last Updated: 2026-06-21*  
*Status: Active Development*  
*Maintained by: Bhavishya Katariya*

</div>
