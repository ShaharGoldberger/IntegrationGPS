package app;

import java.util.List;
import java.util.Optional;

public interface GPSService {

	public GPSBoundary create(String superapp, GPSBoundary boundary);
	
	public Optional<GPSBoundary> getGPSByEmail(String superapp, String email);
	
	public List<GPSBoundary> getAllLocations(String superapp);
	
	public void deleteAll();
	
}
