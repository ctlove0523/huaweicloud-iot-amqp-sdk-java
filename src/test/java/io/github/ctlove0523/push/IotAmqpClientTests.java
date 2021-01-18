package io.github.ctlove0523.push;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import io.github.ctlove0523.common.push.DefaultIotMessageDispatcher;
import io.github.ctlove0523.common.push.IotMessageDispatcher;
import io.github.ctlove0523.push.amqp.IotAmqpClient;
import io.github.ctlove0523.push.amqp.IotAmqpClientOptions;
import org.junit.Test;

public class IotAmqpClientTests {

	@Test
	public void test() throws Exception {
		IotMessageDispatcher dispatcher = new DefaultIotMessageDispatcher();
		dispatcher.addIotDeviceCreatedHandler(new TestIotDeviceCreatedHandler());

		IotAmqpClientOptions options = new IotAmqpClientOptions()
				.setHost("015f603f73.iot-amqps.cn-north-4.myhuaweicloud.com")
				.setPort(5671)
				.setVirtualHost("default")
				.setAccessKey("pZtA8rpi")
				.setAccessCode("uI9jINFke3hTRrqg0CsmwBJppsVnl1nR")
				.setIdleTimeout(6 * 1000)
				.setSsl(true)
				.setDispatcher(dispatcher)
				.setQueueNames(Collections.singletonList("sdk-test"));

		IotAmqpClient client = IotAmqpClient.create(options);
		client.connect().block();
		TimeUnit.HOURS.sleep(1);
	}
}
