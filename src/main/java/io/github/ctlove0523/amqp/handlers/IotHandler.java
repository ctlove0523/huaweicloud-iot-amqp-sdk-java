package io.github.ctlove0523.amqp.handlers;

public interface IotHandler<T> {

	void handle(T data);
}
