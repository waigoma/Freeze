package waigoma.plugin.freeze;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class Freeze implements Listener {

    @EventHandler
    public void onToggleSprint(PlayerToggleSprintEvent e){
        Player p = e.getPlayer();
        if (Main.freeze.get(p.getName())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if (Main.freeze.get(e.getPlayer().getName())) {
            e.setCancelled(true);
        }
    }

}
