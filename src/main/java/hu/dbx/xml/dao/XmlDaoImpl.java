package hu.dbx.xml.dao;

import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Csaba Harangozó
 * Date: 2011.05.11.
 * Time: 17:52
 */
public class XmlDaoImpl extends HibernateDaoSupport implements XmlDao {

    private static final SessionFactory sessionFactory;
    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void saveResponse(final HResponse response) {
        createHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Transaction t = session.beginTransaction();
                session.save(response);
                t.commit();
                return null;
            }
        });
    }

    public HResponse getResponseByProposalNumber(final String proposalNumber) {
        return (HResponse) createHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria c = session.createCriteria(HResponse.class);
                c.add(Restrictions.eq("proposalNumber", proposalNumber));

                //return c.uniqueResult();
                return c.list().get(0);
            }
        });
    }

    private HibernateTemplate createHibernateTemplate() {
        return new HibernateTemplate(sessionFactory);
    }
}
