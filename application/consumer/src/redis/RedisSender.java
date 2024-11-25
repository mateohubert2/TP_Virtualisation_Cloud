package redis;
import redis.clients.jedis.Jedis;
public class RedisSender {
    private static Jedis jedis;

    public static void Connect(){
        try {
            jedis = new Jedis("localhost", 6379);
            System.out.println("Connexion à Redis réussie.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la connexion à Redis : " + e.getMessage());
        }
    }

    public static void Send(String id, Object eval){
        jedis.set(id, eval.toString());
    }

    public static void Disconnect() {
        if (jedis != null) {
            jedis.close();
            System.out.println("Connexion à Redis fermée.");
        }
    }
}
