package bot.model.messageRequest;

public class PostBack {

	private String payload;

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "PostBack [payload=" + payload + "]";
	}
	
	
	
}
