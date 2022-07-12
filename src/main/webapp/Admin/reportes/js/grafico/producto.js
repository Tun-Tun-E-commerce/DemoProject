window.onload = function () {

    var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        theme: "light2", // "light1", "light2", "dark1", "dark2"
        title:{
            text: "Reporte Producto"
        },
        axisY: {
            title: "Ventas"
        },
        data: [{        
            type: "column",  
            showInLegend: true, 
            legendMarkerColor: "grey",
            legendText: "Ropa de bebe",
            dataPoints: [      
                { y: 10000, label: "Ajueres" },
                { y: 20000,  label: "Esqueletos" },
                { y: 30000,  label: "Semanarios" },
                { y: 50000,  label: "Mitones" },
                { y: 60000,  label: "Gorros" },
            ]
        }]
    });
    chart.render();
    
    }