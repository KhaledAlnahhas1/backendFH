package at.fhburgenland.repository;


import at.fhburgenland.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    Optional<Sensor> findByNameAndLocation(String name, String location);

}
