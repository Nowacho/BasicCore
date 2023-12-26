package dev.wacho.proxy.util.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Config {

	private File file;
	
	@Getter private Configuration configuration;
	
	public Config(Plugin plugin, String name) {		
		this.createConfig(plugin, name);
	}
	
	private void createConfig(Plugin plugin, String name) {
		if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdirs();
		}
		
		this.file = new File(plugin.getDataFolder(), name);
		if(!this.file.exists()) {
			try {
				this.file.createNewFile();
                try (InputStream is = plugin.getResourceAsStream(name);
                    OutputStream os = new FileOutputStream(this.file)) {
                    ByteStreams.copy(is, os);
                }
            } catch (IOException e) {
                throw new RuntimeException("Unable to create configuration file", e);
            }
		}
		
		try {
			this.loadConfiguration();
		} catch (Exception e) {
			throw new RuntimeException("Unable to load configuration file", e);
		}
	}
	
	public void loadConfiguration() {
		try {
			this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveConfiguration() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.configuration, this.file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
