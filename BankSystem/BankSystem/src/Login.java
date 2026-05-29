import java.util.ArrayList;
import java.util.Scanner;

public class Login {
  public static int login(Scanner scanner, ArrayList<Client> clients, ArrayList<Employee> emps) {
    System.out.println("Enter Username or ID:");
    String usernameOrId = scanner.nextLine().trim();
    System.out.println("Enter Password:");
    String password = scanner.nextLine().trim();

    try {
      int id = Integer.parseInt(usernameOrId);

      if (id == 0 && password.equals("admin")) {
        System.out.println("Admin login successful.");
        return 0;
      }

      if (id / 100000 == 1) {
        for (Client client : clients) {
          if (client.getId() == id) {
            if (client.getPassword().equals(password)) {
              System.out.println("Client login successful.");
              return client.getId();
            } else {
              System.out.println("Client ID is correct, but the password is incorrect.");
              return -1;
            }
          }
        }
        System.out.println("Client ID does not exist.");
        return -1;
      }

      if (id / 100000 == 2) {
        for (Employee emp : emps) {
          if (emp.getId() == id) {
            if (emp.getPassword().equals(password)) {
              System.out.println("Employee login successful.");
              return emp.getId();
            } else {
              System.out.println("Employee ID is correct, but the password is incorrect.");
              return -1;
            }
          }
        }
        System.out.println("Employee ID does not exist.");
        return -1;
      }

      System.out.println("Invalid ID format.");
      return -1;

    } catch (NumberFormatException e) {

      if (usernameOrId.equals("admin") && password.equals("admin")) {
        System.out.println("Admin login successful.");
        return 0;
      }


      for (Client client : clients) {
        if (client.getUsername().equals(usernameOrId)) {
          if (client.getPassword().equals(password)) {
            System.out.println("Client login successful.");
            return client.getId();
          } else {
            System.out.println("Client username is correct, but the password is incorrect.");
            return -1;
          }
        }
      }

      for (Employee emp : emps) {
        if (emp.getUsername().equals(usernameOrId)) {
          if (emp.getPassword().equals(password)) {
            System.out.println("Employee login successful.");
            return emp.getId();
          } else {
            System.out.println("Employee username is correct, but the password is incorrect.");
            return -1;
          }
        }
      }

      System.out.println("Username does not exist.");
      return -1;
    }
  }
}
