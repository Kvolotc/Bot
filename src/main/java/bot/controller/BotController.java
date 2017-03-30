package bot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bot.message.MessageHolder;
import bot.model.messageRequest.MessageRequest;
import bot.operating.MainFlow;

@RestController
public class BotController {
	
	@Autowired
	private MainFlow flow;
	
	@Autowired
	private MessageHolder holder;

	@RequestMapping(value = "/webhook", method = RequestMethod.GET)
	public String veritify(@RequestParam(name = "hub.verify_token") String hubToken, HttpServletRequest request,
			HttpServletResponse response) {
		
		if (hubToken.equals(holder.getMessage("message.subscriber"))) {
			String challenge = request.getParameter("hub.challenge");
			response.setStatus(HttpServletResponse.SC_OK);
			return challenge;
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "error";
		}
	}
		
	@RequestMapping(value = "/webhook", method = RequestMethod.POST)
	public void send(@RequestBody MessageRequest messageRequest) {

		flow.startFlow(messageRequest);

	}

}