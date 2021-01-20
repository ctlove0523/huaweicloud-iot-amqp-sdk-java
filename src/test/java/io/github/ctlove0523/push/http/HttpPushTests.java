package io.github.ctlove0523.push.http;

import java.io.IOException;
import java.util.UUID;

import io.github.ctlove0523.common.push.DefaultIotMessageDispatcher;
import io.github.ctlove0523.common.push.IotMessageDispatcher;
import io.github.ctlove0523.commons.httpclient.HttpClientFactory;
import io.github.ctlove0523.commons.serialization.JacksonUtil;
import io.github.ctlove0523.push.IotPushDataUtil;
import io.github.ctlove0523.push.TestIotDeviceCreatedHandler;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.boot.test.system.OutputCaptureRule;

import static org.hamcrest.Matchers.containsString;

public class HttpPushTests {

	@Rule
	public OutputCaptureRule outputCapture = new OutputCaptureRule();

	@Test
	public void test_receive_deviceCreatedData() throws IOException {
		IotMessageDispatcher dispatcher = new DefaultIotMessageDispatcher();
		dispatcher.addIotDeviceCreatedHandler(new TestIotDeviceCreatedHandler());

		IotHttpServerOptions options = new IotHttpServerOptions();
		options.setDispatcher(dispatcher)
				.setHost("localhost")
				.setPort(5230)
				.setUrl("/push-data");
		IotHttpServer server = IotHttpServer.createIotHttpServer(options);
		server.start().block();

		OkHttpClient client = HttpClientFactory.getHttpsClient();
		String deviceId = UUID.randomUUID().toString();
		Request request = new Request.Builder()
				.url("http://localhost:5230/push-data")
				.post(RequestBody.create(JacksonUtil.object2Json(IotPushDataUtil.getIotDeviceCreatedData(deviceId)), MediaType.parse("application/json")))
				.build();
		client.newCall(request).execute();
		outputCapture.expect(containsString("begin to handle device created"));

		server.shutdown().block();
	}
}
