import java.util.ArrayList;
import java.util.Scanner;

public class Client extends User {
  private float balance; // total balance among all accounts
  public static int nextId = 0;
  //==================================================
  public Client(String fName, String lName, String username, String password, String phoneNumber, float balance) {
    super(fName, lName, username, password, phoneNumber);
    this.setId(100000 + nextId);
    this.balance = balance;
    nextId++;
  }
  public Client(int id, String fName, String lName, String username, String password, String phoneNumber, float balance) {
    super(fName, lName, username, password, phoneNumber);
    this.setId(id);
    this.balance = balance;
    if (nextId < this.getId() - 99999)
    {
      nextId = this.getId() - 99999;
    }
  }
  //== Getters & Setters =============================
  public void setBalance(float balance) {
    this.balance = balance;
  }
  public float getBalance() {
    return balance;
  }
  //==================================================
  public static void updateTotalBalance(ArrayList<Client> clients, ArrayList<Account> accs) {
    for (Client client : clients) {
      client.setBalance(0);
      for (Account acc : accs) {
        if (acc.getBalance() < 3000 && acc.getAccountType().equals("Current")) {
          acc.setFees(50);
        }
        if (client.getId() == acc.getClientId()) {
          client.setBalance(client.getBalance() + acc.getBalance());
        }
      }
    }
  }
  @Override
  public void menu (Scanner scanner, ArrayList<Employee> emps, ArrayList<Client> clients, ArrayList<Account> accs, ArrayList<Transaction> transactions) {
    int i = 0;
    while(true)
    {
      System.out.println("1. Edit personal info\n" +
                         "2. Show profile details\n" +
                         "3. Show accounts' details\n" +
                         "4. Make a new transaction\n" +
                         "5. Show transaction history\n" +
                         "6. Make a new account\n" +
                         "7. Active or Disable credit card\n" +
                         "8. Logout");
      i = scanner.nextInt();
      scanner.nextLine();
      if (i == 1) {
        this.editPersonalInfo(scanner);
      }
      else if (i == 2) {
        System.out.println(this.details());
      }
      else if (i == 3) {
        this.displayAccounts(accs);
      }
      else if (i == 4) {
        this.makeNewTransaction(scanner, clients, accs, transactions);
      }
      else if (i == 5) {
        this.showTransactionsHistory(transactions);
      }
      else if (i == 6) {
        this.makeNewAccount(scanner, accs, clients);
      }
      else if (i == 7){
        this.changeCreditCardStatus(scanner, accs);
      }
      else if (i == 8) {
        return;
      }
    }
  }
  public void editPersonalInfo(Scanner scanner) {
    while (true){
      System.out.println("1. First name \n" +
                         "2. Last name\n" +
                         "3.phone number\n" +
                         "4.change password\n" +
                         "5. Back");
      int i = scanner.nextInt();
      scanner.nextLine();
      if (i == 1) {
        System.out.println("Enter new first name:");
        String newfname = scanner.nextLine();
        this.setfName(newfname);
      }
      if (i == 2) {
        System.out.println("Enter new last name:");
        String newlname = scanner.nextLine();
        this.setlName(newlname);
      }
      if (i == 3) {
        System.out.println("Enter new phone number:");
        String newphone = scanner.nextLine();
        this.setPhoneNumber(newphone);
      }
      if (i == 4) {
        System.out.println("Enter new password :");
        String newpassword = scanner.nextLine();
        System.out.println("Renter new password again:");
        String renewpassword = scanner.nextLine();
        if (newpassword.equals(renewpassword)){
          this.setPassword(newpassword);
        }
        else {
          System.out.println("Two passwords don't match");
          return;
        }
      }
      else if (i == 5) {
        return;
      }
    }
  }
  @Override
  public String details() {

    String str = super.details();
    str += "Total balance: " + this.getBalance() + "\n###################\n";
    return str;
  }
  public void displayAccounts(ArrayList<Account> accs) {
    boolean haveAccount = false;
    System.out.println("Client's Accounts:");
    for (Account acc : accs) {
      if (acc.getClientId() == this.getId()) {
        haveAccount = true;
        System.out.println("Account Number: " + acc.getAccountNumber());
        System.out.println("Account Type: " + acc.getAccountType());
        System.out.println("Balance: " + acc.getBalance());
        System.out.println("Credit Card Number: " + acc.getCretidCardNumber()); // Added by Ahmed Ali
        System.out.println("Loyalty Points: " + acc.getLoyalityPoints()); // Added by Ahmed Ali
        System.out.println("Security Pin: " + acc.getSecurityPin()); // Added by Ahmed Ali
        System.out.println("Card Amount:" + acc.getCardAmount()); // Added by A.S 12/12/2024
        System.out.println("Expiration Date:" + acc.getExpirationDate()); // Added by A.S 12/12/2024
        // Added by A.S 17/12/2024
        if(acc.getIsCreditCardActive()){
          System.out.println("Credit card status: Active");
        }
        else {
          System.out.println("Credit card status: Not Active");
        }
        if (acc.getAccountType().equals("Savings")) {
          System.out.println("Balance after 1 year: " + acc.getBalance() + acc.getBalance() * acc.getInterestRate());
        }
        else if (acc.getAccountType().equals("Current")) {
          System.out.println("Fees: " + acc.getFees());
        }
        System.out.println("################################");
      }
    }
    // Added by A.S 17/12/2024
    if(!haveAccount || accs.isEmpty()){
      System.out.println("################################");
      System.out.println("No accounts found for the client.");
      System.out.println("################################");
    }
  }
  public void makeNewTransaction(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accs, ArrayList<Transaction> transactions) {
    while (true){
      System.out.println("1. Deposit\n" +
                         "2. Withdraw\n" +
                         "3. Transfer\n" +
                         "4.Pay with credit card\n" +
                         "5. Back");// Edited by A.S 10/12/2024 adds n.4
      int i = scanner.nextInt();
      scanner.nextLine();
      if (i == 1){
        System.out.println("Account number:");
        int accnum = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Amount:");
        float amount = scanner.nextFloat();
        scanner.nextLine();
        if (amount < 0.0f)
        {
          System.out.println("Can't enter negative amount");
          return;
        }
        boolean exists = false;
        for (Account acc : accs)
        {
          if (acc.getAccountNumber() == accnum)
          {
            acc.setBalance(acc.getBalance() + amount);
            Transaction transaction = new Transaction(acc.getClientId(), acc.getAccountNumber(), 0, "Deposit", amount);
            transactions.add(transaction);
            Client.updateTotalBalance(clients, accs);
            exists = true;
            break;
          }
        }
        if (!exists)
        {
          System.out.println("Account doesn't exist.");
        }

      }
      else if (i==2) {
        System.out.println("Account number:");
        int accnum = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Amount:");
        float amount = scanner.nextFloat();
        scanner.nextLine();
        if (amount < 0.0f)
        {
          System.out.println("Can't enter negative amount");
          return;
        }
        boolean exists = false;
        for (Account acc : accs)
        {
          if (acc.getAccountNumber() == accnum)
          {
            if (acc.getBalance() < amount)
            {
              System.out.println("The account doesn't have enough money.\nBalance is:" + acc.getBalance());
              return;
            }
            acc.setBalance(acc.getBalance() - amount);
            Transaction transaction = new Transaction(acc.getClientId(), acc.getAccountNumber(), 0, "Withdraw", amount);
            transactions.add(transaction);
            Client.updateTotalBalance(clients, accs);
            exists = true;
            break;
          }
        }
        if (!exists)
        {
          System.out.println("Account doesn't exist.");
        }
      }
      else if (i == 3) {
        System.out.println("Account number to transfer from:");
        int accnum = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Amount:");
        float amount = scanner.nextFloat();
        scanner.nextLine();
        if (amount < 0.0f)
        {
          System.out.println("Can't enter negative amount");
          return;
        }
        boolean exists = false;
        for (Account acc : accs)
        {
          if (acc.getAccountNumber() == accnum)
          {
            if (acc.getBalance() < amount)
            {
              System.out.println("The account doesn't have enough money.\nBalance is:" + acc.getBalance());
              return;
            }
            System.out.println("Account number to transfer to:");
            int acc2num = scanner.nextInt();
            scanner.nextLine();
            boolean exists2 = false;
            for (Account acc2 : accs)
            {
              if (acc2.getAccountNumber() == acc2num)
              {
                acc.setBalance(acc.getBalance() - amount);
                acc2.setBalance(acc.getBalance() + amount);
                Transaction transaction = new Transaction(acc.getClientId(), acc.getAccountNumber(), 0, "Transfer from", amount);
                Transaction transaction2 = new Transaction(acc2.getClientId(), acc2.getAccountNumber(), 0, "Transfer to", amount);
                transactions.add(transaction);
                transactions.add(transaction2);
                Client.updateTotalBalance(clients, accs);
                exists2 = true;
                break;
              }
            }
            if (!exists2)
            {
              System.out.println(" Account for receive doesn't exist.");
            }
            exists = true;
            break;
          }
        }
        if (!exists)
        {
          System.out.println("Account for send doesn't exist.");
        }
      }
      else if(i == 4) {
        //== Added by A.S 10/12/2024 === same Withdraw with some edits ====
        System.out.println("Account number:");
        int accnum = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Amount:");
        float amount = scanner.nextFloat();
        scanner.nextLine();
        if (amount < 0.0f)
        {
          System.out.println("Can't enter negative amount");
          return;
        }
        boolean exists = false;
        for (Account acc : accs)
        {
          if (acc.getAccountNumber() == accnum)
          {
            if (acc.getCardAmount() < amount)
            {
              System.out.println("The credit card doesn't have enough money.\nAmount is:" + acc.getCardAmount());
              return;
            }
            acc.setCardAmount(acc.getCardAmount() - amount);
            acc.setLoyalityPoints((float)(acc.getLoyalityPoints() + (amount*0.5)), accnum, accs);
            Transaction transaction = new Transaction(acc.getClientId(), acc.getAccountNumber(), 0, "Credit card", amount);
            transactions.add(transaction);
            Client.updateTotalBalance(clients, accs);
            exists = true;
            break;
          }
        }
        if (!exists)
        {
          System.out.println("Account doesn't exist.");
        }
      }
      else if (i == 5) {
        return;
      }

    }
  }
  public void showTransactionsHistory(ArrayList<Transaction> transactions) {
    for (Transaction transaction : transactions)
    {
      if (transaction.getClientId() == this.getId())
      {
        System.out.println(transaction.details());
      }
    }
    if (transactions.size() == 0)
    {
      System.out.println("No transactions found");
    }
  }
  public void makeNewAccount(Scanner scanner, ArrayList<Account> accs, ArrayList<Client> clients) {
    System.out.println("1. New current account\n2. New savings account\n3. back");
    int i = scanner.nextInt();
    scanner.nextLine();
    if (i == 1){
      System.out.println("Balance:");
      float balance = scanner.nextFloat();
      scanner.nextLine();
      Account acc = new Account(id, "Current", balance);
      accs.add(acc);
      Client.updateTotalBalance(clients, accs);
    }
    else if (i == 2) {
      System.out.println("Balance:");
      float balance = scanner.nextFloat();
      scanner.nextLine();
      Account acc = new Account(id, "Savings", balance);
      accs.add(acc);
      Client.updateTotalBalance(clients, accs);
    }
    else if (i == 3) {
      return;
    }
  }
  public void changeCreditCardStatus(Scanner scanner, ArrayList<Account> accs){
    System.out.println("Account number:");
    int accnum = scanner.nextInt();
    scanner.nextLine();
    for(Account acc : accs){
      if(acc.getAccountNumber() == accnum){
        if(acc.getIsCreditCardActive()){
          System.out.println("Your credit card is activated do you want to disable it? (Y/N)");
          String answer = scanner.nextLine();
          if(answer.equals("Y") || answer.equals("y")){
            acc.setIsCreditCardActive(false);
            System.out.println("################################");
            System.out.println("Credit card Disabled successfully.");
            System.out.println("################################");
          }
          else if(!answer.equals("n") && !answer.equals("N")){
            System.out.println("Invalid input please try again.");
          }
        }
        else{
          System.out.println("Your credit card is disable do you want to active it? (Y/N)");
          String answer = scanner.nextLine();
          if(answer.equals("Y") || answer.equals("y")){
            acc.setIsCreditCardActive(true);
            System.out.println("################################");
            System.out.println("Credit card activated successfully.");
            System.out.println("################################");
          }
          else if(!answer.equals("n") && !answer.equals("N")){
            System.out.println("Invalid input please try again.");
          }
        }
      }
    }
  }
}