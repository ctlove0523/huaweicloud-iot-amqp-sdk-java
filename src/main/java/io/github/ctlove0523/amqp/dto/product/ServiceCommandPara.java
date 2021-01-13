package io.github.ctlove0523.amqp.dto.product;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ServiceCommandPara {
	private String unit;

	private String min;

	private String max;

	@JsonProperty("para_name")
	private String paraName;

	@JsonProperty("data_type")
	private String dataType;

	private String description;

	private double step;

	private boolean required;

	@JsonProperty("max_length")
	private int maxLength;

	@JsonProperty("enum_list")
	private List<String> enumList;

}

