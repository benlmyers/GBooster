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

package com.tamrielnetwork.gbooster.api;

import com.tamrielnetwork.gbooster.boosters.Booster;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class BoosterActivateEvent extends Event implements Cancellable {

	private static final HandlerList HANDLERS = new HandlerList();
	private final Booster booster;
	private final Player player;
	private boolean isCancelled;

	public BoosterActivateEvent(Booster booster, Player player) {

		this.booster = booster;
		this.player = player;
		this.isCancelled = false;
	}

	public static HandlerList getHandlerList() {

		return HANDLERS;
	}

	public Booster getBooster() {

		return this.booster;
	}

	public Player getPlayer() {

		return player;
	}

	@Override
	public boolean isCancelled() {

		return this.isCancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {

		this.isCancelled = cancel;
	}

	public @NotNull HandlerList getHandlers() {

		return getHandlerList();
	}

}