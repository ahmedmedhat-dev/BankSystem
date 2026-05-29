import java.util.ArrayList;
import java.util.Scanner;

public class Admin {
  public static void AuthorizeEmployee(Scanner scanner, ArrayList<Employee> emps) {
    int id;
    System.out.println("Enter employee id:");
    id = scanner.nextInt();
    scanner.nextLine();
    for (Employee emp : emps)
    {
      if (emp.getId() == id)
      {
        emp.setAuthorization("Authorized");
        System.out.println("Employee " + id + " was authorized successfully.");
        return;
      }
    }
    System.out.println("Couldn't find an employee with id " + id);
    return;
  }
  public static void AuthorizeAllEmployees(ArrayList<Employee> emps) {
    for (Employee emp : emps)
    {
      emp.setAuthorization("Authorized");
    }
    System.out.println("All employees were authorized successfully.");
    return;
  }
  public static void displayClient (Scanner scanner, ArrayList<Client> clients) {
    int id;
    System.out.println("Enter client id:");
    id = scanner.nextInt();
    scanner.nextLine();
    for (Client client : clients)
    {
      if (client.getId() == id)
      {
        System.out.println(client.details());
        return;
      }
    }
    System.out.println("Client doesn't exist\n###################\n");
    return;
  }
  public static void displayAllClients (ArrayList<Client> clients) {
    String str = "";
    for (Client client : clients)
    {
      str += client.details();
    }
    System.out.println(str);
    return;
  }
  public static void displayEmployee (Scanner scanner, ArrayList<Employee> emps) {
    int id;
    System.out.println("Enter employee id:");
    id = scanner.nextInt();
    scanner.nextLine();
    for (Employee emp : emps)
    {
      if (emp.getId() == id)
      {
        System.out.println(emp.details());
        return;
      }
    }
    System.out.println("Employee doesn't exist\n###################\n");
    return;

  }
  public static void displayAllEmployees (ArrayList<Employee> emps) {
    String str = "";
    for (Employee emp : emps)
    {
      str += emp.details();
    }
    System.out.println(str);
    return;
  }
  public static void displayTransactions (Scanner scanner, ArrayList<Transaction> transactions) {
    System.out.println("1. All transactions\n" +
                       "2. Transactions on a specific date\n" +
                       "3. Transactions by a specific client\n" +
                       "4. Transactions by a specific employee\n" +
                       "5. Back");
    int i = scanner.nextInt();
    scanner.nextLine();
    if (i == 1) {
      for (Transaction transaction : transactions)
      {
        System.out.println(transaction.details());
      }
      if (transactions.size() == 0)
      {
        System.out.println("No transactions found");
      }
    }
    else if (i == 2) {
      System.out.println("Enter date (yyyy-MM-dd):");
      String date = scanner.nextLine();
      boolean exists = false;
      for (Transaction transaction : transactions) {
        if (transaction.getDate().equals(date)) {
          System.out.println(transaction.details());
          exists = true;
        }
      }
      if (!exists) {
        System.out.println("No transactions found");
      }
    }
    else if (i == 3) {
      System.out.println("Enter client id:");
      int clientId = scanner.nextInt();
      scanner.nextLine();
      boolean exists = false;
      for (Transaction transaction : transactions)
      {
        if (transaction.getClientId() == clientId)
        {
          System.out.println(transaction.details());
          exists = true;
        }
      }
      if (!exists)
      {
        System.out.println("No transactions found");
      }
    }
    else if (i == 4) {
      System.out.println("Enter employee id:");
      int empId = scanner.nextInt();
      scanner.nextLine();
      boolean exists = false;
      for (Transaction transaction : transactions)
      {
        if (transaction.getEmployeeId() == empId)
        {
          System.out.println(transaction.details());
          exists = true;
        }
      }
      if (!exists)
      {
        System.out.println("No transactions found");
      }
    }
    else {
      return;
    }
  }
  public static void menu(Scanner scanner, ArrayList<Client> clients, ArrayList<Employee> emps, ArrayList<Transaction> transactions) {
    int i = 0;
    while (true)
    {
      System.out.println("1. Authorize a new employee\n" +
                         "2. Authorize all new employees\n" +
                         "3. Display an employee\n" +
                         "4. Display all employees\n" +
                         "5. Display a client\n" +
                         "6. Display all clients\n" +
                         "7. Display transactions\n" +
                         "8. Logout");
      i = scanner.nextInt();
      scanner.nextLine();
      if (i == 1) {
        AuthorizeEmployee(scanner, emps);
      }
      else if (i == 2) {
        AuthorizeAllEmployees(emps);
      }
      else if (i == 3) {
        displayEmployee(scanner, emps);
      }
      else if (i == 4) {
        displayAllEmployees(emps);
      }
      else if (i == 5) {
        displayClient(scanner, clients);
      }
      else if (i == 6) {
        displayAllClients(clients);
      }
      else if (i == 7) {
        displayTransactions(scanner, transactions);
      }
      else if (i == 8) {
        return;
      }
    }
  }
}