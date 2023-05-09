const data = {
    name: 'root',
    children: [
      { name: 'child1' },
      { name: 'child2' },
      {
        name: 'child3',
        children: [
          { name: 'grandchild1' },
          { name: 'grandchild2' },
        ]
      },
    ]
  };
  
  const svg = d3.select('#tree').append('svg')
    .attr('width', 500)
    .attr('height', 500)
    .append('g')
    .attr('transform', 'translate(40,0)');
  
  const treeLayout = d3.tree().size([400, 400]);
  
  const root = d3.hierarchy(data);
  
  function updateTree(source) {
    const treeData = treeLayout(root);
  
    const nodes = svg.selectAll('.node')
      .data(treeData.descendants(), d => d.id);
  
    nodes.enter().append('circle')
      .attr('class', 'node')
      .attr('r', 10)
      .attr('cx', d => d.x)
      .attr('cy', d => d.y);
  
    nodes.exit().remove();
    
    const links = svg.selectAll('.link')
      .data(treeData.links(), d => d.target.id);
  
    links.enter().append('path')
      .attr('class', 'link')
      .attr('d', d => {
        return `M${d.source.x},${d.source.y}L${d.target.x},${d.target.y}`;
      });
  
    links.exit().remove();
  }
  
  updateTree(root);
  
  function addChild(node) {
    if (!node.children) {
      node.children = [];
    }
    const newNode = { name: `child${node.children.length + 1}` };
    node.children.push(newNode);
    updateTree(root);
  }
  
  function removeChild(node) {
    if (node.children) {
      node.children.pop();
      updateTree(root);
    }
  }
  
  function toggleNode(d) {
    if (d.children) {
      d._children = d.children;
      d.children = null;
    } else {
      d.children = d._children;
      d._children = null;
    }
    updateTree(d);
  }
  
  svg.on("click", function() {
    const coords = d3.mouse(this);
    const newNode = { name: "New Node" };
    const clickedNode = findClosestNode(coords[0], coords[1]);
    if (clickedNode) {
      if (!clickedNode.children) {
        clickedNode.children = [];
      }
      clickedNode.children.push(newNode);
      updateTree(hierarchy);
    }
  });

  function findClosestNode(x, y) {
    let closestNode;
    let closestDistance = Infinity;
    hierarchy.each(function(node) {
      const dx = x - node.x;
      const dy = y - node.y;
      const distance = Math.sqrt(dx * dx + dy * dy);
      if (distance < closestDistance) {
        closestDistance = distance;
        closestNode = node;
      }
    });
    return closestNode;
  }
  svg.on("contextmenu", function() {
    d3.event.preventDefault();
    const coords = d3.mouse(this);
    const clickedNode = findClosestNode(coords[0], coords[1]);
    if (clickedNode && clickedNode.parent) {
      clickedNode.parent.children = clickedNode.parent.children.filter(child => child !== clickedNode);
      updateTree(hierarchy);
    }
  });
  