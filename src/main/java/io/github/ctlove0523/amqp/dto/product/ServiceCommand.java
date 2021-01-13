package io.github.ctlove0523.amqp.dto.product;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ServiceCommand {
	@JsonProperty("command_name")
	private String commandName;

	private List<ServiceCommandResponse> responses;

	private List<ServiceCommandPara> paras;

}

