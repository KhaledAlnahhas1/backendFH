package at.fhburgenland.service;

import at.fhburgenland.WebServiceApplication;
import at.fhburgenland.dto.MeasurementDto;
import at.fhburgenland.model.Measurement;
import at.fhburgenland.model.Sensor;
import at.fhburgenland.model.SensorType;
import at.fhburgenland.repository.MeasurementRepository;
import at.fhburgenland.repository.SensorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(classes = WebServiceApplication.class)
@ActiveProfiles(value = "file")
public class MeasurementServiceTest {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private MeasurementService measurementService;
    @Autowired
    private TaskExecutionProperties taskExecutionProperties;

    @Test
    public void addMeasurementTest() throws Exception {
        cleanUp();
        Sensor sensor = createSensor("Home", "Sensor 1", true, SensorType.INSIDE);
        sensorRepository.save(sensor);

        MeasurementDto measurementDto = new MeasurementDto(sensor.getId(), 25.5f, 50.0f);

        assertDoesNotThrow(() -> measurementService.addMeasurement(measurementDto));

        List<Measurement> measurements = measurementRepository.findAll();
        assertEquals(1, measurements.size());
    }

    @Test
    void updateMeasurementTest() {
        cleanUp();
        Sensor sensor = createSensor("Home", "Sensor 1", true, SensorType.INSIDE);
        sensorRepository.save(sensor);

        MeasurementDto measurementDto = new MeasurementDto(sensor.getId(), 25.5f, 50.0f);

        assertDoesNotThrow(() -> measurementService.addMeasurement(measurementDto));

        List<Measurement> measurements = measurementRepository.findAll();
        assertEquals(1, measurements.size());

        Long measurementId = measurements.iterator().next().getId();
        MeasurementDto updatedMeasurementDto = new MeasurementDto(sensor.getId(), 26.0f, 48.0f);

        try {
            assertDoesNotThrow(() -> measurementService.updateMeasurement(measurementId, updatedMeasurementDto));

            List<Measurement> updatedMeasurements = measurementRepository.findAll();
            assertEquals(1, updatedMeasurements.size());

            Measurement updatedMeasurement = updatedMeasurements.iterator().next();
            assertEquals(26.0f, updatedMeasurement.getTemperature());
            assertEquals(48.0f, updatedMeasurement.getHumidity());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    @Test
    void deleteMeasurementTest() {
        cleanUp();
        Sensor sensor = createSensor("Kitchen", "Sensor 4", true, SensorType.INSIDE);
        sensorRepository.save(sensor);

        MeasurementDto measurementDto = new MeasurementDto(sensor.getId(), 24.0f, 55.0f);

        try {
            assertDoesNotThrow(() -> measurementService.addMeasurement(measurementDto));

            List<Measurement> measurements = measurementRepository.findAll();
            assertEquals(1, measurements.size());

            Long measurementId = measurements.iterator().next().getId();

            assertDoesNotThrow(() -> measurementService.deleteMeasurement(measurementId));

            List<Measurement> remainingMeasurements = measurementRepository.findAll();
            assertEquals(0, remainingMeasurements.size());

        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    @Test
    void getAllMeasurementsTest() {
        cleanUp();
        Sensor sensor1 = createSensor("Living Room", "Sensor 2", true, SensorType.INSIDE);
        Sensor sensor2 = createSensor("Bedroom", "Sensor 3", true, SensorType.INSIDE);

        sensorRepository.saveAll(List.of(sensor1, sensor2));

        MeasurementDto measurementDto1 = new MeasurementDto(sensor1.getId(), 22.0f, 45.0f);
        MeasurementDto measurementDto2 = new MeasurementDto(sensor2.getId(), 23.0f, 50.0f);

        try {
            assertDoesNotThrow(() -> measurementService.addMeasurement(measurementDto1));
            assertDoesNotThrow(() -> measurementService.addMeasurement(measurementDto2));

            List<Measurement> allMeasurements = assertDoesNotThrow(() -> measurementService.getAllMeasurements());
            assertEquals(2, allMeasurements.size());

            // Hier könntest du weitere Überprüfungen durchführen, wenn nötig.

        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }


    private Sensor createSensor(String name, String location, boolean active, SensorType sensorType) {
        Sensor sensor = new Sensor();
        sensor.setName(name);
        sensor.setLocation(location);
        sensor.setActive(active);
        sensor.setSensorType(sensorType);
        return sensor;
    }

    private void cleanUp() {
        measurementRepository.deleteAll();
        sensorRepository.deleteAll();
    }
}
