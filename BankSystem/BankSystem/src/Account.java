import java.util.ArrayList;

public class Account {
  private int accountNumber;
  private int clientId;
  private String accountType;
  private String accountState = "Active";
  private float balance;
  private float fees = 0.0F;
  private float interestRate = 0.0F;
  public static int nextNumber = 0;
  //=====Added by Ahmed Sharafeldin 10/12/2024========
  private String cretidCardNumber;
  private String expirationDate = "10/12/2029";
  private float cardAmount = 20000.0f;
  private int securityPin;
  private static int pin = 111;
  private float loyalityPoints=0;
  private boolean isCreditCardActive; // Edited by A.S 17/12/2024
  //==================================================
  public Account(int clientId, String accountType, float balance) {
    this.accountNumber = 300000 + nextNumber;
    this.clientId = clientId;
    this.accountType = accountType;
    this.balance = balance;
    this.cretidCardNumber = "1234567890" + this.accountNumber; // Added by Ahmed Ali
    this.securityPin = ++pin; // Added by A.S 10/12/2024
    this.isCreditCardActive = true; // Added by A.S 12/12/2024 active by default
    if (accountType.equals("Savings")) {
      this.interestRate = 0.02F;
    }
    if (balance < 3000.0F && accountType.equals("Current")) {
      this.fees = 50.0F;
    }

    ++nextNumber;
    System.out.println("################################");
    System.out.println("  Account created successfully.");
    System.out.println("   Account ID is: " + accountNumber);
    System.out.println("################################");
  }
  public Account(int accountNumber, int clientId, String accountType, String accountState,
                 float balance, float fees, float interestRate, String cretidCardNumber,
                 float loyalityPoints , int securityPin, float cardAmount, boolean isCreditCardActive) {
    this.accountNumber = accountNumber;
    this.clientId = clientId;
    this.accountType = accountType;
    this.accountState = accountState;
    this.balance = balance;
    this.fees = fees;
    this.cretidCardNumber = cretidCardNumber;
    this.securityPin = securityPin;
    this.loyalityPoints = loyalityPoints;
    this.interestRate = interestRate;
    this.cardAmount = cardAmount; // Added by A.S 12/12/2024
    this.isCreditCardActive = isCreditCardActive; // Added by A.S 12/12/2024
    if (nextNumber < this.accountNumber - 299999) {
      nextNumber = this.accountNumber - 299999;
    }

  }
  //== Getters =======================================
  public int getAccountNumber() {
    return this.accountNumber;
  }
  public int getClientId() {
    return this.clientId;
  }
  public String getAccountType() {
    return this.accountType;
  }
  public String getAccountState() {
    return this.accountState;
  }
  public float getBalance() {
    return this.balance;
  }
  public float getFees() {
    return this.fees;
  }
  public float getInterestRate() {
    return this.interestRate;
  }
  // added by A.S 10/12/2024
  public String getCretidCardNumber(){
    return this.cretidCardNumber;
  };
  public String getExpirationDate(){
    return this.expirationDate;
  }
  public float getCardAmount(){
    return this.cardAmount;
  }
  public int getSecurityPin(){
    return this.securityPin;
  }
  public float getLoyalityPoints(){
    return this.loyalityPoints;
  };
  public boolean getIsCreditCardActive(){
    return isCreditCardActive;
  } // Added by A.S 12/12/2024
  //== Setters ====================================
  public void setAccountNumber(int accountNumber) {
    this.accountNumber = accountNumber;
  }
  public void setClientId(int clientId) {
    this.clientId = clientId;
  }
  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }
  public void setAccountState(String accountState) {
    this.accountState = accountState;
  }
  public void setBalance(float balance) {
    this.balance = balance;
  }
  public void setFees(float fees) {
    this.fees = fees;
  }
  public void setInterestRate(float interestRate) {
    this.interestRate = interestRate;
  }
  // Added by A.S 10/12/2024
  public void setCretidCardNumber(String cretidCardNumber){
    this.cretidCardNumber = cretidCardNumber;
  }
  public void setExpirationDate(String expirationDate){
    this.expirationDate = expirationDate;
  }
  public void setCardAmount(float cardAmount){
    this.cardAmount = cardAmount;
  }
  public void setSecurityPin(int securityPin){
    this.securityPin = securityPin;
  }
  public void setLoyalityPoints(float loyalityPoints, int accnum, ArrayList<Account> accs){
    // Edited by A.S 12/12/2024
    if(loyalityPoints >= 1000)
    {
      float points = (float)(loyalityPoints * 0.5); // Added by A.S 17/12/2024
      for (Account acc:accs) {
        if(acc.getAccountNumber() == accnum) {
          acc.balance += points;
        }
      }
      System.out.println("Your loyality points reached 1000!\n" +
                         points + " pounds have been added to your account.");
      this.loyalityPoints = 0.0f;
    }
    else {
      this.loyalityPoints = loyalityPoints;
    }
  }
  public void setIsCreditCardActive(boolean isCreditCardActive){
    this.isCreditCardActive = isCreditCardActive;
  } // Edited by A.S by 17/12/2024
}
