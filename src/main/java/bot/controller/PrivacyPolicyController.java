package bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bot.entity.PrivacyPolicy;
import bot.service.PrivacyPolicyService;

@Controller
public class PrivacyPolicyController {
	
	@Autowired
	private PrivacyPolicyService service;

	@RequestMapping(value = "/webhook/{token}", method = RequestMethod.GET)
	public String getPrivacyPolicyServiceByToken(@PathVariable("token") String token, Model model ) {
		
		PrivacyPolicy policy = service.findByToken(token);
		
		if(policy == null) {
			return "404";
		}
		
		model.addAttribute("policy", policy);
		
		return "PrivacyPolicyInformation";

	}
}
