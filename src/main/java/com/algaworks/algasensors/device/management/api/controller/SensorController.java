package com.algaworks.algasensors.device.management.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.algaworks.algasensors.device.management.api.model.SensorInput;
import com.algaworks.algasensors.device.management.api.model.SensorOutput;
import com.algaworks.algasensors.device.management.common.IdGenerator;
import com.algaworks.algasensors.device.management.domain.model.Sensor;
import com.algaworks.algasensors.device.management.domain.model.SensorBuilder;
import com.algaworks.algasensors.device.management.domain.model.SensorId;
import com.algaworks.algasensors.device.management.domain.repository.SensorRepository;

import io.hypersistence.tsid.TSID;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorRepository repository;

    public SensorController(SensorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("{sensorId}")
    public SensorOutput getOne(@PathVariable TSID sensorId){
        Sensor sensor = repository.findById(new SensorId(sensorId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        return convertSensorToSensorOutput(sensor);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SensorOutput create(@RequestBody SensorInput input){
        Sensor sensor =  SensorBuilder.sensor()
            .id(new SensorId(IdGenerator.generateTSID()))
            .name(input.name())
            .ip(input.ip())
            .location(input.location())
            .protocol(input.protocol())
            .model(input.model())
            .enabled(false)
        .build();

        sensor = repository.saveAndFlush(sensor);

        return convertSensorToSensorOutput(sensor);
    }

    private SensorOutput convertSensorToSensorOutput(Sensor sensor) {
        return new SensorOutput(
            sensor.getId().getValue(), 
            sensor.getName(), 
            sensor.getIp(), 
            sensor.getLocation(), 
            sensor.getProtocol(), 
            sensor.getModel(), 
            sensor.getEnabled()
        );
    }
}
