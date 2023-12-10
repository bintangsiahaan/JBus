BintangSiahaanJBusAF Project

Overview
The BintangSiahaanJBusAF project is a Java application that manages bus-related activities. It includes classes for representing buses, stations, accounts, payments, and more. The application uses JSON files to store and retrieve data related to these entities.

Project Structure
The project is organized into several packages, each containing specific functionalities:

com.bintangSiahaanJBusAF: Root package containing the main application class.
com.bintangSiahaanJBusAF.controller: Package containing controller classes for different entities (e.g., BusController, AccountController).
com.bintangSiahaanJBusAF.dbjson: Package containing classes for managing JSON data and providing a base class (Serializable) for serializable objects.
com.bintangSiahaanJBusAF.model: Package containing model classes representing entities such as Bus, Station, Account, etc.
com.bintangSiahaanJBusAF.enums: Package containing enum classes (e.g., BusType, City, Facility) used in the application.

Class Documentation
AccountController: Manages user accounts, registration, login, and interactions related to accounts.
BaseResponse: Represents a generic response object containing success status, message, and payload.
BasicGetController: An interface providing basic CRUD operations for entities with JSON data storage.
BusController: Handles operations related to buses, schedules, and bus creation.
City, BusType, Facility, PaymentStatus: Enumerations representing cities, bus types, facilities, and payment statuses.
PaymentController: Manages payments, booking, and payment status changes.
Serializable: A base class for serializable objects with an identifier.

How to Run
To run the application, follow these steps:

Ensure you have Java installed on your machine.
Clone the project repository.
Open the project in your preferred Java IDE.
Run the main application class: com.bintangSiahaanJBusAF.MainApplication.
Contributing
Contributions to the BintangSiahaanJBusAF project are welcome. If you find issues or have suggestions for improvements, please open an issue or create a pull request.
