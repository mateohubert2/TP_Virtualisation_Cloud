package rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQSender {
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
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Connecté à la queue : " + QUEUE_NAME);
    }

    public static void SendCalcul(int id, String calcul){
        String message = "{id: "+id+" , calcul:"+calcul+"}";
            try {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Message envoyé : " + message);
    }

    public static void close(){
        if (channel != null && channel.isOpen()) {
            try {
                channel.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (TimeoutException e) {
                System.out.println(e.getMessage());
            }
        }
        if (connection != null && connection.isOpen()) {
            try {
                connection.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Connexion fermée.");
    }
}
