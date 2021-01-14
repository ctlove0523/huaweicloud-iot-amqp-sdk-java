package io.github.ctlove0523.amqp.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DeviceCommandStatusUpdate {
	private Object result;

	@JsonProperty("created_time")
	private String createdTime;

	@JsonProperty("sent_time")
	private String sentTime;

	@JsonProperty("command_id")
	private String commandId;

	@JsonProperty("delivered_time")
	private String deliveredTime;

	@JsonProperty("response_time")
	private String responseTime;

	private String status;

}

