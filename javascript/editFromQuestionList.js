
	
	

function deleteQue()
{
$(".delete").click(function(){
	alert("inside delete");
});	
}

$(document).ready(function(){
	
	$(".edit").click(function(){
		
		 id=$(this).attr("id");
		
		$("#que"+id).removeAttr('disabled');
		$("#option"+id).removeAttr('disabled');
		$("#topic"+id).removeAttr('disabled');
		$("#"+id).hide();
		$("#actions"+id).append('<input type="button" class="change" id="change'+id+'" value="Commit Change" onclick="chaneQue()"/>');
		$("#actions"+id).append('<input type="button" class="delete" id="delete'+id+'" value="Delete" />');

	});
	
	
	
	
	
});

function chaneQue(){
	
	$(".change").click(function(){
		
		alert("inside edit.. ID: "+id);

		
		 question=$("#que"+id).val();
		 topic=$("#topic"+id).val();
		
		 ans=$("#option"+id).val();
		 id2=parseInt($("#no"+id).text,10);
		//alert("QNo: "+id+" question: "+question+" topic : "+topic+" answer: "+ans);
		 $("#Que").val(question);
			$("#Topic").val(topic);
			$("#Option").val(ans);
			$("#QNo").val(id);
			
			$("#hide1").submit();
		
	});
	}

