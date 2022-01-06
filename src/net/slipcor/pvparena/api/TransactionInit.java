package net.slipcor.pvparena.api;

import net.slipcor.pvparena.arena.Arena;
import net.slipcor.pvparena.events.PAStartEvent;
import net.slipcor.pvparena.events.PAJoinEvent;
import net.slipcor.pvparena.events.PALeaveEvent;
import net.slipcor.pvparena.loadables.ArenaGoal;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import net.slipcor.pvparena.arena.ArenaPlayer;


/*
 Final Goal:
 - Initialize transaction once, at the beginning of the arena event
 - Initial Transaction (POT) contains:
    --> initial reward amount
    --> game info (type of game, time limit, metadata, etc)
    --> candidate players (all who joined the arena)
    --> winning players (empty array, but confirmed winners)
    --> send snapshot at PAStartEvent to server.
    --> send updates to server as players join


    Dev Dec 31 2021.

    Calling from PACheck.java
    --> makes new TransactionInit (should print in console)
    --> creates new TransactionInit JSON object (should print in console)
    --> test the above two functionalities, make sure it works.

    Dev Jan 1 2022!
    --> send first request to python webflask server
    --> receive first transaction JSON even from server

    

// make this into Smart Contract INIT,
// send necessary info to instantiate a fresh smart contract
// IMPORTANT: new smart contract instance per game instance

 -
 */
public class TransactionInit {

    private final Arena arena;
    private JSONObject newTransaction;

    public TransactionInit(Arena arena) {
        this.arena = arena;
        this.newTransaction = new JSONObject();

        // extract players from arena
        Set<ArenaPlayer> players = arena.getEveryone();
        ArrayList<String> playersList = new ArrayList<>();
        for (ArenaPlayer player: players){
            // print check
            playersList.add(player.getName());
        }

        // extract goals from arena
        Set<ArenaGoal> goals = arena.getGoals();
        ArrayList<String> goalList = new ArrayList<>();
        for (ArenaGoal goal: goals){
            String goalName = goal.getName();
            // print check
            System.out.println(goalName);
            goalList.add(goalName);
        }

        JSONArray jsonPlayersList = new JSONArray();
        JSONArray jsonGoalList = new JSONArray();

        for (String goal : goalList){
            jsonGoalList.add(goal);
        }
        for (String player: playersList){
            jsonPlayersList.add(player);
        }
        newTransaction.put("players", jsonPlayersList);
        newTransaction.put("goals", jsonGoalList);

    }

    // return json object of transaction
    public JSONObject getNewTransaction(){
        return this.newTransaction;
    }

}
