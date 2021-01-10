package io.github.ctlove0523.amqp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotifyDataHeader {
	@JsonProperty("device_id")
	private String deviceId;

	@JsonProperty("product_id")
	private String productId;

	@JsonProperty("app_id")
	private String appId;

	@JsonProperty("gateway_id")
	private String gatewayId;

	@JsonProperty("node_id")
	private String nodeId;

	private List<TagV5DTO> tags;

}
