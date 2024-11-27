import rabbit.RabbitMQReceiver;
import redis.RedisSender;

public class App {
    public static void main(String[] args) throws Exception {
        RedisSender.Connect();
        RabbitMQReceiver.Connect("rabbitmq", "guest", "guest");
    }
}
