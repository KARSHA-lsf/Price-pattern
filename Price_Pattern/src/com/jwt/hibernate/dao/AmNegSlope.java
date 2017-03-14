package com.jwt.hibernate.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.jwt.hibernate.bean.companydetails;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import antlr.collections.List;

public class AmNegSlope {
	
    
    public ArrayList<String[]> AmNeg_slope(int permno, String d1,String d2) {
    	String q = "SELECT rf.slope_id,dd.date as st_date, d2.date as end_date FROM amneg_result_final rf,dist_dates dd,dist_dates d2 WHERE dd.date BETWEEN '"+d1+"' and '"+d2+"' and permno = "+permno+" and dd.id = rf.date_id and d2.id+1 = date_id";
	 	ArrayList<String[]> arr = new ArrayList<>(); 
    	Properties prop = new Properties();
		InputStream input = null;

	
			Connection conn = null;
			try {
				input = getClass().getClassLoader().getResourceAsStream("dbconfig.properties");
				// load a properties file
				prop.load(input);
				
				Class.forName(prop.getProperty("dbdriver"));
				conn = (Connection) DriverManager.getConnection(prop.getProperty("database"), prop.getProperty("dbuser"), prop.getProperty("dbpassword"));
				Statement stmt = (Statement) conn.createStatement();

				
					//System.out.println("Query statement is " + query1);
					ResultSet rset = stmt.executeQuery(q);
					String [] temp_arr = new String[3];
					while (rset.next()) {
						temp_arr[0] = rset.getString(1);
						temp_arr[1] = rset.getString(2);
						temp_arr[2] = rset.getString(3);
						arr.add(temp_arr);
						//System.out.println(rset.getString(1)+" "+rset.getString(2)+" "+rset.getString(3));	
					}
					
					conn.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return arr;
	}
}
