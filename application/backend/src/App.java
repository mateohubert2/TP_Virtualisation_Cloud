import controllers.CalculController;
import rabbit.RabbitMQReceiver;
import rabbit.RabbitMQSender;
import redis.RedisSender;
import webserver.WebServer;
import webserver.WebServerContext;

public class App {
    public static void main(String[] args) throws Exception {
        //Démarre un webserver qui écoute sur le port 8080
        WebServer webserver = new WebServer();
        webserver.listen(8080);
        
        RabbitMQSender.Connect("localhost", "guest", "guest");
        RabbitMQReceiver.Connect("localhost", "guest", "guest");
        RedisSender.Connect();

        //Création de la route pour récupérer un résultat en fonction de l'id
        webserver.getRouter().get("/polycalculator/id/:id", (WebServerContext context) -> { CalculController.GetCalculResult(context); });

        //Création de la route pour le calcul de l'opération passée en paramètre
        webserver.getRouter().post("/polycalculator/calcul/:calcul", (WebServerContext context) -> { CalculController.CalculProcess(context); });
    }
}
