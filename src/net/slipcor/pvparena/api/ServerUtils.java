package net.slipcor.pvparena.api;

import net.slipcor.pvparena.managers.ServerInfoManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ServerUtils {

    public static void setupPlayer(ServerClient conn, String playerName, String serverUID, int currentMultiplier) throws Exception {
        // TODO: remove hard-coded... add multiplier logic
        JSONObject data = new JSONObject();
        data.put("player", playerName);
        data.put("serverUID", serverUID);
        data.put("currentMultiplier", currentMultiplier);
        conn.setupPlayer(data);
    }

    public static JSONObject isPlayerAuthorized(ServerClient conn, String playerName, String serverUID, int currentMultiplier) throws Exception {
        JSONObject data = new JSONObject();
        data.put("player", playerName);
        data.put("serverUID", serverUID);
        data.put("currentMultiplier", currentMultiplier);
        String resp = conn.authorizePlayer(data);
        System.out.println(resp);
        JSONParser parser = new JSONParser();
        JSONObject jsonResp = (JSONObject) parser.parse(resp);
        System.out.println("printing resp"+ resp);
        System.out.println("printing jsonResp" + jsonResp);
        return jsonResp;
    }

    public static void updatePlayerToSC(ServerClient conn, JSONObject data) throws Exception {
        conn.playerEnterSC(data);
    }


}
