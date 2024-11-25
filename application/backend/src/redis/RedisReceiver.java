package redis;

import redis.clients.jedis.Jedis;

public class RedisReceiver {
    private static Jedis jedis;
    public static void Connect(){
        try {
            jedis = new Jedis("localhost", 6379);
            System.out.println("Connexion à Redis réussie.");
        } catch (Exception e) {
            System.out.println("Erreur lors de la connexion à Redis : " + e.getMessage());
        }
    }

    public static String GetResult(String id){
        String result = jedis.get(id);
        System.out.println("Le resultat est : " + result);
        return result;
    }

    public static void Disconnect() {
        if (jedis != null) {
            jedis.close();
            System.out.println("Connexion à Redis fermée.");
        }
    }
}
