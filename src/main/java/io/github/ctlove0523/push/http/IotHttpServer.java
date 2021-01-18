package io.github.ctlove0523.push.http;


import io.github.ctlove0523.common.push.IotMessageDispatcher;
import reactor.core.publisher.Mono;

public interface IotHttpServer {

	Mono<IotHttpServer> listen(int port, String host);

	Mono<IotHttpServer> listen(int port);

	Mono<IotHttpServer> url(String url);

	Mono<IotHttpServer> dispatcher(IotMessageDispatcher dispatcher);
}
