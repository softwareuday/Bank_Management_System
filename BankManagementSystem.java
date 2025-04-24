
import java.util.*;

public class BankManagementSystem {
    private static HashMap<Integer, BankAccount> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextAccountNumber = 1001;

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> createAccount();
                case 2 -> depositMoney();
                case 3 -> withdrawMoney();
                case 4 -> checkBalance();
                case 5 -> viewAccountDetails();
                case 6 -> transferMoney();
                case 7 -> {
                    System.out.println("Exiting. Thank you for using the system!");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n===== Bank Management System =====");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Check Balance");
        System.out.println("5. View Account Details");
        System.out.println("6. Transfer Money");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }

    private static void createAccount() {
        scanner.nextLine(); // consume newline
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        System.out.print("Enter initial deposit: ");
        double deposit = scanner.nextDouble();
        int accNum = nextAccountNumber++;
        BankAccount account = new BankAccount(accNum, name, deposit);
        accounts.put(accNum, account);
        System.out.println("Account created. Your account number is: " + accNum);
    }

    private static void depositMoney() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        BankAccount acc = accounts.get(accNum);
        if (acc != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            acc.deposit(amount);
            System.out.println("Deposit successful. Updated balance: ₹" + acc.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdrawMoney() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        BankAccount acc = accounts.get(accNum);
        if (acc != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            if (acc.withdraw(amount)) {
                System.out.println("Withdrawal successful. Remaining balance: ₹" + acc.getBalance());
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void checkBalance() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        BankAccount acc = accounts.get(accNum);
        if (acc != null) {
            System.out.println("Balance: ₹" + acc.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void viewAccountDetails() {
        System.out.print("Enter account number: ");
        int accNum = scanner.nextInt();
        BankAccount acc = accounts.get(accNum);
        if (acc != null) {
            System.out.println("Account Details: " + acc);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void transferMoney() {
        System.out.print("Enter source account number: ");
        int fromAcc = scanner.nextInt();
        System.out.print("Enter target account number: ");
        int toAcc = scanner.nextInt();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        BankAccount from = accounts.get(fromAcc);
        BankAccount to = accounts.get(toAcc);

        if (from != null && to != null && from.withdraw(amount)) {
            to.deposit(amount);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Transfer failed. Check accounts or balance.");
        }
    }
}
