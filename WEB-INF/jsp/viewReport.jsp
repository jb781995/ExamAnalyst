<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="common/head.jsp" ></jsp:include>
<head>
  <script type="text/javascript" src="http://www.google.com/jsapi"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="resources/javascript/jquery-2.1.4.min.js" />"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    

<script type="text/javascript">

$(document).ready(function(){
    /* $('#graphicalReport2').click(function(){
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	var data2=["topicName","correct","wrong","unAttempted"];
    	$
		.ajax({
			
			type : 'GET',
			url : 'http://localhost:8080/csjobs-exam/getJsonOfMap2.html',
			
			success : function(data)
			{
				 jsonData= jQuery.parseJSON(data);
				alert(jsonData);
				createChart(jsonData);
					}
		});
		
		
		  
		 
        
        
        function createChart(jsonData) {
        	google.load('visualization', '1', {'packages': ['corechart']});
        	 var data = google.visualization.arrayToDataTable(jsonData);

        	                                              var options = {
        	                                                title : 'Student Result Analysis',
        	                                                vAxis: {title: 'Score'},
        	                                                hAxis: {title: 'Topic'},
        	                                                seriesType: 'bars',
        	                                                series: {5: {type: 'line'}}
        	                                              };
        	   
           
           var chart = new google.visualization.ColumnChart (document.getElementById('chart'));
           var Combochart = new google.visualization.ComboChart(document.getElementById('chart2'));

           
          
           Combochart.draw(data, options);
        }
 
    	
    	
    });
    
});
 */

$('#graphicalReport2').click(function(){
	google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawVisualization);


    function drawVisualization() {
    	
      // Some raw data (not necessarily accurate)
      $
		.ajax({
			
			type : 'GET',
			url : 'http://localhost:8080/csjobs-exam/getJsonOfMap2.html',
			
			success : function(data)
			{
				var jsonData= jQuery.parseJSON(data);
				alert(jsonData);
   var	array = JSON.parse(jsonData);
			      alert(array[0]['topicName']);
			      alert(array[0]['correct']);
			      alert(array[1]['topicName']);
			      alert("length:"+array.length);
			      var data = new google.visualization.DataTable();
			      alert(array.length);
			      data.addColumn('string', 'Topic Name');
			      data.addColumn('number', 'Correct');
			      data.addColumn('number', 'Wrong');
			      data.addColumn('number', 'Unattempted');
			      
			      data.addRows(1);
			      data.setCell(0, 0, 'DFS');
			      data.setCell(0, 1, 10);
			      data.setCell(0, 2, 3);
			      data.setCell(0, 3, 3);
			     
			    /*   for(var i=0;i<array.length;i++)
			    	 {
			    	  alert(array[i]['topicName']);
			    	  alert("correct: "+array[i]['correct']);
			    	  var test=array[i]['topicName'];
			    	  var x="'"+test+"'";
			    	  alert(x);
			    	//data.addRow([x, array[i]['correct'],array[i]['wrong'],array[i]['unAttempted']]);
			    	   
			    	 }
 */
			      
			      
			      
			      /* data.addRows(array.length);
                  for (var i = 0; i < array.length; i++)
                  {
                     for (var k = 0; k < 4; k++)//Total Column 
                      {
                        if (k == 0) 
                          {
                            data.setCell(i, k, array[i]['topicName'].toString());//if first Column is String
                          } 
                           else if(k==1)
                          {
                            data.setCell(i, k, parseInt([array[i]['correct']]));//if other columns are int type... for charts mostly we treat just first column as string
                          }
                           else if(k==2)
	                             {
	                               data.setCell(i, k, parseInt([array[i]['wrong']]));//if other columns are int type... for charts mostly we treat just first column as string
	                             }
                           else 
	                             {
	                               data.setCell(i, k, parseInt([array[i]['unAttempted']]));//if other columns are int type... for charts mostly we treat just first column as string
	                             }
                      }
                 }
		    	  

 */					}
		});
      		
      
       /* var data = google.visualization.arrayToDataTable([
       ['Topic Name', 'Correct', 'Wrong', 'Unattempted'],
       ['DFS',  12,      938,         522],
       ['ACN',  135,      1120,        599] */
       
   /*  ]);  */
      
      
  var options = {
    title : 'Student Result List',
    vAxis: {title: 'Score'},
    hAxis: {title: 'Topic Name'},
    seriesType: 'bars'
  
  };

  var chart = new google.visualization.ComboChart(document.getElementById('chart'));
  chart.draw(data, options);
}

	
});
 });

    

    </script>
</head>
<body>
<jsp:include page="common/header.jsp" ></jsp:include>

<h3><b><i>${thisStudent.stuUser.lname}&nbsp;&nbsp;${thisStudent.stuUser.fname}'s </i></b> Report </h3>

<div class="panel panel-default" style="width:1000px;margin-left:300px;margin-top:30px;">
  <div id="headerdiv" class="panel-heading" style="font-size:20px">Topicwise Analysis</div>
  <div class="panel-body">
    <table  class="table tab" style="font-size:18px;width:900px;margin:0px auto;background-color:DarkGray">
<tr>
<th>Topic </th>
<th>Correct </th>
<th>Wrong </th>
<th>Un-Attempted </th>
</tr>
<c:forEach items="${studentStats}" var="statistics">
<tr class="data">
<td><b> ${statistics.key} </b> </td>
<c:forEach items="${statistics.value}" var="item" varStatus="loop">
     <td>   ${item} ${!loop.last ? ' ' : ''} </td>
    </c:forEach><br>
    </tr>
</c:forEach>

<!-- <input type="button" id="graphicalReport" value="Show Graphical Analysis"  name="View"/> -->

</table>
</div>
</div>
<br><br>

<input type="text" id="json" name="statistics" value="${jsonStats}" />
<!-- <input type="button" id="graphicalReport2" value="Show Graphical Analysis"  name="View"/> -->
<div id="chart" ></div>
<div id="chart2"> </div>

</body>
<script src="<c:url value="resources/javascript/jquery-2.1.4.min.js" />"></script>
<%-- <script src="<c:url value="resources/javascript/graphicalReport.js" />"></script> --%>

</html>