package com.jwt.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import com.jwt.hibernate.bean.stock;

public class stockDAO {
	public List<stock> getDetails(int permno){
		List<stock> list = null;
		
    	try {
           
            Configuration configuration = new Configuration().configure();
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
 
            String Max="max";
            Query<?> query = session.createQuery("from stock where PERMNO=' "+permno+" '   ");
         
            List<stock> result = (List<stock>) query.list();
           
       /*  for(stock max : result)
            {
            	System.out.println("Type: "+max.getPRC()+", Date: "+max.getDate() );
            }
            
       */
            
            transaction.commit();
            session.close();
            System.out.println("\n\n Retrieved \n");
            return result;
            
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
    	
    	return list;
    	
    }
	

	
	
	}

