<<<<<<< HEAD
/**
 * 
 */

var searchfield = location.search.split('com=')[1]; //get the company name from url // this is wrong if space that not valid 
					
					//console.log(searchfield);
				
					if(searchfield){
						if(searchfield.length>=1){
					  var myExp = new RegExp(searchfield,"i"); 
					  
 					  //console.log(myExp);
 				   $.getJSON('data/cmp.json',function(data){
 						//console.log(data);
              
 					  
 					   
 					   	var tmp=''; 
                for (var i=0; i<data.RECORDS.length; i++) {
                  if((data.RECORDS[i].COMNAM.search(myExp) != -1)  || (data.RECORDS[i].TSYMBOL.search(myExp) != -1) || (String(data.RECORDS[i].PERMNO).search(myExp) != -1) ){
                	  	var eachRecord =data.RECORDS[i].COMNAM;
                	  	
                	  	var permno=data.RECORDS[i].PERMNO;
                	  	var tsym=data.RECORDS[i].TSYMBOL;
                	  	var naics=data.RECORDS[i].NAICS;
                	  	
                	  	tmp += '<div class="alert alert-info" role="alert"><a  href="showgraph.jsp?Key='+permno+'&symbol='+tsym+'&naics='+naics+'&company='+eachRecord+'" >'+'<div id="eachRecord" style="display:inline; "><b>'+
                	  	eachRecord+'</b></div>'+
                	  	'| <b>PERMNO :</b>'+permno+
                	  	'|<b>Trademark symbol - </b>'+tsym+
                	  	' |<b> NAICS - </b>'+naics+
                	  	'</a></div><br>'; //get the each permno and symbol
                	  	//console.log(tmp);
                	  	document.getElementById('suggest').innerHTML=tmp;
                  }
                }
                if(tmp.length==0){
                	document.getElementById('suggest').innerHTML="<div style='color:red;display:inline;'>No results found for </div>"+searchfield;
                }
 				   
 				  });	
					}
					}
					
					
/*--------function onload */
					
					function myFunction() {
						if(searchfield){
					    document.getElementById("search").value = searchfield;
						}
					}

=======
/**
 * 
 */

var searchfield = location.search.split('com=')[1]; //get the company name from url // this is wrong if space that not valid 
					
					//console.log(searchfield);
				
					if(searchfield){
						if(searchfield.length>=1){
					  var myExp = new RegExp(searchfield,"i"); 
					  
 					  //console.log(myExp);
 				   $.getJSON('data/cmp.json',function(data){
 						//console.log(data);
              
 					  
 					   
 					   	var tmp=''; 
                for (var i=0; i<data.RECORDS.length; i++) {
                  if((data.RECORDS[i].COMNAM.search(myExp) != -1)  || (data.RECORDS[i].TSYMBOL.search(myExp) != -1) || (String(data.RECORDS[i].PERMNO).search(myExp) != -1) ){
                	  	var eachRecord =data.RECORDS[i].COMNAM;
                	  	
                	  	var permno=data.RECORDS[i].PERMNO;
                	  	var tsym=data.RECORDS[i].TSYMBOL;
                	  	var naics=data.RECORDS[i].NAICS;
                	  	
                	  	tmp += '<div class="alert alert-info" role="alert"><a  href="showgraph.jsp?Key='+permno+'&symbol='+tsym+'&naics='+naics+'&company='+eachRecord+'" >'+'<div id="eachRecord" style="display:inline; "><b>'+
                	  	eachRecord+'</b></div>'+
                	  	'| <b>PERMNO :</b>'+permno+
                	  	'|<b>Trademark symbol - </b>'+tsym+
                	  	' |<b> NAICS - </b>'+naics+
                	  	'</a></div><br>'; //get the each permno and symbol
                	  	//console.log(tmp);
                	  	document.getElementById('suggest').innerHTML=tmp;
                  }
                }
                if(tmp.length==0){
                	document.getElementById('suggest').innerHTML="<div style='color:red;display:inline;'>No results found for </div>"+searchfield;
                }
 				   
 				  });	
					}
					}
					
					
/*--------function onload */
					
					function myFunction() {
						if(searchfield){
					    document.getElementById("search").value = searchfield;
						}
					}

>>>>>>> branch 'master' of https://github.com/dineth454/karsha_price-pattern.git
				