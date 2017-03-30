package bot.model.messageRequest;

import java.util.List;

public class Entry {
	
	private long id;
	
	private long time;
	
	private List<Messaging> messaging;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	public List<Messaging> getMessaging() {
		return messaging;
	}

	public void setMessaging(List<Messaging> messaging) {
		this.messaging = messaging;
	}

	@Override
	public String toString() {
		return "Entry [id=" + id + ", time=" + time + ", messagings=" + messaging + "]";
	}
	
}
