package org.mexican.habanero.tag.command.provider;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;

import org.mexican.habanero.tag.Tag;
import org.mexican.habanero.tag.TagHandler;
import org.mexican.habanero.util.command.provider.Provider;
import org.mexican.habanero.util.command.provider.exception.ProviderException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TagProvider implements Provider<Tag> {

	private final TagHandler tagHandler;
	
	@Override
	public Tag provide(CommandSender sender, String source) {
		Tag tag = tagHandler.getTag(source);
		
		if (tag != null) {
			return tag; 
		}
		
		throw new ProviderException("%red%Tag named '%white%%source%%red%' %red%was not found!", source);
	}
	
	@Override
	public List<String> suggest(CommandSender sender, String source) {
		List<String> completions = Lists.newArrayList();
		
		for (Tag tag : tagHandler.getTagStore()) {
			if (match(tag.getName(), source)) {
				completions.add(tag.getName());
			}
		}
		
		return completions;
	}
}
