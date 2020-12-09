package systems.conduit.example.afktimer;

import lombok.Getter;
import systems.conduit.main.plugin.config.Configuration;
import systems.conduit.main.plugin.config.annotation.ConfigFile;

/**
 * @author Innectic
 * @since 12/8/2020
 */
@ConfigFile(name = "afktimer", type = "json", defaultFile = "afktimer.json")
public class AFKTimerConfiguration extends Configuration {

    @Getter private String broadcastMessage;
    @Getter private String kickMessage;
    @Getter private int afkSeconds;
}
