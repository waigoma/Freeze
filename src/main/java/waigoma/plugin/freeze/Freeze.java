package waigoma.plugin.freeze;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Freeze implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player p = event.getPlayer();
        String str = p.getName();
        if (Main.freeze.get(str)){
            p.teleport(p);
            System.out.println("呼ばれた");
        }
    }
}
