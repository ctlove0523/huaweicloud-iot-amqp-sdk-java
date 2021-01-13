package io.github.ctlove0523.amqp.dto.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DevicePropertyV5 {
    @JsonProperty("service_id")
	private String serviceId;

	private String properties;

    @JsonProperty("event_time")
	private String eventTime;

}

