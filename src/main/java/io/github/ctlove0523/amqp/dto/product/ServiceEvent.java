package io.github.ctlove0523.amqp.dto.product;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ServiceEvent {
	@JsonProperty("event_type")
	private String eventType;

	private List<ServiceCommandPara> paras;

}

