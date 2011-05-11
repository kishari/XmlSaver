package hu.dbx;

import hu.dbx.xml.dao.HResponse;
import hu.dbx.xml.dao.XmlDao;
import hu.dbx.xml.dao.XmlDaoImpl;
import hu.dbx.xml.util.XmlUtils;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.xslt.XSLException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Csaba Harangozó
 * Date: 2011.05.11.
 * Time: 13:17
 */
public class Pilot {

    public static void main(String[] args) {

        String path = "/home/csaba/src/XmlSaver/data/sample_01.xml";

        File f = new File(path);
        String xmlAsString = "";

        XmlUtils xmlUtils = new XmlUtils();
        try {
            xmlAsString = FileUtils.readFileToString(f);

            Document d = xmlUtils.getPureDocument(xmlAsString);
            Document body = xmlUtils.getEnvelopeBody(d);

            System.out.println(body.toXML());

            System.out.println(xmlUtils.getProposalNumber(body));

            HResponse response = new HResponse();
            response.setXml(body.toXML());
            response.setProposalNumber(xmlUtils.getProposalNumber(body));

            new XmlDaoImpl().saveResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (XSLException e) {
            e.printStackTrace();
        }
    }
}
