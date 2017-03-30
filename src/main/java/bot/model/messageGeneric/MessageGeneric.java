package bot.model.messageGeneric;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


import bot.entity.PrivacyPolicy;
import bot.model.messageButton.Button;
import bot.model.messageRequest.Recipient;

public class MessageGeneric {
	
	private Recipient recipient;

	private Message message;

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

	private MessageGeneric() {

	}

	public static MessageGeneric creteMessageGeneric(List<PrivacyPolicy> policies, long id, int page, boolean hasNext) {

		MessageGeneric messageGeneric = new MessageGeneric();
		messageGeneric.setRecipient(new Recipient(id));

		Message message = new Message();

		Attachment attachment = new Attachment();
		attachment.setType("template");

		Payload payload = new Payload();
		payload.setTemplateType("generic");

		List<Element> elements = new ArrayList<>();
		List<Button> buttons;
		Properties properties = new Properties();
		InputStream inputStream;
		
		try {
			inputStream = new FileInputStream("src/main/resources/message.properties");
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		for (PrivacyPolicy policy : policies) {
			
			Button postButton = Button.createPostBackButton("Edit", "Edit&" + policy.getId());
			Button postButton2 = Button.createPostBackButton("Remove", "Remove&" + policy.getId());
			Button webButton = Button.createWebButton(properties.getProperty("message.url") +"/"+ policy.getToken(),
					"Information");
			buttons = Arrays.asList(postButton, postButton2, webButton);

			Element element = new Element();
			element.setButtons(buttons);
			element.setTitle("Policy name is " + policy.getAppName());
			element.setSubtitle("Policy email is " + policy.geteMail() + ", " + "Policy address is " + policy.getAddress());

			elements.add(element);
		}
		
		if(hasNext) {
			Button postButton = Button.createPostBackButton("Next", "Next&"+page);
			
			Element element = new Element();
			element.setButtons(Arrays.asList(postButton));
			element.setTitle("Next policie");
			elements.add(element);
		}

		payload.setElements(elements);

		attachment.setPayload(payload);

		message.setAttachment(attachment);

		messageGeneric.setMessage(message);

		return messageGeneric;

	}

	public static MessageGeneric createMessageGenericEdit(PrivacyPolicy policy, long id) {

		MessageGeneric messageGeneric = new MessageGeneric();
		messageGeneric.setRecipient(new Recipient(id));

		Message message = new Message();

		Attachment attachment = new Attachment();
		attachment.setType("template");

		Payload payload = new Payload();
		payload.setTemplateType("generic");

		List<Element> elements = new ArrayList<>();

		Button postButton = Button.createPostBackButton("Edit App name", "Edit field&" + "App name" + "&" + policy.getId());
		Button postButton2 = Button.createPostBackButton("Edit Email", "Edit field&" + "Email" + "&" + policy.getId());
		Button postButton3 = Button.createPostBackButton("Edit Address","Edit field&" + "Address" + "&" + policy.getId());

		Element element = new Element();
		element.setButtons(Arrays.asList(postButton));
		element.setTitle(policy.getAppName());

		Element element2 = new Element();
		element2.setButtons(Arrays.asList(postButton2));
		element2.setTitle(policy.geteMail());

		Element element3 = new Element();
		element3.setButtons(Arrays.asList(postButton3));
		element3.setTitle(policy.getAddress());

		elements.add(element);
		elements.add(element2);
		elements.add(element3);

		payload.setElements(elements);

		attachment.setPayload(payload);

		message.setAttachment(attachment);

		messageGeneric.setMessage(message);

		return messageGeneric;
	}

}
