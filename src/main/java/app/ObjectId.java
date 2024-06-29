package app;

public class ObjectId {

	private String superapp;
	private String id;
	public String getSuperapp() {
		return superapp;
	}
	public void setSuperapp(String superapp) {
		this.superapp = superapp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ObjectId() {
		super();
	}
	@Override
	public String toString() {
		return "ObjectId [superapp=" + superapp + ", id=" + id + "]";
	}
	
	
	
}
