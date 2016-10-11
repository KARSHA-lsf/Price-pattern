<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

  <link href="css/themes/green/pace-theme-center-simple.css" rel="stylesheet" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


  <title>Stock Price Graph</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
  <link href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600"
          rel="stylesheet">
  <link href="css/font-awesome.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet">
  <link href="css/dashboard.css" rel="stylesheet">
  
   <script src="js/jquery-2.1.4.min.js"></script> 
  <script src="js/bootstrap.min.js"></script>
   <script src="js/predict.js"></script> 


  <!-- Load c3.css -->
  <link href="css/c3.css" rel="stylesheet" type="text/css">
  
  <!-- Load d3.js and c3.js -->
  <script src="js/d3.min.js" charset="utf-8"></script>
  <script src="js/c3.js"></script>

  <script src="js/jquery-1.7.2.min.js"></script> 
    <script src="js/predict.js"></script> 
  
</head>

<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container">
       <a class="brand" href="index.html">Karsha - Price Pattern </a>
      </div>
    </div>
  </div>
 
 <div class="main">
  <div class="main-inner">
    <div class="container">
    <div class="row">
    <!-- pagination in top  -->
    	<div class="col-md-12">
    			 <ol class="breadcrumb">
				  <li><a href="index.html">Home</a></li>
				  <li><a href="search.html">Company Details</a></li>
				  <li class="active">Price Graph</li>
				</ol>
    	</div>
    </div>
</div>


<!-- end Of container -->	
</div>
</div>		
	<!-- start 02container  -->	   
 <div class="container">
<div class="row">
    	
    		<div class="col-md-6">
    			   <div class="page-header">
								<h1>Price Analysis and Predicting Future</h1>
				</div>
			</div>
			<!-- Company Details From URL -->
				<div class="col-md-6">
						<div class="panel panel-default">
								  <div class="panel-heading">
								    <h3 class="panel-title" style="color:blue;">Company Name :&nbsp;&nbsp;<script>document.write(comName)</script> </h3>
								    </div>
								    
								  <div class="panel-body">
							
								   <div class="col-md-4" id="dperm"><h5><b>TSYMBOL</b></h5><script>document.write(tsymbol)</script></div>
								    <div class="col-md-4"  id="dtsym"><h5><b>PERMNO</b></h5><script>document.write(permno)</script></div>
								     <div class="col-md-4"  id="dnaics"><h5><b>NAICS</b></h5><script>document.write(Naics)</script></div>
								   </div> 
								  
</div>
				</div>
		
			<!-- end of company Details -->
 </div>
 </div>
   
    
    
<div class="container">
<div class="row">
			 <div class="col-md-12">
			 			 <div id="chart"></div>  <!-- Graph Representation -->
			 </div>
			 
	 </div>
 </div>
 
 
 
 <script>
 
 setTimeout(function () {
        chart = c3.generate({
            data: {
                url: '/Price_Pattern/getDetails/CompanyPriceController/'+ permno,	
                mimeType: 'json',
                keys: {
                    x: 'Date', // it's possible to specify 'x' when category axis
                    value: ['PRC', 'Pseudo_PRC'],
                }
            },
            axis: {
                x: {
                    type: 'timeseries',
                    tick: {format: '%Y-%m-%d'}
                }
            }
        });
    }, 500);
 </script>
  <script src="js/pace.js"></script>
</body>

</html>