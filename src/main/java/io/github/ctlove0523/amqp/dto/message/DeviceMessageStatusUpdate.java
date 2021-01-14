package io.github.ctlove0523.amqp.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DeviceMessageStatusUpdate {

	private String name;

	private String topic;

    @JsonProperty("message_id")
	private String messageId;

	private String status;

	private String timestamp;



}

