package io.github.ctlove0523.amqp.dto.device;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.ctlove0523.amqp.dto.TagV5DTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryDeviceBase {
	@JsonProperty("device_id")
	private String deviceId;

	@JsonProperty("create_time")
	private String createTime;

	private String description;

	@JsonProperty("auth_info")
	private AuthInfo authInfo;

	@JsonProperty("product_name")
	private String productName;

	@JsonProperty("gateway_id")
	private String gatewayId;

	@JsonProperty("sw_version")
	private String swVersion;

	@JsonProperty("extension_info")
	private Object extensionInfo;

	@JsonProperty("app_name")
	private String appName;

	@JsonProperty("device_name")
	private String deviceName;

	@JsonProperty("node_type")
	private String nodeType;

	@JsonProperty("product_id")
	private String productId;

	@JsonProperty("app_id")
	private String appId;

	@JsonProperty("fw_version")
	private String fwVersion;

	@JsonProperty("node_id")
	private String nodeId;

	private String status;

	private List<TagV5DTO> tags;

}
