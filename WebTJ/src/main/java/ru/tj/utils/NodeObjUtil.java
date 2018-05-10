package ru.tj.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.tj.models.NodeObject;

import java.util.List;
import java.util.ListIterator;


public class NodeObjUtil {
    public static NodeObject getNodeByName (String name, List<NodeObject> nodeObjectList){
        for (NodeObject node : nodeObjectList){
            if (node.getName().equals(name))
                return node;
        }
        return null;
    }

    public static NodeObject lastNotClosed (List<NodeObject> nodeObjectList){
        ListIterator<NodeObject> iterator = nodeObjectList.listIterator(nodeObjectList.size()-1);

        while (iterator.hasPrevious()){
            NodeObject node = iterator.previous();
            if (!node.isEnd()){
                return node;
            }
        }
        return null;
    }

    public static void setValuesToParents (NodeObject no){
        if (no.getChildList() != null) {
            for (NodeObject node : no.getChildList()){
                setValuesToParents(node);
                no.setValue((no.getValue()+node.getValue()));

            }
        }
    }

    public static String convert (NodeObject nodeObject) {

        NodeObject obj1 = nodeObject;

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(NodeObject.class, new NodeObjectConverter());
        Gson gson1 = builder
                .setPrettyPrinting()
                .create();

        return (gson1.toJson(obj1));
    }
}
