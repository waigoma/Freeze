package waigoma.plugin.freeze;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

public final class Main extends JavaPlugin {
    public static Map<String, Boolean> freeze = new HashMap<>();//静止させたいプレイヤー情報
    //イベント登録用
    private final Freeze frz = new Freeze();
    private final Join join = new Join();
    private final Quit quit = new Quit();

    @Override
    public void onEnable() {
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(frz,this);
        pm.registerEvents(join,this);
        pm.registerEvents(quit,this);

        Bukkit.getServer().getLogger().info("[Freeze] Freeze Enabled!");
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getLogger().info("[Freeze] Freeze Disabled.");
        // Plugin shutdown logic
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (label.equalsIgnoreCase("freeze")){//freezeコマンド実装
                if (args.length == 0){//コマンドを実行した人をfreezeさせる
                    String str = player.getName();
                    Boolean next = !freeze.get(str);
                    freeze.put(str, next);

                    if (next){
                        player.setWalkSpeed(0.0f);//FOV変更無
                        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200000, 128, false, false));//ジャンプ不可(ジャンプブーストlvl.128)
                    }else {
                        player.setWalkSpeed(0.2f);//FOV変更有
                        player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);//TODO FragmentSystemからspeedを持ってくる
                        for (PotionEffect effect : player.getActivePotionEffects()) {
                            if (effect.getType().equals(PotionEffectType.JUMP)) player.removePotionEffect(PotionEffectType.JUMP);
                        }
                    }

                }else if (args.length == 1){//他のplayerをfreezeさせるパターン/freeze player
                    Player p = Bukkit.getPlayerExact(args[0]);

                    if (p != null) {
                        String str = p.getName();
                        Boolean next = !freeze.get(str);
                        freeze.put(str, next);

                        if (next) {
                            p.setWalkSpeed(0.0f);
                            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200000, 128, false, false));
                        } else {
                            player.setWalkSpeed(0.2f);
                            p.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.1);
                            for (PotionEffect effect : p.getActivePotionEffects()) {
                                if (effect.getType().equals(PotionEffectType.JUMP)) p.removePotionEffect(PotionEffectType.JUMP);
                            }
                        }
                    }else {
                        player.sendMessage("そのプレイヤーは存在しないかオフラインです。");
                    }
                }
            }
        }

    return true;
    }
}
