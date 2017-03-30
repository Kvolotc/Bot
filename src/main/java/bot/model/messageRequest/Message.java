package bot.model.messageRequest;


import com.fasterxml.jackson.annotation.JsonProperty;


public class Message {

	private String mid;

	private int seq;

	private String text;

	@JsonProperty(value = "quick_reply")
	private QuickReply quickReply;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public QuickReply getQuickReply() {
		return quickReply;
	}

	public void setQuickReply(QuickReply quickReply) {
		this.quickReply = quickReply;
	}

	@Override
	public String toString() {
		return "Message [mid=" + mid + ", seq=" + seq + ", text=" + text + ", quickReply=" + quickReply + "]";
	}

}
