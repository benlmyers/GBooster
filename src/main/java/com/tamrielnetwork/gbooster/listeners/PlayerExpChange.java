/*
 Copyright (C) 2022  Leopold Meinel
 Visit https://github.com/TamrielNetwork/GBooster/blob/main/LICENSE for more details.
 */
package com.tamrielnetwork.gbooster.listeners;

import com.tamrielnetwork.gbooster.GBooster;
import com.tamrielnetwork.gbooster.boosters.BoosterType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerExpChange implements Listener {

	private final GBooster main = JavaPlugin.getPlugin(GBooster.class);

	@EventHandler
	public void onPlayerExpChange(PlayerExpChangeEvent event) {
		event.setAmount((event.getAmount() * (int) main.getActiveBoostersManager().getBoosterMultiplier(BoosterType.MINECRAFT, false)));
	}
}