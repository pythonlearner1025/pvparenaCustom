package net.slipcor.pvparena.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.simple.JSONObject;

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
                .uri(URI.create("http://45.33.25.141:8000"))
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
    /*
    public void register(JSONObject data) throws Exception {
        URI targetURI = URI.create("http://127.0.0.1:5000/listen-server/register");
        sendPost(data, targetURI);
    }

    public void transaction(JSONObject data) throws Exception {
        URI targetURI = URI.create("http://127.0.0.1:5000/listen-server/transaction");
        sendPost(data, targetURI);
    }

    public void addShares(JSONObject data) throws Exception{
        URI targetURI = URI.create("http://127.0.0.1:5000/listen-server/addshares");
        sendPost(data, targetURI);
    }

     */

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // redis middle-server
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public void commit(JSONObject data) throws Exception {
        URI targetURI = URI.create("http://45.33.25.141/commit");
        sendPost(data, targetURI);
    }

    public void registerServer(JSONObject data) throws Exception{
        URI targetURI = URI.create("http://45.33.25.141/update/registerserver");
        sendPost(data, targetURI);
    }
    // reigster GAMEUID so all other requests can go through
    public void registerGame(JSONObject data) throws Exception{
        URI targetURI = URI.create("http://45.33.25.141/update/registergame");
        sendPost(data, targetURI);
    }
    // can player join arena? this may have to be async bool return
    public String authorizePlayer(JSONObject data) throws Exception{
        URI targetURI = URI.create("http://45.33.25.141/update/authorizeplayer");
        return sendPost(data, targetURI);
    }

    // setup player before every authorize call
    public void setupPlayer(JSONObject data) throws Exception{
        URI targetURI = URI.create("http://45.33.25.141/update/setupplayer");
        sendPost(data, targetURI);
    }

    // player joins server
    public void playerJoinServer(JSONObject data) throws Exception{
        URI targetURI = URI.create("http://45.33.25.141/update/playerjoinserver");
        sendPost(data, targetURI);
    }

    // player leaves server
    public void playerExitServer(JSONObject data) throws Exception{
        URI targetURI = URI.create("http://45.33.25.141/update/playerexitserver");
        sendPost(data, targetURI);
    }
    // call when arena game ends, in order to clear player respawns / mult so to
    // remove duplicates
    public void flushData(JSONObject data) throws Exception{
        URI targetURI = URI.create("http://45.33.25.141/update/flushdata");
        sendPost(data, targetURI);
    }


}
