package hu.dbx.xml.dao;

import org.hibernate.annotations.Type;
import javax.persistence.*;
/**
 * Created by IntelliJ IDEA.
 * User: Csaba Harangozó
 * Date: 2011.05.11.
 * Time: 17:13
 */
@Entity
@Table(name = "calc_responses")
public class HResponse {
    private int id;
    private String xml;
    private String proposalNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "xml")
    @Type(type = "text")
    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    @Column(name = "proposal_number")
    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }
}
