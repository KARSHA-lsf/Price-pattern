package com.jwt.hibernate.controller;

import java.util.ArrayList;
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
import com.jwt.hibernate.dao.AmNegSlope;
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
		String AmMax_date = null,AmMin_date = null;
		int i;
		for(i=0; i<list.size(); i++){
			JSONArray inerJsonArray = new JSONArray();
			int kk=0;
			for(companydetails companydetails : list.get(i))
	        {
				if(kk==0){
					AmMax_date = companydetails.getDate();
					kk++;
				}
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("PERMNO",companydetails.getPERMNO());
				jsonObject.put("Date",companydetails.getDate());
				jsonObject.put("PRC",companydetails.getPRC());
				jsonObject.put("Pseudo_PRC",companydetails.getPseudo_PRC());
				AmMin_date = companydetails.getDate();
				
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
				 
			//System.out.println("1 : "+AmMax_date+" 2:"+AmMin_date);
			AmNegSlope amnS = new AmNegSlope();
			ArrayList<String[]> arr = amnS.AmNeg_slope(permno,AmMax_date , AmMin_date);
			JSONObject jsonObject_amn = new JSONObject();
			JSONArray j_amn_arr = new JSONArray();
			for (int j = 0; j < arr.size(); j++) {
				jsonObject_amn.put("Slope_id",arr.get(j)[0]);
				jsonObject_amn.put("d1",arr.get(j)[1]);
				jsonObject_amn.put("d2",arr.get(j)[2]);
				j_amn_arr.put(jsonObject_amn);
			}
			JSONObject jsonObject_t1 = new JSONObject();
			jsonObject_t1.put("AMN",inerJsonArray );
			jsonObject_t1.put("Region",j_amn_arr );
			jsonArray.put(jsonObject_t1);
			
		}
		
		return Response.status(200).entity(jsonArray.toString()).build();
	}
}
