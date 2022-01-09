package net.slipcor.pvparena.listeners;

import net.slipcor.pvparena.api.ServerClient;
import net.slipcor.pvparena.managers.ServerInfoManager;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.json.simple.JSONObject;

public class LogoutListener implements Listener {
    @EventHandler
    public void normalLogin(PlayerQuitEvent event) throws Exception {
        // send info about login of player to redis server
        Player leftPlayer = event.getPlayer();
        String leftPlayerName = leftPlayer.getName();
        String serverUID = ServerInfoManager.getServerUID();
        System.out.println("reached here");
        ServerClient conn = new ServerClient();
        JSONObject data = new JSONObject();
        data.put("serverUID",serverUID);
        data.put("leftPlayerName", leftPlayerName);
        conn.playerExitServer(data);
        System.out.println("notified redis server about player exit");
    }
}
