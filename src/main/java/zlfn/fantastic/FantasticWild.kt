package zlfn.fantastic

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class FantasticWild : JavaPlugin() {
    override fun onEnable() {
        server.pluginManager.registerEvents(MoveListener(),this)
        Bukkit.getConsoleSender().sendMessage("Enabled!")
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}