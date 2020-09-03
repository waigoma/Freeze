package waigoma.plugin.freeze;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player p = event.getPlayer();
        String str = p.getName();
        Main.freeze.put(str, false);
    }
}
