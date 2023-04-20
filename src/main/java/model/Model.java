package model;

import java.util.List;

import BankingRepository.Repository;
import dto.Account;
import dto.Admin;
import dto.DebitCard;
import dto.Customer;
import dto.Request;
import dto.Transaction;

public class Model {
	public static Model model;
	
	public static Model getInstance() {
		if(model==null)
			model = new Model();
		return model;
	}
	
	public Admin chackAdminLogin(String emailId, String password) {
		return Repository.getInstance().checkValidAdmin(emailId,password);
	}

	public Customer checkCustomerLogin(String emailId, String password) {
		return Repository.getInstance().checkValidCustomer(emailId,password);
	}
	
	public boolean isCustomerExist(String emailId, String aadharNumber) {
		return Repository.getInstance().isCustomerExist(emailId,aadharNumber);
	}
	public void addNewCustomer(String name, String emailId,String accountType,  String phoneNumber,String dateOfBirth, String aadharNumber,String password) {
		Repository.getInstance().addNewCustomer(name,emailId,accountType,phoneNumber,dateOfBirth,aadharNumber,password);
	}

	public void addNewAdmin(String name, String emailId, String phoneNumber, String password) {
		Repository.getInstance().addNewAdmin(name,emailId,phoneNumber,password);
	}
	
	public List<Request> getRequests() {
		return Repository.getInstance().getRequests();
	}
	
	public List<Account> getAccounts() {
		return Repository.getInstance().getAccounts();
	}
	
	public Customer getPersonalDetails(int id) {
		return Repository.getInstance().getPersonalDetails(id);
	}
	
	public Account getAccountDetails(int id) {
		return Repository.getInstance().getAccountDetails(id);
	}
	
	public DebitCard getCardDetails(int custId) {
		return Repository.getInstance().getCardDetails(custId);
	}
	public List<Request> getNotifications(int id){
		return Repository.getInstance().getNotifications(id);
	}
	
	public List<Transaction> getTransactions(int id){
		return Repository.getInstance().getTransactions(id);
	}

	public boolean isAccountExist(int accountNumber, String ifscCode) {
		return Repository.getInstance().isAccountExist(accountNumber,ifscCode);
	}

	public float getBalance(int accountNumber) {
		return Repository.getInstance().getBalance(accountNumber);
	}

	public int isCorrectPassword(int id, String password) {
		return Repository.getInstance().isCorrectPassword(id,password);
	}

	public void makeTransaction(int accountNumber, String transactionMethod, String transactionType, float amount, float balance) {
		Repository.getInstance().makeTransaction(accountNumber,transactionMethod,transactionType,amount,balance);
		
	}

	public int isCardExist(String cardNumber, String cvvNumber, String expiryDate, String pin) {
		return Repository.getInstance().isCardExist(cardNumber,cvvNumber,expiryDate,pin);
	}

	public boolean checkValidAccount(int id,int accountNumber, String emailId) {
		return Repository.getInstance().checkValidAccount(id,accountNumber,emailId);
	}

	public boolean resetTransactionPassword(int accountNumber, String otp, String password) {
		return Repository.getInstance().resetTransactionPassword(accountNumber,otp,password);
	}

	public boolean checkValidCard(String cardNumber, String cvvNumber, String expiryDate, int accountNumber, int id) {
		return Repository.getInstance().checkValidCard(cardNumber,cvvNumber,expiryDate,accountNumber,id);
	}

	public boolean resetCardPin(int accountNumber, String otp, String pin) {
		return Repository.getInstance().resetCardPin(accountNumber,otp,pin);
	}

	public void addNewAccount(int custId, String accountType) {
		Repository.getInstance().addNewAccount(custId,accountType);
		
	}

	public void setReplyMessage(int custId, int adminId, String requestType, String requestResult,String replyMessage) {
		Repository.getInstance().setReplyMessage(custId, adminId, requestType, requestResult, replyMessage);
		
	}

	public void setMessageViewed(int requestId) {
		Repository.getInstance().setMessageViewed(requestId);
		
	}

	public boolean isEmailExist(String emailId) {
		return Repository.getInstance().isEmailExist(emailId);
	}

	public void setLoginOTP(String emailId, String otp) {
		Repository.getInstance().setLoginOTP(emailId,otp);
		
	}

	public boolean resetLoginPassword(String emailId, String otp, String password) {
		return Repository.getInstance().resetLoginPassword(emailId, otp, password);
	}

	public void setTransactionOTP(int accountNumber, String otp) {
		Repository.getInstance().setTransactionOTP(accountNumber,otp);
		
	}

	public void setPinOTP(int accountNumber, String otp) {
		Repository.getInstance().setPinOTP(accountNumber,otp);
		
	}

	
}
