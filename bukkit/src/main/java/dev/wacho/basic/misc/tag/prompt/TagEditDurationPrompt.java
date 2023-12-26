package dev.wacho.basic.misc.tag.prompt;

import java.time.Duration;

import dev.wacho.basic.misc.tag.Tag;
import dev.wacho.basic.utils.CC;
import dev.wacho.basic.utils.prompt.Prompt;
import dev.wacho.basic.utils.prompt.defaults.DurationPrompt;
import org.bukkit.entity.Player;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TagEditDurationPrompt extends DurationPrompt {

	private final Tag tag;
	private final Callback<Duration> callback;
	
	@Override
	public void handleBegin(Player player) {
		player.closeInventory();
		player.sendMessage(CC.translate("&aWrite a purchase duration for '&r" + tag.getName() + "&a' tag..."));
		player.sendMessage(CC.translate("&aExample format: &730d60m60s"));
	}

	@Override
	public void handleCancel(Player player) {
		new TagEditorMenu(tag).openMenu(player);
	}

	@Override
	public Prompt<?> acceptInput(Player player, Duration value) {
		
		callback.callback(value);
		
		new TagEditorMenu(tag).openMenu(player);
		
		return null;
	}
}
