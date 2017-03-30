package bot.model.messageBot;

public class MessageSend {
	
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Message [text=" + text + "]";
	}

	public MessageSend(String text) {
		this.text = text;
	}
	
	public MessageSend() {
		
	}
	
}
