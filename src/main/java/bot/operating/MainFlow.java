package bot.operating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bot.entity.User;
import bot.model.messageRequest.Message;
import bot.model.messageRequest.MessageRequest;

@Component
public class MainFlow {

	@Autowired
	private HandlerOfState handlerOfState;
	
	@Autowired
	private Operator operator;
	
	@Autowired
	private ButtonFlow buttonFlow;

	public void startFlow(MessageRequest messageRequest) {

		buttonFlow.checkButton(operator.getMessaging(messageRequest));
		
		User user = operator.getCurrentUser(messageRequest);
		System.out.println(user);

		Message message = operator.getMessage(messageRequest);

		switch (user.getState()) {

		case STATE_GET_STARTED: {
			handlerOfState.greeting(user);
			break;
		}
		case STATE_NEW_POLICY: {
			handlerOfState.newPolicy(user);
			break;
		}
		case STATE_APP_NAME: {
			handlerOfState.checkAppName(message, user);
			break;
		}
		case STATE_EMAIL: {
			handlerOfState.checkEmail(message, user);
			break;
		}
		case STATE_ADDRESS: {
			handlerOfState.checkAdresss(user, message);
			break;
		}
		case STATE_MESSAGE_GENERIC: {
			handlerOfState.messageGeneric(user, message);
			break;
		}
		case STATE_EDIT_APP_NAME_POST : {
			handlerOfState.EditAppNamePost(user);
			break;
		}
		case STATE_EDIT_EMAIL_POST : {
			handlerOfState.EditEmailPost(user);
			break;
		}
		case STATE_EDIT_ADDRESS_POST : {
			handlerOfState.EditAddressPost(user);
			break;
		}
		case STATE_EDIT_APP_NAME: {
			handlerOfState.EditAppName(message, user);
			break;
		}
		case STATE_EDIT_EMAIL: {
			handlerOfState.EditEmail(message, user);
			break;
		}
		case STATE_EDIT_ADDRESS: {
			handlerOfState.EditAddress(message, user);
			break;
		}
		case STATE_NOT_FOUND_POLICY: {
			handlerOfState.notFoundPolicy(user);
			break;
		}
		case STATE_FINISH: {
			handlerOfState.finish(user);
			break;
		}
		default: {
			break;
		}

		}
	}

}
