import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Database {
  public void clear(ArrayList<Client> clients, ArrayList<Employee> emps, ArrayList<Account> accs, ArrayList<Transaction> transactions) {
    if (!clients.isEmpty()) clients.clear();
    if (!emps.isEmpty()) emps.clear();
    if (!accs.isEmpty()) accs.clear();
    if (!transactions.isEmpty()) transactions.clear();
  }
  public void load(ArrayList<Client> clients, ArrayList<Employee> emps, ArrayList<Account> accs, ArrayList<Transaction> transactions) {
    clear(clients, emps, accs, transactions);
    ///////////////////////////////////////////////////////////////////
    /////////////////////////Reading from files////////////////////////
    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////
    BufferedReader clientsf=null;
    BufferedReader empsf=null;
    BufferedReader accsf=null;
    BufferedReader transactionsf=null;
    try
    {
      clientsf = new BufferedReader(new FileReader("./database/clients.txt"));
      String line;
      while ((line = clientsf.readLine()) != null && !line.isEmpty())
      {
        try
        {
          int id = Integer.parseInt(line);
          String fName = clientsf.readLine();
          String lName = clientsf.readLine();
          String username = clientsf.readLine();
          String password = clientsf.readLine();
          String phoneNumber = clientsf.readLine();
          float balance = Float.parseFloat(clientsf.readLine());
          Client client = new Client(id, fName, lName, username, password, phoneNumber, balance);
          clients.add(client);
        }
        catch (Exception e) {
          System.out.println("Error While Reading Client Data \n Skipping Invalid Data");
        }
      }
    } catch (Exception e) {
      System.out.println("Error while Openning The Client File \n Database Is Empty Now ");
      clear(clients, emps, accs, transactions);
    }
    finally {
      if(clientsf!=null)
      {
        try{
          clientsf.close();
        }
        catch (Exception e)
        {
          System.out.println("Error While Closing Clients File");
        }
      }
    }
    try
    {
      empsf = new BufferedReader(new FileReader("./database/employees.txt"));
      String line;
      while((line = empsf.readLine()) != null && !line.isEmpty())
      {
        try
        {
          int id = Integer.parseInt(line);
          String fName = empsf.readLine();
          String lName = empsf.readLine();
          String username = empsf.readLine();
          String password = empsf.readLine();
          String phoneNumber = empsf.readLine();
          String authorization = empsf.readLine();
          String address = empsf.readLine();
          String position = empsf.readLine();
          String graduatedCollege = empsf.readLine();
          int yearOfGraduation = Integer.parseInt(empsf.readLine());
          String collegeGrade = empsf.readLine();
          Employee emp = new Employee(id, fName, lName, username, password, phoneNumber, authorization, address, position, graduatedCollege, yearOfGraduation, collegeGrade);
          emps.add(emp);
        }
        catch (Exception e) {

          System.out.println("Error While Reading Employee Data \n Skipping Invalid Data");

        }
      }
    } catch (Exception e) {
      System.out.println("Error while Openning The Employee File \n Database Is Empty Now ");
      clear(clients, emps, accs, transactions);
    }
    finally {

      if (empsf!=null)
      {
        try {
          empsf.close();
        }
        catch (Exception e)
        {
          System.out.println("Error While Closing Employees File");
        }
      }
    }
    try
    {
      accsf = new BufferedReader(new FileReader("./database/accounts.txt"));
      String line;
      while ((line = accsf.readLine()) != null && !line.isEmpty())
      {
        try
        {
          int accountNumber = Integer.parseInt(line);
          int clientId = Integer.parseInt(accsf.readLine());
          String accountType = accsf.readLine();
          String accountState = accsf.readLine();
          float balance = Float.parseFloat(accsf.readLine());
          float fees = Float.parseFloat(accsf.readLine());
          float interestRate = Float.parseFloat(accsf.readLine());
          String CreditCardNumber = accsf.readLine();
          int SecurityPin=Integer.parseInt(accsf.readLine());
          float LoyaltyPoints=Float.parseFloat(accsf.readLine());
          float cardAmount = Float.parseFloat(accsf.readLine()); // Added by A.S 12/12/2024
          boolean isCreditCardActive = Boolean.parseBoolean(accsf.readLine()); // Added by A.S 12/12/2024
          Account acc = new Account(accountNumber, clientId, accountType, accountState, balance,
                  fees, interestRate,CreditCardNumber,LoyaltyPoints,SecurityPin, cardAmount, isCreditCardActive);
          accs.add(acc);
        }
        catch (Exception e) {

          System.out.println("Error While Reading Account Data \n Skipping Invalid Data.");
        }
      }
    } catch (Exception e) {
      System.out.println("Error while Openning The Account File \n Database Is Empty Now");
      clear(clients, emps, accs, transactions);
    }
    finally {
      if(accsf!=null)
      {
        try{
          accsf.close();
        }
        catch (Exception e)
        {
          System.out.println("Error While Closing Current Accounts File");
        }
      }
    }
    try
    {
      transactionsf = new BufferedReader(new FileReader("./database/transactions.txt"));
      String line;
      while ((line = transactionsf.readLine()) != null && !line.isEmpty())
      {
        try
        {
          int id = Integer.parseInt(line);
          String date = transactionsf.readLine();
          int clientId = Integer.parseInt(transactionsf.readLine());
          int accountNumber = Integer.parseInt(transactionsf.readLine());
          int employeeId = Integer.parseInt(transactionsf.readLine());
          String transactionType = transactionsf.readLine();
          float amount = Float.parseFloat(transactionsf.readLine());
          Transaction transaction = new Transaction(id, date, clientId, accountNumber, employeeId, transactionType, amount);
          transactions.add(transaction);
        }
        catch (Exception e) {

          System.out.println("Error While Reading Transaction Data \n Skipping Invalid Data.");

        }
      }
    } catch (Exception e) {
      System.out.println("Error while Openning The Transaction File \\n Database Is Empty Now\"");
      clear(clients, emps, accs, transactions);
    }
    finally {
      if(transactionsf!=null)
      {
        try
        {
          transactionsf.close();
        }
        catch (Exception e)
        {
          System.out.println("Error While Closing Transactions File");
        }
      }
    }
  }
  public void save(ArrayList<Client> clients, ArrayList<Employee> emps, ArrayList<Account> accs, ArrayList<Transaction> transactions) {
    ///////////////////////////////////////////////////////////////////
    /////////////////////Writing to files//////////////////////////////
    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////
    BufferedWriter clientsfw=null;
    BufferedWriter empsfw=null;
    BufferedWriter accsfw=null;
    BufferedWriter transactionsfw=null;
    try
    {
      clientsfw = new BufferedWriter(new FileWriter("./database/clients.txt"));
      for (Client client : clients)
      {
        clientsfw.write(client.getId() + "\n");
        clientsfw.write(client.getfName() + "\n");
        clientsfw.write(client.getlName() + "\n");
        clientsfw.write(client.getUsername() + "\n");
        clientsfw.write(client.getPassword() + "\n");
        clientsfw.write(client.getPhoneNumber() +"\n");
        clientsfw.write(client.getBalance() + "\n");
      }
    }
    catch (Exception e)
    {
      System.out.println("Error While Saving Clients");
    }
    finally {
      if(clientsfw!=null)
      {
        try{
          clientsfw.close();
        }
        catch (Exception e)
        {
          System.out.println("Error While Closing Clients File");
        }
      }
    }
    try
    {
      empsfw = new BufferedWriter(new FileWriter("./database/employees.txt"));
      for (Employee emp : emps)
      {
        empsfw.write(emp.getId() + "\n");
        empsfw.write(emp.getlName() + "\n");
        empsfw.write(emp.getUsername() + "\n");
        empsfw.write(emp.getPassword() + "\n");
        empsfw.write(emp.getPhoneNumber() +"\n");
        empsfw.write(emp.getAuthorization() + "\n");
        empsfw.write(emp.getAddress() + "\n");
        empsfw.write(emp.getPosition() + "\n");
        empsfw.write(emp.getGraduatedCollege() + "\n");
        empsfw.write(emp.getYearOfGraduation() + "\n");
        empsfw.write(emp.getCollegeGrade() + "\n");

      }
    }
    catch (Exception e)
    {
      System.out.println("Error While Saving Employees");
    }
    finally {
      if(empsfw!=null)
      {
        try{
          empsfw.close();
        }
        catch (Exception e)
        {
          System.out.println("Error While Closing Employees File");
        }
      }
    }
    try
    {
      accsfw = new BufferedWriter(new FileWriter("./database/accounts.txt"));
      for (Account acc : accs)
      {
        accsfw.write(acc.getAccountNumber() + "\n");
        accsfw.write(acc.getClientId() + "\n");
        accsfw.write(acc.getAccountType() + "\n");
        accsfw.write(acc.getAccountState() + "\n");
        accsfw.write(acc.getBalance() + "\n");
        accsfw.write(acc.getFees() + "\n");
        accsfw.write(acc.getInterestRate() + "\n");
        accsfw.write(acc.getCretidCardNumber() + "\n");
        accsfw.write(acc.getSecurityPin() + "\n");
        accsfw.write(acc.getLoyalityPoints() + "\n");
        accsfw.write(acc.getCardAmount() + "\n" ); // Added by A.S 12/12/2024
        accsfw.write(acc.getIsCreditCardActive() + "\n"); // Added by A.S 12/12/2024
      }

    }
    catch (Exception e)
    {
      System.out.println("Error While Saving Current Accounts");
    }
    finally {
      if(accsfw!=null)
      {
        try{
          accsfw.close();
        }
        catch (Exception e)
        {
          System.out.println("Error While Closing Current Accounts File");
        }
      }
    }
    try
    {
      transactionsfw = new BufferedWriter(new FileWriter("./database/transactions.txt"));
      for (Transaction transaction : transactions)
      {
        transactionsfw.write(transaction.getId()+ "\n");
        transactionsfw.write(transaction.getDate()+ "\n");
        transactionsfw.write(transaction.getClientId()+ "\n");
        transactionsfw.write(transaction.getAccountNumber()+ "\n");
        transactionsfw.write(transaction.getEmployeeId()+ "\n");
        transactionsfw.write(transaction.getTransactionType()+ "\n");
        transactionsfw.write(transaction.getAmount()+ "\n");
      }
    }
    catch (Exception e)
    {
      System.out.println("Error While Saving Transactions");
    }
    finally {
      if(transactionsfw!=null)
      {
        try{
          transactionsfw.close();
        }
        catch (Exception e)
        {
          System.out.println("Error While Closing Transactions File");
        }
      }
    }
  }
}
