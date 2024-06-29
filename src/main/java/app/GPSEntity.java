package app;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "GPS_TABLE")
public class GPSEntity {

	@Id private String id; // superapp + "#" + id
	
	private String createdBy;	 // superapp + "#" + email
	private double lat;
	private double lng;
	
	@Temporal(TemporalType.TIMESTAMP) // mapped to Relational DB' timestamp representation
	private Date creationTimestamp;

	@Override
	public String toString() {
		return "GPSEntity [id=" + id + ", createdBy=" + createdBy + ", lat=" + lat + ", lng=" + lng
				+ ", creationTimestamp=" + creationTimestamp + "]";
	}

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public GPSEntity() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}



	
	
}
