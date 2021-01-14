package io.github.ctlove0523.amqp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.ctlove0523.amqp.dto.IotDeviceMessage;
import io.github.ctlove0523.amqp.dto.command.IotDeviceCommandStatusUpdateNotifyDataV5;
import io.github.ctlove0523.amqp.dto.device.IotDeviceCreatedData;
import io.github.ctlove0523.amqp.dto.device.IotDeviceDeleteData;
import io.github.ctlove0523.amqp.dto.device.IotDeviceUpdateData;
import io.github.ctlove0523.amqp.dto.devicestatus.IotDeviceStatusUpdateData;
import io.github.ctlove0523.amqp.dto.message.IotDeviceMessageStatusUpdateNotifyDataV5;
import io.github.ctlove0523.amqp.dto.product.IotProductCreateData;
import io.github.ctlove0523.amqp.dto.product.IotProductDeletedData;
import io.github.ctlove0523.amqp.dto.product.IotProductUpdateData;
import io.github.ctlove0523.amqp.dto.property.IotDevicePropertyReportData;
import io.github.ctlove0523.amqp.dto.tasks.IotBatchTaskUpdateNotifyData;
import io.github.ctlove0523.amqp.handlers.IotBatchTaskUpdateHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceCommandStatusUpdateHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceCreatedHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceDeletedHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceMessageHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceMessageStatusUpdateHandler;
import io.github.ctlove0523.amqp.handlers.IotDevicePropertyReportHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceStatusUpdateHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceUpdateHandler;
import io.github.ctlove0523.amqp.handlers.IotHandler;
import io.github.ctlove0523.amqp.handlers.IotProductCreatedHandler;
import io.github.ctlove0523.amqp.handlers.IotProductDeletedHandler;
import io.github.ctlove0523.amqp.handlers.IotProductUpdateHandler;
import io.github.ctlove0523.commons.serialization.JacksonUtil;
import io.vertx.amqp.AmqpMessage;

public class DefaultIotMessageDispatcher implements IotMessageDispatcher {
	private static final Map<String, Class<?>> TYPE_MAP = new HashMap<>();
	private final Map<String, List<IotHandler<?>>> handlers = new HashMap<>();

	static {
		TYPE_MAP.put("device.delete", IotDeviceDeleteData.class);
		TYPE_MAP.put("device.create", IotDeviceCreatedData.class);
		TYPE_MAP.put("device.update", IotDeviceUpdateData.class);

		TYPE_MAP.put("product.create", IotProductCreateData.class);
		TYPE_MAP.put("product.update", IotProductUpdateData.class);
		TYPE_MAP.put("product.delete", IotProductDeletedData.class);

		TYPE_MAP.put("device.message.report", IotDeviceMessage.class);

		TYPE_MAP.put("device.status.update", IotDeviceStatusUpdateData.class);

		TYPE_MAP.put("device.property.report", IotDevicePropertyReportData.class);
		TYPE_MAP.put("batchtask.update", IotBatchTaskUpdateNotifyData.class);
		TYPE_MAP.put("device.message.status.update", IotDeviceMessageStatusUpdateNotifyDataV5.class);
		TYPE_MAP.put("device.commmad.status.update", IotDeviceCommandStatusUpdateNotifyDataV5.class);
	}

	@Override
	public void dispatch(AmqpMessage message) {
		ObjectNode objectNode = JacksonUtil.json2Object(message.bodyAsString(), ObjectNode.class);
		String resource = objectNode.get("resource").asText();
		String event = objectNode.get("event").asText();
		String key = resource + "." + event;

		handlers.get(key).forEach((Consumer<IotHandler>) iotHandler -> iotHandler.handle(JacksonUtil.json2Object(message.bodyAsString(), TYPE_MAP.get(key))));

	}

	@Override
	public IotMessageDispatcher addIotBatchTaskUpdateHandler(IotBatchTaskUpdateHandler handler) {
		innerAddHandler(handler, "batchtask.update");
		return this;
	}

	@Override
	public IotMessageDispatcher addIotDeviceCommandStatusUpdateHandler(IotDeviceCommandStatusUpdateHandler handler) {
		innerAddHandler(handler, "device.commmad.status.update");
		return this;
	}

	@Override
	public IotMessageDispatcher addIotDeviceMessageHandler(IotDeviceMessageHandler handler) {
		innerAddHandler(handler, "device.message.report");
		return this;
	}

	@Override
	public IotMessageDispatcher addIotDeviceMessageStatusUpdateHandler(IotDeviceMessageStatusUpdateHandler handler) {
		innerAddHandler(handler, "device.message.status.update");
		return this;
	}

	@Override
	public IotMessageDispatcher addIotDevicePropertyReportHandler(IotDevicePropertyReportHandler handler) {
		innerAddHandler(handler, "device.property.report");
		return this;
	}

	@Override
	public IotMessageDispatcher addIotDeviceStatusUpdateHandler(IotDeviceStatusUpdateHandler handler) {
		innerAddHandler(handler, "device.status.update");
		return this;
	}

	@Override
	public IotMessageDispatcher addIotDeviceUpdateHandler(IotDeviceUpdateHandler handler) {
		innerAddHandler(handler, "device.update");
		return this;
	}

	@Override
	public IotMessageDispatcher addIotProductCreatedHandler(IotProductCreatedHandler handler) {
		innerAddHandler(handler, "product.create");
		return this;
	}

	@Override
	public IotMessageDispatcher addIotProductDeletedHandler(IotProductDeletedHandler handler) {
		innerAddHandler(handler, "product.delete");
		return this;
	}

	@Override
	public IotMessageDispatcher testIotProductUpdateHandler(IotProductUpdateHandler handler) {
		innerAddHandler(handler, "product.update");
		return this;
	}


	@Override
	public IotMessageDispatcher addIotDeviceDeletedHandler(IotDeviceDeletedHandler handler) {
		innerAddHandler(handler, "device.delete");
		return this;
	}

	@Override
	public IotMessageDispatcher addIotDeviceCreatedHandler(IotDeviceCreatedHandler handler) {
		innerAddHandler(handler, "device.create");
		return this;
	}

	private void innerAddHandler(IotHandler<?> handler, String key) {
		List<IotHandler<?>> handlerList = handlers.get(key);
		if (handlerList == null) {
			handlerList = new ArrayList<>();
		}
		handlerList.add(handler);
		handlers.put(key, handlerList);
	}

}
