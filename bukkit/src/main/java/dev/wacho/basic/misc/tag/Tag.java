package dev.wacho.basic.misc.tag;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bson.Document;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Tag implements Mongoable {

	private final UUID id;
	private String name;
	
	private String displayName;
	private String permission;
	private TagType type;
	
	private boolean purchasable;
	private long purchaseDuration;
	private int cost;
	
	public Tag(UUID id, String name) {
		this.id = id;
		this.name = name;
		
		this.displayName = "&6&l[Default Display]";
		this.permission = "helium.tag." + name;
		this.type = TagType.NORMAL;
		
		purchaseDuration = TimeUnit.DAYS.toMillis(30L);
	}
	
	@Override
	public UUID getId() {
		return id;
	}
	
	@Override
	public Document toDocument() {
		Document document = new Document("id", id.toString());
		
		document.put("name", name);
		
		document.put("display-name", displayName);
		document.put("permission", permission);
		document.put("type", type.name());
		
		document.put("purchasable", purchasable);
		document.put("purchase-duration", purchaseDuration);
		document.put("cost", cost);
		
		return document;
	}

	@Override
	public void fromDocument(Document document) {
		name = document.getString("name");
		
		displayName = document.getString("display-name");
		permission = document.getString("permission");
		type = TagType.valueOf(document.getString("type"));
		
		purchasable = document.getBoolean("purchasable", false);
		purchaseDuration = document.getLong("purchase-duration");
		cost = document.getInteger("cost", 0);
	}
	
	public boolean requirePermission() {
		return (!TextUtil.isNull(permission));
	}
}
