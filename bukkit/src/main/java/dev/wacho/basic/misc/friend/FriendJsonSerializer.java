package dev.wacho.basic.misc.friend;

import com.google.gson.JsonObject;
import dev.wacho.basic.utils.json.JsonSerializer;

public class FriendJsonSerializer implements JsonSerializer<Friend> {

    @Override
    public JsonObject serialize(Friend friend) {
        JsonObject object = new JsonObject();
        object.addProperty("uuid", friend.getFriend().toString());
        return object;
    }

}
