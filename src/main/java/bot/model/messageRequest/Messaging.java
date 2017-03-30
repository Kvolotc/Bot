package bot.model.messageRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Messaging {
	
	private Sender sender;
	
	private Recipient recipient;
	
	@JsonProperty(value = "postback")
	private PostBack postBack;
	
	@JsonProperty(value = "timestamp")
	private long timeStamp;
	
	private Message message;

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public PostBack getPostBack() {
		return postBack;
	}

	public void setPostBack(PostBack postBack) {
		this.postBack = postBack;
	}

	@Override
	public String toString() {
		return "Messaging [sender=" + sender + ", recipient=" + recipient + ", postBack=" + postBack + ", timeStamp="
				+ timeStamp + ", message=" + message + "]";
	}

}
