<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

  <link href="css/themes/green/pace-theme-corner-indicator.css" rel="stylesheet" />
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

  <title>Stock Price Graph</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
  <link href="css/font-awesome.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet">
  <link href="css/dashboard.css" rel="stylesheet">
  
  <script src="js/jquery-2.1.4.min.js"></script> 
  <script src="js/bootstrap.min.js"></script>

   <script src="js/predict.js"></script> 
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

 <script src="js/pace.js"></script>
  <!-- Load c3.css -->
  <link href="css/c3.css" rel="stylesheet" type="text/css">
  
  <!-- Load d3.js and c3.js -->
  <script src="js/d3.min.js" charset="utf-8"></script>
  <script src="js/c3.js"></script>

  <script src="js/jquery-2.1.4.min.js"></script> 
  <script src="js/predict.js"></script> 
  
  <link href="css/shwgrph.css" rel="stylesheet" type="text/css">
  
</head>
<style>
#circle {
	width: 40px;
	height: 40px;
	background: red;
	-moz-border-radius: 50px;
	-webkit-border-radius: 50px;
	border-radius: 50px;
	position:relative;
}

#circle span{
position: absolute;
	left:80px;
	top:10px;
	font-size: 13px;
}



</style>

<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container">
       <a class="brand" href="index.html">Karsha - Equity Price Features </a>
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
<div class="container ">
<div class="row">
    	
    		<div class="col-md-6">
    			   <div class="page-header" align="center">
								<h1>Equity Price Features </h1>
				</div>
			</div>
			<!-- Company Details From URL -->
				<div class="col-md-6">
						<div class="panel panel-default">
								  <div class="panel-heading">
								    <h3 class="panel-title" >Company Name :&nbsp;&nbsp;<script>document.write(comName)</script> </h3>
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
   
    
    
<div class="container" id="PRCgraph">
<div class="row">
			 <div class="col-md-12">
			 <div class="panel panel-default"  style="border-color: #cceeff;">
			  <div class="panel-body">
			 			 <div id="chart"></div>  <!-- Graph Representation -->
			 			 </div>
			 			 </div>
			 </div>
			 
	 </div>
 </div>
 
<!-- Start of show Matrix Graph -->
<div class="container">
<div class="row">
<div class="col-md-12">
		<div class="panel panel-default">
		<div class="panel-heading">
							    <h3 class="panel-title">Maxima/AmNeg/Minima Occurrence </h3>
							  </div>
		 <div class="panel-body">
		<div id="matrix" align="center"></div>
		</div>
		</div>
</div>
</div>
</div>

