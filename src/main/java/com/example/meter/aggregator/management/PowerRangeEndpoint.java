package com.example.meter.aggregator.management;

import com.example.meter.aggregator.generator.ElectricityMeasureGenerator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "powerrange")
public class PowerRangeEndpoint {

	private final ElectricityMeasureGenerator electricityMeasureGenerator;

	public PowerRangeEndpoint(ElectricityMeasureGenerator electricityMeasureGenerator) {
		this.electricityMeasureGenerator = electricityMeasureGenerator;
	}

	@WriteOperation
	public void updatePowerRange(@Selector String zoneId,
			@Nullable Float powerLow, @Nullable Float powerHigh) {
		this.electricityMeasureGenerator.updatePowerRangeFor(zoneId, powerLow, powerHigh);
	}

}
