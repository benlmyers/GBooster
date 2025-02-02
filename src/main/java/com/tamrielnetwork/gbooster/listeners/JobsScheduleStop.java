/*
 * GBooster is a Spigot Plugin providing Global Boosters for Jobs McMMO and Minecraft.
 * Copyright © 2022 Leopold Meinel & contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see https://github.com/TamrielNetwork/GBooster/blob/main/LICENSE
 */

package com.tamrielnetwork.gbooster.listeners;

import com.gamingmesh.jobs.api.JobsScheduleStopEvent;
import com.gamingmesh.jobs.container.CurrencyType;
import com.tamrielnetwork.gbooster.GBooster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class JobsScheduleStop implements Listener {

	private final GBooster main = JavaPlugin.getPlugin(GBooster.class);

	@EventHandler
	public void onBoosterStop(@NotNull JobsScheduleStopEvent event) {

		main.getActiveBoostersManager().addJobsBooster(-(event.getSchedule().getBoost(CurrencyType.EXP) + 1));
	}

}