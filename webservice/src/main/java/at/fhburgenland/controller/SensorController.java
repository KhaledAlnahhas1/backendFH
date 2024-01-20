package at.fhburgenland.controller;

import at.fhburgenland.dto.SensorDto;
import at.fhburgenland.model.Sensor;
import at.fhburgenland.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sensors")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @PostMapping("/add")
    public ResponseEntity<String> addSensor(@RequestBody SensorDto sensor) {
        sensorService.addSensor(sensor);
        return ResponseEntity.ok("Sensor added successfully.");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Sensor>> getAllSensors() {
        List<Sensor> sensors = sensorService.getAllSensors();
        return ResponseEntity.ok(sensors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable Long id) {
        Optional<Sensor> sensor = sensorService.getSensorById(id);
        return ResponseEntity.of(sensor);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSensor(@PathVariable Long id, @RequestBody SensorDto updatedSensor) {
        sensorService.updateSensor(id, updatedSensor);
        return ResponseEntity.ok("Sensor updated successfully.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSensor(@PathVariable Long id) {
        sensorService.deleteSensor(id);
        return ResponseEntity.ok("Sensor deleted successfully.");
    }
}
