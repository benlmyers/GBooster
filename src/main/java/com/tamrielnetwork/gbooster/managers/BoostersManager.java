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

package com.tamrielnetwork.gbooster.managers;

import com.tamrielnetwork.gbooster.GBooster;
import com.tamrielnetwork.gbooster.boosters.Booster;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BoostersManager {

	private final GBooster main = JavaPlugin.getPlugin(GBooster.class);
	private final List<Booster> boosters = new ArrayList<>();

	public void loadBoosters() {

		for (String key : Objects.requireNonNull(main.getConfig().getConfigurationSection("boosters")).getKeys(false)) {
			boosters.add(new Booster(key, Objects.requireNonNull(main.getConfig().getConfigurationSection("boosters." + key))));
		}
	}

	public Booster getBoosterById(@NotNull String id) {

		return boosters.stream()
				.filter(booster -> booster.getId().equals(id))
				.findFirst().orElse(null);
	}

	public boolean isBooster(@NotNull String id) {

		return boosters.stream()
				.map(Booster::getId)
				.noneMatch(boosterId -> boosterId.equals(id));
	}

	public List<Booster> getBoosters() {

		return boosters;
	}

}