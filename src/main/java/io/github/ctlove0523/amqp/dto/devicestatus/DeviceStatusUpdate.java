package io.github.ctlove0523.amqp.dto.devicestatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DeviceStatusUpdate {
	@JsonProperty("last_online_time")
	private String lastOnlineTime;

	private String status;

}

