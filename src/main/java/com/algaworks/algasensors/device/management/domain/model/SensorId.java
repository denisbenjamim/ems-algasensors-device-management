package com.algaworks.algasensors.device.management.domain.model;

import java.io.Serializable;
import java.util.Objects;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.Embeddable;

@Embeddable
public class SensorId implements Serializable {
    
    private TSID value;

    protected SensorId() {}

    public SensorId(TSID value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public SensorId(Long value) {
        Objects.requireNonNull(value);
        this.value = TSID.from(value);
    }

    public SensorId(String value) {
        Objects.requireNonNull(value);
        this.value = TSID.from(value);
    }

    public TSID getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
