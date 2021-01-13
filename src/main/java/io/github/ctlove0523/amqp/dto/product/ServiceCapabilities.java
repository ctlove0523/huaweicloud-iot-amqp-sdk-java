package io.github.ctlove0523.amqp.dto.product;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceCapabilities {
	@JsonProperty("service_type")
	private String serviceType;

	@JsonProperty("service_id")
	private String serviceId;

	private String description;

	private String option;

	private List<ServiceProperty> properties;

	private List<ServiceCommand> commands;

	private List<ServiceEvent> events;

}

