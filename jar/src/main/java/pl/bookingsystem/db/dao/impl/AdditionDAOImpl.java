package pl.bookingsystem.db.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.Session;

import pl.bookingsystem.db.dao.AdditionDAO;
import pl.bookingsystem.db.entity.Addition;
import org.apache.log4j.Logger;
import pl.bookingsystem.db.utils.HibernateUtil;

import java.util.List;

/**
 * Author: rastek
 * Date: 11.12.13 @ 21:11
 */
public class AdditionDAOImpl extends GenericDAOImpl<Addition, Long> implements AdditionDAO {
    private static Logger logger = Logger.getLogger(ClientDAOImpl.class);

    public List<Addition> getAdditionsBy(List<String> additions, String by) {

        List<Addition> additionList = null;
        String sql = "FROM Addition a where";
        for (int i=0; i<additions.size(); i++) {
            sql= String.format("%s a.%s = '%s'", sql, by, additions.get(i));

            if (!(i==additions.size()-1)){
                sql=sql+" and";
            }
        }
        System.out.println(sql);
    try{

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query query = session.createQuery(sql);
        additionList = query.list();

    } catch (NonUniqueResultException ex) {
        logger.error("FIND Addiotion.java: " + ex.getMessage());
        System.out.println("Query returned more than one results.");
    } catch (HibernateException ex) {
        logger.error("FIND Addition.java: " + ex.getMessage());
    }

    return additionList;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
