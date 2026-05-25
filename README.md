Java application designed to manage, update, and track logistics shipments. This project demonstrates clean object-oriented design,
effective data management, and comprehensive custom exception handling to simulate real-world supply chain edge cases.

**Tech Stack**
Language: Java (JDK 11+ / 17+)
Architecture: Object-Oriented Programming (OOP)
Build Tool: Maven / Gradle 

**Features**
- Shipment Creation & Logging: Generate unique tracking IDs, assign destinations, and set carrier details.
- Real-Time Status Updates: Move shipments through various lifecycle stages (e.g., Ordered, In Transit, Out for Delivery, Delivered).
- Custom Exception Framework: Robust error-handling mechanism that prevents data corruption and handles logistics failures gracefully.
- Search & Filter: Instantly look up packages via Tracking ID or filter by current status.

**Custom Exceptions Handled**
- InvalidTrackingIdException: Thrown if a user searches for a non-existent or malformed tracking number.
- ShipmentDelayedException: Triggered when unforeseen logistics bottlenecks alter the estimated delivery date.
- PackageDamagedException: Simulates transit issues, routing the shipment to an alternative "Inspection" workflow.
- DeliveryFailedException: Raised if a delivery attempt is made but cannot be completed (e.g., incorrect address or recipient unavailable).
