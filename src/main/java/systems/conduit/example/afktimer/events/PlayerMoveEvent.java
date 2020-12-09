package systems.conduit.example.afktimer.events;

import systems.conduit.example.afktimer.AFKTimerPlugin;
import systems.conduit.main.events.EventListener;
import systems.conduit.main.events.annotations.Listener;
import systems.conduit.main.events.types.PlayerEvents;

/**
 * @author Innectic
 * @since 12/8/2020
 */
public class PlayerMoveEvent implements EventListener {

    @Listener
    public void onPlayerMoveEvent(PlayerEvents.MoveEvent e) {
        if (e.getPlayer() == null) return;
        if (e.getFrom() == null || e.getTo() == null || e.getFrom().equals(e.getTo())) return;

        // Since we have a player, and they did ACTUALLY move, we'll actually update the last move time.
        // This makes it so players can't just turn their head to get around the AFK check

        AFKTimerPlugin.getPlugin().ifPresent(p -> p.getLastMoveTime().put(e.getPlayer().getUUID(), System.currentTimeMillis()));
    }
}
