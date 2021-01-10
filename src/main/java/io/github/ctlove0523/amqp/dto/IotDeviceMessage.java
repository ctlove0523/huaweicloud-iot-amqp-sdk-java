package io.github.ctlove0523.amqp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IotDeviceMessage extends BaseNotifyData {

	@JsonProperty("notify_data")
	private DeviceMessageReportNotifyData notifyData;

}
