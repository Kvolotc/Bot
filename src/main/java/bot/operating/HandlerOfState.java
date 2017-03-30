package bot.operating;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bot.entity.PrivacyPolicy;
import bot.entity.User;
import bot.entity.constans.State;
import bot.message.MessageHolder;
import bot.model.messageBot.MessageBot;
import bot.model.messageBot.MessageSend;
import bot.model.messageButton.Button;
import bot.model.messageButton.MessageButton;
import bot.model.messageGeneric.MessageGeneric;
import bot.model.messageRequest.Message;
import bot.model.messageRequest.Recipient;
import bot.service.PrivacyPolicyService;
import bot.service.UserService;

@Component
public class HandlerOfState {
	
	@Autowired
	private UserService userService;

	@Autowired
	private PrivacyPolicyService policyService;
	
	@Autowired
	private Operator operator;
	
	@Autowired
	private MessageHolder holder;
	
	public void greeting(User user) {
		
		MessageBot bot = new MessageBot();
		bot.setRecipient(new Recipient(user.getMessagerId()));
		bot.setMessage(new MessageSend(
				String.format(holder.getMessage("message.greetings"), user.getFirstName(), user.getLastName())));

		operator.postMessage(bot);

		userService.updateState(user, State.STATE_APP_NAME);
	}
	
	public void newPolicy(User user) {
		
		MessageBot bot = new MessageBot();
		bot.setRecipient(new Recipient(user.getMessagerId()));
		bot.setMessage(new MessageSend(holder.getMessage("message.ask_app_name")));
		
		operator.postMessage(bot);

		userService.updateState(user, State.STATE_APP_NAME);
	}

	public void checkAppName(Message message, User user) {
				
		PrivacyPolicy policy = policyService.findLastActivePrivacyPolicy(user.getId());
		
		MessageBot bot = new MessageBot();
		bot.setRecipient(new Recipient(user.getMessagerId()));
		
		if(policy == null) {
			
			user.setState(State.STATE_NOT_FOUND_POLICY);
			
			bot.setMessage(new MessageSend(holder.getMessage("message.not_found_policy")));
			operator.postMessage(bot);
						
		}
		else{

			bot.setMessage(new MessageSend(holder.getMessage("massage.ask_email")));
			operator.postMessage(bot);

			userService.updateState(user, State.STATE_EMAIL);
			
			policy.setAppName(message.getText());
			policyService.updatePrivacyPolicy(policy);
		}
	
	}
	
	public void checkEmail(Message message, User user) {
		
		Pattern pattern = Pattern.compile(holder.getMessage("message.email_pattern"));
		Matcher matcher = pattern.matcher(message.getText());	
		PrivacyPolicy policy = policyService.findLastActivePrivacyPolicy(user.getId());
		MessageBot bot = new MessageBot();
		bot.setRecipient(new Recipient(user.getMessagerId()));

		if (policy == null) {

			user.setState(State.STATE_NOT_FOUND_POLICY);

			bot.setMessage(new MessageSend(holder.getMessage("message.not_found_policy")));
			operator.postMessage(bot);
			
			return;
		}

		if (matcher.matches()) {

			policy.seteMail(message.getText());
			bot.setMessage(new MessageSend(holder.getMessage("message.ask_address")));
			operator.postMessage(bot);

			userService.updateState(user, State.STATE_ADDRESS);

			policyService.updatePrivacyPolicy(policy);

		} else {

			bot.setMessage(new MessageSend(holder.getMessage("message.incorrect_email")));
			operator.postMessage(bot);
		}
	}

	public void checkAdresss(User user, Message message) {
	
		PrivacyPolicy policy = policyService.findLastActivePrivacyPolicy(user.getId());
		
		MessageBot bot = new MessageBot();
		bot.setRecipient(new Recipient(user.getMessagerId()));

		if (policy == null) {

			user.setState(State.STATE_NOT_FOUND_POLICY);

			bot.setMessage(new MessageSend(holder.getMessage("message.not_found_policy")));
			operator.postMessage(bot);

		} else {

			policy.setToken(GeneratorToken.generateToken());
			policy.setAddress(message.getText());

			Button button = Button.createWebButton(holder.getMessage("message.url") + "/" + policy.getToken(),
					holder.getMessage("message.web_button"));

			MessageButton messageButton = MessageButton.createMessageButton(Arrays.asList(button),
					String.format(holder.getMessage("message.button_link"), policy.getAppName()),
					new Recipient(user.getMessagerId()));

			operator.postButton(messageButton);

			policyService.updatePrivacyPolicy(policy);

			userService.updateState(user, State.STATE_FINISH);
		}

	}
	
