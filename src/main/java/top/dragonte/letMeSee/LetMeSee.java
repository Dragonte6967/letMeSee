package top.dragonte.letMeSee;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class LetMeSee extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginCommand("letmesee").setExecutor(new cmdExe());
        Bukkit.getPluginCommand("letmesee").setTabCompleter(new cmdExe());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
