package app;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class GPSServiceImplementation implements GPSService{

	private GPSCrud gpsCrud;
	private GPSConverter gpsConverter;
	private String springApplicationName;

	public GPSServiceImplementation(GPSCrud gpsCrud, GPSConverter gpsConverter) {
		this.gpsCrud = gpsCrud;
		this.gpsConverter = gpsConverter;
	}

	@Value("${spring.application.name:gps.app}")
	public void setSpringApplicationName(String springApplicationName) {
		this.springApplicationName = springApplicationName;
		System.err.println("The Spring Application name is: " + this.springApplicationName);
	}
	
	@Override
	public GPSBoundary create(String superapp, GPSBoundary boundary) {
		
		// created by exists
		if ( boundary.getCreatedBy() == null 
				|| boundary.getCreatedBy().getUserId() == null ) {
			throw new RuntimeException("created by cannot be empty");
		}
		
		String email = boundary.getCreatedBy().getUserId().getEmail();
		if ( email == null || email.equals("")) {
			throw new RuntimeException("email by cannot be empty");
		}
		String objectSuperapp = boundary.getCreatedBy().getUserId().getSuperapp();
		if ( objectSuperapp == null || objectSuperapp.equals("")) {
			throw new RuntimeException("superapp by cannot be empty");
		}
		// validate location
		if ( boundary.getLocation() == null 
				|| boundary.getLocation().getLat() == null 
				|| boundary.getLocation().getLat() <= -90 
				|| boundary.getLocation().getLat() >= 90)
			throw new RuntimeException("latitude value is not valid by cannot be empty");

		
		if ( boundary.getLocation() == null 
				|| boundary.getLocation().getLng() == null 
				|| boundary.getLocation().getLng() <= -180 
				|| boundary.getLocation().getLng() >= 180)
			throw new RuntimeException("longitude value is not valid by cannot be empty");

		
		// superapp exists
		Optional<GPSEntity> gps = this.gpsCrud.findByCreatedBy(springApplicationName + "#" + email);
		boundary.setCreationTimestamp(new Date());

		// save entity
		GPSEntity entity = null;
		if (!gps.isEmpty()) {
			entity = gps.get();
			
			entity.setLat(boundary.getLocation().getLat());
			entity.setLng(boundary.getLocation().getLng());
			gpsCrud.save(entity);
		}else {
			boundary.setObjectId(new ObjectId());
			boundary.getObjectId().setSuperapp(springApplicationName);
			boundary.getObjectId().setId(UUID.randomUUID().toString());
			
			entity = gpsConverter.toEntity(boundary);
			
			gpsCrud.save(entity);
		}
		
		return gpsConverter.toBoundary(entity);
	}

	@Override
	public Optional<GPSBoundary> getGPSByEmail(String superapp, String email) {
		return this.gpsCrud.findByCreatedBy(superapp + "#" + email).map(gpsConverter::toBoundary);
	}

	@Override
	public List<GPSBoundary> getAllLocations(String superapp) {
		return this.gpsCrud.findAll().stream().map(this.gpsConverter::toBoundary).toList();
	}

	@Override
	public void deleteAll() {
		this.gpsCrud.deleteAll();
	}

}
