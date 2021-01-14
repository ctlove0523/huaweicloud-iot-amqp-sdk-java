package io.github.ctlove0523.amqp;

import io.github.ctlove0523.amqp.handlers.IotBatchTaskUpdateHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceCommandStatusUpdateHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceCreatedHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceDeletedHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceMessageHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceMessageStatusUpdateHandler;
import io.github.ctlove0523.amqp.handlers.IotDevicePropertyReportHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceStatusUpdateHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceUpdateHandler;
import io.github.ctlove0523.amqp.handlers.IotProductCreatedHandler;
import io.github.ctlove0523.amqp.handlers.IotProductDeletedHandler;
import io.github.ctlove0523.amqp.handlers.IotProductUpdateHandler;
import io.vertx.amqp.AmqpMessage;

public interface IotMessageDispatcher {

	void dispatch(AmqpMessage message);

	IotMessageDispatcher addIotBatchTaskUpdateHandler(IotBatchTaskUpdateHandler handler);

	IotMessageDispatcher addIotDeviceCommandStatusUpdateHandler(IotDeviceCommandStatusUpdateHandler handler);

	IotMessageDispatcher addIotDeviceCreatedHandler(IotDeviceCreatedHandler handler);

	IotMessageDispatcher addIotDeviceDeletedHandler(IotDeviceDeletedHandler handler);

	IotMessageDispatcher addIotDeviceMessageHandler(IotDeviceMessageHandler handler);

	IotMessageDispatcher addIotDeviceMessageStatusUpdateHandler(IotDeviceMessageStatusUpdateHandler handler);

	IotMessageDispatcher addIotDevicePropertyReportHandler(IotDevicePropertyReportHandler handler);

	IotMessageDispatcher addIotDeviceStatusUpdateHandler(IotDeviceStatusUpdateHandler handler);

	IotMessageDispatcher addIotDeviceUpdateHandler(IotDeviceUpdateHandler handler);

	IotMessageDispatcher addIotProductCreatedHandler(IotProductCreatedHandler handler);

	IotMessageDispatcher addIotProductDeletedHandler(IotProductDeletedHandler handler);

	IotMessageDispatcher testIotProductUpdateHandler(IotProductUpdateHandler handler);
}
