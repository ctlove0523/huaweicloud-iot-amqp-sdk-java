package io.github.ctlove0523.amqp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.ctlove0523.amqp.dto.BaseNotifyData;
import io.github.ctlove0523.amqp.dto.IotDeviceMessage;
import io.github.ctlove0523.amqp.dto.device.IotDeviceDeleteData;
import io.github.ctlove0523.amqp.handlers.IotDeviceDeletedHandler;
import io.github.ctlove0523.amqp.handlers.IotDeviceMessageHandler;
import io.github.ctlove0523.amqp.handlers.IotHandler;
import io.github.ctlove0523.commons.serialization.JacksonUtil;
import io.vertx.amqp.AmqpMessage;
import io.vertx.core.parsetools.JsonParser;

public class DefaultAmqpMessageDispatcher implements AmqpMessageDispatcher {
	private static final Map<String, Class<?>> TYPE_MAP = new HashMap<>();
	private final Map<String, List<IotHandler<?>>> handlers = new HashMap<>();

	static {
		TYPE_MAP.put("device.message.report", IotDeviceMessage.class);
		TYPE_MAP.put("device.delete", IotDeviceDeleteData.class);
	}

	@Override
	public void dispatch(AmqpMessage message) {
		System.out.println(message.bodyAsString());
		ObjectNode objectNode = JacksonUtil.json2Object(message.bodyAsString(), ObjectNode.class);
		String resource = objectNode.get("resource").asText();
		System.out.println(resource);
		String event = objectNode.get("event").asText();
		String key = resource + "." + event;

		IotDeviceMessage deviceMessage = JacksonUtil.json2Object(message.bodyAsString(), IotDeviceMessage.class);

		System.out.println("key = " + key);
	}

	private void test() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
	}
	public <T> T convertBaseNotifyData(BaseNotifyData data, Class<T> clazz) {
		try {
			Field[] fields = clazz.getDeclaredFields();
			T ob = clazz.newInstance();
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldTypeName = field.getGenericType().toString();
				Class fieldType = getFieldType(fieldTypeName);
				//获取属性名称
				String name = field.getName();
				String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
				String getMethodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);

				System.out.println(methodName);
				System.out.println(getMethodName);
				//设置字段值
				Method methodSet = ob.getClass().getMethod(methodName, fieldType);
				Method methodGet = data.getClass().getMethod(getMethodName);
				methodSet.invoke(ob, methodGet.invoke(data));
			}
			return ob;
		}
		catch (Exception e) {

		}

		return null;

	}

	public static Class getFieldType(String fieldTypeName) {
		Class fieldType = null;
		if (fieldTypeName.equals("class java.lang.String"))
			fieldType = String.class;
		else if (fieldTypeName.equals("class java.lang.Integer"))
			fieldType = Integer.class;
		else if (fieldTypeName.equals("class java.lang.Boolean"))
			fieldType = Boolean.class;
		else if (fieldTypeName.equals("class java.util.Date"))
			fieldType = java.util.Date.class;
		else if (fieldTypeName.equals("class java.lang.Double"))
			fieldType = Double.class;
		else if (fieldTypeName.equals("class java.lang.Long"))
			fieldType = Long.class;
		else
			fieldType = Object.class;
		return fieldType;
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
	public void addDeviceDeltedHandler(IotDeviceDeletedHandler handler) {
		List<IotHandler<?>> handlerList = handlers.get("device.delete");
		if (handlerList == null) {
			handlerList = new ArrayList<>();
		}
		handlerList.add(handler);
		handlers.put("device.delete", handlerList);
	}

}
