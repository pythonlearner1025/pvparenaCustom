package net.slipcor.pvparena.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.slipcor.pvparena.arena.Arena;
import net.slipcor.pvparena.arena.ArenaPlayer;
import net.slipcor.pvparena.managers.ArenaManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

public class ArenaExpansion extends PlaceholderExpansion{
    private Plugin plugin;
    public ArenaExpansion(Plugin plugin){
        this.plugin = plugin;
    }
    @Override
    public  String getIdentifier() {
        return "arenaexpansion";
    }

    @Override
    public String getAuthor() {
        return "minjune";
    }

    @Override
    public  String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist(){
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params){
        Arena arena = ArenaManager.getFirst();
        int playerBal = 0;

        if (arena != null){
            for (final ArenaPlayer ap : arena.getFighters()){
                if (ap.getName().equals(player.getName())){
                    playerBal = ap.getBal();
                }
            }
        }

       if (params.equals("total_liquid")){
           if (arena == null){
               return "0";
           }
           return String.valueOf(arena.getPot());
       }

       if (params.equals("player_liquid")){
           return String.valueOf(playerBal);
       }

       return null;
    }
}
