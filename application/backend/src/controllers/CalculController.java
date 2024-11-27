package controllers;

import rabbit.RabbitMQSender;
import redis.RedisReceiver;
import webserver.WebServerContext;
import webserver.WebServerRequest;
import webserver.WebServerResponse;

public class CalculController {
    //Tableau qui stock les résultats
    static private int id = 0;
    //Fonction qui remplace les mots par les opérateurs adéquats et qui utilise un ScriptEngine pour transformer le String en vrai calcul puis répond l'id du calcul
    public static void CalculProcess(WebServerContext context){
        id++;
        WebServerResponse response = context.getResponse();
        WebServerRequest request = context.getRequest();
        String calculString = request.getParam("calcul");
        calculString = calculString.replace("div", "/");
        calculString = calculString.replace("parl", "(");
        calculString = calculString.replace("pard", ")");
        RabbitMQSender.SendCalcul(id, calculString);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        response.json(id);
    }


    //Fonction qui répond le résultat en fonction de l'id
    public static void GetCalculResult(WebServerContext context){
        WebServerResponse response = context.getResponse();
        WebServerRequest request = context.getRequest();
        String id = request.getParam("id");
        String result = RedisReceiver.GetResult(id);
        response.json(result);
    }
}