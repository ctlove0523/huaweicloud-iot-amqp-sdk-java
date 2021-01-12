package io.github.ctlove0523.amqp.dto.device;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ctlove0523.amqp.dto.BaseNotifyData;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IotDeviceCreatedData extends BaseNotifyData {

	@JsonProperty("notify_data")
	private DeviceUpdateNotifyData notifyData;
}
