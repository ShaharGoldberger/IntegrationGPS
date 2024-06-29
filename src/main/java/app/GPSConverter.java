package app;

import org.springframework.stereotype.Component;

@Component
public class GPSConverter {

	public GPSEntity toEntity(GPSBoundary boundary) {
		GPSEntity entity = new GPSEntity();		
		entity.setId(boundary.getObjectId().getSuperapp() + "#" + boundary.getObjectId().getId());
		
		if ( boundary.getLocation() == null 
				|| boundary.getLocation().getLat() == null 
				|| boundary.getLocation().getLat() <= -90 
				|| boundary.getLocation().getLat() >= 90)
			entity.setLat(37.954813);
		else
			entity.setLat(boundary.getLocation().getLat());
		
		if ( boundary.getLocation() == null 
				
				|| boundary.getLocation().getLng() == null 
				|| boundary.getLocation().getLng() <= -180 
				|| boundary.getLocation().getLng() >= 180)
			entity.setLng(13.365387);
		else
			entity.setLng(boundary.getLocation().getLng());
		
		entity.setCreationTimestamp(boundary.getCreationTimestamp());

		entity.setCreatedBy(boundary.getCreatedBy().getUserId().getSuperapp() + "#" + boundary.getCreatedBy().getUserId().getEmail().toLowerCase());
		
		return entity;
	}
	
	public GPSBoundary toBoundary(GPSEntity entity) {
		GPSBoundary boundary = new GPSBoundary();
		System.err.println("GPSEntity.getId = " + entity.getId());

		String [] idArr = entity.getId().split("#");
		System.err.println("idArr = " + idArr[0] + " " + idArr[1]);

		ObjectId objectId = new ObjectId();
		objectId.setId(idArr[1]);
		objectId.setSuperapp(idArr[0]);
		System.err.println("objectId = " + objectId.getId() + " \n" + objectId.getSuperapp());
		
		boundary.setObjectId(objectId);

		Location location = new Location();
		location.setLat(entity.getLat());
		location.setLng(entity.getLng());
		boundary.setLocation(location);
		
		boundary.setCreationTimestamp(entity.getCreationTimestamp());
		
		String[] CreatedByArr = entity.getCreatedBy().split("#");
		CreatedBy createdBy = new CreatedBy(new UserId(CreatedByArr[0], CreatedByArr[1]));
		boundary.setCreatedBy(createdBy);
		
		return boundary;
	}
	
}
