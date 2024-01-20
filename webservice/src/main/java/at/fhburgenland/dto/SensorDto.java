package at.fhburgenland.dto;

import at.fhburgenland.model.SensorType;

public class SensorDto {

    private boolean active;
    private String location;
    private String name;
    private SensorType type;

    public SensorDto(boolean active, String location, String name, SensorType type) {
        this.active = active;
        this.location = location;
        this.name = name;
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorType getType() {
        return type;
    }

    public void setType(SensorType type) {
        this.type = type;
    }
}
