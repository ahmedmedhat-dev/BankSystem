import java.util.ArrayList;
import java.util.Scanner;

public class Signup {
  public static void signup(Scanner scanner, ArrayList<Client> clients, ArrayList<Employee> emps, ArrayList<Account> accs) {
    int i = 0;
    while (true) {
      while (true) {
        System.out.println("1. Signup as a client\n2. Signup as an employee");
        try {
          i = Integer.parseInt(scanner.nextLine());
          if (i == 1 || i == 2) {
            break;
          }
          else {
            System.out.println("Invalid option. Please enter 1 or 2.");
          }
        } catch (NumberFormatException e) {
          System.out.println("Invalid input. Please enter a number (1 or 2).");
        }
      }
      if (i == 1) {
        System.out.println("First name:");
        String fName;
        while (true) {
          fName = scanner.nextLine().trim();
          if (fName.isEmpty()) {
            System.out.println("First name cannot be empty. Please try again:");
          } else if (!fName.matches("[a-zA-Z]+")) {
            System.out.println("First name must contain only alphabetical characters. Please try again:");
          } else if (fName.length() > 30) {
            System.out.println("First name is too long (maximum 30 characters). Please try again:");
          } else {
            break;
          }
        }
        System.out.println("Last name:");
        String lName;
        while (true) {
          lName = scanner.nextLine().trim();
          if (lName.isEmpty()) {
            System.out.println("Last name cannot be empty. Please try again:");
          } else if (!lName.matches("[a-zA-Z]+")) {
            System.out.println("Last name must contain only alphabetical characters. Please try again:");
          } else if (lName.length() > 30) {
            System.out.println("Last name is too long (maximum 30 characters). Please try again:");
          } else {
            break;
          }
        }
        System.out.println("Username:");
        String username ;
        while (true) {
          username = scanner.nextLine().trim();
          if (username.isEmpty()) {
            System.out.println("Username cannot be empty. Please try again:");
          } else if (!username.matches("[a-zA-Z0-9_]+")) {
            System.out.println("Username can only contain alphanumeric characters and underscores. Please try again:");
          } else if (username.length() > 30) {
            System.out.println("Username is too long (maximum 30 characters). Please try again:");
          } else {
            break;
          }
        }
        System.out.println("Password:");
        String password;
        while (true) {
          password = scanner.nextLine().trim();
          if (password.isEmpty()) {
            System.out.println("Password cannot be empty. Please try again:");
          } else if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters long. Please try again:");
          } else if (!password.matches(".*[A-Z].*")) {
            System.out.println("Password must contain at least one uppercase letter. Please try again:");
          } else if (!password.matches(".*[0-9].*")) {
            System.out.println("Password must contain at least one digit. Please try again:");
          } else {
            break;
          }
        }
        String passwordc="";
        boolean passwordMatch = false;
        int times = 3;
        while (times > 0) {
          System.out.println("Re-enter password:");
          passwordc = scanner.nextLine();

          if (password.equals(passwordc)) {
            passwordMatch = true;
            break;
          } else {
            times--;
            if (times > 0) {
              System.out.println("Passwords don't match. You have " + times + " times left.");
            }
          }
        }
        if (!passwordMatch) {
          System.out.println("Maximum attempts reached. Signup failed.");
          return;
        }
        String phoneNumber;
        while (true) {
          System.out.println("Phone number (must be 11 digits and start with '01'):");
          phoneNumber = scanner.nextLine().trim();
          if (phoneNumber.isEmpty()) {
            System.out.println("Phone number cannot be empty. Please try again:");
          } else if (!phoneNumber.matches("01\\d{9}")) {
            System.out.println("Phone number must be 11 digits and start with '01'. Please try again:");
          } else {
            break;
          }
        }
        if (username.equals("admin")) {
          System.out.println("Username can't be \"admin\"");
          return;
        }
        for (Client clientt : clients) {
          if (clientt.getUsername().equals(username)) {
            System.out.println("Username already exists");
            return;
          }
        }
        for (Employee empp : emps) {
          if (empp.getUsername().equals(username)) {
            System.out.println("Username already exists");
            return;
          }
        }
        Client client = new Client(fName, lName, username, password, phoneNumber, 0.0f);
        clients.add(client);
        // Account acc = new Account(client.getId(), "Current", balance);
        //accs.add(acc);
        System.out.println("Your id is: " + client.getId());
        return;
      }
      else if (i == 2) {
        System.out.println("First name:");
        String fName;
        while (true) {
          fName = scanner.nextLine().trim();
          if (fName.isEmpty()) {
            System.out.println("First name cannot be empty. Please try again:");
          } else if (!fName.matches("[a-zA-Z]+")) {
            System.out.println("First name must contain only alphabetical characters. Please try again:");
          } else if (fName.length() > 30) {
            System.out.println("First name is too long (maximum 30 characters). Please try again:");
          } else {
            break;
          }
        }

        System.out.println("Last name:");
        String lName;
        while (true) {
          lName = scanner.nextLine().trim();
          if (lName.isEmpty()) {
            System.out.println("Last name cannot be empty. Please try again:");
          } else if (!lName.matches("[a-zA-Z]+")) {
            System.out.println("Last name must contain only alphabetical characters. Please try again:");
          } else if (lName.length() > 30) {
            System.out.println("Last name is too long (maximum 30 characters). Please try again:");
          } else {
            break;
          }
        }

        System.out.println("Username:");
        String username;
        while (true) {
          username = scanner.nextLine().trim();
          if (username.isEmpty()) {
            System.out.println("Username cannot be empty. Please try again:");
          } else if (!username.matches("[a-zA-Z0-9_]+")) {
            System.out.println("Username can only contain alphanumeric characters and underscores. Please try again:");
          } else if (username.length() > 30) {
            System.out.println("Username is too long (maximum 30 characters). Please try again:");
          } else {
            break;
          }
        }


        System.out.println("Password:");
        String password ;
        while (true) {
          password = scanner.nextLine().trim();
          if (password.isEmpty()) {
            System.out.println("Password cannot be empty. Please try again:");
          } else if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters long. Please try again:");
          } else if (!password.matches(".*[A-Z].*")) {
            System.out.println("Password must contain at least one uppercase letter. Please try again:");
          } else if (!password.matches(".*[0-9].*")) {
            System.out.println("Password must contain at least one digit. Please try again:");
          } else {
            break;
          }
        }


        String passwordc = "";
        boolean passwordMatch = false;
        int times = 3;

        while (times > 0) {
          System.out.println("Re-enter password:");
          passwordc = scanner.nextLine();

          if (password.equals(passwordc)) {
            passwordMatch = true;
            break;
          } else {
            times--;
            if (times > 0) {
              System.out.println("Passwords don't match. You have " + times + " times left.");
            }
          }
        }

        if (!passwordMatch) {
          System.out.println("Maximum attempts reached. Signup failed.");
          return;
        }

        String phoneNumber;
        while (true) {
          System.out.println("Phone number (must be 11 digits and start with '01'):");
          phoneNumber = scanner.nextLine().trim();
          if (phoneNumber.isEmpty()) {
            System.out.println("Phone number cannot be empty. Please try again:");
          } else if (!phoneNumber.matches("01\\d{9}")) {
            System.out.println("Phone number must be 11 digits and start with '01'. Please try again:");
          } else {
            break;
          }
        }

        System.out.println("Address:");
        String address = scanner.nextLine();
        System.out.println("Position:");
        String position = scanner.nextLine();
        System.out.println("Graduated college:");
        String graduatedCollege = scanner.nextLine();

        boolean pass = false;
        int yearOfGraduation = 0;
        while (!pass) {
          try {
            System.out.println("Year of graduation:");
            yearOfGraduation = Integer.parseInt(scanner.nextLine());
            pass = true;
          } catch (Exception e) {
            System.out.println("Invalid input. Only integers are allowed.");
          }
        }

        System.out.println("College grade:");
        String collegeGrade = scanner.nextLine();

        for (Client clientt : clients) {
          if (clientt.getUsername().equals(username)) {
            System.out.println("Username already exists");
            return;
          }
        }
        for (Employee empp : emps) {
          if (empp.getUsername().equals(username)) {
            System.out.println("Username already exists");
            return;
          }
        }


        Employee emp = new Employee(fName, lName, username, password, phoneNumber, address, position, graduatedCollege, yearOfGraduation, collegeGrade);
        emps.add(emp);
        System.out.println("Your id is: " + emp.getId());
        return;
      }
    }
  }
}
