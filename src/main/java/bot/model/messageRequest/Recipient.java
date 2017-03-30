package bot.model.messageRequest;

public class Recipient {

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Recipient [id=" + id + "]";
	}

	public Recipient(long id) {
		this.id = id;
	}
	
	public Recipient() {
		
	}
	
	
	
}
