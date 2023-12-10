# BintangSiahaanJBusAF Project

## Overview
BintangSiahaanJBusAF is a Java application designed to manage bus-related activities. It comprises classes for buses, stations, accounts, payments, etc., with data stored and retrieved using JSON files.

## Project Structure
The project is structured into several packages, each serving specific functionalities:

- **com.bintangSiahaanJBusAF:** Root package containing the main application class.
- **com.bintangSiahaanJBusAF.controller:** Package housing controller classes for entities (e.g., BusController, AccountController).
- **com.bintangSiahaanJBusAF.dbjson:** Package with classes managing JSON data and providing a base class (Serializable) for serializable objects.
- **com.bintangSiahaanJBusAF.model:** Package holding model classes representing entities like Bus, Station, Account, etc.
- **com.bintangSiahaanJBusAF.enums:** Package with enum classes (e.g., BusType, City, Facility) used in the application.

## Class Documentation
- **AccountController:** Manages user accounts, registration, login, and related interactions.
- **BaseResponse:** Represents a generic response object with success status, message, and payload.
- **BasicGetController:** An interface offering basic CRUD operations for entities with JSON data storage.
- **BusController:** Handles operations related to buses, schedules, and bus creation.
- **City, BusType, Facility, PaymentStatus:** Enumerations representing cities, bus types, facilities, and payment statuses.
- **PaymentController:** Manages payments, booking, and payment status changes.
- **Serializable:** Base class for serializable objects with an identifier.

## How to Run
To run the application:

1. Ensure Java is installed on your machine.
2. Clone the project repository.
3. Open the project in your preferred Java IDE.
4. Run the main application class: `com.bintangSiahaanJBusAF.MainApplication`.

## Contributing
Contributions to BintangSiahaanJBusAF are welcome. If you encounter issues or have improvement suggestions, please open an issue or create a pull request.
