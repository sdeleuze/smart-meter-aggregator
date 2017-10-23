package com.example.meter.aggregator.generator;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("meter.aggregator.generator")
public class ElectricityMeasureGeneratorProperties {

	private final Map<String, Zone> zones = new LinkedHashMap<>();

	public Map<String, Zone> getZones() {
		return this.zones;
	}

}
