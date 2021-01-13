package io.github.ctlove0523.amqp.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DeletedProduct {
	@JsonProperty("product_id")
	private String productId;

	private String name;

	@JsonProperty("app_id")
	private String appId;

}

