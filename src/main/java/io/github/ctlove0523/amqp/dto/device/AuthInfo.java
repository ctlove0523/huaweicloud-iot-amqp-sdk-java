package io.github.ctlove0523.amqp.dto.device;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthInfo {
	@JsonProperty("auth_type")
	private String authType;

	@JsonProperty("secure_access")
	private boolean secureAccess;

	private int timeout;

}
