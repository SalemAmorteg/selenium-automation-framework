#  Selenium Automation Framework (Java)

##  Overview
This project is a UI Test Automation Framework built using **Java + Selenium WebDriver**, following the **Page Object Model (POM)** design pattern.

It is designed with scalability, maintainability, and clean architecture in mind — progressively evolving toward enterprise-level automation standards.

---

##  Tech Stack

- Java 17+
- Selenium WebDriver
- Maven
- TestNG / JUnit
- Page Object Model (POM)

---

##  Framework Architecture

src
├── pages
│ ├── BasePage.java
│ ├── LoginPage.java
│ ├── DashboardPage.java
│ └── InventoryPage.java
│
├── smoke
│ ├── BaseTest.java
│ └── InventoryTest.java

###  Design Principles
- Separation of concerns
- Encapsulation of locators
- Reusable explicit waits
- Clean test logic
- Reduced duplication


 Recent Improvements

Refactored InventoryPage for better encapsulation

Extracted reusable createTestProduct() method

Improved explicit wait handling

Cleaner test structure

 Future Enhancements (Roadmap)

Implement ThreadLocal WebDriver (parallel execution ready)

Add Test Data Factory pattern

Integrate Allure Reports

Add CI/CD pipeline (GitHub Actions)

Dockerize execution

 What This Project Demonstrates

Understanding of Selenium architecture

Clean automation design

Code reusability

Git workflow with feature branches

Professional commit conventions

 Author

Salem Amortegui
QA Automation Engineer

## Test Reports (Allure)

This project uses Allure Reports to generate professional test execution reports.


## How to Run the Tests and generate Allure reports:

```bash
mvn clean test
mvn test -Dgroups=smoke
mvn allure:serve 
allure serve allure-results
