package io.github.ctlove0523.amqp;

import io.github.ctlove0523.common.push.dto.IotDeviceMessage;
import io.github.ctlove0523.common.push.handlers.IotDeviceMessageHandler;
import io.github.ctlove0523.commons.serialization.JacksonUtil;

public class TestIotDeviceMessageHandler implements IotDeviceMessageHandler {
	@Override
	public void handle(IotDeviceMessage message) {
		System.out.println("message handler begin to handle message");
		System.out.println("data = " + JacksonUtil.object2Json(message));
	}
}
