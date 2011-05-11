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

    private XmlDao xmlDao = new XmlDaoImpl();
    public void saveResponse(String xmlResponse) {
        XmlUtils xmlUtils = new XmlUtils();

        try {
            Document d = xmlUtils.getPureDocument(xmlResponse);
            Document body = xmlUtils.getEnvelopeBody(d);

            //System.out.println(body.toXML());

            //System.out.println(xmlUtils.getProposalNumber(body));

            HResponse response = new HResponse();
            response.setXml(body.toXML());
            response.setProposalNumber(xmlUtils.getProposalNumber(body));

            xmlDao.saveResponse(response);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (XSLException e) {
            e.printStackTrace();
        }

    }

    public HResponse getResponseByProposalNumber(String proposalNumber) {
        HResponse response = xmlDao.getResponseByProposalNumber(proposalNumber);
        return response;
    }

    public static void main(String[] args) {

        Pilot pilot = new Pilot();

        File f = new File("/home/csaba/src/XmlSaver/data/sample_01.xml");
        try {
            pilot.saveResponse(FileUtils.readFileToString(f));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(pilot.getResponseByProposalNumber("9779970").getXml());
    }
}
