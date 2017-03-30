package bot.model.messageBot;

import bot.model.messageRequest.Recipient;

public class MessageBot {
	
	private Recipient recipient;
	
	private MessageSend message;

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public MessageSend getMessage() {
		return message;
	}

	public void setMessage(MessageSend message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageBot [recipient=" + recipient + ", message=" + message + "]";
	}
	

}
