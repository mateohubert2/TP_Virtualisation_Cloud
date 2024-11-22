package controllers;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import webserver.WebServerContext;
import webserver.WebServerRequest;
import webserver.WebServerResponse;

public class CalculController {
    //Tableau qui stock les résultats
    static ArrayList<Object> results = new ArrayList<>();

    //Fonction qui remplace les mots par les opérateurs adéquats et qui utilise un ScriptEngine pour transformer le String en vrai calcul puis répond l'id du calcul
    public static void CalculProcess(WebServerContext context){
        Object eval = new Object();
        WebServerResponse response = context.getResponse();
        WebServerRequest request = context.getRequest();
        String calculString = request.getParam("calcul");
        calculString = calculString.replace("div", "/");
        calculString = calculString.replace("parl", "(");
        calculString = calculString.replace("pard", ")");
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            eval = engine.eval(calculString);
            System.out.println("Résultat : " + eval);
        } catch (ScriptException e) {
            System.out.println("Erreur lors de l'évaluation : " + e.getMessage());
        }
        results.add(eval);
        response.json(results.size());
    }


    //Fonction qui répond le résultat en fonction de l'id
    public static void GetCalculResult(WebServerContext context){
        WebServerResponse response = context.getResponse();
        WebServerRequest request = context.getRequest();
        int id = Integer.parseInt(request.getParam("id"));
        id -= 1;
        response.json(results.get(id));
    }
}