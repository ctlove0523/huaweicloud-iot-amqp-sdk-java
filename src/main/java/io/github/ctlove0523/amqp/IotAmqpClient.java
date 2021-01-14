package io.github.ctlove0523.amqp;

import reactor.core.publisher.Mono;

public interface IotAmqpClient {

	static IotAmqpClient create(IotAmqpClientOptions options) {
		return new DefaultIotAmqpClientImpl(options);
	}

	Mono<Boolean> connect();

	Mono<Boolean> disConnect();
}
