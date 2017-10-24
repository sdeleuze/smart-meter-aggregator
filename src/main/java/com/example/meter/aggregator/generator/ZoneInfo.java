package com.example.meter.aggregator.generator;

import java.util.Random;

/**
 *
 * @author Stephane Nicoll
 */
class ZoneInfo {

	private static final Random random = new Random();

	private final String zoneId;

	private int devicesCount;

	private float powerLow;

	private float powerHigh;

	public ZoneInfo(String zoneId, int devicesCount, float powerLow, float powerHigh) {
		this.zoneId = zoneId;
		this.devicesCount = devicesCount;
		this.powerLow = powerLow;
		this.powerHigh = powerHigh;
	}

	public String getZoneId() {
		return this.zoneId;
	}

	public int getDevicesCount() {
		return this.devicesCount;
	}

	public float randomPower() {
		float delta = this.powerHigh - this.powerLow;
		return this.powerLow + (random.nextFloat() * delta);
	}
}
