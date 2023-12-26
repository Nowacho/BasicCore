package org.mexican.habanero.tag.prompt;

import org.bukkit.entity.Player;

import org.mexican.habanero.tag.Tag;
import org.mexican.habanero.tag.menu.TagEditorMenu;
import org.mexican.habanero.util.Callback;
import org.mexican.habanero.util.prompt.Prompt;
import org.mexican.habanero.util.prompt.defaults.StringPrompt;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TagEditStringPrompt extends StringPrompt {

	private final Tag tag;
	private final Callback<String> callback;
	
	@Override
	public void handleBegin(Player player) {
		player.closeInventory();
	}

	@Override
	public void handleCancel(Player player) {
		new TagEditorMenu(tag).openMenu(player);
	}

	@Override
	public Prompt<?> acceptInput(Player player, String value) {
		
		callback.callback(value);
		
		new TagEditorMenu(tag).openMenu(player);
		
		return null;
	}
}
