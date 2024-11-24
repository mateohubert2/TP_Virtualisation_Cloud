import rabbit.RabbitMQReceiver;
import redis.RedisSender;

public class App {
    public static void main(String[] args) throws Exception {
        RabbitMQReceiver.Connect("localhost", "guest", "guest");
        RedisSender.Connect();
    }
}
