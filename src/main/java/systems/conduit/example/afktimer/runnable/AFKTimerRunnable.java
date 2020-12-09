package systems.conduit.example.afktimer.runnable;

import net.minecraft.Util;
import net.minecraft.network.chat.TextComponent;
import systems.conduit.example.afktimer.AFKTimerConfiguration;
import systems.conduit.example.afktimer.AFKTimerPlugin;
import systems.conduit.main.Conduit;

/**
 * @author Innectic
 * @since 12/9/2020
 */
public class AFKTimerRunnable implements Runnable {

    @Override
    public void run() {
        AFKTimerPlugin.getPlugin().ifPresent(plugin -> plugin.getConfig().map(AFKTimerConfiguration.class::cast)
                .ifPresent(config -> plugin.getLastMoveTime().forEach((uuid, lastMove) -> {
                    long delta = (System.currentTimeMillis() - lastMove) / 1000;

                    if (delta >= config.getAfkSeconds()) {
                        // Kick this player
                        Conduit.getPlayerManager().getPlayer(uuid).ifPresent(player -> {
                            Conduit.getPlayerManager().broadcastMessage(new TextComponent(config.getBroadcastMessage()), Util.NIL_UUID);
                            player.kick(new TextComponent(config.getKickMessage()));
                        });
                    }
        })));
    }
}
