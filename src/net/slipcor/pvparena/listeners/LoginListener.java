package net.slipcor.pvparena.listeners;
import net.slipcor.pvparena.api.ServerClient;
import net.slipcor.pvparena.managers.ServerInfoManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
import org.json.simple.JSONObject;

public class LoginListener implements Listener {
    @EventHandler
    public void normalLogin(PlayerLoginEvent event) throws Exception {
       // send info about login of player to redis server event.getPlayer()

        Player joinedPlayer = event.getPlayer();
        String joinedPlayerName = joinedPlayer.getName();
        String serverUID = ServerInfoManager.getServerUID();
        System.out.println("reached here");
        ServerClient conn = new ServerClient();
        JSONObject data = new JSONObject();
        data.put("serverUID", serverUID);
        data.put("joinedPlayerName", joinedPlayerName);
        conn.playerJoinServer(data);
        System.out.println("notified redis server about new player join");
    }
}
