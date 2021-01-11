package io.github.ctlove0523.amqp;

import io.github.ctlove0523.amqp.dto.device.IotDeviceDeleteData;
import io.github.ctlove0523.amqp.handlers.IotDeviceDeletedHandler;
import io.github.ctlove0523.commons.serialization.JacksonUtil;

public class TestIotDeviceDeletedHandler implements IotDeviceDeletedHandler {
	@Override
	public void handle(IotDeviceDeleteData data) {
		System.out.println("begin to handle device delte");
		System.out.println(JacksonUtil.object2Json(data));
	}
}
