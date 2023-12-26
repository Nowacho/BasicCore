package dev.wacho.basic.misc.friend.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Data
public class FriendRequest {

    @Getter private static FriendRequestJsonDeserializer deserializer = new FriendRequestJsonDeserializer();
    @Getter private static FriendRequestJsonSerializer serializer = new FriendRequestJsonSerializer();

    private UUID sender;
    private UUID target;
}
