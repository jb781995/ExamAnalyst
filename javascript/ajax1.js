


function	checkfunction(i)
{
	alert(i);
	var value=$(i).val();
	$
	.ajax({
		type : 'POST',
		url : 'http://localhost:8080/csjobs-exam/savesubjects.html',
		dataType : 'json',
		data : {
			subject : value
		},
		success : function(data) {
			alert(data);
			
},
		error : function(xmlHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
			if (xmlHttpRequest.readyState = 0 || xmlHttpRequest.status == 0)
				return;
		}
	});

}


function checkSemList()
{
	for(var i=0;i<=semList.length;i++)
		{
		
	console.log(semList+" : sem");
	alert(semList+" : sem");

		}
}
var sub='';
$('#saveBtn').click(function(){
	
	
	
	$('.check').each(function(subject,index){
			//alert($(this).attr('id')+"="+$(this).is(':checked'));
		
		if($(this).is(':checked')){
			sub+=$(this).attr('id')+",";
		}
		
	});
	
	sub=sub.substring(0,sub.length-1);
	
	
	$('#hiddenField1').val(sub);
	$('#hide').submit();
	
	
});

$('.checkSelectedSem').click(function(){
	  
	alert("inside go");
	$('.check').each(function(){
		
		
		alert("inside each of : Go");
		
		var isChecked=$(this).attr('id');
		alert(isChecked+" :is selected");
			
	});
	
	

});

function countSubjects()
{
	alert("inside subject count");

	var subName=$(this).val();
	subName+=subName;
	
        for(var i=0;i<=subName.length;i++)
        	{
        	alert(list+" : is checked");
        	}
}


$(document).ready(function(){
	
$('.semester').change(function(){
		alert("got semester ");
		
		 value=$(this).val();
		 $
		.ajax({
			type : 'GET',
			url : 'http://localhost:8080/csjobs-exam/getSubjectBySemester.html?sem='+value,
			success : function(data) {
				
				for(var i in data)
					{
				
					$('#subjects').append('<input type="checkbox" class="check"  id="'+data[i].id+'" name="'+i+'"  value="'+data[i].subjectName+'"  />'+data[i].subjectName+'</br>');
					}
	},
			error : function(xmlHttpRequest, textStatus, errorThrown) {
				if (xmlHttpRequest.readyState = 0 || xmlHttpRequest.status == 0)
					return;
			},
	              
	
	
		});
		 semList=value.add();
		

		
	
		
		/*var value=$(this).val();
		$.getJSON("/getSubjectBySemester",
				{sem:$(value)},
				function(data)
				{
					alert(data);
					for(var i in data)
						{
						$('#subjects').append('<input type="checkbox"/>'+data[i].subjectName);
						}
				}
				);*/
	});
});