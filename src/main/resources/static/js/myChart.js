//https://www.chartjs.org/docs/latest/charts/doughnut.html
//<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
//In browser inspector: console > var to see values, also errors

//Id defined in home.html
var ctx = document.getElementById('myChart');

//chartData used in HTML to capture DB data (via model & crud interface)
var chartDataStr = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataStr);
var arrayLength = chartJsonArray.length;
var numericData = [];
var labelData = [];

for(var i=0; i < arrayLength; i++){
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label;
}


var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'pie',
    // The data for our dataset
    data: {
        labels: labelData,
        datasets: [{label: 'project status donut',
            backgroundColor: ["#EB5D65", "#95B5CC", "#FBDC7D"],
            borderColor: ["#ECECEC"],
            data: numericData}]
    },

    // Configuration options go here
    options: {
    	title: {
    		display: true,
    		text: 'Project Statuses'
			}
	}
});

//CONVERT: [{&quot;value&quot;:1,&quot;label&quot;:&quot;COMPLETED&quot;},{&quot;value&quot;:2,&quot;label&quot;:&quot;INPROGRESS&quot;},{&quot;value&quot;:1,&quot;label&quot;:&quot;NOTSTARTED&quot;}]"
//TO: "[{"value": 1, "label": "COMPLETED"},{"value": 2, "label": "INPROGRESS"},{"value": 1, "label": "NOTSTARTED"}]"
function decodeHtml(html){
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}