package io.github.ctlove0523.amqp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceMessageReport {
	private String topic;
	private Object content;
}
