package org.mexican.habanero.tag.menu;

import java.util.Map;

import org.bukkit.entity.Player;

import com.google.common.collect.Maps;

import org.mexican.habanero.tag.Tag;
import org.mexican.habanero.tag.TagHandler;
import org.mexican.habanero.tag.menu.button.TagInfoButton;
import org.mexican.habanero.util.menu.button.Button;
import org.mexican.habanero.util.menu.pagination.PaginatedMenu;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TagViewAllMenu extends PaginatedMenu {

	private final TagHandler tagHandler;
	
	@Override
	public String getPrePaginatedTitle(Player player) {
		return "&9There are &r" + tagHandler.getTagStore().size() + "&9 tags";
	}

	@Override
	public Map<Integer, Button> getAllPagesButtons(Player player) {
		Map<Integer, Button> buttons = Maps.newHashMap();
		
		for (Tag tag : tagHandler.getTagStore()) {
			buttons.put(buttons.size(), new TagInfoButton(tag, true, false));
		}
		
		return buttons;
	}
}
