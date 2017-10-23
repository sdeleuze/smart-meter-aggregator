package com.example.meter.aggregator;

import com.example.meter.aggregator.generator.ElectricityMeasureGenerator;
import com.example.meter.aggregator.generator.ElectricityMeasureGeneratorProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ElectricityMeasureGeneratorProperties.class)
class GeneratorConfig {

	@Bean
	public ElectricityMeasureGenerator electricityMeasureGenerator(
			ElectricityMeasureGeneratorProperties properties) {
		return new ElectricityMeasureGenerator(properties.getZones());
	}
}
