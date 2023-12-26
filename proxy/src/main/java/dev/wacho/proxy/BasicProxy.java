package dev.wacho.proxy;

import dev.wacho.proxy.command.basic.VersionCommand;
import dev.wacho.proxy.command.chat.TestHubCommand;
import dev.wacho.proxy.command.chat.SendDevCommand;
import dev.wacho.proxy.command.chat.SendSoupCommand;
import dev.wacho.proxy.command.chat.SendSurvivalCommand;
import dev.wacho.proxy.command.staff.WhoisCommand;
import dev.wacho.proxy.handler.PlayerHandler;
import dev.wacho.proxy.handler.VipChatHandler;
import dev.wacho.proxy.util.FileConfig;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Getter
public class BasicProxy extends Plugin {

	private static Optional<BasicProxy> instance;
	public static Configuration config;
	
	@Override
	public void onEnable() {
		this.onConfig();
		this.reloadConfig();

		PluginManager pluginManager = getProxy().getPluginManager();
		//pluginManager.registerCommand(this, new TestHubCommand(this));
		pluginManager.registerCommand(this, new SendSoupCommand(this));
		pluginManager.registerCommand(this, new SendSurvivalCommand(this));
		pluginManager.registerCommand(this, new SendDevCommand(this));
		pluginManager.registerCommand(this, new WhoisCommand(this));
		pluginManager.registerCommand(this, new WhoisCommand(this));
		pluginManager.registerCommand(this, new VersionCommand(this));

		pluginManager.registerListener(this, new PlayerHandler());
		pluginManager.registerListener(this, new VipChatHandler());
	}
	
	@Override
	public void onDisable() {

	}

	public void onConfig() {
		try {
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(FileConfig.startConfig(this, "settings.yml"));
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void saveConfig() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(BasicProxy.getInst().getConfig(), new File(this.getDataFolder(), "settings.yml"));
		}
		catch (IOException e) {
			throw new RuntimeException("Unable to save configuration", e);
		}
	}

	public void reloadConfig() {
		try {
			config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(this.getDataFolder(), "settings.yml"));
		}
		catch (IOException e) {
			throw new RuntimeException("Unable to load configuration", e);
		}
	}

	public Configuration getConfig() { return config; }

	public static BasicProxy getInst() {
		return instance.orElseThrow(() -> new IllegalStateException("Basic instance is not initialized!"));
	}
}
