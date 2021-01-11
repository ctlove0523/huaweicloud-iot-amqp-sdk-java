package io.github.ctlove0523.amqp;

import io.vertx.amqp.AmqpMessage;

public interface AmqpMessageDispatcher extends HandlerContainer {

	void dispatch(AmqpMessage message);
}
