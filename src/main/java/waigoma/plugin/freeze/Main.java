package waigoma.plugin.freeze;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin {
    public static Map<String,Boolean> freeze = new HashMap<>();
    private final Freeze frz = new Freeze();
    private final Join join = new Join();
    private final Quit quit = new Quit();

    @Override
    public void onEnable() {
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(frz,this);
        pm.registerEvents(join,this);
        pm.registerEvents(quit,this);
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
                    Player player = (Player) sender;
                    String str = player.getName();
                    Boolean next = !freeze.get(str);
                    freeze.put(str,next);
                }
            }
        }

    return true;
    }
}
