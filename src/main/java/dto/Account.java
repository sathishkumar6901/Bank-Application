package dto;


public class Account {
	private int accountNumber;
	private int CustomerId;
	private String accountType;
	private String IFSCCode;
	private float balance;
	public Account() {
		
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getIFSCCode() {
		return IFSCCode;
	}
	public void setIFSCCode(String IFSCCode) {
		this.IFSCCode = IFSCCode;
	}
	
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}

	public int getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}

}
