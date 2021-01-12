package io.github.ctlove0523.amqp;

import java.util.concurrent.ExecutorService;

import io.github.ctlove0523.amqp.handlers.IotDeviceCreatedHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceDeletedHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceMessageHandler;

public interface HandlerContainer {

	void addDeviceMessageReportedHandler(IotDeviceMessageHandler handler);

	void addDeviceMessageReportedHandler(IotDeviceMessageHandler handler, ExecutorService executor);

	void addDeviceDeletedHandler(IotDeviceDeletedHandler handler);

	void addDeviceCreatedHandler(IotDeviceCreatedHandler handler);
}
