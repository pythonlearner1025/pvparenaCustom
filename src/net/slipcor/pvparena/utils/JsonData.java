package net.slipcor.pvparena.utils;

import org.json.simple.JSONObject;

public class JsonData {
    // TODO: all JSON data to be sent over to middle and listen servers should be pacakged here
    // middle-server /update/setupplayer
    public static JSONObject setupPlayerData(String playerName, String gameUID, int currMult, int entranceFee){
        JSONObject data = new JSONObject();
        data.put("playerName", playerName);
        data.put("gameUID", gameUID);
        data.put("currentMultiplier", currMult);
        data.put("entranceFee", entranceFee);
        return data;
    }

    public static JSONObject playerAuthorizedData(String playerName, String gameUID,
                                                  int currMult){
        JSONObject data = new JSONObject();
        data.put("playerName", playerName);
        data.put("gameUID", gameUID);
        data.put("currentMultiplier", currMult);
        return data;
    }
}
