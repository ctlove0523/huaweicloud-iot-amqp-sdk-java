package io.github.ctlove0523.push.http;

import io.github.ctlove0523.common.push.IotMessageDispatcher;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import reactor.core.publisher.Mono;

public class DefaultIotHttpServer implements IotHttpServer {

	private HttpServer httpServer;
	private IotHttpServerOptions options;

	DefaultIotHttpServer(IotHttpServerOptions options) {
		this.options = options;

		HttpServerOptions serverOptions = new HttpServerOptions();
		serverOptions.setPort(options.getPort());
		serverOptions.setHost(options.getHost());
		this.httpServer = Vertx.vertx().createHttpServer(serverOptions);

	}

	@Override
	public Mono<IotHttpServer> listen(int port, String host) {
		httpServer.listen(port, host);
		return null;
	}

	@Override
	public Mono<IotHttpServer> listen(int port) {
		return null;
	}

	@Override
	public Mono<IotHttpServer> url(String url) {
		return null;
	}

	@Override
	public Mono<IotHttpServer> dispatcher(IotMessageDispatcher dispatcher) {
		return null;
	}
}
