package dto;


public class Request {
	private int requestId;
	private int customerId;
	private String requestType;
	private String requestedDate;
	private String requestResult;
	private String isViewed;
	private String replyMessage;
	private String repliedDate;
	
	public Request() {
		
	}
	
	public String getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(String requestedDate) {
		System.out.println(requestedDate);
		this.requestedDate = requestedDate;
	}

	public String getRepliedDate() {
		return repliedDate;
	}

	public void setRepliedDate(String repliedDate) {
		this.repliedDate = repliedDate;
	}
	
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getRequestResult() {
		return requestResult;
	}
	public void setRequestResult(String requestResult) {
		this.requestResult = requestResult;
	}
	public String getIsViewed() {
		return isViewed;
	}
	public void setIsViewed(String isViewed) {
		this.isViewed = isViewed;
	}
	public String getReplyMessage() {
		return replyMessage;
	}
	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}

}