	public void notFoundPolicy(User user) {
		
		MessageBot bot = new MessageBot();
		bot.setRecipient(new Recipient(user.getMessagerId()));
		bot.setMessage(new MessageSend(holder.getMessage("message.not_found_policy")));
		
		operator.postMessage(bot);
	}
	
	public void messageGeneric(User user, Message message) {

		if (message != null) {
			MessageBot bot = new MessageBot();
			bot.setRecipient(new Recipient(user.getMessagerId()));
			bot.setMessage(new MessageSend(holder.getMessage("message.warning_in_generic_template")));

			operator.postMessage(bot);
		}

	}
	
	public void EditAppNamePost(User user) {
		
		MessageBot bot = new MessageBot();
		bot.setMessage(new MessageSend(holder.getMessage("message.edit_new_app_name")));
		bot.setRecipient(new Recipient(user.getMessagerId()));
		operator.postMessage(bot);
		
		userService.updateState(user, State.STATE_EDIT_APP_NAME);
	}
	
	public void EditEmailPost(User user) {
		
		MessageBot bot = new MessageBot();
		bot.setMessage(new MessageSend(holder.getMessage("message.edit_new_email")));
		bot.setRecipient(new Recipient(user.getMessagerId()));
		operator.postMessage(bot);
		
		
		userService.updateState(user, State.STATE_EDIT_EMAIL);
	}
	
	public void EditAddressPost(User user) {

		MessageBot bot = new MessageBot();
		bot.setMessage(new MessageSend(holder.getMessage("message.edit_new_address")));
		bot.setRecipient(new Recipient(user.getMessagerId()));
		operator.postMessage(bot);
		
		userService.updateState(user, State.STATE_EDIT_ADDRESS);
	}
	
	public void EditAppName(Message message, User user) {
		
		PrivacyPolicy policy = policyService.getEditPolicy(user.getId());
		
		policyService.checkForIsEdit(policy);
		policy.setAppName(message.getText());
		
		policyService.checkForIsEdit(policy);

		operator.postMessageGeneric(MessageGeneric.createMessageGenericEdit(policy, user.getMessagerId()));

		userService.updateState(user, State.STATE_MESSAGE_GENERIC);
	}

	public void EditEmail(Message message, User user) {

		Pattern pattern = Pattern.compile(holder.getMessage("message.email_pattern"));
		Matcher matcher = pattern.matcher(message.getText());	
		PrivacyPolicy policy = policyService.getEditPolicy(user.getId());
		
		if (!matcher.matches()) {
			
			MessageBot bot = new MessageBot();
			bot.setRecipient(new Recipient(user.getMessagerId()));
			bot.setMessage(new MessageSend(holder.getMessage("message.incorrect_email")));
			
			operator.postMessage(bot);
		}
		else{
			
			policy.seteMail(message.getText());
			
			policyService.checkForIsEdit(policy);
			
			operator.postMessageGeneric(MessageGeneric.createMessageGenericEdit(policy, user.getMessagerId()));

			userService.updateState(user, State.STATE_MESSAGE_GENERIC);
		}

	}

	public void EditAddress(Message message, User user) {

		PrivacyPolicy policy = policyService.getEditPolicy(user.getId());
		policy.setAddress(message.getText());
		
		policyService.checkForIsEdit(policy);
		
		operator.postMessageGeneric(MessageGeneric.createMessageGenericEdit(policy, user.getMessagerId()));
		
		userService.updateState(user, State.STATE_MESSAGE_GENERIC);
	}
	
	public void finish(User user) {
		
		MessageBot bot = new MessageBot();
		bot.setRecipient(new Recipient(user.getMessagerId()));
		bot.setMessage(new MessageSend(holder.getMessage("message.finish")));
		
		operator.postMessage(bot);
	}
}
