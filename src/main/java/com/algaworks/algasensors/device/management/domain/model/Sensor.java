package com.algaworks.algasensors.device.management.domain.model;

import org.jilt.Builder;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Builder
@Entity
public class Sensor {
    @Id
    @AttributeOverride(
        name = "value", column = @Column(name="id", columnDefinition = "BIGINT")
    )
    private SensorId id;
    private String name;
    private String ip;
    private String location;
    private String protocol;
    private String model;
    private Boolean enabled;

    protected Sensor() {}
    public Sensor(SensorId id, String name, String ip, String location, String protocol, String model, Boolean enabled) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.location = location;
        this.protocol = protocol;
        this.model = model;
        this.enabled = enabled;
    }
    public SensorId getId() {
        return id;
    }
    public void setId(SensorId id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getProtocol() {
        return protocol;
    }
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
