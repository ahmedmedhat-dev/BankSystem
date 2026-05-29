import java.util.ArrayList;
import java.util.Scanner;

public abstract class User {
  protected int id;
  protected String fName;
  protected String lName;
  protected String username;
  protected String password;
  protected String phoneNumber;
  //== Getters =================================================
  public int getId() {
    return this.id;
  }
  public String getfName() {
    return this.fName;
  }
  public String getlName() {
    return this.lName;
  }
  public String getUsername() {
    return this.username;
  }
  public String getPassword() {
    return this.password;
  }
  public String getPhoneNumber() {
    return this.phoneNumber;
  }
  //== Setters ==================================================
  public void setId(int id) {
    this.id = id;
  }
  public void setfName(String fName) {
    this.fName = fName;
  }
  public void setlName(String lName) {
    this.lName = lName;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String details() {
    String str = "";
    str = str + "ID: " + this.getId() + "\n";
    str = str + "Full name: " + this.getfName() + " " + this.getlName() + "\n";
    str = str + "Username: " + this.getUsername() + "\n";
    str = str + "Phone number: " + this.getPhoneNumber() + "\n";
    return str;
  }

  public User(String fName, String lName, String username, String password, String phoneNumber) {
    this.fName = fName;
    this.lName = lName;
    this.username = username;
    this.password = password;
    this.phoneNumber = phoneNumber;
  }

  public abstract void menu(Scanner var1, ArrayList<Employee> var2, ArrayList<Client> var3, ArrayList<Account> var4, ArrayList<Transaction> var5);
}
