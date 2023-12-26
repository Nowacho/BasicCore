package dev.wacho.basic.misc.tag;

import com.google.common.collect.Sets;
import dev.wacho.basic.Basic;
import lombok.Getter;
import org.bson.Document;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

@Getter
public class TagHandler {

	private final Basic instance;
	
	private final Set<Tag> tagStore;
	
	public TagHandler(Basic instance) {
		this.instance = instance;
		
		this.tagStore = Sets.newConcurrentHashSet();
		
		instance.getCommandHandler().getBinder().bind(Tag.class).toProvider(new TagProvider(this));
		
		instance.getCommandHandler().register(new TagCommands());
		
		instance.getMongoManager().getMongoDatabase().getCollection("tags").createIndex(new Document("id", 1));
		instance.getRedis().registerListener(new TagPacketListener(this));
		
		loadTags();
		
		BroadcastUtil.sendConsole("&9[Helium Logger] &7&oLoaded '&r" + tagStore.size() + "&7&o' tags from database!");
	}
	
	private void loadTags() {
		for (Document document : instance.getMongo().find("tags")) {
            try {
            	Tag tag = new Tag(UUID.fromString(document.getString("id")), document.getString("name"));
                
                if (instance.getMongo().load(tag, "tags")) tagStore.add(tag);
            } catch (Exception ex) {
                if (document.containsKey("id")) {
                    throw new RuntimeException("Failed to load tag from document: " + document.getString("id"), ex);
                } else {
                    throw new RuntimeException("Failed to load tag from document: Couldn't identify tag ID", ex);
                }
            }
        }
	}
	
	public Tag createTag(String name) {
		Tag tag = new Tag(UUID.randomUUID(), name);
		
		tagStore.add(tag);
		
		saveTag(tag);
		
		return tag;
	}
	
    public void saveTag(Tag tag) {
    	instance.getMongoManager().getMongoDatabase()..save(tag, "tags");
    	instance.getRedis().sendPacket(new TagUpdatePacket(tag.getId()));
    }

    public void deleteTag(Tag tag) {
        instance.getRedis().sendPacket(new TagDeletePacket(tag.getId()));

        tagStore.remove(tag);

        instance.getMongo().getCollection("tags").deleteOne(new Document("id", tag.getId().toString()));
    }

    public void pullUpdate(UUID id) {
        Document document = instance.getMongo().getCollection("tags").find(new Document("id", id.toString())).first();
        if (document != null) {
            Tag freshRank = new Tag(UUID.fromString(document.getString("id")), document.getString("name"));
            freshRank.fromDocument(document);

            tagStore.remove(getTag(id));
            tagStore.add(freshRank);
        }
    }
    
    public Tag getTag(String name) {
        return tagStore.stream().filter(tag -> tag.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    
    public Tag getTag(UUID id) {
    	Iterator<Tag> iterator = tagStore.iterator();
    	
    	while (iterator.hasNext()) {
			Tag tag = iterator.next();
			
			if (tag.getId().equals(id)) {
				return tag;
			}
		}
    	
    	return null;
    }
}
