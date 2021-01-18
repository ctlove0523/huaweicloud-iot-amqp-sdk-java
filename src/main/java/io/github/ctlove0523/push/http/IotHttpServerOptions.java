package io.github.ctlove0523.push.http;

import io.github.ctlove0523.common.push.IotMessageDispatcher;

public class IotHttpServerOptions {
	private int port;
	private String host;
	private String url;
	private IotMessageDispatcher dispatcher;


	public int getPort() {
		return port;
	}

	public IotHttpServerOptions setPort(int port) {
		this.port = port;
		return this;
	}

	public String getHost() {
		return host;
	}

	public IotHttpServerOptions setHost(String host) {
		this.host = host;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public IotHttpServerOptions setUrl(String url) {
		this.url = url;
		return this;
	}

	public IotMessageDispatcher getDispatcher() {
		return dispatcher;
	}

	public IotHttpServerOptions setDispatcher(IotMessageDispatcher dispatcher) {
		this.dispatcher = dispatcher;
		return this;
	}
}
