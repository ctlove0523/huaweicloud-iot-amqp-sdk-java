package io.github.ctlove0523.amqp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseNotifyData {
	private String resource;

	private String event;

	@JsonProperty("event_time")
	private String eventTime;

	@JsonProperty("notify_data")
	private Object notifyData;


}
