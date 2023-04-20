package BankingRepository;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import dto.Account;
import dto.Admin;
import dto.DebitCard;
import dto.Customer;
import dto.Request;
import dto.Transaction;

public class Repository {
	private static Repository bankingDbInstance;
	Connection conn;
	Statement stmt;
	private String query = "";
	private ResultSet resultSet;

	private Repository() {
	}

	// get repository instance
	public static Repository getInstance() {
		if (bankingDbInstance == null) {
			bankingDbInstance = new Repository();
			bankingDbInstance.getConnection();
		}
		return bankingDbInstance;
	}

	// get connection with database
	private void getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			String url = "jdbc:mysql://localhost:3306/BankApplication";
			String username = "root";
			String password = "Sathish@20";

			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
		} catch (Exception e) {
			System.out.println("Not able to connect with database...\n");
		}
	}

	// password hashing
	public static String hasingString(String input) {
		try {
			MessageDigest msgDst = MessageDigest.getInstance("MD5");
			byte[] msgArr = msgDst.digest(input.getBytes());
			BigInteger bi = new BigInteger(1, msgArr);
			String hshtxt = bi.toString(16);
			while (hshtxt.length() < 30) {
				hshtxt = "0" + hshtxt;
			}
			return hshtxt.substring(0, 30);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	// generate otp for reset password
	private String generateOTP() {
		UUID uuid = UUID.randomUUID();
		String pass = uuid.toString().replace("-", "");
		String password = pass.substring(pass.length() - 6);
		return password;
	}

	// validate the Admin
	public Admin checkValidAdmin(String emailId, String password) {
		String hassedPassword = hasingString(password);
		Admin admin;
		query = "select * from Admin where EmailID ='" + emailId + "' and Password ='" + hassedPassword + "';";
		try {
			resultSet = stmt.executeQuery(query);
			if (resultSet.next()) {
				admin = new Admin();
				admin.setAdminId(resultSet.getInt("AdminId"));
				admin.setAdminName(resultSet.getString("AdminName"));
				return admin;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// validate the customer
	public Customer checkValidCustomer(String emailId, String password) {
		String hassedPassword = hasingString(password);
		Customer customer;
		query = "select * from Customer where EmailID='" + emailId + "' and Password='" + hassedPassword+ "';";
		try {
			resultSet = stmt.executeQuery(query);
			if (resultSet.next()) {
				customer = new Customer();
				customer.setCustomerId(resultSet.getInt("CustomerId"));
				customer.setCustomerName(resultSet.getString("CustomerName"));
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// check if the customer already exist/not
	public boolean isCustomerExist(String emailId, String aadharNumber) {
		query = "select CustomerId from Customer where EmailId='" + emailId + "' or AadharNumber='" + aadharNumber
				+ "';";
		try {
			resultSet = stmt.executeQuery(query);
			if (resultSet.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// add new admin by existing admin
	public void addNewAdmin(String name, String emailId, String phoneNumber, String password) {
		String hassedPassword = hasingString(password);
		query = "insert into Admin(AdminName,PhoneNumber,EmailID,Password) values('" + name + "','" + phoneNumber
				+ "','" + emailId + "','" + hassedPassword + "');";
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Request> getRequests() {
		List<Request> requests = new ArrayList<>();
		Request request;
		String status="pending";
		query = "select * from UserRequest where RequestResult='"+status+"';";
		try {
			resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				request = new Request();
				request.setRequestId(resultSet.getInt("RequestId"));
				request.setCustomerId(resultSet.getInt("CustomerId"));
				request.setRequestType(resultSet.getString("RequestType"));
				request.setRequestedDate(resultSet.getDate("RequestedDate").toString());
				requests.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}

	public List<Account> getAccounts() {
		List<Account> accounts = new ArrayList<>();
		Account account;
		String query = "select * from Account";

		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				account = new Account();
				account.setCustomerId(rs.getInt("CustomerId"));
				account.setAccountNumber(rs.getInt("AccountNumber"));
				account.setAccountType(rs.getString("AccountType"));
				account.setIFSCCode(rs.getString("IFSCCode"));
				account.setBalance(rs.getFloat("Balance"));
				accounts.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public void addNewCustomer(String name, String emailId, String accountType, String phoneNumber, String dateOfBirth,
			String aadharNumber, String password) {
		String hassedPassword = hasingString(password);
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		String query = "insert into Customer(CustomerName,PhoneNumber,AccountType, AadharNumber,DateOfBirth, Password, EmailID)"
				+ "values('" + name + "','" + phoneNumber + "','" + accountType + "','" + aadharNumber + "','" + sqlDate
				+ "','" + hassedPassword + "','" + emailId + "');";
		try {
			stmt.executeUpdate(query);
			query = "select CustomerId from Customer where AadharNumber='" + aadharNumber + "';";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				int id = rs.getInt("CustomerId");
				addRequest(id, "NewCustomer");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addRequest(int id, String requestType) {
		String query = "insert into UserRequest(CustomerId,RequestType) values('" + id + "','" + requestType + "');";
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Request> getNotifications(int id) {
		List<Request> requests = new ArrayList<>();
		Request request;
		String status = "pending";
		String viewed = "no";
		query = "select * from UserRequest where CustomerId='" + id + "' and RequestResult<>'" + status
				+ "' and isViewed='" + viewed + "';";
		try {
			resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				request = new Request();
				request.setCustomerId(resultSet.getInt("CustomerId"));
				request.setRequestId(resultSet.getInt("RequestId"));
				request.setRequestType(resultSet.getString("RequestType"));
				request.setRequestedDate(resultSet.getDate("RequestedDate").toString());
				request.setRequestResult(resultSet.getString("RequestResult"));
				request.setRepliedDate(resultSet.getDate("RepliedDate").toString());
				requests.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}

	public Customer getPersonalDetails(int id) {
		Customer customer;
		String query = "select * from Customer where CustomerId='" + id + "';";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				customer = new Customer();
				customer.setCustomerId(rs.getInt("CustomerId"));
				customer.setEmailId(rs.getString("EmailId"));
				customer.setCustomerName(rs.getString("CustomerName"));
				customer.setDob(rs.getDate("DateOfBirth").toString());
				customer.setPhoneNumber(rs.getString("PhoneNumber"));
				customer.setAadharNumber(rs.getString("AadharNumber"));
				customer.setAccountType(rs.getString("AccountType"));
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Account getAccountDetails(int id) {
		Account account;
		String status="active";
		query = "select * from Account where CustomerId = '" + id + "' and AccountStatus='"+status+"';";
		try {
			resultSet = stmt.executeQuery(query);
			if (resultSet.next()) {
				account = new Account();
				account.setAccountNumber(resultSet.getInt("AccountNumber"));
				account.setAccountType(resultSet.getString("AccountType"));
				account.setIFSCCode(resultSet.getString("IFSCCode"));
				account.setBalance(resultSet.getFloat("Balance"));
				return account;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public DebitCard getCardDetails(int custId) {
		DebitCard debitCard;
		Account account = getAccountDetails(custId);
		if(account==null)
			return null;
		query ="select * from DebitCard where AccountNumber='"+account.getAccountNumber()+"';";
		try {
			resultSet = stmt.executeQuery(query);
			if(resultSet.next()) {
				debitCard = new DebitCard();
				debitCard.setCardNumber(resultSet.getString("CardNumber"));
				debitCard.setAccountNumber(resultSet.getInt("AccountNumber"));
				debitCard.setCvvNumber(resultSet.getString("CVVNumber"));
				debitCard.setExpiryDate(resultSet.getDate("ExpiryDate"));
				return debitCard;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public float getBalance(int accountNumber) {
		String query = "select Balance from Account where AccountNumber='" + accountNumber + "';";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next())
				return rs.getFloat("Balance");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
//
//	public void resetPassword(int id, String password) {
//		String query = "update Customer set password='"+password+"' where CustomerId='"+id+"';";
//		try {
//			stmt.executeUpdate(query);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}

	public List<Transaction> getTransactions(int id) {
		List<Transaction> transactions = new ArrayList<>();
		Transaction t;
		query = "select * from Transaction where AccountNumber=(select AccountNumber from Account where CustomerId='"+ id + "')";
		try {
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				t = new Transaction();
				t.setTransactionId(rs.getInt("TransactionId"));
				t.setTransactionDate(rs.getDate("TransactionDate").toString());
				t.setTransactionMethod(rs.getString("TransactionMethod"));
				t.setTransationType(rs.getString("TransactionType"));
				t.setTransactionAmount(rs.getFloat("TransactionAmount"));
				t.setCurrentBalance(rs.getFloat("CurrentBalance"));
				transactions.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}

	public boolean isAccountExist(int accountNumber, String ifscCode) {
		String query = "select CustomerId from Account where AccountNumber='" + accountNumber + "' and IFSCCode='"
				+ ifscCode + "';";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int isCorrectPassword(int id, String password) {
		String hasingPassword = hasingString(password);
		String query = "select * from Account where CustomerId='" + id + "' and TransactionPassword='" + hasingPassword
				+ "';";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next())
				return rs.getInt("AccountNumber");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public void makeTransaction(int accountNumber, String transactionMethod, String transactionType, float amount,
			float balance) {
		query = "insert into Transaction(AccountNumber,TransactionMethod,TransactionType,TransactionAmount,CurrentBalance)"
				+ "values('" + accountNumber + "','" + transactionMethod + "','" + transactionType + "','" + amount + "','"
				+ balance + "');";
		try {
			stmt.executeUpdate(query);
			setBalance(accountNumber, balance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setBalance(int accountNumber, float balance) {
		String query = "update Account set Balance ='" + balance + "' where AccountNumber='" + accountNumber + "';";
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int isCardExist(String cardNumber, String cvvNumber, String expiryDate, String pin) {
		String hassedPin = hasingString(pin);
		String query = "select AccountNumber from DebitCard where CardNumber='" + cardNumber + "' and CVVNumber='"
				+ cvvNumber + "' and " + "ExpiryDate='"+expiryDate+"' and ExpiryDate>=current_date() and PINNumber='" + hassedPin + "';";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				return rs.getInt("AccountNumber");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public boolean checkValidAccount(int id, int accountNumber, String emailId) {
		String query = "select a.AccountNumber from Account a inner join Customer c on a.CustomerId=c.CustomerId and "
				+ "a.AccountNumber='" + accountNumber + "' and c.EmailId='" + emailId + "' and c.CustomerId='"
				+ id + "';";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				return true;
				/*
				 * query = "update Account set TransactionPassword='" + hashedOTP +
				 * "' where AccountNumber='" + accountNumber + "';"; stmt.executeUpdate(query);
				 * return otp;
				 */
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean resetTransactionPassword(int accountNumber, String otp, String password) {
		String hashedOTP = hasingString(otp);
		String query = "select AccountNumber from Account where AccountNumber='" + accountNumber
				+ "' and TransactionPassword='" + hashedOTP + "';";
		try {
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				setTransactionOTP(accountNumber,password);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkValidCard(String cardNumber, String cvvNumber, String expiryDate, int accountNumber, int id) {
		query ="select d.AccountNumber from DebitCard d inner join Account a on d.AccountNumber=a.AccountNumber and a.CustomerId='"+id+"' and "
				+ "CardNumber='"+cardNumber+"' and CVVNumber='"+cvvNumber+"' and ExpiryDate='"+expiryDate+"' and ExpiryDate>=current_date();";
		try {
			resultSet = stmt.executeQuery(query);
			if (resultSet.next()) {
				return true;
				/*
				 * String otp = generateOTP(); String hashedPin = hasingString(otp); query =
				 * "update DebitCard set PINNumber='" + hashedPin + "' where AccountNumber='" +
				 * accountNumber + "';"; stmt.executeUpdate(query); return otp;
				 */
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean resetCardPin(int accountNumber, String otp, String pin) {
		String hashedOTP = hasingString(otp);
		query = "select AccountNumber from DebitCard where AccountNumber='" + accountNumber + "' and PINNumber='"
				+ hashedOTP + "';";
		try {
			resultSet = stmt.executeQuery(query);
			if (resultSet.next()) {
				setPinOTP(accountNumber,pin);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void addNewAccount(int custId, String accountType) {
		query = "select AccountNumber from Account order by AccountNumber desc limit 1;";
		try {
			resultSet = stmt.executeQuery(query);
			if (resultSet.next()) {
				int accountNumber = resultSet.getInt("AccountNumber");				
				query = "insert into Account(CustomerId,AccountNumber,AccountType) values('"+custId+"','"+(accountNumber+1)+"','"+accountType+"');";
				stmt.executeUpdate(query);
				addCard(accountNumber);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void addCard(int accountNumber) {
		query = "select * from DebitCard order by CardNumber desc limit 1;";
		try {
			resultSet = stmt.executeQuery(query);
			if(resultSet.next()) {
				String cardNumber = getNextNumber(resultSet.getString("CardNumber"));
				String cvvNumber = getNextNumber(resultSet.getString("CVVNumber"));
				query = "insert into DebitCard(AccountNumber,CardNumber,CVVNumber) values('"+(accountNumber+1)+"','"+cardNumber+"','"+cvvNumber+"');";
				stmt.executeUpdate(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String getNextNumber(String s) {
		char[] str = s.toCharArray();
		int carry = 1;
		int i = s.length() - 1;
		while (i >= 0 && carry == 1) {
			int num = str[i] - '0';
			num += carry;
			carry = num / 10;
			str[i] = (char) ((num % 10) + '0');
			i--;
		}
		return new String(str);
	}

	public void setReplyMessage(int custId, int adminId, String requestType, String requestResult,
			String replyMessage) {
		String query = "update UserRequest set AdminId='" + adminId + "', RequestResult='" + requestResult
				+ "', ReplyMessage='" + replyMessage + "'" + "where CustomerId='" + custId + "' and RequestType='"
				+ requestType + "';";

		System.out.println("hello");
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setMessageViewed(int requestId) {
		String view = "yes";
		String query = "update UserRequest set isViewed='" + view + "' where RequestId='" + requestId + "';";
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isEmailExist(String emailId) {
		query = "select CustomerId from Customer where EmailId='"+emailId+"';";
		try {
			resultSet = stmt.executeQuery(query);
			if(resultSet.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void setLoginOTP(String emailId, String password) {
		System.out.println("hello set login otp");
		String hashedPassword = hasingString(password);
		query = "update Customer set Password='"+hashedPassword+"' where EmailId='"+emailId+"';";
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean resetLoginPassword(String emailId, String otp, String password) {
		String hashedOTP = hasingString(otp);
		query = "select CustomerId from Customer where EmailId='"+emailId+"' and Password='"+hashedOTP+"';";
		try {
			resultSet = stmt.executeQuery(query);
			if(resultSet.next()) {
				setLoginOTP(emailId,password);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void setTransactionOTP(int accountNumber, String otp) {
		String hashedOtp = hasingString(otp);
		query = "update Account set TransactionPassword='"+hashedOtp+"' where AccountNumber='"+accountNumber+"';";
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setPinOTP(int accountNumber, String otp) {
		String hashedOtp = hasingString(otp);
		query = "update DebitCard set PINNumber='"+hashedOtp+"' where AccountNumber='"+accountNumber+"';";
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
