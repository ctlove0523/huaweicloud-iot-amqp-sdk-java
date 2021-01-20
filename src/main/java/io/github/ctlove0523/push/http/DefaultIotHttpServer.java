package io.github.ctlove0523.push.http;

import java.util.concurrent.CompletableFuture;

import io.github.ctlove0523.common.push.DefaultIotPushMessage;
import io.github.ctlove0523.common.push.IotMessageDispatcher;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import reactor.core.publisher.Mono;

public class DefaultIotHttpServer implements IotHttpServer {

	private HttpServer httpServer;
	private IotHttpServerOptions options;
	private IotMessageDispatcher dispatcher;

	DefaultIotHttpServer(IotHttpServerOptions options) {
		this.options = options;
		this.dispatcher = options.getDispatcher();

		HttpServerOptions serverOptions = new HttpServerOptions();
		serverOptions.setPort(options.getPort());
		serverOptions.setHost(options.getHost());
		this.httpServer = Vertx.vertx().createHttpServer(serverOptions);
		if (options.getUrl() != null && !options.getUrl().isEmpty()) {
			Router router = Router.router(Vertx.vertx());
			router.route(HttpMethod.POST, options.getUrl())
					.handler(BodyHandler.create())
					.handler(new Handler<RoutingContext>() {
						@Override
						public void handle(RoutingContext event) {
							String requestBody = event.getBodyAsJson().toString();
							if (dispatcher != null) {
								dispatcher.dispatch(new DefaultIotPushMessage(requestBody));
							}

							event.response().end();
						}
					});

			httpServer.requestHandler(router);
		}

	}

	@Override
	public Mono<Boolean> start() {
		CompletableFuture<Boolean> result = new CompletableFuture<>();
		httpServer.listen(options.getPort(), options.getHost(), new Handler<AsyncResult<HttpServer>>() {
			@Override
			public void handle(AsyncResult<HttpServer> event) {
				if (event.succeeded()) {
					result.complete(true);
				}
				else {
					result.completeExceptionally(event.cause());
				}
			}
		});

		return Mono.fromFuture(result);

	}

	@Override
	public Mono<Boolean> shutdown() {
		if (httpServer == null) {
			return Mono.just(true);
		}

		CompletableFuture<Boolean> result = new CompletableFuture<>();
		httpServer.close(new Handler<AsyncResult<Void>>() {
			@Override
			public void handle(AsyncResult<Void> event) {
				if (event.succeeded()) {
					result.complete(true);
				}
				else {
					result.completeExceptionally(event.cause());
				}
			}
		});

		return Mono.fromFuture(result);
	}
}
