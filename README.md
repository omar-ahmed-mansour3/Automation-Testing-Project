# Automation Exercise: End-to-End Testing Framework

This repository contains a robust automation framework designed to verify the functional workflows of the **Automation Exercise** platform. This project was developed as the final deliverable for the **Software Testing Diploma**.

## Project Objective

The primary goal was to design and execute a comprehensive suite of **26 automated test cases**. These cases verify critical site functionalities, including:

*   User registration and multi-scenario login.
*   Product search, navigation, and category filtering.
*   Complete checkout workflows and order processing.
*   Contact form submissions and newsletter subscriptions.

---

## Technical Stack

*   **Language**: Java
*   **Automation Tool**: Selenium WebDriver
*   **Test Runner**: TestNG
*   **Design Pattern**: Page Object Model (POM)
*   **Data Handling**: JSON-driven testing (parameterized)
*   **Reporting**: Allure Reporting

---

## Framework Architecture

The project follows a strict **Layered Model Architecture** to ensure maintainability and high code quality:

*   **Pages**: Contains all Page Objects representing the UI components and their interactions.
*   **Tests**: Contains the TestNG test scripts mapping to the 26 defined scenarios.
*   **Utils**: Includes framework-wide helpers and common Selenium functionalities.
*   **Data**: External JSON files used to decouple test data from the execution logic.

---

## Execution & Reporting

Tests are managed and executed via specialized **TestNG XML files**, allowing for modular execution of specific suites (e.g., Authentication, Shopping, Misc UI).

Upon execution, the framework generates **Allure Reports**, providing a visual dashboard for test status, execution time, and failure analysis.

---

