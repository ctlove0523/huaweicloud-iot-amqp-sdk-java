package io.github.ctlove0523.amqp.dto.device;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceUpdateNotifyData {
	private QueryDeviceBase body;
}
