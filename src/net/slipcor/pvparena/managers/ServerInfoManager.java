package net.slipcor.pvparena.managers;
import java.io.*;
import java.util.Random;

import org.bukkit.Bukkit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ServerInfoManager {
    // register server info for first time,
    // if info.json file does not exist
    public static void registerServerInfo(){
        JSONParser jsonParser = new JSONParser();
        String baseDir = System.getProperty("user.dir");
        File infoFile = new File(baseDir + "/plugins/pvparena/serverinfo/info.json");
        System.out.println("dir: " + infoFile);
        try (FileReader reader = new FileReader(infoFile)) {
            System.out.println("info.json already exists");
        } catch (FileNotFoundException e) {
            // file not found, therefore write new one
            String serverUID = genRandString();
            String gameType = "Minecraft";
            int maxCap = Bukkit.getServer().getMaxPlayers();
            JSONObject info = new JSONObject();
            info.put("serverUID", serverUID);
            info.put("gameType", gameType);
            info.put("maxCap", maxCap);


            try (FileWriter file = new FileWriter(infoFile)){
                file.write(info.toJSONString());
                file.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("no existing info.json, so writing new one");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getServerInfo(){
        JSONParser jsonParser = new JSONParser();
        String baseDir = System.getProperty("user.dir");
        File infoFile = new File(baseDir + "/plugins/pvparena/serverinfo/info.json");
        try (FileReader reader = new FileReader(infoFile)){
           Object obj = jsonParser.parse(reader);
           JSONObject info = (JSONObject) obj;
           System.out.println("read info" + info.toString());
           return info;
        } catch (FileNotFoundException e){
           e.printStackTrace();
        } catch (IOException e ){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getServerUID(){
     JSONObject info = getServerInfo();
     assert info != null;
     String serverUID = info.get("serverUID").toString();
     System.out.println("serverUID: " + serverUID);
        return serverUID;
    }

    private static String genRandString() {
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 7;
        for (int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        System.out.println("Random String is: " + randomString);

        return randomString;
    }
}
