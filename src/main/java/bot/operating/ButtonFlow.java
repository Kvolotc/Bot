package bot.operating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bot.message.MessageHolder;
import bot.model.messageRequest.Messaging;
import bot.model.messageRequest.PostBack;

@Component
public class ButtonFlow {

	@Autowired
	private HandlerOfButton handlerOfButton;
	
	@Autowired
	private MessageHolder holder;
	
	private final String separateElement = "&";
	private final int FIRST_ELEMENT = 0;
	private final int SECOND_ELEMENT = 1;
	private final int THIRD_ELEMENT = 2;

	public void checkButton(Messaging messaging) {
		
		PostBack back = messaging.getPostBack();

		if (back != null) {

			int id = 0;
			int page = 0;
			String allPayload[] = back.getPayload().split(separateElement);
			String payloadButton = allPayload[FIRST_ELEMENT];
			String payload = back.getPayload();

			if (payloadButton.equals(holder.getMessage("message.payload_edit")) || payloadButton.equals(holder.getMessage("message.payload_remove"))) {
				payload = allPayload[FIRST_ELEMENT];
				id = Integer.valueOf(allPayload[SECOND_ELEMENT]);
			}
			if (payloadButton.equals(holder.getMessage("message.payload_edit_field"))) {
				String field = allPayload[SECOND_ELEMENT];
				id = Integer.valueOf(allPayload[THIRD_ELEMENT]);
				payload = payloadButton +" "+ field;
			}
			if(payloadButton.equals(holder.getMessage("message.payload_next"))) {
				payload = payloadButton;
				page = Integer.valueOf(allPayload[SECOND_ELEMENT]);
			}

			switch (payload) {

			case "Get Started": {
				handlerOfButton.buttonGetStarted(messaging);
				break;
			}
			case "New Policy": {
				handlerOfButton.buttonNewPolicy(messaging);
				break;
			}
			case "Check All Policy": {
				handlerOfButton.buttonAllPolicy(messaging);
				break;
			}
			case "Edit": {
				handlerOfButton.buttonEdit(messaging, id);
				break;
			}
			case "Edit field App name": {
				handlerOfButton.buutonEditFieldAppName(messaging, id);
				break;
			}
			case "Edit field Email": {
				handlerOfButton.buutonEditFieldEmail(messaging, id);
				break;
			}
			case "Edit field Address": {
				handlerOfButton.buutonEditFieldAddress(messaging, id);
				break;
			}
			case "Remove": {
				handlerOfButton.buttonRemove(messaging, id);
				break;
			}
			case "Next": {
				handlerOfButton.buttonNext(messaging, page);
				break;
			}
			default: {
				break;
			}
			}

		}
	}
}
