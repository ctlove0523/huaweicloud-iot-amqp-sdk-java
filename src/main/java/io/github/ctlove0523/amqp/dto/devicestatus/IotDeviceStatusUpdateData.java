package io.github.ctlove0523.amqp.dto.devicestatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ctlove0523.amqp.dto.BaseNotifyData;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class IotDeviceStatusUpdateData extends BaseNotifyData {
	@JsonProperty("notify_data")
	private DeviceStatusUpdateNotifyDataV5 notifyData;

}

