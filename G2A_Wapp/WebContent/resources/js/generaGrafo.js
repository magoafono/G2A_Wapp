
/* global vis */

function drawGraph(height, width) {
   // alert("nella drawGraph(" + height + ", " + width+ " ) ");
    var a = document.getElementById('tabViewId:formnascosto:nascosto').value;
    var res = a.split("-");
    var nodeList = res[0].split("*");
    var edgeList = res[1].split("*");

    var nodes = [];
    var edges = [];
    var network = null;

    // creo i nodi
    var len = nodeList.length;
    for (var i = 0; i < len; i++) {
        if (i === 0) {
            nodes.push({
                id: i,
                label: nodeList[i],
                color: 'red'
            });
        } else {
            nodes.push({
                id: i,
                label: nodeList[i]
            });
        }
    }

    // creo le relazioni
    var len = edgeList.length;
    for (var i = 0; i < len; i++) {
        edges.push({
            from: 0,
            to: i + 1,
            label: edgeList[i]
        });
    }

    var options = {
        autoResize: true,

        nodes: {
            // default for all nodes
            // fontFace: 'times',// unknown
            shape: 'box',
            color: {
                border: 'orange',
                background: 'yellow',
                highlight: {
                    border: 'darkorange',
                    background: 'gold'
                }
            }
        },
        "edges": {
            "arrows": {
                "to": {
                    "enabled": true
                }
            },
            "smooth": {
                "forceDirection": "none"
            }
        },
        "interaction": {
            "navigationButtons": true
        },
        "physics": {
            "enabled": true,
            "minVelocity": 0.75,
            "solver": "repulsion"
        }
    }

    var options2 = {
        //  navigation: true, // unknown
        //   stabilize: true, // unknown
        //   configurePhysics: false,// unknown
        //   smoothCurves: false,// unknown
        autoResize: true,
        //   showButton: true,// unknown

        edges: {
            width: 2,
            arrows: true,
            //  style: 'arrow',// unknown
            color: 'gray'
        },
        nodes: {
            // default for all nodes
            // fontFace: 'times',// unknown
            shape: 'box',
            color: {
                border: 'orange',
                background: 'yellow',
                highlight: {
                    border: 'darkorange',
                    background: 'gold'
                }
            }
        },

        physics: {
            barnesHut: {
                gravitationalConstant: -20000,
                centralGravity: 0, //this should be set to 0 to avoid jumping
                springLength: 300,
                springConstant: 0.5, //this should be set to 0 to avoid jumping
                damping: 0.3
            }/*, 
             repulsion: {
             centralGravity: 0.1,
             springLength: 10,
             springConstant: 0.1,
             nodeDistance: 300,
             damping: 0.09
             }*/
        }// this is the correct way to set the length of the springs
    };
    // create the network
    var container = document.getElementById('tabViewId:reportViewForm:mynetwork');
    var data = {
        nodes: nodes,
        edges: edges
    };
    network = new vis.Network(container, data, options);
    network.setSize( width, height );
    //network.zoomExtent(true);
    //  network.fit({animate: false});
    // add event listener
    //network.on('select', function(properties) {
    //    document.getElementById('info').innerHTML += 'Hai selezionato il nodo: ' + properties.nodes + '<br/>';
    //});

}
