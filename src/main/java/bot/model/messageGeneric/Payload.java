package bot.model.messageGeneric;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payload {
	
	@JsonProperty(value = "template_type")
	private String templateType;

	private List<Element> elements;

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public List<Element> getElements() {
		return elements;
	}

	public void setElements(List<Element> elements) {
		this.elements = elements;
	}
	
}
