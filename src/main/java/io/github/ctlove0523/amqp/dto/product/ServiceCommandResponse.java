package io.github.ctlove0523.amqp.dto.product;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ServiceCommandResponse {
	@JsonProperty("response_name")
	private String responseName;

	private List<ServiceCommandPara> paras;

}

