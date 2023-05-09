$(function() {
    var treeData = [
      {
        text: "SoSs",
        id: "soss",
        children: [
          {
            text: "Example SoS",
            id: "example-sos",
            children: [
              {
                text: "Example Device 1",
                id: "example-device-1",
                children: [
                  {
                    text: "Example Component 1",
                    id: "example-component-1",
                    children: [
                      {
                        text: "Example Skill 1",
                        id: "example-skill-1",
                        children: [
                          {
                            text: "Example Rule 1",
                            id: "example-rule-1"
                          }
                        ]
                      }
                    ]
                  }
                ]
              },
              {
                text: "Example Device 2",
                id: "example-device-2",
                children: [
                  {
                    text: "Example Component 2",
                    id: "example-component-2",
                    children: [
                      {
                        text: "Example Skill 2",
                        id: "example-skill-2",
                        children: [
                          {
                            text: "Example Rule 2",
                            id: "example-rule-2"
                          }
                        ]
                      }
                    ]
                  }
                ]
              }
            ]
          }
        ]
      }
    ];
  
    $('#tree').jstree({
      'core': {
        'data': treeData
      }
    });
  
    $('#addNode').click(function() {
      var nodeName = $('#nodeName').val();
      var parentNode = $('#parentNode').val();
      var nodeType = $('#nodeType').val();
  
      if (nodeName && parentNode && nodeType) {
        var parent = $('#' + parentNode);
        if (parent.length > 0) {
          var nodeId = nodeName.toLowerCase().replace(/ /g, '-');
          var newNode = {
            text: nodeName,
            id: nodeId,
            icon: 'glyphicon glyphicon-leaf'
          };
          switch (nodeType) {
            case 'device':
              newNode.children = [];
              newNode.icon = 'glyphicon glyphicon-hdd';
              break;
            case 'component':
              newNode.children = [];
              newNode.icon = 'glyphicon glyphicon-cog';
              break;
            case 'skill':
              newNode.children = [];
              newNode.icon = 'glyphicon glyphicon-flash';
              break;
            case 'rule':
              newNode.icon = 'glyphicon glyphicon-ok-sign';
              break;
          }
          parent.jstree('create_node', null, newNode, 'last');
          if (nodeType !== 'rule') {
            parent.jstree('open_node', '#' + nodeId);
          }
        } else {
          alert('Parent node not found!');
        }
      } else {
        alert('Please enter node name, parent node, and node type!');
      }
    });
  });
  