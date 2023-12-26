package dev.wacho.basic.misc.friend.request;

import com.google.gson.JsonObject;
import dev.wacho.basic.utils.json.JsonDeserializer;

import java.util.UUID;

public class FriendRequestJsonDeserializer implements JsonDeserializer<FriendRequest> {

    @Override
    public FriendRequest deserialize(JsonObject object) {
        return new FriendRequest(UUID.fromString(object.get("sender").getAsString()), UUID.fromString(object.get("target").getAsString()));
    }
}
