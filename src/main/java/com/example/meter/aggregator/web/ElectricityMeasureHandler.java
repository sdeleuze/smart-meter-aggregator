package com.example.meter.aggregator.web;

import java.util.Map;

import com.example.meter.aggregator.domain.ElectricityMeasure;
import com.example.meter.aggregator.generator.ElectricityMeasureGenerator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
class ElectricityMeasureHandler {

	private final Map<String, Flux<ElectricityMeasure>> content;

	private final Flux<ElectricityMeasure> firehose;

	ElectricityMeasureHandler(@NonNull ElectricityMeasureGenerator generator) {
		this.content = generator.generateSensorData();
		this.firehose = Flux.merge(content.values()).share();
	}

	@NonNull
	public Mono<ServerResponse> firehose(@NonNull ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(this.firehose, ElectricityMeasure.class);
	}

    @NonNull
	public Mono<ServerResponse> forZone(@NonNull ServerRequest request) {
		String id = request.pathVariable("id");
		return ServerResponse.ok().contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(this.content.get(id), ElectricityMeasure.class);
	}

}
