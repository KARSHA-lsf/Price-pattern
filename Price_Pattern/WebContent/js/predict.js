<<<<<<< HEAD
/**
 * 
 * 
 */
/**method for get the url variable to print --*/
function getQueryVariable(variable) {
	var query = window.location.search.substring(1)
	//console.log(query);
	var vars = query.split("&");
	
	for (var i=0;i<vars.length;i++) {
	    var pair = vars[i].split("=")
	   
	    if(pair[0] == variable){
	        if(pair[1].indexOf('%20') != -1){
	           
	            var fullName = pair[1].split('%20')
	           
	            return fullName[0] + ' ' + fullName[1]
	        }
	        else {
	            return pair[1];
	        }
	    }
	}
	return(false)
}

var permno=getQueryVariable('Key');
var comName=getQueryVariable('company');
var Naics=getQueryVariable('naics');
var tsymbol=getQueryVariable('symbol');




=======
/**
 * 
 * 
 */
/**method for get the url variable to print --*/
function getQueryVariable(variable) {
	var query = window.location.search.substring(1)
	//console.log(query);
	var vars = query.split("&");
	
	for (var i=0;i<vars.length;i++) {
	    var pair = vars[i].split("=")
	   
	    if(pair[0] == variable){
	        if(pair[1].indexOf('%20') != -1){
	           
	            var fullName = pair[1].split('%20')
	           
	            return fullName[0] + ' ' + fullName[1]
	        }
	        else {
	            return pair[1];
	        }
	    }
	}
	return(false)
}

var permno=getQueryVariable('Key');
var comName=getQueryVariable('company');
var Naics=getQueryVariable('naics');
var tsymbol=getQueryVariable('symbol');




>>>>>>> branch 'master' of https://github.com/dineth454/karsha_price-pattern.git
