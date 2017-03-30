package bot.operating;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bot.entity.PrivacyPolicy;
import bot.entity.User;
import bot.entity.constans.State;
import bot.model.messageBot.MessageBot;
import bot.model.messageBot.MessageSend;
import bot.model.messageGeneric.MessageGeneric;
import bot.model.messageRequest.Messaging;
import bot.model.messageRequest.Recipient;
import bot.model.userInformation.UserInformation;
import bot.service.PrivacyPolicyService;
import bot.service.UserService;

@Component
public class HandlerOfButton {

	@Autowired 
	private Operator operator;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private PrivacyPolicyService policyService;
	
	
	public void buttonAllPolicy(Messaging messaging) {
		
        long id = operator.getRecipientId(messaging);
		
		User user = userService.findByMessagerId(id);
		
		int firstPage = 0;
		int nextPage = 1;
		
		boolean hasNext = false;
		
		userService.updateState(user, State.STATE_MESSAGE_GENERIC);
		
		PrivacyPolicy editPolicy = policyService.getEditPolicy(user.getId());	
		PrivacyPolicy policy = policyService.findLastActivePrivacyPolicy(user.getId());
		
		policyService.checkForIsEdit(editPolicy);

		policyService.removeActivePolicy(policy);
		
		List <PrivacyPolicy> policie = policyService.getAllActivePolicy(user.getId(), firstPage);
		List <PrivacyPolicy> next = policyService.getAllActivePolicy(user.getId(), nextPage);
		
		
		if(next.size() > 0) {
			hasNext = true;
		}
		
		if(policie.size() == 0) {
			userService.updateState(user, State.STATE_NOT_FOUND_POLICY);
			return;
		}

		operator.postMessageGeneric(MessageGeneric.creteMessageGeneric(policie, id, firstPage, hasNext));

	}

	public void buttonGetStarted(Messaging messaging) {

		long id = operator.getRecipientId(messaging);
		User user = userService.findByMessagerId(id);
		UserInformation userInformation = operator.getUserInformation(id);

		if (user == null) {

			user = new User(userInformation.getFirstName(), userInformation.getLastName(), State.STATE_GET_STARTED, id);
			PrivacyPolicy policy = new PrivacyPolicy(true, false, user);
			policyService.savePrivacyPolicy(policy);

		} else {

			userService.updateState(user, State.STATE_GET_STARTED);

			PrivacyPolicy policy = policyService.findLastActivePrivacyPolicy(user.getId());

			policyService.removeActivePolicy(policy);

			policy = new PrivacyPolicy(true, false, user);
			policyService.savePrivacyPolicy(policy);
		}
		
		PrivacyPolicy editPolicy = policyService.getEditPolicy(user.getId());	
		
		policyService.checkForIsEdit(editPolicy);
	}
	
	public void buttonNewPolicy(Messaging messaging) {

		long id = operator.getRecipientId(messaging);
		
		User user = userService.findByMessagerId(id);	
		userService.updateState(user, State.STATE_NEW_POLICY);

		PrivacyPolicy policy = policyService.findLastActivePrivacyPolicy(user.getId());
		PrivacyPolicy editPolicy = policyService.getEditPolicy(user.getId());	
		
		policyService.checkForIsEdit(editPolicy);

		policyService.removeActivePolicy(policy);
		
		policy = new PrivacyPolicy(true, false, user);
		policyService.savePrivacyPolicy(policy);

	}
	
	public void buttonRemove(Messaging messaging, int id) {

		User user = userService.findByMessagerId(operator.getRecipientId(messaging));
		
		PrivacyPolicy activePolicy = policyService.findLastActivePrivacyPolicy(user.getId());
		
		policyService.removeActivePolicy(activePolicy);
		
		PrivacyPolicy policy = policyService.getPrivacyPolicy(id);
		MessageBot bot = new MessageBot();

		if (!policy.getIsActive()) {
			bot.setMessage(new MessageSend("You already deleted it"));
			bot.setRecipient(new Recipient(operator.getRecipientId(messaging)));
			operator.postMessage(bot);
			
		} else {

			policyService.removeActivePolicy(policy);

			bot.setMessage(new MessageSend("Policy " + policy.getAppName() + " has been deleted"));
			bot.setRecipient(new Recipient(operator.getRecipientId(messaging)));
			operator.postMessage(bot);

			buttonAllPolicy(messaging);
		}
		
	}
	
