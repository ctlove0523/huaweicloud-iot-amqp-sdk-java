package io.github.ctlove0523.amqp;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class IotAmqpClientTests {

	@Test
	public void test() throws Exception {
		IotAmqpClientOptions options = new IotAmqpClientOptions()
				.setHost("015f603f73.iot-amqps.cn-north-4.myhuaweicloud.com")
				.setPort(5671)
				.setVirtualHost("default")
				.setAccessKey("pZtA8rpi")
				.setAccessCode("uI9jINFke3hTRrqg0CsmwBJppsVnl1nR")
				.setIdleTimeout(6 * 1000)
				.setQueueNames(Collections.singletonList("sdk-test"));

		IotAmqpClient client = IotAmqpClient.create(options);
		client.addDeviceMessageReportedHandler(new TestIotDeviceMessageHandler());
		client.addDeviceDeletedHandler(new TestIotDeviceDeletedHandler());
		client.addDeviceCreatedHandler(new TestIotDeviceCreatedHandler());
		client.connect().block();
		TimeUnit.HOURS.sleep(1);
	}
}
