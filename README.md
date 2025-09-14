<img width="351" height="241" alt="image" src="https://github.com/user-attachments/assets/7139c3e5-8878-4c21-99cf-55d9f88d19c8" />API Automation Framework – Rest Assured + Cucumber (BDD)

📌 Overview
This project is an API automation framework built using Rest Assured for HTTP request handling and Cucumber for Behavior-Driven Development (BDD). It enables writing human-readable test scenarios that validate RESTful APIs efficiently.

🚀 Tech Stack
Tool/Library	Purpose
Rest Assured	API testing and request/response handling
Cucumber	BDD-style test scenarios
JUnit/TestNG	Test runner
Maven/Gradle	Dependency management and build tool
Jackson/Gson	JSON serialization/deserialization
Log4j/SLF4J	Logging

📂 Project Structure
src/
├── test/
│   ├── java/
│   │   ├── stepDefinitions/     # Cucumber step implementations
│   │   ├── runners/             # Test runner classes
│   │   └── utils/               # Utility classes (e.g., config, data handling)
│   └── resources/
│       ├── features/            # Cucumber .feature files
│       └── config.properties    # Environment configs

🧾 Sample Feature File
Feature: User API Validation

  Scenario: Verify user details by ID
    Given the API is up and running
    When I send a GET request to "/users/1"
    Then the response status code should be 200
    And the response should contain user name "John Doe"


🛠️ How to Run Tests
1. Clone the repo
git clone https://github.com/Jagadheswaran96/your-repo-name.git

2. Install dependencies
mvn clean install

3. Run tests
mvn test

📈 Reports
Cucumber HTML reports are generated in the /target folder.
You can integrate Extent Reports or Allure for enhanced reporting.

🔐 Environment Configuration
Use config.properties to manage:
Base URL
Authentication tokens
Timeout settings

📬 Contribution
Feel free to fork the repo, raise issues, or submit pull requests. Collaboration is welcome!
