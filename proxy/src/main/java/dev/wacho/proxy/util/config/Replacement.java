package dev.wacho.proxy.util.config;

import com.google.common.collect.Maps;
import dev.wacho.proxy.util.ColorUtil;
import lombok.Getter;

import java.util.Map;

@Getter
public class Replacement {
	
    private Map<Object, Object> replacements = Maps.newHashMap();
    
    private String message;

    public Replacement(String message) {
		this.message = message;
	}
    
    public Replacement add(Object placeholder, Object replacement) {
        replacements.put(placeholder, replacement);
        return this;
    }

    public String toString() {
        for(Object placeholder : replacements.keySet()) {
        	message = message.replace(String.valueOf(placeholder), String.valueOf(replacements.get(placeholder)));
        }
        
        return ColorUtil.colorize(message);
    }
}
