package io.github.ctlove0523.amqp.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ctlove0523.amqp.dto.BaseNotifyData;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class IotDeviceCommandStatusUpdateNotifyDataV5 extends BaseNotifyData {
	@JsonProperty("notify_data")
	private DeviceCommandStatusUpdateNotifyDataV5 notifyData;

}

