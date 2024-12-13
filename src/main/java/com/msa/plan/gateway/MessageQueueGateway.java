package com.msa.plan.gateway;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageQueueGateway {
    @Value("${spring.azure.servicebus.connection-string}")
    private String connectionString;

    @Value("${spring.azure.servicebus.queue-name}")
    private String queueName;

    public void sendPlanChangeNotification(String userId, String planId) {
        try (ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
                .connectionString(connectionString)
                .sender()
                .queueName(queueName)
                .buildClient()) {

            String messageBody = String.format("User %s changed plan to %s", userId, planId);
            ServiceBusMessage message = new ServiceBusMessage(messageBody);
            senderClient.sendMessage(message);
        }
    }
}
