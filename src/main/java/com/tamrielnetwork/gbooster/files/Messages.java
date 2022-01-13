/*
 Copyright (C) 2022  Leopold Meinel
 Visit https://github.com/TamrielNetwork/GBooster/blob/main/LICENSE for more details.
 */
package com.tamrielnetwork.gbooster.files;

import com.tamrielnetwork.gbooster.GBooster;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Messages {

	private final GBooster main = JavaPlugin.getPlugin(GBooster.class);
	private final File messagesFile;
	private final FileConfiguration messagesConf;

	public Messages() {
		messagesFile = new File(main.getDataFolder(), "messages.yml");
		saveMessagesFile();
		messagesConf = YamlConfiguration.loadConfiguration(messagesFile);
	}

	private void saveMessagesFile() {
		if (!messagesFile.exists()) {
			main.saveResource("messages.yml", false);
		}
	}

	public FileConfiguration getMessagesConf() {
		return messagesConf;
	}
}
