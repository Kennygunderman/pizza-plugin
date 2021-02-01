package com.kgeezy.pizza

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class PlayerButtonClickListener(private val webHelper: WebHelper): Listener {
    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        if (event.clickedBlock?.type == Material.JUNGLE_BUTTON) {
            event.player.sendMessage("${ChatColor.LIGHT_PURPLE}Creating your pizza order...")
            webHelper.requestOrder { response ->
                Bukkit.broadcastMessage("${ChatColor.LIGHT_PURPLE}${event.player.name} ordered a pizza for Kenny!")
                event.player.sendMessage(response)
            }
        }
    }
}