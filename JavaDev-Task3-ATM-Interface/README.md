# ATM Interface - Java Development Internship (OIBSIP)

A console-based ATM (Automated Teller Machine) simulation program written in Java. This project was developed as part of the Oasis Infobyte Java Development Internship (Task 3).

## Features
* **Secure Authentication:** User ID and PIN verification with a 3-attempt limit before account lockout.
* **Interactive Main Menu:** Loop-driven menu allowing users to perform multiple transactions in a single session.
* **Transaction History:** Real-time logging and display of all deposits, withdrawals, and transfers using an `ArrayList`.
* **Deposit Functionality:** Add funds securely to the account balance.
* **Withdrawal Functionality:** Check for sufficient funds before withdrawing; displays `"Insufficient Funds"` if the balance is too low.
* **Transfer Functionality:** Transfer money securely between different registered bank accounts, updating both sender and recipient balances and logs.
* **Object-Oriented Design:** Built cleanly across 5 distinct classes (`Main`, `Bank`, `ATM`, `Account`, and `Transaction`).

## Project Structure
The program consists of 5 modular Java classes:
1. **`Main`**: Entry point that initializes the bank and starts the ATM.
2. **`Bank`**: Manages multiple user accounts and user verification.
3. **`ATM`**: Handles user interactions, authentication attempts, and the main menu workflow.
4. **`Account`**: Encapsulates account details (User ID, PIN, balance, and transaction list).
5. **`Transaction`**: Represents individual transaction records (Type and Amount).

## Test Credentials
You can use the following pre-loaded accounts to test the application:
* **Account 1:** 
  * User ID: `user123` 
  * PIN: `1234` 
  * Initial Balance: `$1000.00`
* **Account 2:** 
  * User ID: `user456` 
  * PIN: `5678` 
  * Initial Balance: `$500.00` *(Ideal for testing fund transfers)*

## Sample Output
```text
=== Welcome to the ATM System ===

Enter User ID: user123
Enter PIN: 1234

Login Successful!

--- ATM Main Menu ---
1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit
Choose an option (1-5): 3
Enter amount to deposit: $250.50
Successfully deposited $250.50

--- ATM Main Menu ---
1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit
Choose an option (1-5): 2
Enter amount to withdraw: $150.00
Successfully withdrew $150.00

--- ATM Main Menu ---
1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit
Choose an option (1-5): 4
Enter recipient Account/User ID: user456
Enter amount to transfer: $200.00
Successfully transferred $200.00 to account user456

--- ATM Main Menu ---
1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit
Choose an option (1-5): 1

--- Transaction History ---
Deposit: $250.50
Withdrawal: $150.00
Transfer to user456: $200.00
Current Available Balance: $900.50

--- ATM Main Menu ---
1. Transaction History
2. Withdraw
3. Deposit
4. Transfer
5. Quit
Choose an option (1-5): 5

Thank you for banking with us. Goodbye!