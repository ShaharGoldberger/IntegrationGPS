package app;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = { "/miniapp/gps" })
public class GPSController {

	private GPSService gpsService;
	
	public GPSController(GPSService gpsService) {
		this.gpsService = gpsService;
	}

	@PostMapping(
			path = "/{superapp}/",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public GPSBoundary create(@PathVariable("superapp") String superappName, @RequestBody GPSBoundary objectBoundary) {
		return this.gpsService
		.create(superappName, objectBoundary);
	}

	
	@GetMapping(
			path = {"/{superapp}/{email}"}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
		public GPSBoundary getGPSBySuperappNameAndEmail(
				@PathVariable("superapp") String superappName, 
				@PathVariable("email") String email
				) {
			return this.gpsService
					.getGPSByEmail(superappName, email)
					.get();
		}
}
