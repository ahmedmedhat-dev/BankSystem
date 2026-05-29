import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
  protected int id;
  protected String date;
  protected int clientId;
  protected int accountNumber;
  protected int employeeId;
  protected String transactionType;
  protected float amount;
  public static int nextId = 0;

  public Transaction(int clientId, int accountNumber, int employeeId, String transactionType, float amount) {
    this.id = 400000 + nextId;
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    this.date = sdf.format(date);
    this.clientId = clientId;
    this.accountNumber = accountNumber;
    this.employeeId = employeeId;
    this.transactionType = transactionType;
    this.amount = amount;
    ++nextId;
    System.out.println("################################");
    System.out.println("Transaction done successfully");
    System.out.println("################################");
  }

  public Transaction(int id, String date, int clientId, int accountNumber, int employeeId, String transactionType, float amount) {
    this.id = id;
    this.date = date;
    this.clientId = clientId;
    this.accountNumber = accountNumber;
    this.employeeId = employeeId;
    this.transactionType = transactionType;
    this.amount = amount;
    if (nextId < this.id - 399999) {
      nextId = this.id - 399999;
    }

  }

  public String details() {
    String str = "";
    str += "Transaction ID: " + this.id + "\n";
    str += "Date: " + this.date + "\n";
    str += "Amount: " + this.amount + "\n";
    str += "Transaction type: " + this.transactionType + "\n";
    str += "Client ID: " + this.clientId + "\n";
    str += "Account number: " + this.accountNumber + "\n";
    str += "################################";
    return str;
  }
  //== Getters ===================================================
  public int getId() {
    return this.id;
  }
  public String getDate() {
    return this.date;
  }
  public int getClientId() {
    return this.clientId;
  }
  public int getAccountNumber() {
    return this.accountNumber;
  }
  public int getEmployeeId() {
    return this.employeeId;
  }
  public String getTransactionType() {
    return this.transactionType;
  }
  public float getAmount() {
    return this.amount;
  }
  //== Setters ====================================================
  public void setId(int id) {
    this.id = id;
  }
  public void setDate(String date) {
    this.date = date;
  }
  public void setClientId(int clientId) {
    this.clientId = clientId;
  }
  public void setAccountNumber(int accountNumber) {
    this.accountNumber = accountNumber;
  }
  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }
  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }
  public void setAmount(float amount) {
    this.amount = amount;
  }
}
