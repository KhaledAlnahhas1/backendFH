package at.fhburgenland.service;

import at.fhburgenland.dto.MeasurementDto;
import at.fhburgenland.model.Measurement;
import at.fhburgenland.model.Sensor;
import at.fhburgenland.repository.MeasurementRepository;
import at.fhburgenland.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private SensorRepository sensorRepository;

    public void addMeasurement(MeasurementDto measurementDto) throws Exception {
        Optional<Sensor> sensor = sensorRepository.findById(measurementDto.getSensorId());
        if (sensor.isEmpty()) {
            throw new Exception("Sensor with Id: " + measurementDto.getSensorId() + " not found");
        } else {
            Measurement measurement = new Measurement();
            measurement.setSensor(sensor.get());
            measurement.setHumidity(measurementDto.getHumidity());
            measurement.setTemperature(measurementDto.getTemperature());
            Timestamp now = new Timestamp(System.currentTimeMillis());
            measurement.setTimestamp(now);

            measurementRepository.save(measurement);
        }

    }

    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    public Optional<Measurement> getMeasurementById(Long id) {
        return measurementRepository.findById(id);
    }

    public void updateMeasurement(Long id, MeasurementDto updatedMeasurement) {
        Optional<Measurement> existingMeasurement = measurementRepository.findById(id);
        existingMeasurement.ifPresent(measurement -> {
            Optional<Sensor> sensor = sensorRepository.findById(updatedMeasurement.getSensorId());
            if (sensor.isEmpty()) {
                try {
                    throw new Exception("Sensor with Id: " + updatedMeasurement.getSensorId() + " not found");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                measurement.setSensor(sensor.get());
                measurement.setTemperature(updatedMeasurement.getTemperature());
                measurement.setHumidity(updatedMeasurement.getHumidity());
                measurementRepository.save(measurement);
            }
        });
    }

    public void deleteMeasurement(Long id) {
        measurementRepository.deleteById(id);
    }
}
