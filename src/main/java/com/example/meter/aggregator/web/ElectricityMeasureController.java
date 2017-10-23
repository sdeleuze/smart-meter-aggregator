package com.example.meter.aggregator.web;

import java.time.Duration;

import com.example.meter.aggregator.domain.ElectricityMeasure;
import com.example.meter.aggregator.generator.ElectricityMeasureGenerator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElectricityMeasureController {


	private final ElectricityMeasureGenerator generator;

	private final Flux<ElectricityMeasure> firehose;

	public ElectricityMeasureController(ElectricityMeasureGenerator generator) {
		this.generator = generator;
		this.firehose = createFirehose(generator);
	}

	@GetMapping(path = "/electricity/firehose", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<ElectricityMeasure> firehose() {
		return this.firehose;
	}


	private static Flux<ElectricityMeasure> createFirehose(ElectricityMeasureGenerator generator) {
		return Flux.generate(sink -> sink.next("tick"))
				.delayUntil(i -> Mono.delay(Duration.ofMillis(50)))
				.map(i -> generator.generateMeasure())
				.share();
	}
}
