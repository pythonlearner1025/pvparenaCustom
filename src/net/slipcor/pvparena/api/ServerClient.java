package net.slipcor.pvparena.api;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ServerClient {
    private final HttpClient httpClient;
    public ServerClient(){
        httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }
    public boolean checkServerConn(){
        return true;
    }
    // send POST request to server, and receive a positive reply
    // return true if reply is positive
    public boolean sendRequest(String data){
        System.out.println(data);
        return true;
    }


    // eventually replace all URIs with environment variables
    public void sendGet() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8000"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());

    }

    private String sendPost(JSONObject data, URI targetURI) throws Exception {
        System.out.println("check body, normal string: " + data.toString());
        System.out.println("check body, json string: " + data.toJSONString());
        // form parameters
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(data.toString()))
                .uri(targetURI)
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());

        return response.body();

    }

    // redis game-session server
    public void initiateSC(JSONObject data) throws Exception {
       URI targetURI = URI.create("http://localhost:8000/");
       sendPost(data, targetURI);
    }

    public void playerUpdateSC(JSONObject data) throws Exception {
        URI targetURI = URI.create("http://localhost:8000/update");
        sendPost(data, targetURI);
    }

    public void commitSC(JSONObject data) throws Exception {
        URI targetURI = URI.create("http://localhost:8000/commit");
        sendPost(data, targetURI);
    }

    public void playerEnterSC(JSONObject data) throws Exception{
        URI targetURI = URI.create("http://localhost:8000/enter");
        sendPost(data, targetURI);
    }

    // redis server-info server
    public void registerServer(JSONObject data) throws Exception{
        URI targetURI = URI.create("http://localhost:3000/update/register");
        sendPost(data, targetURI);
    }

    // can player join arena? this may have to be async bool return
    public String authorizePlayer(JSONObject data) throws Exception{
        URI targetURI = URI.create("http://localhost:3000/update/authorizeplayer");
        return sendPost(data, targetURI);
    }

    // setup player before every authorize call
    public String setupPlayer(JSONObject data) throws Exception{
        URI targetURI = URI.create("http://localhost:3000/update/setupplayer");
        return sendPost(data, targetURI);
    }

    public void updateGameStatus(JSONObject data) throws Exception{

    }

    public void updateGameStats(JSONObject data) throws Exception{

    }
    // player joins server
    public void playerJoinServer(JSONObject data) throws Exception{
        URI targetURI = URI.create("http://localhost:3000/update/playerjoinserver");
        sendPost(data, targetURI);
    }

    // player leaves server
    public void playerExitServer(JSONObject data) throws Exception{
        URI targetURI = URI.create("http://localhost:3000/update/playerexitserver");
        sendPost(data, targetURI);
    }

}
