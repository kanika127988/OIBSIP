import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Class 1: Transaction
class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + ": $" + String.format("%.2f", amount);
    }
}

// Class 2: Account
class Account {
    private String userId;
    private String pin;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account(String userId, String pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
    }

    public boolean validatePin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public String getUserId() {
        return userId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction("Deposit", amount));
            System.out.println("Successfully deposited $" + String.format("%.2f", amount));
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient Funds");
            return false;
        }
        balance -= amount;
        transactions.add(new Transaction("Withdrawal", amount));
        System.out.println("Successfully withdrew $" + String.format("%.2f", amount));
        return true;
    }

    public boolean transfer(double amount, Account recipientAccount) {
        if (amount <= 0) {
            System.out.println("Invalid transfer amount.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Insufficient Funds");
            return false;
        }
        balance -= amount;
        recipientAccount.depositWithoutLog(amount);
        
        // Log transaction for sender and recipient
        transactions.add(new Transaction("Transfer to " + recipientAccount.getUserId(), amount));
        recipientAccount.addTransaction(new Transaction("Transfer from " + userId, amount));
        
        System.out.println("Successfully transferred $" + String.format("%.2f", amount) + " to account " + recipientAccount.getUserId());
        return true;
    }

    public void depositWithoutLog(double amount) {
        balance += amount;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void printTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        if (transactions.isEmpty()) {
            System.out.println("No past transactions in this session.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
        System.out.println("Current Available Balance: $" + String.format("%.2f", balance));
    }
}

// Class 3: Bank
class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
        // Sample accounts pre-loaded for testing
        accounts.put("user123", new Account("user123", "1234", 1000.00));
        accounts.put("user456", new Account("user456", "5678", 500.00));
    }

    public Account getAccount(String userId) {
        return accounts.get(userId);
    }
}

// Class 4: ATM
class ATM {
    private Bank bank;
    private Scanner scanner;

    public ATM(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== Welcome to the ATM System ===");
        Account currentAccount = authenticateUser();

        if (currentAccount != null) {
            showMainMenu(currentAccount);
        } else {
            System.out.println("\n[Access Denied] You have exceeded 3 incorrect login attempts. Card blocked.");
        }
    }

    private Account authenticateUser() {
        int attemptsRemaining = 3;

        while (attemptsRemaining > 0) {
            System.out.print("\nEnter User ID: ");
            String userId = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            Account account = bank.getAccount(userId);
            if (account != null && account.validatePin(pin)) {
                System.out.println("\nLogin Successful!");
                return account;
            } else {
                attemptsRemaining--;
                System.out.println("Invalid User ID or PIN.");
                if (attemptsRemaining > 0) {
                    System.out.println("Attempts remaining: " + attemptsRemaining);
                }
            }
        }
        return null;
    }

    private void showMainMenu(Account account) {
        int choice;
        do {
            System.out.println("\n--- ATM Main Menu ---");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option (1-5): ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    account.printTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient Account/User ID: ");
                    String recipientId = scanner.nextLine();
                    Account recipient = bank.getAccount(recipientId);

                    if (recipient != null && !recipient.getUserId().equals(account.getUserId())) {
                        System.out.print("Enter amount to transfer: $");
                        double transferAmount = scanner.nextDouble();
                        scanner.nextLine();
                        account.transfer(transferAmount, recipient);
                    } else {
                        System.out.println("Invalid recipient ID or cannot transfer to your own account.");
                    }
                    break;
                case 5:
                    System.out.println("\nThank you for banking with us. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select an option between 1 and 5.");
            }
        } while (choice != 5);
    }
}

// Class 5: Main (Note: 'public' keyword removed so it matches ATMInterface.java)
class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        ATM atm = new ATM(bank);
        atm.start();
    }
}