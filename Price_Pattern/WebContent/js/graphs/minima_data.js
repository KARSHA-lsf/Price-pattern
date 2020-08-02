/**
 * 
 */

/**
 * 
 * This is where graph data is found and visualize graph in web page
 */

//$('#minima').on('click', doThis);
//function doThis () {    
 var c;
 var arr;
 var minarr;
 $.ajax({
	    url:  '/Price_Pattern/getDetails/minimaLogic/'+ permno,
	    type: "GET",
	    dataType: 'html',
	    async: false,
         cache: false,
	    success: function (data) {
	           arr=data;
	    }
	            });
 

arr = JSON.parse( arr); // Do not need to pass to a another array; 
minarr=arr;
document.getElementById("count1").innerHTML = "Minima Count  "; 
document.getElementById("badges1").innerHTML = arr.length;
if(arr.length>0){
for(c=0;c<arr.length;c++){
	  $('#parent1').append('<div class="col-md-4"><div class="page-header" align="center">Minima -'+(c+1)+'</div><div id="demot'+ c +'"></div></div>');
	  }



var i;
 for(i=0;i<arr.length;i++){
var chart=c3.generate({
	bindto: document.getElementById('demot'+i),
	    data: {                
	        
	        json: arr[i],
	   
	     hide: ['Date'],
	        keys: {
	            x: 'Date',
	            value: ['PRC','Pseudo_PRC']
	        }
	    },
	    
	    axis: {
	        x: {
	        	 type: 'timeseries',
	        	 tick: {
	                 count: 4,
	                 format: '%Y-%m-%d'
	             },
				    label: {
				        text: 'Dates',
				        position: 'Right',
				     }
	        },
	        y: {
	        	tick: {
	        		format: d3.format("$.2f,")
//	                format: function (d) { return "$" + d; }
	            },
	            label: {
	                text: 'PRC/PsedoPRC in $',
	                position: 'outer-middle'
	               
	            }
	        },
	    },
	    grid: {
	        x: {
	            lines: [
	                {value: arr[i][arr[i].length-1]['minimaDate'], text: 'MINIMA',position: 'middle'}
	               
	            ]
	        }
	    },
	    size:{
	    	 width:300,
	    	 height:275
	    },
	    zoom: {
	    	enabled: true,
	  		rescale: true
			},
			regions: [
			          {axis: 'x', start:  arr[i][arr[i].length-1]['leftDate'], end:  arr[i][arr[i].length-1]['rightDate'], class: 'regionX'},
			         
			          
			      ],
	    bar: {
	        width: {
	            ratio: 0.5
	        }
	    }
	    
	});
 }
}
else{
	//document.write("No Maxima Found")
	alert(" No Data Found");
}
//$('#minima').off('click',doThis);
 //}
