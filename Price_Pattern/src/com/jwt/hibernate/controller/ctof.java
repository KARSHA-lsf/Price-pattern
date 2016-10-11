package com.jwt.hibernate.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.jwt.hibernate.bean.companydetails;
import com.jwt.hibernate.dao.CompanyDAO;

@Path("/ctof")
public class ctof {
	@GET
	@Produces("application/json")
	
	public Response convertCtoF(){
	/*	JSONObject jsonObject = new JSONObject();
		Double fahrenheit;
		Double celsius = 36.8;
		String list = "json speaks";
		fahrenheit = ((celsius * 9) / 5) + 32;
		jsonObject.put("F Value", fahrenheit); 
		jsonObject.put("C Value", celsius); */
	//	System.out.println(permno);
	//	int permno = Integer.parseInt(headers.getRequestHeader("user-agent").get(0));
	//	CompanyDAO company = new CompanyDAO();
	//	List<companydetails>  list =  company.getDetails(permno);
		
		
 
		//String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity("fuc").build();
	}
	
	@Path("{c}")
	@GET
	@Produces("application/json")
	public Response ctof_input(@PathParam("c") int permno){
		/*JSONObject jsonObject = new JSONObject();
		Double fahrenheit;
		Double celsius = c;
		fahrenheit = ((celsius * 9) / 5) + 32;
		jsonObject.put("F Value", fahrenheit); 
		jsonObject.put("C Value", celsius);*/
		
		CompanyDAO company = new CompanyDAO();
		List<companydetails>  list =  company.getDetails(permno);
		
		
		Gson gson = new Gson();
		String theList = gson.toJson(list);
		
		
 
		//String result = "@Produces(\"application/json\") Output: \n\nF to C Converter Output: \n\n" + jsonObject;
		return Response.status(200).entity(theList).build();
		//to check this result please refer the following URL
		//http://localhost:8080/PricePattern/WStest/ctof
	}
	
}