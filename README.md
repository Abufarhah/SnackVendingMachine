# Snacks Vending Machine

## Introduction:

This project is about a Snacks Vending Machine, which uses Spring State Machine to manage the vending machine
transactions. The main aim of the project is to enable the end-user to select a snack item, insert money, pay using a
card, or cancel the transaction. The vending machine has been designed to be simple, efficient and user-friendly.

## Functionalities:

The vending machine has four main actions for the end-user:

- **Select Item:** This API allows the user to select a snack item from the available options. The endpoint for this API
  is /vending-machine/select-item.
- **Insert Money:** This API allows the user to insert money into the vending machine. The endpoint for this API is
  /vending-machine/insert-money.
- **Pay using Card:** This API allows the user to pay for the selected snack item using a card. The endpoint for this
  API is /vending-machine/pay-using-card.
- **Cancel Transaction:** This API allows the user to cancel the transaction and get back the money. The endpoint for
  this API is /vending-machine/cancel-transaction.

## Technology Stack:

- **Spring State Machine:** Spring State Machine has been used to manage the vending machine transactions.
- **Java:** The project has been developed using the Java programming language.
- **REST API:** The vending machine functionalities have been exposed as REST APIs.
- **Logging:** The project uses logging to log the vending machine transactions.

## Improvements:

The project still needs the following improvements:

- **Unit Testing:** The project needs unit testing to ensure that each component of the vending machine is working as
  expected.
- **E2E Testing:** End-to-End testing is required to ensure that the vending machine functionalities are working as
  expected.
- **More Logging:** The project needs more logging to help in debugging any issues that may arise during the vending
  machine transactions.
- **Comments:** The project needs more comments to make it easier for developers to understand the code.
- **WebSockets:** Using WebSockets could enable interactive communication between users and the vending machine UI,
  allowing for real-time updates and feedback during the transaction process.

## Conclusion:

The Snacks Vending Machine project has been developed using Spring State Machine to manage the vending machine
transactions. The project is aimed at providing a simple, efficient, and user-friendly experience to the end-user. The
project still needs some improvements, including unit testing, E2E testing, more logging, and comments to make it easier
for developers to understand the code.