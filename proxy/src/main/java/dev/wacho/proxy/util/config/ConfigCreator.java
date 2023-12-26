package dev.wacho.proxy.util.config;

import dev.wacho.proxy.util.config.annotation.Path;
import net.md_5.bungee.api.plugin.Plugin;

import java.lang.reflect.Field;

public class ConfigCreator {

	private final Config config;
		
	public ConfigCreator(Plugin plugin, String file) {
		
		this.config = new Config(plugin, file);
		
		reload();
	}
	
	private void setup() {		
		for (Field field : this.getClass().getDeclaredFields()) {
			if (!field.isAnnotationPresent(Path.class)) {
				continue;
			}
			
			Path path = field.getAnnotation(Path.class);
			if (config.getConfiguration().contains(path.id())) {
				try {
					
					field.setAccessible(true);
					
					Object update = deserialize(path, field.getType());					
					field.set(null, update);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			} else {
				config.getConfiguration().set(path.id(), serialize(path, field));
			}
		}
		
		config.saveConfiguration();
	}
	
	public void reload() {
		config.loadConfiguration();
		config.saveConfiguration();
		setup();
	}
	
	private <T> Object deserialize(Path path, Class<?> type) {
    	return config.getConfiguration().get(path.id());
    }
	
	private Object serialize(Path path, Field field) {
		try {
			return field.get(null);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
