package at.fhburgenland.model;

import jakarta.persistence.*;

@Entity
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private boolean active;
    @Enumerated(EnumType.STRING)
    private SensorType sensorType;

    // Constructors
    public Sensor() {
        // Default constructor
    }

    public Sensor(String name, String location, boolean active, SensorType sensorType) {
        this.name = name;
        this.location = location;
        this.active = active;
        this.sensorType = sensorType;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }
}
