package webserver;

import com.sun.net.httpserver.HttpExchange;

public class WebServerContext {
    private WebServerRequest request;
    private WebServerResponse response;

    WebServerContext(HttpExchange exchange)
    {
        this.request = new WebServerRequest(exchange);
        this.response = new WebServerResponse(exchange);
    }

    public WebServerRequest getRequest() {
        return request;
    }

    public WebServerResponse getResponse() {
        return response;
    }
}
