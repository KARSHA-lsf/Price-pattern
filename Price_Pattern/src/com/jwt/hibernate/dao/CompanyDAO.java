
package com.jwt.hibernate.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.google.gson.Gson;
import com.jwt.hibernate.bean.Company;
//import com.jwt.hibernate.bean.Company;
import com.jwt.hibernate.bean.companydetails;


public class CompanyDAO {
	
	   // 1. configuring hibernate
    Configuration configuration = new Configuration().configure();

    // 2. create sessionfactory
    SessionFactory sessionFactory = configuration.buildSessionFactory();

    // 3. Get Session object
    Session session = sessionFactory.openSession();

    // 4. Starting Transaction
    Transaction transaction = session.beginTransaction();
	
	public List<companydetails> getDetails(int permno){
		List<companydetails> list = null;

    	try {
         
            Query<?> query = session.createQuery("from companydetails where PERMNO=' "+permno+" ' ");
            
            //1st way....
            List<companydetails> result = (List<companydetails>) query.list();
            
          
            
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
	
	public void genJson(List<companydetails> list, HttpServletRequest request, HttpServletResponse response){
		String theList = "";
		Gson gson = new Gson();
		theList = gson.toJson(list);
		//System.out.print(theList);
		
		String userPath = request.getServletPath();
		try {
			PrintWriter pwr = response.getWriter();
			//pwr.print(theList);
			if (userPath.equals("/pricecontroller")) {
				pwr.print(theList);
				
			}else{}
			System.out.print("success inside genJson method..");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* this is for all company info table method */

	public List<Company> getPermnoForNaics(int naics){
		List<Company> list = null;

    	try {
          
            Query<?> query = session.createQuery(" from Company  where NAICS like '"+naics+"%' group by PERMNO");
            
            //1st way....
          
            List<Company> result = (List<Company>) query.list();
         
        
        
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

