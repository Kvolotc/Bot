package bot.model.messageButton;

import java.util.List;

import bot.model.messageRequest.Recipient;

public class MessageButton {

	private Recipient recipient;
	
	private Message  message;

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	private MessageButton() {
		
	}
	
	public static MessageButton createMessageButton(List<Button> buttons, String text, Recipient recipient) {
		
		Payload payload = new Payload();	
		payload.setTemplateType("button");
		payload.setText(text);
		payload.setButtons(buttons);
		
		Attachment attachment = new Attachment();
		attachment.setType("template");
		attachment.setPayload(payload);
		
		Message message = new Message();
		message.setAttachment(attachment);
		
		MessageButton messageButton = new MessageButton();
		messageButton.setRecipient(recipient);
		messageButton.setMessage(message);
		
		return messageButton;
	}
	
}
