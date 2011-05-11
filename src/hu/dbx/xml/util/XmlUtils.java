package hu.dbx.xml.util;

import nu.xom.Document;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.xslt.XSLException;
import nu.xom.xslt.XSLTransform;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Csaba Harangozó
 * Date: 2011.05.11.
 * Time: 13:18
 */
public class XmlUtils {

    public static void processXml(String xml) throws IOException, ParsingException, XSLException {
        //System.out.println(xml);


        File style = new File("/home/csaba/src/XmlSaver/data/ns_remove.xsl");

        Document doc = XOMUtils.buildDocumentFromString(xml);
        Document stylesheet = XOMUtils.buildDocumentFromString(FileUtils.readFileToString(style));

        XSLTransform transform = new XSLTransform(stylesheet);

        Nodes output = transform.transform(doc);
        Document result = XSLTransform.toDocument(output);
        System.out.println(result.toXML());

        //System.out.println(doc.toXML());
        //XPathResolver resolver = new XPathResolver(doc);
        //System.out.println(resolver.get("S:Envelope/S:Body"));
    }
}
