package bot.model.messageButton;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payload {

	@JsonProperty(value = "template_type")
	private String templateType;
	
	private String text;
	
	private List<Button> buttons;

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}
	
}
