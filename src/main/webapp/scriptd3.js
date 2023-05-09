const data = {
    name: 'SoSs',
    children: [
      {
        name: 'Device 1',
        children: [
          { name: 'Component 1' },
          { name: 'Component 2' },
          { name: 'Component 3' },
        ],
      },
      {
        name: 'Device 2',
        children: [
          { name: 'Component 4' },
          { name: 'Component 5' },
        ],
      },
      {
        name: 'Device 3',
        children: [
          { name: 'Component 6' },
          { name: 'Component 7' },
        ],
      },
    ],
  };
  
  const svg = d3.select('#tree').append('svg').attr('width', 800).attr('height', 600);
  
  const hierarchy = d3.hierarchy(data);
  
  const treeLayout = d3.tree().size([600, 400]).nodeSize([100, 100]);
  
  const diagonal = d3.linkVertical().x((d) => d.x).y((d) => d.y);
  
  function updateTree(source) {
    const treeData = treeLayout(hierarchy);
  
    const nodes = svg.selectAll('g.node').data(treeData.descendants(), (d) => d.id);
  
    const nodeEnter = nodes
      .enter()
      .append('g')
      .attr('class', 'node')
      .attr('transform', (d) => `translate(${source.x0},${source.y0})`)
      .on('click', toggleNode);
  
    nodeEnter.append('circle').attr('r', 20).style('fill', '#fff').style('stroke', '#666');
  
    nodeEnter
      .append('text')
      .text((d) => d.data.name)
      .attr('dy', 5)
      .style('text-anchor', 'middle');
  
    const nodeUpdate = nodes.merge(nodeEnter);
  
    nodeUpdate
      .transition()
      .duration(500)
      .attr('transform', (d) => `translate(${d.x},${d.y})`);
  
    nodeUpdate.select('circle').attr('r', 20).style('fill', '#fff').style('stroke', '#666');
  
    const nodeExit = nodes.exit().transition().duration(500).attr('transform', (d) => `translate(${source.x},${source.y})`).remove();
  
    nodeExit.select('circle').attr('r', 0);
  
    const links = svg.selectAll('path.link').data(treeData.links(), (d) => d.target.id);
  
    const linkEnter = links.enter().insert('path', 'g.node').attr('class', 'link').attr('d', (d) => {
      const o = { x: source.x0, y: source.y0 };
      return diagonal({ source: o, target: o });
    });
  
    const linkUpdate = links.merge(linkEnter);

    linkUpdate
      .transition()
      .duration(500)
      .attr('d', diagonal);
  
    const linkExit = links.exit().transition().duration(500).attr('d', (d) => {
      const o = { x: source.x, y: source.y };
      return diagonal({ source: o, target: o });
    }).remove();
  
    nodes.each(function(d) {
      d.x0 = d.x;
      d.y0 = d.y;
    });
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
  
  updateTree(hierarchy);
  
  