package net.slipcor.pvparena.commands;

import net.slipcor.pvparena.arena.Arena;
import net.slipcor.pvparena.core.Language;
import net.slipcor.pvparena.managers.ArenaManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class PAA_SetPot extends AbstractArenaCommand{

    public PAA_SetPot() {
        super(new String[]{"pvparena.cmds.setpot"});
    }

    @Override
    public List<String> getMain() {
        return Collections.singletonList("setpot");
    }

    @Override
    public List<String> getShort() {
        return Collections.singletonList("!sp");
    }

    @Override
    public CommandTree<String> getSubs(Arena arena) {
        final CommandTree<String> result = new CommandTree<>(null);
        return result;
    }


    @Override
    public void commit(Arena arena, CommandSender sender, String[] args) {
        if (!hasPerms(sender, arena)){
            return;
        }

        if (!argCountValid(sender, arena, args, new Integer[]{1})){
            return;
        }
        //                                args[0]
        // usage: /pa {arenaname} setfee [amount]
        try {
            int pot = Integer.parseInt(args[0]);
            arena.setPot(pot);
            System.out.println("pot set as" + pot);
        } catch (Exception e){
            Arena.pmsg(sender, "enter a valid integer pot");
            return;
        }

    }

    @Override
    public String getName() {
        return getClass().getName();
    }

    @Override
    public void displayHelp(CommandSender sender) {

    }
}

