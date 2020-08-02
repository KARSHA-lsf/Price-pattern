package com.jwt.hibernate.controller;

import java.util.List;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jwt.hibernate.bean.maxminDetails;
import com.jwt.hibernate.bean.stock;
import com.jwt.hibernate.dao.maxminDAO;
import com.jwt.hibernate.dao.stockDAO;



@Path("/minimaLogic")
public class minimaLogic {
	@Path("{minimaData}")
	@GET
	@Produces("application/json")
	public Response generateJson(@PathParam("minimaData") int permno){
		
		JSONArray jsonArrayobj = new JSONArray();
		JSONArray jsonArray = new JSONArray();
		
		maxminDAO minima = new maxminDAO();
		List<maxminDetails> minset = minima.getMinimaSet(permno); // get the minima details 
		
		stockDAO st = new stockDAO();
		List<stock> stockdata = st.getDetails(permno); // get the Stock  details
		
		//OuterLOOP
		for(int k=0;k<minset.size();k++){
		
		double psedoPRC = minset.get(k).getMETA_PSEUDOPRC();
		double minpp= psedoPRC  + (psedoPRC*0.01);// percentage should be 0.05
		
		
		
		
		String date = minset.get(k).getMETA_DATE();
		
		jsonArrayobj=makeJsonArray(minpp,date,stockdata,permno);
		jsonArray.put(jsonArrayobj);
		
		
		
		}		
		
		return Response.status(200).entity(jsonArray.toString()).build();
	
	}
	


	private JSONArray makeJsonArray(double minpp, String date, List<stock> stockdata,int permno) {
		
		JSONArray jsonArray = new JSONArray();
		
		/*this is the loop for find the index of Maxima date in stock Table */
		int x=0; // x is refer to index of maxima in stock list
		for(int i =0;i<stockdata.size();i++){
			String sdate =stockdata.get(i).getDate();
			if(sdate.equals(date)){
				x=i;
				
			}
		}
		
		String leftminimaDate="";
		
		int count=0;
		if(x-10>=0){
		for(int j=x-1;j>x-10;j--){
			
			JSONObject jsonObject = new JSONObject();
			double spp = stockdata.get(j).getPseudoPRC();
		
			if(spp>=minpp && count==0){
				leftminimaDate=stockdata.get(j).getDate();
				count=1;
				
			}
			jsonObject.put("PERMNO",permno);
			jsonObject.put("Date",stockdata.get(j).getDate());
			jsonObject.put("PRC",stockdata.get(j).getPRC());
			jsonObject.put("Pseudo_PRC",stockdata.get(j).getPseudoPRC());
			jsonArray.put(jsonObject);
		}
	}else{
			for(int j=x-1;j>=0;j--){
						
						JSONObject jsonObject = new JSONObject();
						double spp = stockdata.get(j).getPseudoPRC();
					
						if(spp>=minpp && count==0){
							leftminimaDate=stockdata.get(j).getDate();
							count=1;
							
						}
						jsonObject.put("PERMNO",permno);
						jsonObject.put("Date",stockdata.get(j).getDate());
						jsonObject.put("PRC",stockdata.get(j).getPRC());
						jsonObject.put("Pseudo_PRC",stockdata.get(j).getPseudoPRC());
						jsonArray.put(jsonObject);
					}
	}
		
		

		String rightminimaDate="";
		
		count=0;
		if(x+10<=stockdata.size()-1){
		for(int j=x;j<=x+10;j++){
			JSONObject jsonObject = new JSONObject();
			//System.out.println(stockdata.get(j).getDate());
			double spp = stockdata.get(j).getPseudoPRC();
			
			if(spp>=minpp && count==0){
				rightminimaDate=stockdata.get(j).getDate();
				count=1;
				
			}
			jsonObject.put("PERMNO",permno);
			jsonObject.put("Date",stockdata.get(j).getDate());
			jsonObject.put("PRC",stockdata.get(j).getPRC());
			jsonObject.put("Pseudo_PRC",stockdata.get(j).getPseudoPRC());
			if(j==x+10){
				jsonObject.put("rightDate",rightminimaDate);
				jsonObject.put("leftDate",leftminimaDate);
				jsonObject.put("minimaDate",date);
			}
			jsonArray.put(jsonObject);
		
		} 
		
		} else if(x+10>stockdata.size()-1){
			for(int j=x;j<stockdata.size();j++){
				JSONObject jsonObject = new JSONObject();
				//System.out.println(stockdata.get(j).getDate());
				double spp = stockdata.get(j).getPseudoPRC();
				
				if(spp>=minpp && count==0){
					rightminimaDate=stockdata.get(j).getDate();
					
					count=1;
					
				}
				jsonObject.put("PERMNO",permno);	
				jsonObject.put("Date",stockdata.get(j).getDate());
				jsonObject.put("PRC",stockdata.get(j).getPRC());
				jsonObject.put("Pseudo_PRC",stockdata.get(j).getPseudoPRC());
				if(j==stockdata.size()-1){
					jsonObject.put("rightDate",rightminimaDate);
					jsonObject.put("leftDate",leftminimaDate);
					jsonObject.put("minimaDate",date);
				}
				jsonArray.put(jsonObject);
			
			}
			
		}
		//System.out.println("right Maxima Date"+rightmaximaDate);
		
		return jsonArray;
	}
}
	
