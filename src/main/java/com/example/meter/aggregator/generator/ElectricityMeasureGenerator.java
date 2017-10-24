package com.example.meter.aggregator.generator;

import java.util.HashMap;
import java.util.Map;

import com.example.meter.aggregator.domain.ElectricityMeasure;
import reactor.core.publisher.Flux;

import org.springframework.stereotype.Component;

@Component
public class ElectricityMeasureGenerator {

	private final Map<String, ZoneInfo> zones;

	public ElectricityMeasureGenerator(ElectricityMeasureGeneratorProperties properties) {
		this.zones = extractConfiguration(properties);
	}

	public Map<String, Flux<ElectricityMeasure>> generateSensorData() {
		Map<String, Flux<ElectricityMeasure>> content = new HashMap<>();
		this.zones.forEach((id, zone) -> content.put(id, new ElectricityMeasureFluxGenerator(zone).sensorData()));
		return content;
	}


	private static Map<String, ZoneInfo> extractConfiguration(
			ElectricityMeasureGeneratorProperties properties) {
		Map<String, ZoneInfo> zones = new HashMap<>();
		properties.getZones().forEach((id, zone) -> zones.put(id,
				new ZoneInfo(id, zone.getDevicesCount(), zone.getPowerLow(), zone.getPowerHigh())));
		return zones;
	}
}
