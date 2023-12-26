package org.mexican.habanero.tag.network;

import org.mexican.habanero.shared.database.redis.packet.PacketListener;
import org.mexican.habanero.shared.database.redis.packet.annotation.IncomingPacketHandler;
import org.mexican.habanero.tag.Tag;
import org.mexican.habanero.tag.TagHandler;
import org.mexican.habanero.tag.network.packet.TagDeletePacket;
import org.mexican.habanero.tag.network.packet.TagUpdatePacket;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TagPacketListener implements PacketListener {

	private final TagHandler tagHandler;
	
    @IncomingPacketHandler
    public void onDelete(TagDeletePacket packet) {
        Tag tag = tagHandler.getTag(packet.getId());
        if (tag != null) {
        	tagHandler.getTagStore().remove(tag);
        }
    }

    @IncomingPacketHandler
    public void onUpdate(TagUpdatePacket packet) {
        tagHandler.pullUpdate(packet.getId());
    }
}
