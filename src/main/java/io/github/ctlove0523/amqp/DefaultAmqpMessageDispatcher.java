package io.github.ctlove0523.amqp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.ctlove0523.amqp.dto.IotDeviceMessage;
import io.github.ctlove0523.amqp.dto.device.IotDeviceCreatedData;
import io.github.ctlove0523.amqp.dto.device.IotDeviceDeleteData;
import io.github.ctlove0523.amqp.handlers.IotDeviceCreatedHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceDeletedHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceMessageHandler;
import io.github.ctlove0523.amqp.handlers.IotHandler;
import io.github.ctlove0523.commons.serialization.JacksonUtil;
import io.vertx.amqp.AmqpMessage;

public class DefaultAmqpMessageDispatcher implements AmqpMessageDispatcher {
	private static final Map<String, Class<?>> TYPE_MAP = new HashMap<>();
	private final Map<String, List<IotHandler<?>>> handlers = new HashMap<>();

	static {
		TYPE_MAP.put("device.message.report", IotDeviceMessage.class);
		TYPE_MAP.put("device.delete", IotDeviceDeleteData.class);
		TYPE_MAP.put("device.create", IotDeviceCreatedData.class);
	}

	@Override
	public void dispatch(AmqpMessage message) {
		ObjectNode objectNode = JacksonUtil.json2Object(message.bodyAsString(), ObjectNode.class);
		String resource = objectNode.get("resource").asText();
		System.out.println(resource);
		String event = objectNode.get("event").asText();
		String key = resource + "." + event;


		handlers.get(key).forEach(new Consumer<IotHandler>() {
			@Override
			public void accept(IotHandler iotHandler) {
				iotHandler.handle(JacksonUtil.json2Object(message.bodyAsString(), TYPE_MAP.get(key)));
			}
		});

	}


	@Override
	public void addDeviceMessageReportedHandler(IotDeviceMessageHandler handler) {
		List<IotHandler<?>> handlerList = handlers.get("device.message.report");
		if (handlerList == null) {
			handlerList = new ArrayList<>();
		}
		handlerList.add(handler);
		handlers.put("device.message.report", handlerList);
	}

	@Override
	public void addDeviceMessageReportedHandler(IotDeviceMessageHandler handler, ExecutorService executor) {

	}

	@Override
	public void addDeviceDeletedHandler(IotDeviceDeletedHandler handler) {
		List<IotHandler<?>> handlerList = handlers.get("device.delete");
		if (handlerList == null) {
			handlerList = new ArrayList<>();
		}
		handlerList.add(handler);
		handlers.put("device.delete", handlerList);
	}

	@Override
	public void addDeviceCreatedHandler(IotDeviceCreatedHandler handler) {
		List<IotHandler<?>> handlerList = handlers.get("device.create");
		if (handlerList == null) {
			handlerList = new ArrayList<>();
		}
		handlerList.add(handler);
		handlers.put("device.create", handlerList);
	}

}
