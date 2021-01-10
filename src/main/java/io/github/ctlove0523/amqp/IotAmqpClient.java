package io.github.ctlove0523.amqp;

import java.util.concurrent.ExecutorService;

import io.github.ctlove0523.amqp.handlers.IotDeviceMessageHandler;
import reactor.core.publisher.Mono;

public interface IotAmqpClient {

	static IotAmqpClient create(IotAmqpClientOptions options) {
		return new DefaultIotAmqpClientImpl(options);
	}

	Mono<Boolean> connect();

	Mono<Boolean> disConnect();

	void addDeviceMessageReportedHandler(IotDeviceMessageHandler handler);

	void addDeviceMessageReportedHandler(IotDeviceMessageHandler handler, ExecutorService executor);

}
