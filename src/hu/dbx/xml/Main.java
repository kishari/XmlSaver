package hu.dbx.xml;

import hu.dbx.xml.util.XmlUtils;
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
public class Main {

    public static void main(String[] args) {

        String path = "/home/csaba/src/XmlSaver/data/sample_01.xml";

        File f = new File(path);
        String xmlAsString = "";

        try {
            xmlAsString = FileUtils.readFileToString(f);

            new XmlUtils().getPureDocument(xmlAsString);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (XSLException e) {
            e.printStackTrace();
        }
    }
}
