package at.fhburgenland.dto;


public class MeasurementDto {

    private long sensorId;
    private float temperature;
    private float humidity;


    public MeasurementDto(long sensorId, float temperature, float humidity) {
        this.sensorId = sensorId;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public long getSensorId() {
        return sensorId;
    }

    public void setSensorId(long sensorId) {
        this.sensorId = sensorId;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}
