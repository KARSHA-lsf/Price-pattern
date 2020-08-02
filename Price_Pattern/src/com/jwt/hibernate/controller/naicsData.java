package com.jwt.hibernate.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jwt.hibernate.bean.AmNeg;
import com.jwt.hibernate.bean.Company;
import com.jwt.hibernate.bean.companydetails;
import com.jwt.hibernate.bean.maxminDetails;
import com.jwt.hibernate.bean.mxmn;
import com.jwt.hibernate.dao.CompanyDAO;
import com.jwt.hibernate.dao.amNegDAO;
import com.jwt.hibernate.dao.maxminDAO;
import com.jwt.hibernate.dao.mxmnDAO;




@Path("/naicsData")
public class naicsData {
	@Path("{naicsData}")
	@GET
	@Produces("application/json")
	public Response generateJson(@PathParam("naicsData") int naics) throws ParseException{
		
		//CompanyDAO CD = new CompanyDAO();
		//List<Company> permList = CD.getPermnoForNaics(naics); // unique permno list 
		
		mxmnDAO mx =new mxmnDAO();
		mxmnDAO mx1 =new mxmnDAO();
	
		List<mxmn> list = mx.getmaxSetForNaics(naics);
		
		mxmnDAO mn =new mxmnDAO();
		
		List<mxmn> listmn = mn.getminSetForNaics(naics);
		
		List<?> count = mx1.getCount(naics); //get the individual max min count
		
		JSONArray NaicsData = new JSONArray();
		
		
		
		JSONArray maxDates = new JSONArray();
		maxDates.put("Dates_Maxima");
		
		JSONArray maxValues = new JSONArray();
		maxValues.put("Maxima");
		
		JSONArray minDates = new JSONArray();
		minDates.put("Dates_Minima");
		
		JSONArray minValues = new JSONArray();
		minValues.put("Minima");
		
	/*	JSONArray amNegDates = new JSONArray();
		amNegDates.put("Dates_AmNeg");
		
		JSONArray amNegValues = new JSONArray();
		amNegValues.put("AmNeg"); */
		
		JSONArray lines = new JSONArray();
		
		JSONArray pl = new JSONArray(count);
		
		
		for(int k=0;k<list.size();k++){
			//System.out.println(k);
			maxDates.put(list.get(k).getDate());
				}	
		
		for(int k=0;k<listmn.size();k++){
			//System.out.println(k);
			minDates.put(listmn.get(k).getDate());
				}	
		
		
		int minv=1;
		//int amv=2;
		int mv=3;
		for(int k=0;k<count.size();k++){
			int permno=pl.getJSONObject(k).getInt("PERMNO");
			JSONObject jsonObject1 = new JSONObject();
			jsonObject1.put("value",(minv*(k+1)+2*k)-0.5);
			jsonObject1.put("text","Minima");
			lines.put(jsonObject1);
			
			JSONObject jsonObject3 = new JSONObject();
			jsonObject3.put("value",mv*(k+1)-0.5);
			jsonObject3.put("text","Maxima/"+permno);
			lines.put(jsonObject3);
			
			int Max_cnt= pl.getJSONObject(k).getInt("Max_cnt");
			for(int i=0;i<Max_cnt;i++){
				maxValues.put(mv*(k+1));
				
			}
			
			int Min_cnt= pl.getJSONObject(k).getInt("Min_cnt");
			for(int i=0;i<Min_cnt;i++){
				minValues.put(minv*(k+1)+2*k);
				
			}
			
			
			
			
			
			
				}	
		
		NaicsData.put(maxDates);
		NaicsData.put(minDates);
		NaicsData.put(maxValues);
		NaicsData.put(minValues);
		NaicsData.put(lines);
	/*	
		int minv=1;
		int amv=2;
		int mv=3;
		for(int z=0;z<permList.size();z++){
			int permno=permList.get(z).getPERMNO();
			JSONObject jsonObject1 = new JSONObject();
			jsonObject1.put("value",(minv*(z+1)+2*z)-0.5);
			jsonObject1.put("text","Minima/"+permno);
			lines.put(jsonObject1);
			
			JSONObject jsonObject2 = new JSONObject();
			jsonObject2.put("value",(amv*(z+1)+z)-0.5);
			jsonObject2.put("text","AmNeg");
			lines.put(jsonObject2);
			
			JSONObject jsonObject3 = new JSONObject();
			jsonObject3.put("value",mv*(z+1)-0.5);
			jsonObject3.put("text","Maxima/"+permno);
			lines.put(jsonObject3);
			
			
			maxminDAO maxima = new maxminDAO();
			List<maxminDetails> maxset = maxima.getMaximaSet(permno); // get the maixma details 
			
			amNegDAO amNegObj = new amNegDAO();
			List<AmNeg> amNegList =amNegObj.getPatternDetails(permno);  // get the amNeg details 
			
			maxminDAO minima = new maxminDAO();
			List<maxminDetails> minset = minima.getMinimaSet(permno); // get the minima details 
			
			
			//OuterLOOP
			
			for(int k=0;k<maxset.size();k++){
				maxDates.put(maxset.get(k).getMETA_DATE());
				maxValues.put(mv*(z+1));
				
				}	
			

			//OuterLOOP
				
			for(int k=0;k<minset.size();k++){
						minDates.put(minset.get(k).getMETA_DATE());
						minValues.put(minv*(z+1)+2*z);
						}	
			
			for(int k=0;k<amNegList.size();k++){
				amNegDates.put(middleDate(amNegList.get(k).getDateMax(),amNegList.get(k).getDateMin()));
				amNegValues.put(amv*(z+1)+z);
				}	
			
		
			}
		
		
		NaicsData.put(amNegDates);
		NaicsData.put(minDates);
		NaicsData.put(maxValues);
		NaicsData.put(amNegValues);
		
		
		
	*/	
		
		return Response.status(200).entity(NaicsData.toString()).build();
	
	}
	
public static String  middleDate(String sD,String eD) throws ParseException{
		
		String startDateString = sD;
		String endDateString = eD;
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
	    Date startDate;
	    Date endDate;
	   
	        startDate = df.parse(startDateString);
	        endDate = df.parse(endDateString);
	        
	        long sum = startDate.getTime() + (endDate.getTime() - startDate.getTime())/2;
	           
	     Date sumDate = new Date(sum);
	   
	     
	     String middleDate = df.format(sumDate);
	       
		
		return middleDate;
		
		
		
	}
	


	
}
	
