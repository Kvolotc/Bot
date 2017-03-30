package bot.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@PropertySource("classpath:message.properties")
@Component
public class MessageHolder {
	
	@Autowired
	private Environment environment;
	
	public String getMessage(String key) {
		
		return environment.getProperty(key);
	}
	
	
}
