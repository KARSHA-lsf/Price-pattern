<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Naics Graph Visualization</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/bootstrap-responsive.min.css" rel="stylesheet">
 <link href="https://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600" rel="stylesheet">
  <link href="css/font-awesome.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet">
  <link href="css/dashboard.css" rel="stylesheet">
   <link href="css/themes/green/pace-theme-corner-indicator.css" rel="stylesheet" />
   <script src="js/jquery-2.1.4.min.js"></script> 
  <script src="js/bootstrap.min.js"></script>
   

 <script src="js/pace.js"></script>
  <!-- Load c3.css -->
  <link href="css/c3.css" rel="stylesheet" type="text/css">
  
  <!-- Load d3.js and c3.js -->
  <script src="js/d3.min.js" charset="utf-8"></script>
  <script src="js/c3.js"></script>

    <script src="js/jquery-2.1.4.min.js"></script> 
      <link href="css/shwgrph.css" rel="stylesheet" type="text/css">
    
</head>

<style>
.g{
background-color: yellow;
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
  <!-- pagination  -->
  <div class="container">
    <div class="row">
    <!-- pagination in top  -->
    	<div class="col-md-12">
    			 <ol class="breadcrumb">
				  <li><a href="index.html">Home</a></li>
				  <li><a href="naics.jsp">Sector</a></li>
				  <li class="active">Visualization</li>
				</ol>
    	</div>
    </div>
</div>
  
  <%
    int naicskey = Integer.parseInt(request.getParameter("naics"));
%>
<script>
var naicskey = "<%=naicskey%>";
var arr;
$.ajax({
	    url:  '/Price_Pattern/getDetails/naicsData/'+naicskey , //this is the url where json format is generate for graph visualization 
	    type: "GET",
	    dataType: 'html',
	    async: false,
        cache: false,
	    success: function (data) {
	           arr=data;
	    }
	            });


arr = JSON.parse( arr); // Do not need to pass to a another array; 

</script>
 
<div class="container">
<div class="row">
<div class="col-md-12">
<div class="panel panel-default">
		<div class="panel-heading">
							    <h3 class="panel-title">Sector Based Maxima , Minima Occurance </h3>
							  </div>
		 <div class="panel-body">
			<div id="chart"></div>
		</div>
</div>

</div>

</div>
</div>
<script type="text/javascript">
function tooltip_contents(d, defaultTitleFormat, defaultValueFormat, color) {
    var $$ = this, config = $$.config, CLASS = $$.CLASS,
        titleFormat = config.tooltip_format_title || defaultTitleFormat,
        nameFormat = config.tooltip_format_name || function (name) { return name; },
        valueFormat = config.tooltip_format_value || defaultValueFormat,
        text, i, title, value, name, bgcolor;
    
    // You can access all of data like this:
    console.log($$.data.targets);
    
    for (i = 0; i < d.length; i++) {
        if (! (d[i] && (d[i].value || d[i].value === 0))) { continue; }

        // ADD
        if (d[i].name === 'data2') { continue; }
        
        if (! text) {
            //title = 'you can add your tooltip title here'
            title =''
            text = "<table class='" + CLASS.tooltip + "'>" + (title || title === 0 ? "<tr><th colspan='2'>" + title + "</th></tr>" : "");
        }

        name = nameFormat(d[i].name);
        value = valueFormat(d[i].value, d[i].ratio, d[i].id, d[i].index);
        bgcolor = $$.levelColor ? $$.levelColor(d[i].value) : color(d[i].id);

        text += "<tr class='" + CLASS.tooltipName + "-" + d[i].id + "'>";
        text += "<td class='name'><span style='background-color:" + bgcolor + "'></span>" + name + "</td>";
        text += "<td class='value'></td>";
        text += "</tr>";
    }
    return text + "</table>";   
}

if(arr.length>0){
var chart = c3.generate({
	bindto: document.getElementById('chart'),
    data: {
        xs: {
            Maxima: 'Dates_Maxima',
          //  AmNeg: 'Dates_AmNeg',
           Minima : 'Dates_Minima'
        },
        
       // json: arr,
        // iris data from R
       columns: [
			arr[0],
			arr[1],
			arr[2],
			arr[3],
			
			
			
			
        ],
        type: 'scatter'
    },
    tooltip: {
        contents: tooltip_contents
    },
    color: {
        pattern: ['#0066ff', ' #ff3300']
    },
    axis: {
        x: {
        	type : 'timeseries',
            label: 'Time',
           tick: {
                format: '%Y-%m-%d'
            }
        },
        y: {
        	 show: false,
            label: 'Permno',
            tick: {
	      values: [1, 2, 3, 4,5,6]
	    }
        }
    },
zoom: {
  enabled: true,
  rescale: true
},
subchart: {
  show: true
},
size:{
	 width:1140,
	 height: 800
},
grid: {
        y: {
            lines: arr[arr.length-1]
        }
    }
});
}
else{
	alert(" Sector Data is not Found"); //show alert when  data missing..
}

</script>
</body>
</html>