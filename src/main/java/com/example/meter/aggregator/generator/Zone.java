package com.example.meter.aggregator.generator;

/**
 *
 * @author Stephane Nicoll
 */
public class Zone {

	private int devicesCount = 50;

	private float powerLow = 2000;

	private float powerHigh = 4000;

	public int getDevicesCount() {
		return this.devicesCount;
	}

	public void setDevicesCount(int devicesCount) {
		this.devicesCount = devicesCount;
	}

	public float getPowerLow() {
		return this.powerLow;
	}

	public void setPowerLow(float powerLow) {
		this.powerLow = powerLow;
	}

	public float getPowerHigh() {
		return this.powerHigh;
	}

	public void setPowerHigh(float powerHigh) {
		this.powerHigh = powerHigh;
	}

}
