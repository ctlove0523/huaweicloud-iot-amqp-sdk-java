package io.github.ctlove0523.push;

import io.github.ctlove0523.common.push.dto.device.IotDeviceDeleteData;
import io.github.ctlove0523.common.push.handlers.IotDeviceDeletedHandler;
import io.github.ctlove0523.commons.serialization.JacksonUtil;

public class TestIotDeviceDeletedHandler implements IotDeviceDeletedHandler {
	@Override
	public void handle(IotDeviceDeleteData data) {
		System.out.println("begin to handle device delete");
		System.out.println(JacksonUtil.object2Json(data));
	}
}
