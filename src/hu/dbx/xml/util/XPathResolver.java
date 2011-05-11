package hu.dbx.xml.util;

import nu.xom.Document;
import nu.xom.Node;
import nu.xom.Nodes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Csaba Harangozó
 * Date: 2011.05.11.
 * Time: 13:38
 */
public class XPathResolver {
    private Document document;

    public XPathResolver(Document document) {
        this.document = document;
    }

    public String get(String xPath) {
        return get(document, xPath);
    }

    public String get(Node node, String xPath) {
        try {
            return XOMUtils.getSingleXPathValue(node, xPath);
        } catch (NullPointerException e) {
            return "???";
        }
    }

    public List<Node> all(String xPath) {
        return all(document, xPath);
    }

    public List<Node> all(Node node, String xPath) {
        final Nodes xmlNodes = node.query(xPath);
        List<Node> nodes = new ArrayList<Node>(xmlNodes.size());
        for (int i = 0; i < xmlNodes.size(); i++) {
            nodes.add(xmlNodes.get(i));
        }
        return nodes;
    }

    public List<String> allValues(Node node, String xPath) {
        final List<Node> nodes = all(node, xPath);
        if (nodes == null) {
            return null;
        }
        final List<String> values = new ArrayList<String>(nodes.size());
        for (Node lNode : nodes) {
            values.add(lNode.getValue());
        }
        return values;
    }

    public List<String> allValues(String xPath) {
        return allValues(document, xPath);
    }
}

