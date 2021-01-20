package io.github.ctlove0523.push.amqp;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import io.github.ctlove0523.common.push.DefaultIotPushMessage;
import io.github.ctlove0523.common.push.IotMessageDispatcher;
import io.vertx.amqp.AmqpClient;
import io.vertx.amqp.AmqpClientOptions;
import io.vertx.amqp.AmqpConnection;
import io.vertx.amqp.AmqpMessage;
import io.vertx.amqp.AmqpReceiver;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

class DefaultIotAmqpClientImpl implements IotAmqpClient {
	private static final Logger log = LoggerFactory.getLogger(DefaultIotAmqpClientImpl.class);

	private final IotAmqpClientOptions options;
	private AmqpClient amqpClient;
	private AmqpConnection amqpConnection;

	private IotMessageDispatcher dispatcher;

	public DefaultIotAmqpClientImpl(IotAmqpClientOptions options) {
		this(options, options.getDispatcher());
	}

	public DefaultIotAmqpClientImpl(IotAmqpClientOptions options, IotMessageDispatcher dispatcher) {
		this.options = options;
		this.dispatcher = dispatcher;
	}

	@Override
	public Mono<Boolean> connect() {
		String timesStamp = Long.toString(System.currentTimeMillis());
		String userName = String.format("accessKey=%s|timestamp=%s|", options.getAccessKey(), timesStamp);
		AmqpClientOptions clientOptions = new AmqpClientOptions()
				.setHost(options.getHost())
				.setPort(options.getPort())
				.setUsername(userName)
				.setPassword(options.getAccessCode())
				.setVirtualHost(options.getVirtualHost())
				.setIdleTimeout(options.getIdleTimeout())
				.setIdleTimeout(600000)
				.setSsl(options.isSsl())
				.setTrustAll(true)
				.setTcpNoDelay(true)
				.setTcpKeepAlive(true);
		if (options.isSsl()) {
			clientOptions.addEnabledSaslMechanism("PLAIN");
		}

		AmqpClient client = AmqpClient.create(clientOptions);
		this.amqpClient = client;

		CompletableFuture<Boolean> connectFuture = new CompletableFuture<>();
		client.connect(new Handler<AsyncResult<AmqpConnection>>() {
			@Override
			public void handle(AsyncResult<AmqpConnection> asyncResult) {
				if (asyncResult.succeeded()) {
					log.info("connect to remote server success");
					amqpConnection = asyncResult.result();
					createReceivers();
					connectFuture.complete(true);
				}
				else {
					log.info("connect to remote server error: ", asyncResult.cause());
					connectFuture.completeExceptionally(asyncResult.cause());
				}
			}
		});

		return Mono.fromFuture(connectFuture);
	}

	@Override
	public Mono<Boolean> disConnect() {
		CompletableFuture<Boolean> connectionCloseFuture = new CompletableFuture<>();
		if (amqpConnection != null) {
			amqpConnection.close(new Handler<AsyncResult<Void>>() {
				@Override
				public void handle(AsyncResult<Void> voidAsyncResult) {
					if (voidAsyncResult.succeeded()) {
						log.info("amqp connection close success");
						connectionCloseFuture.complete(true);
					}
					else {
						log.warn("amqp connection close failed: ", voidAsyncResult.cause());
						connectionCloseFuture.completeExceptionally(voidAsyncResult.cause());
					}
				}
			});
		}
		else {
			connectionCloseFuture.complete(true);
		}

		CompletableFuture<Boolean> clientCloseFuture = new CompletableFuture<>();
		if (amqpClient != null) {
			amqpClient.close(new Handler<AsyncResult<Void>>() {
				@Override
				public void handle(AsyncResult<Void> voidAsyncResult) {
					if (voidAsyncResult.succeeded()) {
						log.info("amqp client close success");
						clientCloseFuture.complete(true);
					}
					else {
						log.warn("amqp client close failed: ", voidAsyncResult.cause());
						clientCloseFuture.completeExceptionally(voidAsyncResult.cause());
					}
				}
			});
		}
		else {
			clientCloseFuture.complete(true);
		}
		return Mono.fromFuture(connectionCloseFuture.thenCombine(clientCloseFuture, new BiFunction<Boolean, Boolean, Boolean>() {
			@Override
			public Boolean apply(Boolean aBoolean, Boolean aBoolean2) {
				return aBoolean && aBoolean2;
			}
		}));

	}

	private void createReceivers() {
		options.getQueueNames().forEach(new Consumer<String>() {
			@Override
			public void accept(String s) {
				amqpConnection.createReceiver(s, new Handler<AsyncResult<AmqpReceiver>>() {
					@Override
					public void handle(AsyncResult<AmqpReceiver> receiverAsyncResult) {
						if (receiverAsyncResult.succeeded()) {
							log.info("create receiver for {} queue success", s);
							receiverAsyncResult.result().handler(new Handler<AmqpMessage>() {
								@Override
								public void handle(AmqpMessage amqpMessage) {
									dispatcher.dispatch(new DefaultIotPushMessage(amqpMessage.bodyAsString()));

								}
							});
						}
						else {
							log.warn("create receiver for {} queue failed: ", s, receiverAsyncResult.cause());
						}
					}
				});
			}
		});
	}
}
