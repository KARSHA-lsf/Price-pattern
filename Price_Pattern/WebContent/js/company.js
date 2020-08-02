$("#search").keyup(function(){
          var searchfield =$("#search").val();
            var myExp = new RegExp(searchfield,"i"); 
            
            if(searchfield ){
            	if(searchfield.length>1){
 					  //console.log(myExp);
 				   $.getJSON('data/cmp.json',function(data){
 						//console.log(data);
              
 					  
 					   
 					   	var tmp=''; 
                for (var i=0; i<data.RECORDS.length; i++) 
                  if(data.RECORDS[i].COMNAM.search(myExp) != -1) {
                	  	var eachRecord =data.RECORDS[i].COMNAM;
                	  	
                	  	var permno=data.RECORDS[i].PERMNO;
                	  	var tsym=data.RECORDS[i].TSYMBOL;
                	  	var naics=data.RECORDS[i].NAICS;
                	  	
                	  	tmp += '<a  href="showgraph.jsp?Key='+permno+'&symbol='+tsym+'&naics='+naics+'&company='+eachRecord+'" >'+eachRecord+'</a><br>'; //get the each permno and symbol
                	  	//console.log(tmp);
                	  	document.getElementById('suggest').innerHTML=tmp //.link(eachRecord ); //link here
              }
                 
 						
 				   	});	
            }
            }
 				});


