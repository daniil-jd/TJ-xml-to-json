package ru.tj.utils;

import ru.tj.models.NodeObject;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StaxParserDemo implements StaxParser{

    @Override
    public NodeObject parse(InputStream input) {
        List<NodeObject> nodeObjectList = new ArrayList<>();
        try {
            XMLInputFactory inFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = inFactory.createXMLEventReader(input);

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                if (!event.toString().contains("\n") && (event.isStartElement() || event.isCharacters() || event.isEndElement())) {
                    switch (event.getEventType()) {

                        case XMLStreamConstants.START_ELEMENT: {
                            String name = event.toString().substring(1, event.toString().length()-1);

                            if (NodeObjUtil.getNodeByName(name, nodeObjectList) == null){
                                NodeObject node = NodeObject.builder().name(name).build();
                                nodeObjectList.add(node);

                                NodeObject parent = NodeObjUtil.lastNotClosed(nodeObjectList);
                                if (parent != null){
                                    parent.setChild(node);
                                }
                            }
                            break;
                        }

                        case XMLStreamConstants.CHARACTERS: {
                            NodeObject node = nodeObjectList.get(nodeObjectList.size()-1);
                            double value = Double.parseDouble(event.toString().replaceAll("[^\\.0-9\\+]", ""));
                            node.setValue(value);
                            break;
                        }

                        case XMLStreamConstants.END_ELEMENT: {
                            String name = event.toString().substring(2,event.toString().length()-1);

                            NodeObject node = NodeObjUtil.getNodeByName(name, nodeObjectList);
                            node.setEnd(true);
                            break;
                        }
                    }
                }
            }

        } catch (XMLStreamException ex) {
            Logger.getLogger(StaxParserDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nodeObjectList.get(0);
    }
}
