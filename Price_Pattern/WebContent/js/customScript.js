//============== D3 tree scripts ===============//


//functions to collapse all nodes
function collapse(d) {
    if (d.children) {
      d._children = d.children;
      d._children.forEach(collapse);
      d.children = null;
    }
  }
  
  function collapseAllNodes(){
	    root.children.forEach(collapse);
	    collapse(root);
	    update(root);
  }
  
//functions to expand all nodes
function expand(d){   
    var children = (d.children)?d.children:d._children;
    if (d._children) {        
        d.children = d._children;
        d._children = null;       
    }
    if(children)
      children.forEach(expand);
}

function expandAll(){
    expand(root); 
    update(root);
}

//creating tree structure
var margin = {top: 20, right: 120, bottom: 20, left: 120},
    width = 960 - margin.right - margin.left,
    height = 800 - margin.top - margin.bottom;

var i = 0,
    duration = 750,
    root;

var tree = d3.layout.tree()
    .size([height, width]);

var diagonal = d3.svg.diagonal()
    .projection(function(d) { return [d.y, d.x]; });

var svg = d3.select("#treeView").append("svg")
    .attr("width", width + margin.right + margin.left)
    .attr("height", height + margin.top + margin.bottom)
  	.append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


//load json data to the tree
d3.json("/Price_Pattern/getDetails/CompanyTreeController", function(error, flare) {
  if (error) throw error;

  root = flare;
  root.x0 = height / 2;
  root.y0 = 0;

  root.children.forEach(collapse);
  update(root);
});


d3.select(self.frameElement).style("height", "800px");


//update tree content according to a node click
function update(source) {

	var duration = d3.event && d3.event.altKey ? 5000 : 500;  

	// compute the new height (handle node collision error & maintain readable view of tree in this part)
	var levelWidth = [1];
	var childCount = function(level, n) {
	if(n.children && n.children.length > 0) {
	  if(levelWidth.length <= level + 1) levelWidth.push(0);      
	  levelWidth[level+1] += n.children.length;
	  n.children.forEach(function(d) {
	    childCount(level + 1, d);
	  });
	}
	};

	childCount(0, root);  
	
	// 20 pixels per line
	newHeight = d3.max(levelWidth) * 30;     
	tree = tree.size([newHeight, width]);
	
	//to remove the already svg content & reload on every update
	d3.select("svg").remove();

	svg = d3.select("#treeView").append("svg");

	svg.attr("width", width + margin.right + margin.left)
	.attr("height", newHeight + margin.top + margin.bottom)
	.append("g")
	.attr("transform", "translate(" + margin.left + "," + margin.top + ")");
	
	$("svg").css({position: "relative", left: "70px"});

	// Compute the new tree layout.
	var nodes = tree.nodes(root).reverse(),
	links = tree.links(nodes);

	// Normalize for fixed-depth.
	nodes.forEach(function(d) { d.y = d.depth * 180; });

	// Update the nodes…
	var node = svg.selectAll("g.node")
	  .data(nodes, function(d) { return d.id || (d.id = ++i); });

	// Enter any new nodes at the parent's previous position.
	var nodeEnter = node.enter().append("g")
	  .attr("class", "node")
	  .attr("transform", function(d) { return "translate(" + source.y0 + "," + source.x0 + ")"; })
	  .on("click", click)
	  .on("mouseover", mouseover)
      .on("mouseout", mouseout);

	nodeEnter.append("circle")
	  .style("fill", function(d) { return d._children ? "#88ddd8" : "#fff"; });

	nodeEnter.append("text")
	  .attr("x", function(d) { return d.children || d._children ? -10 : 10; })
	  .attr("dy", ".35em")
	  .attr("text-anchor", function(d) { return d.children || d._children ? "end" : "start"; })
	  .text(function(d) { return d.sector; })
	  .style('stroke', '#000000')
	  .style('stroke-width', '0.1px')
	  .style('font-size', '12px');
	  //.style("fill-opacity", 1e-2);
	  
	//add hyperlinks to the children node (used another comDetails & url attributes only for children nodes)
	nodeEnter.append("svg:a")
	  .attr("xlink:href", function(d){return d.url;})  // <-- reading the new "url" property
	.append("svg:text")
	  .text(function(d) { return d.comDetails; })
	  .attr("x", 12)
	  .attr("dy", ".35em")
	  .style('stroke', '#000000')
	  .style('stroke-width', '0.1px')
	  .style('font-size', '12px')
	  .style("fill", '#000')
	  .on("click", click);

	nodeEnter.append("text")
	  .attr("x", 12)
	  .attr("dy", ".35em")
	  .text(function(d) { return d.rootSector; })
	  .style('stroke', '#000000')
	  .style('stroke-width', '0.1px')
	  .style('font-size', '12px')
	  .style("fill", '#000')
	  .style("fill-opacity", 1e-2);

	// Transition nodes to their new position.
	var nodeUpdate = node.transition()
	  .duration(duration)
	  .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; });

	nodeUpdate.select("circle")
	  .attr("r", 6)
	  .style("fill", function(d) { return d._children ? "#4dcbc5" : "#ebfaf9"; });

	nodeUpdate.selectAll("text")
	  .style("fill-opacity", 4);

	// Transition exiting nodes to the parent's new position.
	var nodeExit = node.exit().transition()
	  .duration(duration)
	  .attr("transform", function(d) { return "translate(" + source.y + "," +     source.x + ")"; })
	  .remove();

	nodeExit.selectAll("text")
	  .style("fill-opacity", 1e-6);

	// Update the links…
	var link = svg.selectAll("path.link")
	  .data(links, function(d) { return d.target.id; });

	// Enter any new links at the parent's previous position.
	link.enter().insert("path", "g")
	  .attr("class", "link")
	  .attr("d", function(d) {
	    var o = {x: source.x0, y: source.y0};
	    return diagonal({source: o, target: o});
	  });

	// Transition links to their new position.
	link.transition()
	  .duration(duration)
	  .attr("d", diagonal);

	// Transition exiting nodes to the parent's new position.
	link.exit().transition()
	  .duration(duration)
	  .attr("d", function(d) {
	    var o = {x: source.x, y: source.y};
	    return diagonal({source: o, target: o});
	  })
	  .remove();

	// Stash the old positions for transition.
	nodes.forEach(function(d) {
	d.x0 = d.x;
	d.y0 = d.y;
	});

	}

// Toggle children on click.
function click(d) {
  if (d.children) {
    d._children = d.children;
    d.children = null;
  } else {
    d.children = d._children;
    d._children = null;
  }
  update(d);
}

//hover action to text
function mouseover(d) {
    d3.select(this).append("text")
    	.attr("dx", "7")
    	.attr("dy", ".10em")
    	.attr("text-anchor", function(d) { return d.children || d._children ? "middle" : "start"; })
    	.text(function(d) { return d.name; })
        .attr("class", "hover")
        .attr('transform', function(d){ 
            return 'translate(5, -10)';
        })
        .style('font-size','10px')
        .style('fill','#247b77');
        //.text(d.name);
}

//Toggle children on click.
function mouseout(d) {
    d3.select(this).select("text.hover").remove();
}

