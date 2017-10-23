package com.example.meter.aggregator.generator;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.example.meter.aggregator.domain.ElectricityMeasure;

public class ElectricityMeasureGenerator {

	private final Random random = new Random();

	private final Map<String, Zone> zones;

	private final List<String> zoneIds;

	public ElectricityMeasureGenerator(Map<String, Zone> zones) {
		this.zones = Collections.unmodifiableMap(new LinkedHashMap<>(zones));
		this.zoneIds = new ArrayList<>(this.zones.keySet());
	}

	public ElectricityMeasure generateMeasure() {
		String zoneId = randomZoneId();
		long timestamp = generateTimestamp();
		return new ElectricityMeasure(UUID.randomUUID().toString(), zoneId, timestamp,
				randomPower(zoneId));
	}

	private String randomZoneId() {
		return this.zoneIds.get(this.random.nextInt(this.zoneIds.size()));
	}

	private long generateTimestamp() {
		LocalDateTime now = LocalDateTime.now();
		int mod = now.getSecond() % 15;
		return now.withSecond(now.getSecond() + (mod < 8 ? -mod : (15 - mod)))
				.withNano(0)
				.toInstant(ZoneOffset.UTC).toEpochMilli();
	}

	private float randomPower(String zoneId) {
		Zone zone = zones.get(zoneId);
		float delta = zone.getPowerHigh() - zone.getPowerLow();
		return zone.getPowerLow() + (this.random.nextFloat() * delta);
	}

}
