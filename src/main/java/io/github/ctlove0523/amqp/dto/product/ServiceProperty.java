package io.github.ctlove0523.amqp.dto.product;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ServiceProperty {
	private String unit;

	private String min;

	private String method;

	private String max;

	@JsonProperty("data_type")
	private String dataType;

	private String description;

	private double step;

	@JsonProperty("default_value")
	private Object defaultValue;

	private boolean required;

	@JsonProperty("property_name")
	private String propertyName;

	@JsonProperty("max_length")
	private int maxLength;

	@JsonProperty("enum_list")
	private List<String> enumList;

}

