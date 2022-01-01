package net.slipcor.pvparena.events;

import net.slipcor.pvparena.arena.Arena;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

// mjsong APIS

import net.slipcor.pvparena.api.TransactionInit;

/**
 * <pre>PVP Arena Death Event class</pre>
 * <p/>
 * is called when an arena match starts
 *
 * @author slipcor
 * @version v0.9.1
 */

public class PAStartEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Arena arena;
    private boolean cancelled;

    /**
     * create a start event instance
     *
     * @param arena the starting arena
     */
    public PAStartEvent(final Arena arena) {
        super();
        this.arena = arena;
    }

    /**
     * hand over the arena instance
     *
     * @return the starting arena
     */
    public Arena getArena() {
        return arena;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(final boolean value) {
        cancelled = value;
    }
}
