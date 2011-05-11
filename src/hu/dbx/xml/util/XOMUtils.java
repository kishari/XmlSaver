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

    private static JAXBContext ctx;

    static {
        try {
            ctx = JAXBContext.newInstance(Object.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static Document buildDocumentFromString(String xmlData) throws ParsingException, IOException {
        return new Builder().build(xmlData, null);
    }



//------------------------------------
    protected static Node getFirstNode(Node node, String childXPath) {
        final Nodes nodes = node.query(childXPath);
        return nodes != null && nodes.size() > 0 ? nodes.get(0) : null;
    }

    public static String getSingleXPathValue(Node node, String childXPath) {
        return getFirstNode(node, childXPath).getValue();
    }

    public static String getSingleXPathValueNullSafe(Node node, String childXPath) {
        final Node firstNode = getFirstNode(node, childXPath);
        return firstNode == null ? null : firstNode.getValue();
    }

    public static Object getSimpleObjectXPathValue(Node node, String childXPath) {
        final Node pathNode = getFirstNode(node, childXPath);
        if (pathNode instanceof Element && ((Element)pathNode).getAttribute("type", "http://www.w3.org/2001/XMLSchema-instance") == null) {
            return pathNode.getValue();
        } else {
            final String xmlRepresentation = pathNode.toXML();
            try {
                Unmarshaller um = ctx.createUnmarshaller();
                final JAXBElement<Object> jaxbElementInt = um.unmarshal(new StreamSource(new StringReader(xmlRepresentation)), Object.class);
                return jaxbElementInt.getValue();
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
