package systems.conduit.example.afktimer;

import lombok.Getter;
import systems.conduit.example.afktimer.runnable.AFKTimerRunnable;
import systems.conduit.main.Conduit;
import systems.conduit.main.plugin.Plugin;
import systems.conduit.main.plugin.annotation.PluginMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Innectic
 * @since 12/8/2020
 */
@PluginMeta(name = "AFKTimer", author = "ConduitMC", version = "0.0.1",
        description = "Automatically kick players when they've been AFK for a certain amount of time", reloadable = true,
        config = AFKTimerConfiguration.class)
public class AFKTimerPlugin extends Plugin {

    @Getter private final Map<UUID, Long> lastMoveTime = new HashMap<>();

    private int afkTimerId = -1;

    @Override
    protected void onEnable() {
        if (afkTimerId == -1) afkTimerId = Conduit.getRunnableManager().schedule(new AFKTimerRunnable(), 20);
    }

    @Override
    protected void onDisable() {
        lastMoveTime.clear();

        if (afkTimerId != -1) Conduit.getRunnableManager().cancelRunnable(afkTimerId);
    }

    public static Optional<AFKTimerPlugin> getPlugin() {
        return Conduit.getPluginManager().getPlugin("AFKTimer").map(AFKTimerPlugin.class::cast);
    }
}
