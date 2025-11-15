# ATM Simulator (Java)

ATM Simulator (Java Swing GUI)

This is a simple ATM Simulator built using Java and Swing.
The project allows a user to create an account, deposit money, withdraw money, and view transaction history — all inside a clean graphical user interface.

I built this project to practice Java OOP concepts and GUI development. It also helped me revise event handling, class design, and basic user input validation.

## Features

- Create an account with:
  - Account ID
  - Owner name
  - Initial balance

- Perform ATM operations:
  - Deposit money
  - Withdraw money
  - View updated balance
  - See complete transaction history

- User-friendly GUI using Java Swing  
- Separate classes for Account, Transaction, and GUI (proper OOP structure)

## Project Structure

ATM-GUI/
└── src/
└── com/example/atm/
├── Main.java
├── ATMGUI.java
├── Account.java
└── Transaction.java

## How to Run the Project

1. Go to the src folder  
cd ATM-GUI/src

2. Compile all Java files  
javac com/example/atm/*.java

3. Run the project  
java com.example.atm.Main

That’s it — the ATM GUI window will open.

## Technologies Used

- Java (JDK 8+)
- Java Swing
- Object-Oriented Programming (OOP)

## What I Learnt

- Working with Swing components and layouts  
- Handling button events  
- Designing small OOP models  
- Managing multiple screens using CardLayout  
- Input validation and exception handling  
