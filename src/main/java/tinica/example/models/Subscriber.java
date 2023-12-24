package tinica.example.models;

public class Subscriber {
     
	private int Id;
	private String name;
	private String email;
	
	public Subscriber() {}

	public Subscriber(int id, String name, String email) {
		super();
		Id = id;
		this.name = name;
		this.email = email;
	}
	
	public Subscriber( String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Subscriber [Id=" + Id + ", name=" + name + ", email=" + email + "]";
	}
	
	
	
	
}
