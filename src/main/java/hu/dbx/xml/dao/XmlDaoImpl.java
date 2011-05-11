package hu.dbx.xml.dao;

import hu.dbx.xml.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by IntelliJ IDEA.
 * User: Csaba Harangozó
 * Date: 2011.05.11.
 * Time: 17:52
 */
public class XmlDaoImpl implements XmlDao {

    public void saveResponse(HResponse response) {
        SessionFactory session = HibernateUtil.getSessionFactory();
        Session sess = session.getCurrentSession();
        Transaction tx = sess.beginTransaction();
        sess.save(response);
        tx.commit();
        System.out.println("Record Inserted");
        session.close();
    }
}
