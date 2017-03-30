package bot.operating;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import bot.entity.User;
import bot.message.MessageHolder;
import bot.model.messageBot.MessageBot;
import bot.model.messageButton.MessageButton;
import bot.model.messageGeneric.MessageGeneric;
import bot.model.messageRequest.Message;
import bot.model.messageRequest.MessageRequest;
import bot.model.messageRequest.Messaging;
import bot.model.messageRequest.PostBack;
import bot.model.userInformation.UserInformation;
import bot.service.UserService;

@Component
public class Operator {

	@Autowired
	private UserService userService;
	
	@Autowired
	MessageHolder holder;
	
	private RestTemplate restTemplate;

	public Operator() {
		this.restTemplate = new RestTemplate();
	}

	public User getCurrentUser(MessageRequest messageRequest) {
		
		long id = getRecipientId(getMessaging(messageRequest));
	    return userService.findByMessagerId(id); 
	}

	public long getRecipientId(Messaging messaging) {
		return messaging.getSender().getId();
	}
	
	public Messaging getMessaging(MessageRequest messageRequest) {
		return messageRequest.getEntry().get(0).getMessaging().get(0);
	}
	
	public Message getMessage(MessageRequest messageRequest) {
		return messageRequest.getEntry().get(0).getMessaging().get(0).getMessage();
	}


	public PostBack getPostBack(MessageRequest messageRequest) {
		return messageRequest.getEntry().get(0).getMessaging().get(0).getPostBack();
	}

	public void postMessage(MessageBot bot) {
		restTemplate.postForObject(holder.getMessage("message.post") + holder.getMessage("message.token"), bot,
				String.class);
	}
	
	public void postButton(MessageButton button) {
		restTemplate.postForObject(holder.getMessage("message.post") + holder.getMessage("message.token"), button,
				String.class);
	}
	
	public void postMessageGeneric(MessageGeneric messageGeneric) {

		restTemplate.postForObject(holder.getMessage("message.post") + holder.getMessage("message.token"), messageGeneric,
				String.class);
	}

	public UserInformation getUserInformation(long id) {
		return restTemplate.getForObject(String.format(holder.getMessage("message.get_information_user"), id)
				+ holder.getMessage("message.token"), UserInformation.class);
	}
	
}
