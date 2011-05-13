package hu.dbx.xml.util;

/**
 * Created by IntelliJ IDEA.
 * User: Csaba Harangozó
 * Date: 2011.05.11.
 * Time: 13:30
 */

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.StringReader;

import nu.xom.*;

public class XOMUtils {

    public static Document buildDocumentFromString(String xmlData) throws ParsingException, IOException {
        return new Builder().build(xmlData, null);
    }

    public static String getSingleXPathValueNullSafe(Node node, String childXPath) {
        final Node firstNode = getFirstNode(node, childXPath);
        return firstNode == null ? null : firstNode.getValue();
    }

    private static Node getFirstNode(Node node, String childXPath) {
        final Nodes nodes = node.query(childXPath);
        return nodes != null && nodes.size() > 0 ? nodes.get(0) : null;
    }

}
