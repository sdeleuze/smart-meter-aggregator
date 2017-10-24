package com.example.meter.aggregator.web;

import java.util.Map;

import com.example.meter.aggregator.domain.ElectricityMeasure;
import com.example.meter.aggregator.generator.ElectricityMeasureGenerator;
import reactor.core.publisher.Flux;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElectricityMeasureController {

	private final Map<String, Flux<ElectricityMeasure>> content;

	private final Flux<ElectricityMeasure> firehose;

	public ElectricityMeasureController(ElectricityMeasureGenerator generator) {
		this.content = generator.generateSensorData();
		this.firehose = Flux.merge(content.values());
	}

	@GetMapping(path = "/electricity/firehose", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<ElectricityMeasure> firehose() {
		return this.firehose;
	}

	@GetMapping(path = "/electricity/zones/{id}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<ElectricityMeasure> measuresFor(@PathVariable String id) {
		return this.content.get(id);
	}

}
