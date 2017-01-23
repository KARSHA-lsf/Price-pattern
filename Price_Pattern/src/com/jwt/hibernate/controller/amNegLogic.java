package com.jwt.hibernate.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;

import com.jwt.hibernate.bean.AmNeg;
import com.jwt.hibernate.bean.companydetails;
import com.jwt.hibernate.dao.amNegDAO;



@Path("/amNegLogic")
public class amNegLogic {
	@Path("{graphData}")
	@GET
	@Produces("application/json")
	public Response generateAmnegJson(@PathParam("graphData") int permno){
		amNegDAO amNegObj = new amNegDAO();
		List<List<companydetails>> list = amNegObj.returnAmnegList(permno);
		
		List<AmNeg> amNegList =amNegObj.getPatternDetails(permno);
		
		amNegObj.closeSession();
		
		JSONArray jsonArray = new JSONArray();
		
		int i;
		for(i=0; i<list.size(); i++){
			JSONArray inerJsonArray = new JSONArray();
		
			for(companydetails companydetails : list.get(i))
	        {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("PERMNO",companydetails.getPERMNO());
				jsonObject.put("Date",companydetails.getDate());
				jsonObject.put("PRC",companydetails.getPRC());
				jsonObject.put("Pseudo_PRC",companydetails.getPseudo_PRC());
				
				
				 switch(amNegList.get(i).getPattern()) {
		         case 1 :
		        	 jsonObject.put("pattern","MmaxToMmin");
		        	 jsonObject.put("color","#ccb3ff");
		            break;
		         case 2 :
		        	 jsonObject.put("pattern","MmaxToMin");
		        	 jsonObject.put("color","#b3ffb3");
		            break;
		         case 3 :
		        	 jsonObject.put("pattern","MaxToMmin");
		        	 jsonObject.put("color","#ffccff");
		            break;
		         case 4 :
		        	 jsonObject.put("pattern","MaxToMin");
		        	 jsonObject.put("color","#ffcccc");
		            break;
				 }
				inerJsonArray.put(jsonObject);
				//jsonObject.put(companydetails.getPRC());	
	        }
				 
			
			jsonArray.put(inerJsonArray);
		}
		
		return Response.status(200).entity(jsonArray.toString()).build();
	}
}
