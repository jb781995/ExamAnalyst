$(document).ready(function(){
	
/*	$('#graphicalReport2').click(function(){
		
		alert("view");
		$
		.ajax({
			
			type : 'GET',
			url : 'http://localhost:8080/csjobs-exam/getJsonOfMap2.html',
			success : function(data)
			{
				alert("data i recieved: "+data);
			}
		});
		
		google.load('visualization', '1', {'packages': ['columnchart','combochart']});
		 
        //set callback
        google.setOnLoadCallback (createChart);
        function createChart() {
        	 
            //create data table object
            var dataTable = new google.visualization.DataTable();
            

            //define columns
            dataTable.addColumn('string','Topic');
            dataTable.addColumn('number', 'Correct');
            dataTable.addColumn('number', 'Wrong');
            dataTable.addColumn('number', 'UnAttempted');

           $('table.tab tr.data').each(function(i){
        	   
        	   this.$("td").each(function (j){
        		
        		   dataTable.addRows([i,j]);
        	   }) ;
        	   
           });
           
           var chart = new google.visualization.ColumnChart (document.getElementById('chart'));
           var Combochart = new google.visualization.ComboChart(document.getElementById('chart2'));

           
           var options = {width: 400, height: 240, is3D: true, title: 'This Student Report'};
           
           //draw our chart
           chart.draw(dataTable, options);
           Combochart.draw(dataTable, options);
        }
		
		
		
	});*/
	
	$('#graphicalReport2').click(function(){
		
		$
		.ajax({
			
			type : 'GET',
			url : 'http://localhost:8080/csjobs-exam/getJsonOfMap2.html',
			success : function(data)
			{
				 jsonData= JSON.parse(data);
				alert("data i recieved: "+jsonData);
			}
		});
		
		
		google.load('visualization', '1', {'packages': ['columnchart','combochart']});
		 
        //set callback
        google.setOnLoadCallback (createChart);
        function createChart() {
        	 
            //create data table object
            var dataTable = new google.visualization.arrayToDataTable(jsonData);
            

            //define columns
            dataTable.addColumn('string','Topic');
            dataTable.addColumn('string', 'Correct');
            dataTable.addColumn('string', 'Wrong');
            dataTable.addColumn('string', 'UnAttempted');

           
           // dataTable.addRows(jsonData);
        	   
           
           var chart = new google.visualization.ColumnChart (document.getElementById('chart'));
           var Combochart = new google.visualization.ComboChart(document.getElementById('chart2'));

           
           var options = {width: 400, height: 240, is3D: true, title: 'This Student Report'};
           
           //draw our chart
           //chart.draw(dataTable, options);
           Combochart.draw(dataTable, options);
        }
		
		
		
	});

		
	});
	