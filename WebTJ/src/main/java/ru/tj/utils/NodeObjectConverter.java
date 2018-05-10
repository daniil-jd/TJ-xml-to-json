package ru.tj.utils;

import com.google.gson.*;
import ru.tj.models.NodeObject;

import java.lang.reflect.Type;


public class NodeObjectConverter implements JsonSerializer<NodeObject>{

    @Override
    public JsonElement serialize(NodeObject nodeObject, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("name", nodeObject.getName());
        object.addProperty("value", nodeObject.getValue());

        JsonArray array = new JsonArray();
        if (nodeObject.getChildList() != null) {
            for (NodeObject no : nodeObject.getChildList()) {
                array.add(jsonSerializationContext.serialize(no));
            }
            object.add("childList", array);
        }
        return object;
    }
}
