package gov.usgs.wma.messaging;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;

import gov.usgs.cida.microservices.api.messaging.MicroserviceHandler;
import gov.usgs.cida.microservices.api.messaging.exception.MqConnectionException;
import gov.usgs.cida.microservices.messaging.MicroserviceMsgservice;

public class RequestTest {

	/**
	 * NOT ACTUAL UNIT TEST, used as helper, keep this Ignored and only use if you want to send MQ requests during dev.
	 */
	@Ignore
	@Test
	public void sendGetMqRequest() throws MqConnectionException, InterruptedException {
		String requestId = UUID.randomUUID().toString();
		String serviceRequestId = UUID.randomUUID().toString();
		String testServiceName = UUID.randomUUID().toString();
		
		Set<Class<? extends MicroserviceHandler>> handlers = new HashSet<>();
		handlers.add(DataIngestionHandler.class);
		MicroserviceUtils.initTransientService(testServiceName, handlers);
		
		MicroserviceMsgservice service = MicroserviceUtils.getServiceInstance(testServiceName);
		
		Map<String, Object> headers = new HashMap<>();
		headers.put("serviceName", MessageConfiguration.MLR_SERVICE_NAME);
		headers.put("eventType", MessageConfiguration.GET_REQUEST_TOPIC);
		headers.put("serviceTag", MessageConfiguration.MLR_SERVICE_TAG);
		headers.put("locationNumber", "00001");

		service.sendMessage(requestId, serviceRequestId, headers, new byte[0]);
		
		while(DataCacheSingleton.getData(requestId) == null) {
			//wait for data to return from async process
			Thread.sleep(1000);
		}
		System.out.println("received data: " + DataCacheSingleton.getData(requestId));
		
		MicroserviceUtils.quietCloseService(testServiceName);
	}
}
