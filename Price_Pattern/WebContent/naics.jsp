<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta charset="utf-8">
  <title>Karsha | Sectors</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
  <link href="css/font-awesome.css" rel="stylesheet">
  <link href="css/dashboard.css" rel="stylesheet">
  <link href="css/shwgrph.css" rel="stylesheet" type="text/css">
  
    <!-- CSS files for D3 Tree -->
  <link href="css/customStyle.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet">
  
  <script src="js/jquery-2.1.4.min.js"></script> 
  <script src="js/bootstrap.min.js"></script> 

  <style type="text/css">
	.logo {
	    width: 250px;
	    height: 150px;
	    border: 3px solid #73AD21; 
	}
	
	.main{
	height: auto;
	
	}
	
#suggest a:hover{
    text-decoration: none;
}
  </style> 
</head>
<body>
   <div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container">
       <a class="brand" href="index.html">Karsha - Equity Price Features</a>
      </div>
    </div>
  </div>
  <div class="subnavbar">
    <div class="subnavbar-inner">
      <div class="container">
       <ul class="mainnav">
          <li><a href="index.html"><i class="icon-home"></i><span>Home</span> </a> </li>
          <li><a href="search.html"><i class="icon-search"></i><span>Search</span> </a> </li>
          <li class="active"><a href="naics.jsp"><i class="icon-list-alt"></i><span>Sectors</span> </a></li>
          <li><a href="aggregate.html"><i class="icon-bar-chart"></i><span>Daily Aggregate</span> </a></li>
          <li><a href="details.html"><i class="icon-file-text"></i><span>Details</span> </a></li>
          <li><a href="about.html"><i class="icon-user"></i><span>About</span> </a></li>
        </ul>
      </div>
    </div>
  </div>


<div class="container">
  <h2>Equity Price Features comparison</h2>
  <div class="list-group">
	  <a href="" class="list-group-item active"><b>NAICS Details</b><span class="badge">No of Companies</span></a>

			<%@ page import = "java.io.FileInputStream" %>
			<%@ page import = "java.io.InputStream" %>
			<%@ page import = "java.io.IOException" %>
			<%@ page import = "java.util.Properties" %>
			<%@ page import = "java.sql.*" %>
			<% 
			Properties prop = new Properties();
			InputStream input = null;

			try {
				input = getClass().getClassLoader().getResourceAsStream("dbconfig.properties");
				// load a properties file
				prop.load(input);
				
				Class.forName(prop.getProperty("dbdriver"));
				Connection conn = DriverManager.getConnection(prop.getProperty("database"), prop.getProperty("dbuser"), prop.getProperty("dbpassword"));
				Statement stmt = conn.createStatement();

				String query1 = "SELECT NAICS, n.industryName,COUNT(DISTINCT PERMNO)as Number_of_company from ( SELECT PERMNO,SUBSTR(naics FROM 1 FOR 2) as NAICS FROM all_company_info )as a, naics n WHERE n.code=a.NAICS GROUP BY a.NAICS";
				
				try{
					System.out.println("Query statement is " + query1);
					ResultSet rset = stmt.executeQuery(query1);
					
					while(rset.next()){%>
						<a href="naicsGraph.jsp?naics=<%= rset.getString(1)%>" class="list-group-item list-group-item-action"><%= rset.getString(1) %> | <%= rset.getString(2) %><span class="badge"><%= rset.getString(3) %></span></a>
					<%}
				}catch(SQLException e){
					System.out.println("errrrror" + e);
				}finally{
					conn.close();
				}

				// get the property value and print it out
				System.out.println(prop.getProperty("database"));
				System.out.println(prop.getProperty("dbuser"));
				System.out.println(prop.getProperty("dbpassword"));

			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		%>
  </div>
</div>

<div class="footer">
  <div class="footer-inner">
    <div class="container">
      <div class="row">
        <div class="span12"> &copy; 2016 <a href="#">
          </a></div>
        <!-- /span12 --> 
      </div>
      <!-- /row --> 
    </div>
    <!-- /container --> 
  </div>
  <!-- /footer-inner --> 
</div>
<!-- /footer --> 
</body>
</html>
