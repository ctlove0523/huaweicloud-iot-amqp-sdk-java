package io.github.ctlove0523.push;

import java.util.UUID;

import io.github.ctlove0523.common.push.dto.device.DeviceUpdateNotifyData;
import io.github.ctlove0523.common.push.dto.device.IotDeviceCreatedData;
import io.github.ctlove0523.common.push.dto.device.QueryDeviceBase;

public class IotPushDataUtil {

	public static IotDeviceCreatedData getIotDeviceCreatedData(String deviceId) {
		QueryDeviceBase queryDeviceBase = new QueryDeviceBase();
		queryDeviceBase.setDescription("test device create");
		queryDeviceBase.setAppId(UUID.randomUUID().toString());
		queryDeviceBase.setAppName("test app");
		queryDeviceBase.setDescription(deviceId);

		DeviceUpdateNotifyData notifyData = new DeviceUpdateNotifyData();
		notifyData.setBody(queryDeviceBase);

		IotDeviceCreatedData data = new IotDeviceCreatedData();
		data.setResource("device");
		data.setEvent("create");
		data.setEventTime("20210117T121212Z");
		data.setNotifyData(notifyData);

		return data;
	}
}