<!-- End Of Matrix Graph -->

 <div class="container" >
 <div class="row">
 	<div class="col-md-12">
							<div class="panel panel-default">
							  <div class="panel-heading">
							    <h3 class="panel-title">Maxima & Minima Calculation</h3>
							  </div>
							  <div class="panel-body">
							    <button id="maxima" type="button" class="btn btn-danger" style="background-color: #e62e00;">Maxima</button>
							    <button id="minima" type="button" class="btn btn-success" style="background-color: #00802b;">Minima</button>
							    <button id="amNeg" type="button" class="btn btn-default" style="background-color: #009900;color:white;">AmNeg</button>
							    <button id="PRCB" type="button" class="btn btn-primary" style="background-color: #004d00;display:none">Show PRC_Graph</button>
							    
							  <a  id="count" style="display:none" class="col-md-offset-6 "></a><span style="display:none;" id="badges" class="badge"></span> <!-- To show how many Charts maxima minima -->
							    <a  id="count1" style="display:none" class="col-md-offset-6 "></a><span style="display:none;"  id="badges1" class="badge"></span>
							     <a  id="count2" style="display:none" class="col-md-offset-6 "></a><span  style="display:none;" id="badges2" class="badge"></span>
							  </div>
							</div> 	
 	
 					</div>
 					<!-- display amNeg color pattern type -->
 					<div id="amnegcolor" class="col-md-12"  style="display:none">
 							<div class="panel panel-default">
							  <div class="panel-heading">
							    <h3 class="panel-title">AmNeg Color Patterns</h3>
							  </div>
							  <div class="panel-body  " >
							   <div  class="col-md-3"><div  id="circle" style="background-color: #ccb3ff;"><span class="badge">MmaxToMmin</span><span id="p1" class="badge" style="left:190px;"></span></div></div>
							   <div  class="col-md-3"><div  id="circle" style="background-color: #b3ffb3;"><span class="badge">MmaxToMin</span><span id="p2" class="badge" style="left:190px;"></span></div></div>
							   <div  class="col-md-3"><div  id="circle" style="background-color: #ffccff;"><span class="badge">MaxToMmin</span><span id="p3" class="badge" style="left:190px;"></span></div></div>
							   <div class="col-md-3"><div  id="circle" style="background-color: #ffcccc;"><span class="badge">MaxToMin</span><span id="p4" class="badge" style="left:190px;"></span></div></div>
							  </div>
							</div> 			
 					</div>
			 </div>
	</div>
	<div class="container pre-scrollable">	
	
 <div class="row" id="parent" style="display:none;">

 </div><!-- This id for show maxima -->
  <div class="row" id="parent1" style="display:none;"></div><!-- This id for show minima -->
  <div class="row" id="parent2"  style="display:none;"></div><!-- This id for show amNeg -->
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
 
 

   <script src="js/graphs/price_graph.js"></script>
   
    <script src="js/graphs/maxima_data.js"></script>
   <script src="js/graphs/minima_data.js"></script>
   <script src="js/graphs/amNeg_data.js"></script>
   <script src="js/graphs/matrix_data.js"></script>
    <script>
$(document).ready(function(){
    $("#maxima,#minima,#amNeg").click(function(){
       // $("#PRCgraph").fadeOut();
       // $("#PRCgraph").fadeOut("slow");
      //$("#parent").fadeOut();
       $("#PRCgraph").fadeOut(1000);
       $("#PRCB").fadeIn(1000);
   
       
 
     
       
    });
    
    $("#maxima").click(function(){
    	
        
       
     });
    
    $("#maxima").click(function(){
        // $("#PRCgraph").fadeOut();
        // $("#PRCgraph").fadeOut("slow");
       $("#parent1").fadeOut();
       $("#parent2").fadeOut();
       $("#amnegcolor").fadeOut();
       $("#parent").fadeIn();
      $("#count1,#badges1").fadeOut(-1000);
      $("#count2,#badges2").fadeOut(-1000);
      $("#count,#badges").fadeIn(1000);
       
     });
    $("#minima").click(function(){
        // $("#PRCgraph").fadeOut();
        // $("#PRCgraph").fadeOut("slow");
       $("#parent").fadeOut();
       $("#parent2").fadeOut();
       $("#amnegcolor").fadeOut();
       $("#parent1").fadeIn();
      
       $("#count,#badges").fadeOut(-1000);
       $("#count2,#badges2").fadeOut(-1000);
       $("#count1,#badges1").fadeIn(1000);
       
     });
    
    $("#amNeg").click(function(){
        // $("#PRCgraph").fadeOut();
        // $("#PRCgraph").fadeOut("slow");
        
       $("#parent").fadeOut();
       $("#parent1").fadeOut();
      
       $("#parent2").fadeIn();
       $("#amnegcolor").fadeIn();
      
       $("#count,#badges").fadeOut(-1000);
       $("#count1,#badges1").fadeOut(-1000);
       $("#count2,#badges2").fadeIn(1000);
       
     });
    
    
    $("#PRCB").click(function(){
        // $("#PRCgraph").fadeOut();
        // $("#PRCgraph").fadeOut("slow");
        $("#PRCgraph").fadeIn(1000);
        $("#PRCB").fadeOut(1000);
     });
});
</script>

  
 
  

</body>

</html>