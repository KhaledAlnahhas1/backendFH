package at.fhburgenland.service;

import at.fhburgenland.WebServiceApplication;
import at.fhburgenland.dto.SensorDto;
import at.fhburgenland.model.Sensor;
import at.fhburgenland.model.SensorType;
import at.fhburgenland.repository.SensorRepository;
import at.fhburgenland.service.SensorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = WebServiceApplication.class)
@ActiveProfiles(value = "file")
public class SensorServiceTest {

    @Autowired
    private SensorRepository sensorRepository;
    @Autowired
    private SensorService sensorService;



    @Test
    public void basicTest() {
        cleanUp();
        Sensor sensor = new Sensor();
        sensor.setName("1");
        sensor.setLocation("1");
        sensor.setActive(true);
        sensor.setSensorType(SensorType.INSIDE);

        sensorRepository.save(sensor);

        List<Sensor> sensors = sensorRepository.findAll();
        List<Sensor> sensorList = sensorService.getAllSensors();
        assertTrue(sensors.size() == sensorList.size());

   }

   @Test
    public void saveSensorTest() {
       cleanUp();
       SensorDto sensorDto = new SensorDto(true, "Home", "Sensor 1", SensorType.INSIDE);
       sensorService.addSensor(sensorDto);

       List<Sensor> sensors = sensorRepository.findAll();
       List<Sensor> sensorList = sensorService.getAllSensors();
       assertTrue(sensors.size() == sensorList.size());
   }

   @Test
   public void updateSensortest() {
       cleanUp();
       SensorDto sensorDto = new SensorDto(true, "Home", "Sensor 1", SensorType.INSIDE);
       sensorService.addSensor(sensorDto);
       List<Sensor> sensors = sensorRepository.findAll();
       assertTrue(sensors.size() == 1);

       long sensorId = sensors.iterator().next().getId();
       SensorDto sensorDtoUpdated = new SensorDto(false, "Kitchen", "Sensor 1", SensorType.INSIDE);

       sensorService.updateSensor(sensorId, sensorDtoUpdated);
       List<Sensor> updatedSensors = sensorRepository.findAll();
       assertTrue(updatedSensors.size() == 1);
       assertEquals("Sensor 1", updatedSensors.iterator().next().getName());
       assertEquals("Kitchen", updatedSensors.iterator().next().getLocation());
       assertEquals(false, updatedSensors.iterator().next().isActive());
   }

   @Test
    public void deleteSensorTest() {
       cleanUp();
       Sensor sensor = new Sensor();
       sensor.setName("SENSOR-2");
       sensor.setLocation("Bedroom");
       sensor.setActive(true);
       sensor.setSensorType(SensorType.INSIDE);

       sensorRepository.save(sensor);
       List<Sensor> sensors = sensorRepository.findAll();
       assertEquals(1, sensors.size());

       sensorService.deleteSensor(sensors.iterator().next().getId());
       assertEquals(0, sensorRepository.findAll().size());
   }



   private void cleanUp() {
       sensorRepository.deleteAll();
   }
}
