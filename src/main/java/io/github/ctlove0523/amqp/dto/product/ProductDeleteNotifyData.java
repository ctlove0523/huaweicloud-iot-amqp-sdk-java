package io.github.ctlove0523.amqp.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDeleteNotifyData {
	private DeletedProduct body;
}
