package org.mexican.habanero.tag.network.packet;

import java.util.UUID;

import org.mexican.habanero.shared.database.redis.packet.Packet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TagUpdatePacket implements Packet {

    private final UUID id;
}
