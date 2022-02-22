/*
 * GBooster is a Spigot Plugin providing Global Boosters for Jobs McMMO and Minecraft.
 * Copyright © 2022 Leopold Meinel
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

package com.tamrielnetwork.gbooster.utils.commands;

import com.tamrielnetwork.gbooster.GBooster;
import com.tamrielnetwork.gbooster.boosters.Booster;
import com.tamrielnetwork.gbooster.player.BoosterPlayer;
import com.tamrielnetwork.gbooster.utils.Chat;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class CmdSpec {

	private static final GBooster main = JavaPlugin.getPlugin(GBooster.class);

	public static boolean isInvalidCmd(@NotNull CommandSender sender, @NotNull String[] args, @NotNull String perm, int length, Booster booster, BoosterPlayer boosterPlayer) {
		switch (args[0]) {
			case "give":
				if (Cmd.isNotPermitted(sender, perm)) {
					return true;
				}
				if (Cmd.isArgsLengthNotEqualTo(sender, args, length)) {
					return true;
				}
				if (isInvalidBooster(sender, booster)) {
					return true;
				}
				if (isInvalidNumber(sender, args[3])) {
					return true;
				}
				return isInvalidBoosterPlayer(sender, boosterPlayer);
			case "use":
				if (Cmd.isInvalidSender(sender)) {
					return true;
				}
				if (Cmd.isNotPermitted(sender, perm)) {
					return true;
				}
				if (Cmd.isArgsLengthNotEqualTo(sender, args, length)) {
					return true;
				}
				if (isInvalidBooster(sender, booster)) {
					return true;
				}
				if (isOnCountdown(sender, boosterPlayer, booster)) {
					return true;
				}
				if (exceedsLimit(sender, booster)) {
					return true;
				}
				return !hasBooster(sender, boosterPlayer, booster);
			default:
				return true;
		}
	}

	public static boolean isInvalidCmd(@NotNull CommandSender sender, @NotNull String[] args, @NotNull String perm, int length) {
		if (Cmd.isNotPermitted(sender, perm)) {
			return true;
		}
		return Cmd.isArgsLengthNotEqualTo(sender, args, length);
	}

	private static boolean isInvalidBoosterPlayer(@NotNull CommandSender sender, BoosterPlayer boosterPlayer) {
		if (boosterPlayer == null) {
			Chat.sendMessage(sender, "invalid-player");
			return true;
		}

		return false;
	}

	private static boolean isInvalidBooster(@NotNull CommandSender sender, Booster booster) {
		if (booster == null) {
			Chat.sendMessage(sender, "invalid-booster");
			return true;
		}

		return false;
	}

	private static boolean isInvalidNumber(@NotNull CommandSender sender, @NotNull String arg) {
		if (!StringUtils.isNumeric(arg)) {
			Chat.sendMessage(sender, "invalid-amount");
			return true;
		}
		if (Integer.parseInt(arg) <= 0) {
			Chat.sendMessage(sender, "invalid-amount");
			return true;
		}
		return false;
	}

	private static boolean isOnCountdown(@NotNull CommandSender sender, @NotNull BoosterPlayer boosterPlayer, @NotNull Booster booster) {
		if (!boosterPlayer.canUseBooster(booster)) {
			Chat.sendMessage(sender, "countdown-active");
			return true;
		}
		return false;
	}

	private static boolean exceedsLimit(@NotNull CommandSender sender, @NotNull Booster booster) {
		if (!main.getActiveBoostersManager().canUseBooster(booster)) {
			Chat.sendMessage(sender, "limit");
			return true;
		}
		return false;
	}

	private static boolean hasBooster(@NotNull CommandSender sender, @NotNull BoosterPlayer boosterPlayer, @NotNull Booster booster) {
		if (!boosterPlayer.takeBooster(booster)) {
			Chat.sendMessage(sender, "no-booster");
			return true;
		}
		return false;
	}

}