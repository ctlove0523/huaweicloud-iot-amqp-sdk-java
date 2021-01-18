package io.github.ctlove0523.push.amqp;

import java.util.ArrayList;
import java.util.List;

import io.github.ctlove0523.common.push.IotMessageDispatcher;

public class IotAmqpClientOptions {
	private String accessKey;
	private String accessCode;
	private String host;
	private String virtualHost;
	private int port;
	private int idleTimeout;
	private List<String> queueNames = new ArrayList<>();
	private IotMessageDispatcher dispatcher;
	private boolean ssl;

	public String getAccessKey() {
		return accessKey;
	}

	public IotAmqpClientOptions setAccessKey(String accessKey) {
		this.accessKey = accessKey;
		return this;
	}


	public String getAccessCode() {
		return accessCode;
	}

	public IotAmqpClientOptions setAccessCode(String accessCode) {
		this.accessCode = accessCode;
		return this;
	}

	public String getHost() {
		return host;
	}

	public IotAmqpClientOptions setHost(String host) {
		this.host = host;
		return this;
	}

	public String getVirtualHost() {
		return virtualHost;
	}

	public IotAmqpClientOptions setVirtualHost(String virtualHost) {
		this.virtualHost = virtualHost;
		return this;
	}

	public int getPort() {
		return port;
	}

	public IotAmqpClientOptions setPort(int port) {
		this.port = port;
		return this;
	}

	public int getIdleTimeout() {
		return idleTimeout;
	}

	public IotAmqpClientOptions setIdleTimeout(int idleTimeout) {
		this.idleTimeout = idleTimeout;
		return this;
	}

	public List<String> getQueueNames() {
		return queueNames;
	}

	public IotAmqpClientOptions setQueueNames(List<String> queueNames) {
		this.queueNames = queueNames;
		return this;
	}

	public IotMessageDispatcher getDispatcher() {
		return dispatcher;
	}

	public IotAmqpClientOptions setDispatcher(IotMessageDispatcher dispatcher) {
		this.dispatcher = dispatcher;
		return this;
	}

	public boolean isSsl() {
		return ssl;
	}

	public IotAmqpClientOptions setSsl(boolean ssl) {
		this.ssl = ssl;
		return this;
	}
}
