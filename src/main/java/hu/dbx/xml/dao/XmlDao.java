package hu.dbx.xml.dao;

/**
 * Created by IntelliJ IDEA.
 * User: Csaba Harangozó
 * Date: 2011.05.11.
 * Time: 17:51
 */
public interface XmlDao {
    public void saveResponse(HResponse response);

    public HResponse getResponseByProposalNumber(String proposalNumber);
}
