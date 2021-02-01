package com.kgeezy.pizza


import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class PizzaPlugin : JavaPlugin() {
    private val webHelper: WebHelper = WebHelper()

    override fun onEnable() {
        super.onEnable()
        server.pluginManager.registerEvents(PlayerButtonClickListener(webHelper), this)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player && command.name != MAIN) {
            return true
        }

        if (args.firstOrNull() == ORDER_ARG) {
            webHelper.requestOrder { response ->
                sender.sendMessage(response)
            }
        }

        return true
    }
}