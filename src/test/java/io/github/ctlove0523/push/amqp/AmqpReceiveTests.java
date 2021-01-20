package io.github.ctlove0523.push.amqp;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.github.ctlove0523.common.push.DefaultIotMessageDispatcher;
import io.github.ctlove0523.common.push.IotMessageDispatcher;
import io.github.ctlove0523.commons.serialization.JacksonUtil;
import io.github.ctlove0523.push.IotPushDataUtil;
import io.github.ctlove0523.push.TestIotDeviceCreatedHandler;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import org.apache.qpid.proton.amqp.messaging.Accepted;
import org.apache.qpid.proton.amqp.messaging.AmqpValue;
import org.apache.qpid.proton.amqp.messaging.ApplicationProperties;
import org.apache.qpid.proton.message.Message;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.boot.test.system.OutputCaptureRule;

import static org.hamcrest.Matchers.containsString;

public class AmqpReceiveTests extends BareTestBase {

	@Rule
	public OutputCaptureRule outputCapture = new OutputCaptureRule();

	private AmqpMockServer server;

	@After
	@Override
	public void tearDown() throws InterruptedException {
		super.tearDown();
		if (server != null) {
			server.close();
		}
	}

	@Test
	public void test_receive_deviceCreatedData(TestContext context) throws Exception {
		String deviceId = UUID.randomUUID().toString();
		String sentContent = JacksonUtil.object2Json(IotPushDataUtil.getIotDeviceCreatedData(deviceId));

		Async asyncSendMsg = context.async();

		server = new AmqpMockServer(vertx, serverConnection -> {
			serverConnection.openHandler(serverSender -> {
				serverConnection.closeHandler(x -> serverConnection.close());
				serverConnection.open();
			});

			serverConnection.sessionOpenHandler(serverSession -> {
				serverSession.closeHandler(x -> serverSession.close());
				serverSession.open();
			});

			serverConnection.senderOpenHandler(serverSender -> {
				Message protonMsg = Message.Factory.create();
				protonMsg.setBody(new AmqpValue(sentContent));

				Map<String, Object> props = new HashMap<>();
				ApplicationProperties appProps = new ApplicationProperties(props);
				protonMsg.setApplicationProperties(appProps);

				serverSender.open();

				serverSender.send(protonMsg, delivery -> {
					context.assertNotNull(delivery.getRemoteState(), "message had no remote state");
					context.assertTrue(delivery.getRemoteState() instanceof Accepted, "message was not accepted");
					context.assertTrue(delivery.remotelySettled(), "message was not settled");
					asyncSendMsg.complete();
				});
			});
		});

		IotMessageDispatcher dispatcher = new DefaultIotMessageDispatcher();
		dispatcher.addIotDeviceCreatedHandler(new TestIotDeviceCreatedHandler());

		IotAmqpClientOptions options = new IotAmqpClientOptions();
		options.setHost("localhost")
				.setPort(server.actualPort())
				.setAccessCode("access code")
				.setAccessKey("access key")
				.setQueueNames(Collections.singletonList("test"))
				.setSsl(false)
				.setDispatcher(dispatcher);

		IotAmqpClient iotAmqpClient = IotAmqpClient.create(options);
		iotAmqpClient.connect().block();

		outputCapture.expect(containsString("begin to handle device created"));
	}
}
