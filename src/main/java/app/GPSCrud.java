package app;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface GPSCrud extends JpaRepository<GPSEntity, String> {

	
	public Optional<GPSEntity> findByCreatedBy(@Param("createBy") String createBy);
}
