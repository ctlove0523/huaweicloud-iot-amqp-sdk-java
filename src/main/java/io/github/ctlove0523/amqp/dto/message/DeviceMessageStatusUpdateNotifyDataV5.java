package io.github.ctlove0523.amqp.dto.message;

import io.github.ctlove0523.amqp.dto.NotifyDataHeader;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DeviceMessageStatusUpdateNotifyDataV5 {
	private NotifyDataHeader header;

	private DeviceMessageStatusUpdate body;

}

