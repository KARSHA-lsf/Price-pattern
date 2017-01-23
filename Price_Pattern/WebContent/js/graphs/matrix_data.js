/**
 * 
 */




var maxDates=["Dates_Maxima"];
var minDates=["Dates_Minima"];
var amNegDates=["Dates_AmNeg"];
var maxValues=["Maxima"];
var minValues=["Minima"];
var amValues=["AmNeg"];

for(var i = 0;i<maxarr.length;i++){
	
	maxDates.push((maxarr[i][maxarr[i].length-1]['maximaDate']));
	maxValues.push(3);
	
}

for(var i = 0;i<minarr.length;i++){
	
	minDates.push((minarr[i][minarr[i].length-1]['minimaDate']));
	minValues.push(1);
}

for(var i = 0;i<amNegarr.length;i++){
	
	var startTime = new Date(amNegarr[i][0]['Date']);
	var endTime = new Date(amNegarr[i][amNegarr[i].length-1]['Date']);
	
    
    var middleTime = new Date((startTime.getTime() + endTime.getTime()) / 2);
    //console.log(middleTime);
    
    var currentDate = new Date(middleTime);
    var day = currentDate.getDate();
    var month = currentDate.getMonth() + 1;
    var year = currentDate.getFullYear();
    var middleTimew=year + "-" + month + "-" + day;
    
    amNegDates.push(middleTimew);
    amValues.push(2);
}


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

var chart = c3.generate({
	bindto: document.getElementById('matrix'),
    data: {
        xs: {
            Maxima: 'Dates_Maxima',
            AmNeg: 'Dates_AmNeg',
           Minima : 'Dates_Minima',
        },
        
       // json: arr,
        // iris data from R
       columns: [
			maxDates,
			minDates,
			amNegDates,
			maxValues,
			minValues,
			amValues
			
			
			
        ],
        type: 'scatter'
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
	      values: [0,1, 2, 3]
	    }
        }
    },
zoom: {
  enabled: true,
  rescale: true
},

size:{
	 width:1000,
	 height: 200
},
tooltip: {
	//show: true,
	contents: tooltip_contents
	},
	
	subchart: {
        show: true,
        size: {
            height: 25
          }
    },
grid: {
        y: {
            lines: [
					{value: 0.8, text: 'Minima'},
					{value: 1.8, text: 'AmNeg'},
					{value: 2.8, text: 'Maxima'}
                    ]

        }
    }
});



