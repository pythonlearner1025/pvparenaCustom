package net.slipcor.pvparena.api;

import net.slipcor.pvparena.managers.ServerInfoManager;
import net.slipcor.pvparena.utils.JsonData;
import org.bukkit.Server;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


// TODO: wrap all jsonData here before sending off to listen/mid server.. for better code legibility
public class ServerUtils {

    public static void setupPlayer(ServerClient conn, String playerName, String gameUID, int currentMultiplier, int entranceFee) throws Exception {
        // TODO: remove hard-coded... add multiplier logic
        JSONObject data = JsonData.setupPlayerData(playerName, gameUID, currentMultiplier, entranceFee);
        conn.setupPlayer(data);
    }

    public static JSONObject isPlayerAuthorized(ServerClient conn, String playerName, String gameUID, int currentMultiplier) throws Exception {
        JSONObject data = JsonData.playerAuthorizedData(playerName, gameUID, currentMultiplier);
        String resp = conn.authorizePlayer(data);
        JSONParser parser = new JSONParser();
        JSONObject jsonResp = (JSONObject) parser.parse(resp);
        // TODO: remove print
        System.out.println("printing jsonResp" + jsonResp);
        return jsonResp;
    }

}
