package rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import redis.RedisSender;

public class RabbitMQReceiver {
    private final static String QUEUE_NAME = "PolyCalculatorQueue";
    static private Channel channel;
    static private Connection connection;
    public static void Connect(String host, String user, String password){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setUsername(user);
        factory.setPassword(password);

        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (TimeoutException e) {
            System.out.println(e.getMessage());
        }

        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Connecté à la queue : " + QUEUE_NAME);

        System.out.println("En attente des messages...");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Message reçu : " + message);
            message = message.replaceAll("[{}]", "");
        
            String[] parts = message.split(",");
            
            String id = null;
            String calcul = null;
            
            for (String part : parts) {
                part = part.trim();
                if (part.startsWith("id:")) {
                    id = part.split(":")[1].trim();
                } else if (part.startsWith("calcul:")) {
                    calcul = part.split(":")[1].trim();
                }
            }

            System.out.println("id: " + id);
            System.out.println("calcul: " + calcul);
            RedisSender.Send();
            RedisSender.Disconnect();
        };

        try {
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