	public void buttonEdit(Messaging messaging, int id) {
		
		User user = userService.findByMessagerId(operator.getRecipientId(messaging));
		userService.updateState(user, State.STATE_MESSAGE_GENERIC);
			
		PrivacyPolicy editPolicy = policyService.getEditPolicy(user.getId());
		policyService.checkForIsEdit(editPolicy);
		
		PrivacyPolicy activePolicy = policyService.findLastActivePrivacyPolicy(user.getId());	
		policyService.removeActivePolicy(activePolicy);

		PrivacyPolicy policy = policyService.getPrivacyPolicy(id);
		policy.setEdit(true);
		policyService.updatePrivacyPolicy(policy);

		long messagerId = operator.getRecipientId(messaging);

		operator.postMessageGeneric(MessageGeneric.createMessageGenericEdit(policy, messagerId));

	}

	public void buutonEditFieldAppName(Messaging messaging, int id) {
		
		User user = userService.findByMessagerId(operator.getRecipientId(messaging));
		
		PrivacyPolicy editPolicy = policyService.getEditPolicy(user.getId());
		policyService.checkForIsEdit(editPolicy);

		PrivacyPolicy activePolicy = policyService.findLastActivePrivacyPolicy(user.getId());
		policyService.removeActivePolicy(activePolicy);
		
		PrivacyPolicy policy = policyService.getPrivacyPolicy(id);
		policy.setEdit(true);
		policyService.updatePrivacyPolicy(policy);
	
		
		userService.updateState(user, State.STATE_EDIT_APP_NAME_POST);
		
	}
	
	public void buutonEditFieldEmail(Messaging messaging, int id) {
		
		User user = userService.findByMessagerId(operator.getRecipientId(messaging));

		PrivacyPolicy editPolicy = policyService.getEditPolicy(user.getId());
		policyService.checkForIsEdit(editPolicy);
		
		PrivacyPolicy activePolicy = policyService.findLastActivePrivacyPolicy(user.getId());
		policyService.removeActivePolicy(activePolicy);
		
		PrivacyPolicy policy = policyService.getPrivacyPolicy(id);
		policy.setEdit(true);
		policyService.updatePrivacyPolicy(policy);
		
		
		userService.updateState(user, State.STATE_EDIT_EMAIL_POST);		
	}
	
	public void buutonEditFieldAddress(Messaging messaging, int id) {
		
		User user = userService.findByMessagerId(operator.getRecipientId(messaging));

		PrivacyPolicy editPolicy = policyService.getEditPolicy(user.getId());
		policyService.checkForIsEdit(editPolicy);
		
		PrivacyPolicy activePolicy = policyService.findLastActivePrivacyPolicy(user.getId());
		policyService.removeActivePolicy(activePolicy);
		
		PrivacyPolicy policy = policyService.getPrivacyPolicy(id);
		policy.setEdit(true);
		policyService.updatePrivacyPolicy(policy);		
		
		userService.updateState(user, State.STATE_EDIT_ADDRESS_POST);		
	}

	public void buttonNext(Messaging messaging, int page) {
		
		long id = operator.getRecipientId(messaging);

		User user = userService.findByMessagerId(id);

		PrivacyPolicy activePolicy = policyService.findLastActivePrivacyPolicy(user.getId());

		policyService.removeActivePolicy(activePolicy);

		int currentPage = page + 1;
		int nextPage = currentPage + 1;

		boolean hasNext = false;

		List<PrivacyPolicy> policie = policyService.getAllActivePolicy(user.getId(), currentPage);
		List<PrivacyPolicy> next = policyService.getAllActivePolicy(user.getId(), nextPage);

		if (next.size() > 0) {
			hasNext = true;
		}

		operator.postMessageGeneric(
				MessageGeneric.creteMessageGeneric(policie, messaging.getSender().getId(), currentPage, hasNext));
	}

}
