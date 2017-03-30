package bot.model.messageButton;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Button {
	
	private String type;
	
	private String url;
	
	private String title;
	
	private String payload;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	private Button() {
		
	}
	
	public static Button createWebButton(String url, String title) {
		
		Button button = new Button();
		
		button.setTitle(title);
		button.setType("web_url");
		button.setUrl(url);
		
		return button;
	}
	
	public static Button createPostBackButton(String title, String payload) {
		
	Button button = new Button();
		
		button.setTitle(title);
		button.setPayload(payload);
		button.setType("postback");
		
		return button;
	}

}
