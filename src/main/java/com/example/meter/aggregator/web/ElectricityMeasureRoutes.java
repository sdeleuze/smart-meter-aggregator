package com.example.meter.aggregator.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ElectricityMeasureRoutes {

	private final ElectricityMeasureHandler handler;

	public ElectricityMeasureRoutes(ElectricityMeasureHandler handler) {
		this.handler = handler;
	}

	@Bean
	public RouterFunction<ServerResponse> electricityMeasureRouter() {
		return route(GET("/electricity/firehose"), this.handler::firehose)
				.andRoute(GET("/electricity/zones/{id}"), this.handler::forZone);
	}
}
