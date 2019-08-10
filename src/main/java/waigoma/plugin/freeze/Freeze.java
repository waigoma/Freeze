package waigoma.plugin.freeze;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Freeze implements Listener {
    private Main plugin;
    public Freeze(Main pl){
        plugin = pl;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if (Main.freeze){
            Player player = event.getPlayer();
            player.teleport(player);
        }
    }
}
