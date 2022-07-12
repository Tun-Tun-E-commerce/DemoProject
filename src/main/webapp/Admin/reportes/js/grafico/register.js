window.onload = function() {

    var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        title: {
            text: "Reporte Registros - 2022"
        },
        data: [{
            type: "pie",
            startAngle: 240,
            yValueFormatString: "##0.00\"%\"",
            indexLabel: "{label} {y}",
            dataPoints: [
                {y: 1, label: "Enero"},
                {y: 1, label: "Febrero"},
                {y: 1, label: "Marzo"},
                {y: 1, label: "Abril"},
            ]
        }]
    });
    chart.render();
    
    }