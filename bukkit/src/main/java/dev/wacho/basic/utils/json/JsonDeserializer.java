package dev.wacho.basic.utils.json;

import com.google.gson.JsonObject;

public interface JsonDeserializer<T> {

    T deserialize(JsonObject object);

}
