package dev.wacho.basic.misc.friend.request;

import com.google.gson.JsonObject;
import dev.wacho.basic.utils.json.JsonSerializer;

public class FriendRequestJsonSerializer implements JsonSerializer<FriendRequest> {

    @Override
    public JsonObject serialize(FriendRequest friendRequest) {
        JsonObject object = new JsonObject();
        object.addProperty("sender", friendRequest.getSender().toString());
        object.addProperty("target", friendRequest.getTarget().toString());
        return object;
    }
}
