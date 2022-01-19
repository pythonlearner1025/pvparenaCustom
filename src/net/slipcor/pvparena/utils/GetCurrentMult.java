package net.slipcor.pvparena.utils;

import net.slipcor.pvparena.arena.Arena;
import net.slipcor.pvparena.goals.GoalTime;
import net.slipcor.pvparena.loadables.ArenaGoal;

public class GetCurrentMult {
    public static int getCurrentMult(Arena arena) {
        int currMult = -1;
        for (ArenaGoal goal : arena.getGoals()) {
            // mjsong code
            System.out.println("getting currMult");
            if (goal instanceof GoalTime) {
                // retriece currentMult
                currMult = ((GoalTime) goal).getCurrentMult();
            }

        }
        return currMult;
    }

}
