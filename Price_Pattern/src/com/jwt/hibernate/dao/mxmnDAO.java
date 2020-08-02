package com.jwt.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import com.jwt.hibernate.bean.mxmn;

public class mxmnDAO {
	
	
    Configuration configuration = new Configuration().configure();

    // 2. create sessionfactory
    SessionFactory sessionFactory = configuration.buildSessionFactory();

    // 3. Get Session object
    Session session = sessionFactory.openSession();

    // 4. Starting Transaction
    Transaction transaction = session.beginTransaction();
    
	public List<mxmn> getmaxSetForNaics(int naics){
		List<mxmn> list = null;

    	try {
    		String Max="max";
            Query<?> query = session.createQuery(" from mxmn  where NAICS like '"+naics+"' and extremaType =  '"+Max+"' order by PERMNO ");
            
            //1st way....
          
            List<mxmn> result = (List<mxmn>) query.list();
         
        
        
            transaction.commit();
            session.close();
           
            System.out.println("\n\n Retrievedddd \n");
            return result;
            
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
    	
    	return list;
    	
    }
	
	public List<mxmn> getminSetForNaics(int naics){
		List<mxmn> list = null;

    	try {
    		String Min="min";
            Query<?> query = session.createQuery(" from mxmn  where NAICS like '"+naics+"' and extremaType =  '"+Min+"' order by PERMNO ");
            
            //1st way....
          
            List<mxmn> result = (List<mxmn>) query.list();
         
        
        
            transaction.commit();
           
            session.close();
            System.out.println("\n\n Retrievedddd \n");
            return result;
            
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
    	
    	return list;
    	
    }
	
	public List<?> getCount(int naics){
		List<?> list = null;

    	try {
    		String sql = "SELECT PERMNO,COUNT(CASE WHEN extremaType='max'then 1 END) as Max_cnt ,COUNT(CASE WHEN extremaType='min'then 1  END) as Min_cnt FROM all_mxmn WHERE NAICS ="+naics+"  GROUP BY PERMNO";
            SQLQuery query = session.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
           
            
            //1st way....
          
            List<?> result = (List<?>) query.list();
         
        
        
            transaction.commit();
           
            session.close();
            System.out.println("\n\n Retrieve count \n");
            return result;
            
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
    	
    	return list;
    	
    }
	
	/*public static void main(String args[]){
		mxmnDAO n = new mxmnDAO();
		List<mxmn> list = n.getmaxSetForNaics(11);
		
		for(mxmn max : list)
        {
        	System.out.println("Type: "+max.getTYPE()+", Date: "+max.getDATE() );
        }
	}*/

}
