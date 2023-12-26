package org.mexican.habanero.tag.menu;

import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.mexican.habanero.Habanero;
import org.mexican.habanero.profile.Profile;
import org.mexican.habanero.tag.Tag;
import org.mexican.habanero.tag.TagHandler;
import org.mexican.habanero.tag.TagType;
import org.mexican.habanero.tag.menu.button.TagInfoButton;
import org.mexican.habanero.tag.menu.button.TagResetButton;
import org.mexican.habanero.util.TextUtil;
import org.mexican.habanero.util.menu.button.Button;
import org.mexican.habanero.util.menu.button.impl.BackButton;
import org.mexican.habanero.util.menu.pagination.FilterablePaginatedMenu;
import org.mexican.habanero.util.menu.pagination.PageFilter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TagOwnListMenu extends FilterablePaginatedMenu<Tag> {

	private final TagHandler tagHandler;
	
	{
		setAutoUpdate(true);
	}
	
	@Override
	public String getPrePaginatedTitle(Player player) {
		return "&9Own Tags (" + getFilteredCount() + ")";
	}
	
	@Override
	public Map<Integer, Button> getFilteredButtons(Player player) {
		Map<Integer, Button> buttons = Maps.newHashMap();
		
		getButtons().put(4, new TagResetButton());
		getButtons().put(36, new BackButton(new TagListMenu(tagHandler)));
		
		obj:
		for(Tag tag : tagHandler.getTagStore()) {
			Profile profile = Habanero.get().getProfileHandler().getProfile(player.getUniqueId());
			if (tag.requirePermission()) {
				if (profile.canSelectTag(tag)) {
					for(PageFilter<Tag> filter : getFilters()) {
						if(filter.test(tag)) {
							buttons.put(buttons.size(), new TagInfoButton(tag, false, false));
							continue obj;
						}
					}
				}
			}
		}
		
		return buttons;
	}

	@Override
	public List<PageFilter<Tag>> generateFilters() {
		List<PageFilter<Tag>> filters = Lists.newArrayList();
		
		filters.add(new PageFilter<Tag>("Type: All", (tag -> tag.getType() != null), true));
		for(TagType type : TagType.values()) {
			filters.add(new PageFilter<Tag>("Type: " + TextUtil.getPrettyName(type.name()), (tag -> tag.getType() == type), false));
		}
		
		return filters;
	}
	
	public int getFilteredCount() {
		int count = 0;
		
		obj:
		for(Tag rank : tagHandler.getTagStore()) {
			for(PageFilter<Tag> filter : getFilters()) {
				if(filter.test(rank)) {
					count++;
					continue obj;
				}
			}
		}
		
		return count;
	}
}
