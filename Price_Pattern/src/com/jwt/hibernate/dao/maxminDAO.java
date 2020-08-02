package com.jwt.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import com.jwt.hibernate.bean.maxminDetails;

public  class maxminDAO {
	Configuration configuration = new Configuration().configure();
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
	
	
	    
	
	
	public List<maxminDetails> getMaximaSet(int permno){
		List<maxminDetails> list = null;  	
            try{	
            String Max="max";
            Query<?> query = session.createQuery("from maxminDetails where META_PERMNO=' "+permno+" ' and EXTREMATYPE = '"+Max+"'   ");
         
            List<maxminDetails> result = (List<maxminDetails>) query.list();
           
          /*  for(maxminDetails max : result)
            {
            	System.out.println("Type: "+max.getEXTREMATYPE()+", Date: "+max.getMETA_DATE() );
            }
            */
       
            
            transaction.commit();
          //  session.close();
            System.out.println("\n\n Retrieved Maxima\n");
            return result;
            
 
            }catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
    	
    	return list;
    	
    }
	
	
	//Minima Data List
	public List<maxminDetails> getMinimaSet(int permno){
		List<maxminDetails> list = null;
		
    	try {
    		Configuration configuration = new Configuration().configure();
    	    SessionFactory sessionFactory = configuration.buildSessionFactory();
    	    Session session = sessionFactory.openSession();
    	    Transaction transaction = session.beginTransaction();
    		
 
            String Min="min";
            Query<?> query = session.createQuery("from maxminDetails where META_PERMNO=' "+permno+" ' and EXTREMATYPE = '"+Min+"'   ");
         
            List<maxminDetails> result = (List<maxminDetails>) query.list();
           
          /*  for(maxminDetails max : result)
            {
            	System.out.println("Type: "+max.getEXTREMATYPE()+", Date: "+max.getMETA_DATE() );
            }
            */
       
            
            transaction.commit();
            //session.close();
            System.out.println("\n\n RetrievedMinima \n");
            return result;
            
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
    	finally{
    		session.close();
    	}
    	
    	return list;
    	
    }
	
	
	
	}

