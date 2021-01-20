package io.github.ctlove0523.push.http;


import reactor.core.publisher.Mono;

public interface IotHttpServer {

	static IotHttpServer createIotHttpServer(IotHttpServerOptions options) {
		return new DefaultIotHttpServer(options);
	}

	Mono<Boolean> start();

	Mono<Boolean> shutdown();
}
