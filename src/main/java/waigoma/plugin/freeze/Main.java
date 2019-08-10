package waigoma.plugin.freeze;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static boolean freeze;
    private final Freeze frz = new Freeze(this);

    @Override
    public void onEnable() {
        freeze = false;
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(frz,this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            if (label.equalsIgnoreCase("freeze")){
                if (args.length == 0){
                    if (freeze){
                        freeze = false;
                    }else{
                        freeze = true;
                    }
                }
            }
        }

    return true;
    }
}
