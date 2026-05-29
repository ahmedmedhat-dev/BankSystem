import java.util.ArrayList;
import java.util.Scanner;

public class Employee extends User {
  private String authorization = "Unauthorized";
  private String address;
  private String position;
  private String graduatedCollege;
  private int yearOfGraduation;
  private String collegeGrade;
  public static int nextId = 0;
  //== Getters ======================================================
  public String getAuthorization() {
    return authorization;
  }
  public String getAddress() {
    return address;
  }
  public String getPosition() {
    return position;
  }
  public String getGraduatedCollege() {
    return graduatedCollege;
  }
  public int getYearOfGraduation() {
    return yearOfGraduation;
  }
  public String getCollegeGrade() {
    return collegeGrade;
  }
  //== Setters =====================================================
  public void setAuthorization(String authorization) {
    this.authorization = authorization;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public void setPosition(String position) {
    this.position = position;
  }
  public void setGraduatedCollege(String graduatedCollege) {
    this.graduatedCollege = graduatedCollege;
  }
  public void setYearOfGraduation(int yearOfGraduation) {
    this.yearOfGraduation = yearOfGraduation;
  }
  public void setCollegeGrade(String collegeGrade) {
    this.collegeGrade = collegeGrade;
  }
  //================================================================
  @Override
  public String details() {

    String str = super.details();
    str += "Authorization: " + this.getAuthorization() + '\n';
    str += "Address: " + this.getAddress() + '\n';
    str += "Position: " + this.getPosition() + '\n';
    str += "Graduated college: " + this.getGraduatedCollege() + '\n';
    str += "Year of graduation: " + this.getYearOfGraduation() + '\n';
    str += "College grade: " + this.collegeGrade + "\n###################\n";
    return str;
  }

  public Employee(String fName, String lName, String username, String password, String phoneNumber, String address, String position, String graduatedCollege, int yearOfGraduation, String collegeGrade) {
    super(fName, lName, username, password, phoneNumber);
    this.setId(200000 + nextId);
    this.address = address;
    this.position = position;
    this.graduatedCollege = graduatedCollege;
    this.yearOfGraduation = yearOfGraduation;
    this.collegeGrade = collegeGrade;
    nextId++;
  }

  public Employee(int id, String fName, String lName, String username, String password, String phoneNumber, String authorization, String address, String position, String graduatedCollege, int yearOfGraduation, String collegeGrade) {
    super(fName, lName, username, password, phoneNumber);
    this.setId(id);
    this.authorization = authorization;
    this.address = address;
    this.position = position;
    this.graduatedCollege = graduatedCollege;
    this.yearOfGraduation = yearOfGraduation;
    this.collegeGrade = collegeGrade;
    if (nextId < this.getId() - 199999)
    {
      nextId = this.getId() - 199999;
    }
  }
  @Override
  public void menu (Scanner scanner, ArrayList<Employee> emps, ArrayList<Client> clients, ArrayList<Account> accs, ArrayList<Transaction> transactions) {
    int i = 0;
    if (this.getAuthorization().equals("Authorized")) {
      while(true) {
        System.out.println("1. Edit personal info\n" +
                           "2. Add a new client\n" +
                           "3. Create a new account for an existing client\n" +
                           "4. Edit a client profile\n" +
                           "5. Edit a client account\n" +
                           "6. Find a client\n" +
                           "7. Close a client account\n" +
                           "8. Make a new transaction\n" +
                           "9. Logout");
        i = scanner.nextInt();
        scanner.nextLine();
        if (i == 1) {
          this.editPersonalInfo(scanner);
        }
        else if (i == 2) {
          this.addNewClient(scanner, clients, emps, accs);
        }
        else if (i == 3) {
          this.addNewClientAccount(scanner, clients, accs);
        }
        else if (i == 4) {
          this.editClientProfile(scanner, clients);
        }
        else if (i == 5) {
          this.editClientAccount(scanner, clients, accs);
        }
        else if (i == 6) {
          this.findClient(scanner, clients, accs);
        }
        else if (i == 7) {
          this.deleteClientAccount(scanner, accs);
        }
        else if (i == 8) {
          this.makeNewTransaction(scanner, clients, accs, transactions);
        }
        else if (i == 9) {
          return;
        }
      }
    }
    else
    {
      while(true) {
        System.out.println("1. Edit personal info\n" +
                           "2. Find a client\n" +
                           "3. Logout");
        i = scanner.nextInt();
        scanner.nextLine();
        if (i == 1) {
          this.editPersonalInfo(scanner);
        }
        else if (i == 2) {
          this.findClient(scanner, clients, accs);
        }
        else if (i == 3) {
          return;
        }
      }
    }
  }
  public void editPersonalInfo(Scanner scanner) {
    while(true) {
      System.out.println(this.details());// Added by A.S 20/12/2024
      System.out.println("1. Edit address\n2. Edit position\n3. Back");
      int i = scanner.nextInt();
      scanner.nextLine();
      if (i == 1) {
        System.out.println("Enter the new address:");
        String newAddress = scanner.nextLine();
        this.setAddress(newAddress);
      }
      else if (i == 2) {
        System.out.println("Enter the new position:");
        String newPosition = scanner.nextLine();
        this.setPosition(newPosition);
      }
      else if (i == 3) {
        return;
      }
    }
  }
  public void addNewClient(Scanner scanner, ArrayList<Client> clients, ArrayList<Employee> emps, ArrayList<Account> accs) {
    System.out.println("First name:");
    String fName = scanner.nextLine();
    System.out.println("Last name:");
    String lName = scanner.nextLine();
    System.out.println("Username");
    String username = scanner.nextLine();
    System.out.println("Password:");
    String password = scanner.nextLine();
    System.out.println("Re-enter password:");
    String passwordc = scanner.nextLine();
    String phoneNumber = " ";
    boolean pass = false;
    while (!pass) {
      try {
        System.out.println("Phone number:");
        phoneNumber = scanner.nextLine();
        pass = true;
      }
      catch (Exception e) {
        System.out.println("Invalid input. Only integers are allowed.");
      }
    }
    pass = false;
    float balance = 0;
    while (!pass) {
      try {
        System.out.println("Balance:");
        balance = Float.parseFloat(scanner.nextLine());
        pass = true;
      } catch (Exception e) {
        System.out.println("Invalid input. Only decimals are allowed.");
      }
    }
    // Validations
    if (username.isEmpty() || password.isEmpty()) {
      System.out.println("Can't have an empty username or password");
      return;
    }
    if (username.length() > 30) {
      System.out.println("Username is too long");
      return;
    }
    boolean containsAlpha = false;
    for (int j = 0; j < username.length(); j++) {
      if ((username.charAt(j) >= 'a' && username.charAt(j) <= 'z') || (username.charAt(j) >= 'A' && username.charAt(j) <= 'Z')) {
        containsAlpha = true;
        break;
      }
    }
    if (!containsAlpha) {
      System.out.println("Username must contain at least one alphabetical character");
      return;
    }
    if (username.equals("admin")) {
      System.out.println("Username can't be \"admin\"");
      return;
    }
    for (Client clientt : clients) {
      if (clientt.getUsername().equals(username))
      {
        System.out.println("Username already exists");
        return;
      }
    }
    for (Employee empp : emps) {
      if (empp.getUsername().equals(username))
      {
        System.out.println("Username already exists");
        return;
      }
    }
    if (password.length() > 30) {
      System.out.println("Password is too long");
      return;
    }
    if (!password.equals(passwordc)) {
      System.out.println("Password doesn't match. Please try again.");
      return;
    }
    if (balance < 0.0f) {
      System.out.println("Can't have negative balance");
      return;
    }
    //
    Client client = new Client(fName, lName, username, password, phoneNumber, balance);
    clients.add(client);
    Account acc = new Account(client.getId(), "Current", balance);
    accs.add(acc);
    System.out.println("The new client's id is: " + client.getId());
  }
  public void addNewClientAccount(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accs) {
    System.out.println("Client ID:");
    int id = scanner.nextInt();
    scanner.nextLine();
    boolean exists = false;
    for (Client client : clients) {
      if (client.getId() == id) {
        exists = true;
        while (true) {
          System.out.println("1. New current account\n2. New savings account\n3. back");
          int i = scanner.nextInt();
          scanner.nextLine();
          if (i == 1) {
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
      }
    }
    System.out.println("Client doesn't exist");
    return;
  }
  public void editClientProfile(Scanner scanner, ArrayList<Client> clients) {
    System.out.println("Client ID:");
    int id = scanner.nextInt();
    scanner.nextLine();
    boolean exists = false;
    for (Client client : clients) {
      if (client.getId() == id) {
        exists = true;
        while(true) {
          System.out.println("1. Edit first name\n2. Edit last name\n3. Edit password\n4. Edit phone number\n5. Back");
          int i = scanner.nextInt();
          scanner.nextLine();
          if (i == 1) {
            System.out.println("Enter the new first name:");
            String neww = scanner.nextLine();
            client.setfName(neww);
          }
          else if (i == 2) {
            System.out.println("Enter the new last name:");
            String neww = scanner.nextLine();
            client.setlName(neww);
          }
          else if (i == 3) {
            System.out.println("Enter the new password:");
            String neww = scanner.nextLine();
            client.setPassword(neww);
          }
          else if (i == 4) {
            System.out.println("Enter the new phone number:");
            String neww = scanner.nextLine();
            scanner.nextLine();
            client.setPhoneNumber(neww);
          }
          else if (i == 5) {
            return;
          }
        }
      }
    }
    System.out.println("Client doesn't exist");
    return;
  }
  public void editClientAccount(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accs) {
    System.out.println("Account number:");
    int num = scanner.nextInt();
    scanner.nextLine();
    boolean exists = false;
    for (Account acc : accs) {
      if (acc.getAccountNumber() == num) {
        exists = true;
        while (true) {
          System.out.println("1. Activate account\n2. Close account\n3. Back");
          int i = scanner.nextInt();
          scanner.nextLine();
          if (i == 1) {
            acc.setAccountState("Active");
          }
          else if (i == 2) {
            acc.setAccountState("Closed");
          }
          else if (i == 3) {
            return;
          }
        }
      }
    }
    System.out.println("Account doesn't exist.");
  }
  public void findClient(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accs) {
    while (true)
    {
      System.out.println("1. By ID\n2. By username\n3. By account number\n4. Back");
      int i = scanner.nextInt();
      scanner.nextLine();
      if (i == 1) {
        System.out.println("Client ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean exists = false;
        for (Client client : clients) {
          if (client.getId() == id) {
            System.out.println(client.details());
            exists = true;
            break;
          }
        }
        if (!exists) {
          System.out.println("Client doesn't exist");
        }
      }
      else if (i == 2) {
        System.out.println("Client username:");
        String username = scanner.nextLine();
        boolean exists = false;
        for (Client client : clients) {
          if (client.getUsername().equals(username)) {
            System.out.println(client.details());
            exists = true;
            break;
          }
        }
        if (!exists) {
          System.out.println("Client doesn't exist");
        }
      }
      else if (i == 3) {
        System.out.println("Client account number:");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();
        boolean exists = false;
        for (Account acc : accs) {
          if (acc.getAccountNumber() == accountNumber) {
            int id = acc.getClientId();
            for (Client client : clients) {
              if (client.getId() == id) {
                System.out.println(client.details());
                exists = true;
                break;
              }
            }
            break;
          }
        }
        if (!exists) {
          System.out.println("Client doesn't exist");
        }
      }
      else if (i == 4) {
        return;
      }
    }
  }
  public void deleteClientAccount(Scanner scanner, ArrayList<Account> accs) {
    System.out.println("Account number:");
    int num = scanner.nextInt();
    scanner.nextLine();
    for (Account acc : accs) {
      if (acc.getAccountNumber() == num) {
        accs.remove(acc);
        return;
      }
    }
    System.out.println("Account doesn't exist.");
  }
  public void makeNewTransaction(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accs, ArrayList<Transaction> transactions) {
    while (true) {
      System.out.println("1. Deposit\n2. Withdraw\n3. Transfer\n4. Back");
      int i = scanner.nextInt();
      scanner.nextLine();
      if (i == 1) {
        System.out.println("Account number:");
        int accnum = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Amount:");
        float amount = scanner.nextFloat();
        scanner.nextLine();
        if (amount < 0.0f) {
          System.out.println("Can't enter negative amount");
          return;
        }
        boolean exists = false;
        for (Account acc : accs) {
          if (acc.getAccountNumber() == accnum) {
            acc.setBalance(acc.getBalance() + amount);
            Transaction transaction = new Transaction(acc.getClientId(), acc.getAccountNumber(), this.getId(), "Deposit", amount);
            transactions.add(transaction);
            exists = true;
            Client.updateTotalBalance(clients, accs);
            break;
          }
        }
        if (!exists) {
          System.out.println("Account doesn't exist.");
        }
      }
      else if (i == 2) {
        System.out.println("Account number:");
        int accnum = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Amount:");
        float amount = scanner.nextFloat();
        scanner.nextLine();
        if (amount < 0.0f) {
          System.out.println("Can't enter negative amount");
          return;
        }
        boolean exists = false;
        for (Account acc : accs) {
          if (acc.getAccountNumber() == accnum) {
            if (acc.getBalance() < amount) {
              System.out.println("The account doesn't have enough money.\nBalance is:" + acc.getBalance());
              return;
            }
            acc.setBalance(acc.getBalance() - amount);
            Transaction transaction = new Transaction(acc.getClientId(), acc.getAccountNumber(), this.getId(), "Withdraw", amount);
            transactions.add(transaction);
            Client.updateTotalBalance(clients, accs);
            exists = true;
            break;
          }
        }
        if (!exists) {
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
        if (amount < 0.0f) {
          System.out.println("Can't enter negative amount");
          return;
        }
        boolean exists = false;
        for (Account acc : accs) {
          if (acc.getAccountNumber() == accnum) {
            if (acc.getBalance() < amount) {
              System.out.println("The account doesn't have enough money.\nBalance is:" + acc.getBalance());
              return;
            }
            System.out.println("Account number to transfer to:");
            int acc2num = scanner.nextInt();
            scanner.nextLine();
            boolean exists2 = false;
            for (Account acc2 : accs) {
              if (acc2.getAccountNumber() == acc2num) {
                acc.setBalance(acc.getBalance() - amount);
                acc2.setBalance(acc2.getBalance() + amount);
                Transaction transaction = new Transaction(acc.getClientId(), acc.getAccountNumber(), this.getId(), "Transfer from", amount);
                Transaction transaction2 = new Transaction(acc2.getClientId(), acc2.getAccountNumber(), this.getId(), "Transfer to", amount);
                transactions.add(transaction);
                transactions.add(transaction2);
                Client.updateTotalBalance(clients, accs);
                exists2 = true;
                break;
              }
            }
            if (!exists2) {
              System.out.println("Account doesn't exist.");
            }
            exists = true;
            break;
          }
        }
        if (!exists) {
          System.out.println("Account doesn't exist.");
        }
      }
      else if (i == 4) {
        return;
      }
    }
  }
}
