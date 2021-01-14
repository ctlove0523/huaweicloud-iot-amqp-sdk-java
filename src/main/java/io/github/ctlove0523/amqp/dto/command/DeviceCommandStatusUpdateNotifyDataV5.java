package io.github.ctlove0523.amqp.dto.command;

import io.github.ctlove0523.amqp.dto.NotifyDataHeader;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DeviceCommandStatusUpdateNotifyDataV5 {
	private NotifyDataHeader header;

	private DeviceCommandStatusUpdate body;

}

