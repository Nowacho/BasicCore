package org.mexican.habanero.tag.menu;

import java.util.Map;

import org.bukkit.entity.Player;

import com.google.common.collect.Maps;

import org.mexican.habanero.tag.Tag;
import org.mexican.habanero.tag.TagHandler;
import org.mexican.habanero.tag.TagType;
import org.mexican.habanero.tag.menu.button.TagInfoButton;
import org.mexican.habanero.tag.menu.button.TagResetButton;
import org.mexican.habanero.util.menu.button.Button;
import org.mexican.habanero.util.menu.button.impl.BackButton;
import org.mexican.habanero.util.menu.pagination.PaginatedMenu;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TagTypeListMenu extends PaginatedMenu {

	private final TagType type;
	private final TagHandler tagHandler;
	
	@Override
	public String getPrePaginatedTitle(Player player) {
		return "&9Select " + type.name().toLowerCase() + " tag";
	}
	
	{
		setAutoUpdate(true);
	}

	@Override
	public Map<Integer, Button> getAllPagesButtons(Player player) {
		Map<Integer, Button> buttons = Maps.newHashMap();
		
		getButtons().put(4, new TagResetButton());
		getButtons().put(36, new BackButton(new TagListMenu(tagHandler)));
		
		for (Tag tag : tagHandler.getTagStore()) {
			if (tag.getType().equals(type)) {
				buttons.put(buttons.size(), new TagInfoButton(tag, false, false));
			}
		}
		
		return buttons;
	}
}
