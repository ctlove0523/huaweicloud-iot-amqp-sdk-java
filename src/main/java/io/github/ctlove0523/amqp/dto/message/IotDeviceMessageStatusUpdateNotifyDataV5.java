package io.github.ctlove0523.amqp.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ctlove0523.amqp.dto.BaseNotifyData;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class IotDeviceMessageStatusUpdateNotifyDataV5 extends BaseNotifyData {
	@JsonProperty("notify_data")
	private DeviceMessageStatusUpdateNotifyDataV5 notifyData;

}

