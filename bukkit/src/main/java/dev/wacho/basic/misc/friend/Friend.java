package dev.wacho.basic.misc.friend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Data
public class Friend {

    @Getter private static FriendJsonDeserializer deserializer = new FriendJsonDeserializer();
    @Getter private static FriendJsonSerializer serializer = new FriendJsonSerializer();

    private UUID friend;

}
