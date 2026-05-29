import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    ArrayList<Client> clients = new ArrayList();
    ArrayList<Employee> emps = new ArrayList();
    ArrayList<Account> accs = new ArrayList();
    ArrayList<Transaction> transactions = new ArrayList();
    Database database = new Database();
    database.load(clients, emps, accs, transactions);
    Scanner scanner = new Scanner(System.in);

    int i;
    do {
      int curId = -1;

      do {
        System.out.println("Welcome\n1. Login\n2. Signup\n-1. Exit");
        i = scanner.nextInt();
        scanner.nextLine();
        if (i == 1) {
          curId = Login.login(scanner, clients, emps);
        } else if (i == 2) {
          Signup.signup(scanner, clients, emps, accs);
        }
      } while(i != -1 && curId == -1);

      if (curId == 0) {
        Admin.menu(scanner, clients, emps, transactions);
      }
      else {
        Iterator var9;
        if (curId / 100000 == 2) {
          var9 = emps.iterator();

          while(var9.hasNext()) {
            Employee emp = (Employee)var9.next();
            if (emp.getId() == curId) {
              emp.menu(scanner, emps, clients, accs, transactions);
              break;
            }
          }
        } else if (curId / 100000 == 1) {
          var9 = clients.iterator();

          while(var9.hasNext()) {
            Client client = (Client)var9.next();
            if (client.getId() == curId) {
              client.menu(scanner, emps, clients, accs, transactions);
              break;
            }
          }
        }
      }
    } while(i != -1);

    while(true) {
      System.out.println("Save changes to the database (y/n)?");
      String c = scanner.nextLine();
      if (!c.equals("y") && !c.equals("Y")) {
        if (!c.equals("n") && !c.equals("N")) {
          System.out.println("Type y for yes or n for no.");
          continue;
        }

        System.out.println("Changes were not saved.");
        return;
      }

      database.save(clients, emps, accs, transactions);
      System.out.println("Changes where saved successfully.");
      return;
    }
  }
}
