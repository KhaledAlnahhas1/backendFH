package at.fhburgenland.service;

import at.fhburgenland.dto.SensorDto;
import at.fhburgenland.model.Sensor;
import at.fhburgenland.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    public void addSensor(SensorDto sensorDto) {
        Sensor sensor = new Sensor();
        sensor.setName(sensorDto.getName());
        sensor.setActive(sensorDto.isActive());
        sensor.setLocation(sensorDto.getLocation());
        sensor.setSensorType(sensorDto.getType());
        sensorRepository.save(sensor);
    }

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> getSensorById(Long id) {
        return sensorRepository.findById(id);
    }

    public void updateSensor(Long id, SensorDto updatedSensor) {
        Optional<Sensor> existingSensor = sensorRepository.findById(id);
        existingSensor.ifPresent(sensor -> {
            sensor.setName(updatedSensor.getName());
            sensor.setLocation(updatedSensor.getLocation());
            sensor.setActive(updatedSensor.isActive());
            sensor.setSensorType(updatedSensor.getType());
            sensorRepository.save(sensor);
        });
    }

    public void deleteSensor(Long id) {
        sensorRepository.deleteById(id);
    }
}
