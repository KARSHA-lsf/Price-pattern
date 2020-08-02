package com.jwt.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.jwt.hibernate.bean.AmNeg;
import com.jwt.hibernate.bean.companydetails;

public class amNegDAO {
				 // 1. configuring hibernate
			    Configuration configuration = new Configuration().configure();
			
			    // 2. create sessionfactory
			    SessionFactory sessionFactory = configuration.buildSessionFactory();
			
			    // 3. Get Session object
			    Session session = sessionFactory.openSession();
			
			    // 4. Starting Transaction
			    Transaction transaction = session.beginTransaction();
			    
	public List<List<companydetails>> returnAmnegList(int permno){
		List<List<companydetails>> list = null;
		
    	try {
           
         
            List<AmNeg> amNegList = getPatternDetails(permno); 
            
            Query<?> queryToGetComanyList = session.createQuery("from companydetails where PERMNO='"+permno+"'");
    		List<companydetails> resultComanyList = (List<companydetails>) queryToGetComanyList.list();
    		
    		List<List<companydetails>> amNegCollection = new ArrayList<List<companydetails>>();
    		
    		int i;
    		for(i=0; i < amNegList.size(); i++){
    			amNegCollection.add(getAmNegDetails(resultComanyList, amNegList.get(i).getDateMax(), amNegList.get(i).getDateMin()));
    		}
    
            

            
            transaction.commit(); 
            //session.close();
            System.out.println("\n\n Retrieved Amneg\n");
            return amNegCollection;
            
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
    	
    	
    
    	return list;
    }
	
	public  List<AmNeg> getPatternDetails(int permno){
		
		   Query<?> query1 = session.createQuery("from AmNeg where PERMNO=' "+permno+" ' and pattern!=null");
           List<AmNeg> amNegList = (List<AmNeg>) query1.list();
       	

    		
    	
    	
           return amNegList;
	}
	
	public List<companydetails> getAmNegDetails(List<companydetails> result, String maxDate, String minDate){
		List<companydetails> maximaRange = new ArrayList<companydetails>();
		
		
		int maximaPosition = 0;
		int minimaPosition = 0;
		int i;
		for(i=0 ; i < result.size(); i++){
			if(result.get(i).getDate().equals(maxDate)){
				maximaPosition = i;
				break;
			}
		}
		
		int j;
		for(j = maximaPosition; j < result.size(); j++){
			if(result.get(j).getDate().equals(minDate)){
				minimaPosition = j;
				break;
			}
		}
		
		int k;
		for(k = maximaPosition; k <= minimaPosition; k++){
			maximaRange.add(result.get(k));
		}
		
		return maximaRange;
	}
	
	public void closeSession(){
		session.close();
	}
}
