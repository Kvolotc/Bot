package bot.model.messageGeneric;

import java.util.List;

import bot.model.messageButton.Button;

public class Element {

	private String title;
	
	private String subtitle;
	
	private List<Button> buttons;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}
	
}
