package at.fhburgenland.controller;

import at.fhburgenland.dto.MeasurementDto;
import at.fhburgenland.model.Measurement;
import at.fhburgenland.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/measurements")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @PostMapping("/add")
    public ResponseEntity<String> addMeasurement(@RequestBody MeasurementDto measurementDto) throws Exception {
        measurementService.addMeasurement(measurementDto);
        return ResponseEntity.ok("Measurement added successfully.");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Measurement>> getAllMeasurements() {
        List<Measurement> measurements = measurementService.getAllMeasurements();
        return ResponseEntity.ok(measurements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Measurement> getMeasurementById(@PathVariable Long id) {
        Optional<Measurement> measurement = measurementService.getMeasurementById(id);
        return ResponseEntity.of(measurement);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMeasurement(@PathVariable Long id, @RequestBody MeasurementDto updatedMeasurement) {
        measurementService.updateMeasurement(id, updatedMeasurement);
        return ResponseEntity.ok("Measurement updated successfully.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMeasurement(@PathVariable Long id) {
        measurementService.deleteMeasurement(id);
        return ResponseEntity.ok("Measurement deleted successfully.");
    }
}
