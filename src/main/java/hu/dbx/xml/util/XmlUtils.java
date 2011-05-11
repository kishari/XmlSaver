package hu.dbx.xml.util;

import nu.xom.Document;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.xslt.XSLException;
import nu.xom.xslt.XSLTransform;
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Csaba Harangozó
 * Date: 2011.05.11.
 * Time: 13:18
 */
public class XmlUtils {

    public Document getPureDocument(String xml) throws XSLException, IOException, ParsingException {
        InputStream styleInputStream = getClass().getClassLoader().getResourceAsStream("ns_remove.xsl");
        String styleAsString = IOUtils.toString(styleInputStream, "UTF-8");

        Document origin = XOMUtils.buildDocumentFromString(xml);
        Document stylesheet = XOMUtils.buildDocumentFromString(styleAsString);

        XSLTransform transform = new XSLTransform(stylesheet);
        Nodes output = transform.transform(origin);

        Document pureDoc = XSLTransform.toDocument(output);

        return pureDoc;
    }

    public Document getEnvelopeBody(Document doc) throws IOException, ParsingException, XSLException {
        InputStream styleInputStream = getClass().getClassLoader().getResourceAsStream("get_body.xsl");
        String styleAsString = IOUtils.toString(styleInputStream, "UTF-8");
        Document stylesheet = XOMUtils.buildDocumentFromString(styleAsString);

        XSLTransform transform = new XSLTransform(stylesheet);
        Nodes output = transform.transform(doc);

        Document bodyDoc = XSLTransform.toDocument(output);

        return bodyDoc;
    }

    public String getProposalNumber(Document doc) {
        return XOMUtils.getSingleXPathValueNullSafe(doc, "Body/calculationResponse/general/proposalNumber");
    }

}
