package io.github.ctlove0523.amqp.dto.product;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
	@JsonProperty("app_name")
	private String appName;

	@JsonProperty("protocol_type")
	private String protocolType;

	@JsonProperty("data_format")
	private String dataFormat;

	@JsonProperty("create_time")
	private String createTime;

	@JsonProperty("product_id")
	private String productId;

	private String name;

	private String description;

	@JsonProperty("device_type")
	private String deviceType;

	private String industry;

	@JsonProperty("manufacturer_name")
	private String manufacturerName;

	@JsonProperty("app_id")
	private String appId;

	@JsonProperty("service_capabilities")
	private List<ServiceCapabilities> serviceCapabilities;

}
