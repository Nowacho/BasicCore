package dev.wacho.basic.misc.friend;
import com.google.gson.JsonObject;
import dev.wacho.basic.utils.json.JsonDeserializer;

import java.util.UUID;

public class FriendJsonDeserializer implements JsonDeserializer<Friend> {

    @Override
    public Friend deserialize(JsonObject object) {
        return new Friend(UUID.fromString(object.get("uuid").getAsString())
        );
    }
}
