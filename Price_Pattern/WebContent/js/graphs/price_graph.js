/**
 * 
 */

setTimeout(function () {
        chart = c3.generate({
            data: {
                url: '/Price_Pattern/getDetails/CompanyPriceController/'+ permno,	
                mimeType: 'json',
                keys: {
                    x: 'Date', // it's possible to specify 'x' when category axis
                    value: ['PRC', 'Pseudo_PRC','Turnover'],
                }
            },
            zoom: {
    	    	enabled: true,
    	  		rescale: true
    			},
            axis: {
                x: {
                    type: 'timeseries',
                    tick: {format: '%Y-%m-%d'}
                },
                y: {
    	        	//tick: {
    	              //  format: d3.format("$,")
//    	                format: function (d) { return "$" + d; }
    	            //},
    	            label: {
    	                text: 'PRC/PsedoPRC in $',
    	                position: 'outer-middle'
    	            }
    	        },
    	        y2: {
                    show: true,
                    max: 0.1,
                    min: -0.1,
                    label:'Turnover'
                }
            },
            tooltip: {
                format: {
                    value: function (value, ratio, id) {
                        var format = id === 'Turnover' ? d3.format(',.4f') : d3.format('$.4f');
                        return format(value);
                    }
                }
            },
            point: {
                show: function(){
                	var show = id === 'Turnover' ? false : true;
                	return show;
                }
            }
        });
        chart.hide(['Turnover'], {withLegend: true});
    }

, 500);

