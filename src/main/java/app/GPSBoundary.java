package app;

import java.util.Date;

public class GPSBoundary {

	private ObjectId objectId;
	private CreatedBy createdBy;
	
	private Location location;
	private Date creationTimestamp;
	public GPSBoundary() {
		super();
	}
	public ObjectId getObjectId() {
		return objectId;
	}
	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}
	public CreatedBy getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(CreatedBy createdBy) {
		this.createdBy = createdBy;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Date getCreationTimestamp() {
		return creationTimestamp;
	}
	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	@Override
	public String toString() {
		return "GPSBoundary [objectId=" + objectId + ", createdBy=" + createdBy + ", location=" + location
				+ ", creationTimestamp=" + creationTimestamp + "]";
	}
	
	
	
	
}
