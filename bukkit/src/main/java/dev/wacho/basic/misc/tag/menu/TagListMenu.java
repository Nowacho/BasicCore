package org.mexican.habanero.tag.menu;

import java.util.Map;

import org.bukkit.entity.Player;

import com.google.common.collect.Maps;

import org.mexican.habanero.tag.TagHandler;
import org.mexican.habanero.tag.TagType;
import org.mexican.habanero.tag.menu.button.TagOwnListButton;
import org.mexican.habanero.tag.menu.button.TagResetButton;
import org.mexican.habanero.tag.menu.button.TagTypeButton;
import org.mexican.habanero.util.menu.Menu;
import org.mexican.habanero.util.menu.button.Button;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TagListMenu extends Menu {

	private final TagHandler tagHandler;
	
	@Override
	public String getTitle(Player player) {
		return "&9Tags";
	}

	{
		setAutoUpdate(true);
	}
	
	@Override
	public Map<Integer, Button> getButtons(Player player) {
		Map<Integer, Button> buttons = Maps.newHashMap();
		
		buttons.put(4, new TagOwnListButton(tagHandler));
		
		buttons.put(10, new TagTypeButton(TagType.NORMAL, tagHandler));
		buttons.put(12, new TagTypeButton(TagType.SPECIAL, tagHandler));
		buttons.put(14, new TagTypeButton(TagType.COUNTRY, tagHandler));
		buttons.put(16, new TagTypeButton(TagType.PARTNER, tagHandler));
		
		buttons.put(22, new TagResetButton());
		
		return buttons;
	}
}
