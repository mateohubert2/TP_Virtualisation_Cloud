import rabbit.RabbitMQReceiver;
import redis.RedisSender;

public class App {
    public static void main(String[] args) throws Exception {
        RedisSender.Connect();
        RabbitMQReceiver.Connect("10.2.9.27", "guest", "guest");
    }
}
