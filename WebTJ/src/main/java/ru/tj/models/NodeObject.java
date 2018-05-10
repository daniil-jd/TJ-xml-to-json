package ru.tj.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude = "childList")
@Builder
public class NodeObject {
    private String name;
    private double value;
    private boolean isEnd;
    private List<NodeObject> childList;

    public void setChild (NodeObject node) {
        if (childList == null)
            childList = new ArrayList<>();

        childList.add(node);
    }

    @Override
    public String toString() {
        String start = "NodeObject " + name + " {\n" +
                "value='" + value + '\'' +
                ", \nisEnd=" + isEnd + "\n";
        String child = "children: \n";
        if (childList != null){
            for (NodeObject node : childList) {
                child += "   " + node.getName() + "\n";
            }
        }
        else child += "   empty \n";


        return start + child;
    }
}
