package io.github.ctlove0523.amqp;

import io.github.ctlove0523.common.push.dto.device.IotDeviceCreatedData;
import io.github.ctlove0523.common.push.handlers.IotDeviceCreatedHandler;
import io.github.ctlove0523.commons.serialization.JacksonUtil;

public class TestIotDeviceCreatedHandler implements IotDeviceCreatedHandler {
	@Override
	public void handle(IotDeviceCreatedData data) {
		System.out.println("begin to handler device created");
		System.out.println(JacksonUtil.object2Json(data));
	}
}
