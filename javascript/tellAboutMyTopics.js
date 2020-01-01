$(document).ready(function(){
	
	$('.topic').click(function(){
		
		id= $(this).attr("id");
		subId=$('#s'+id).val();
		sem=$('#sem'+id).val();
		topicList=$('#topic'+id).val();
		alert('subid: '+subId+' and topics: '+topicList+' and semester :' +sem);
		
		$('#sem').val(sem);
		$('#subID').val(subId);
		$('#topics').val(topicList);
		alert("almost done");
		$('#hide').submit();
	});
	
});
	
	/*$('.editt').click(function(){
		alert("click...");
		
		 id2=$(this).attr('id');
		$('#t'+id2).removeAttr('disabled');
		$('#'+id2).hide();
		$('#row'+id2).append('<input type="button" class="change" id="c'+id2+'" value="Commit Change" onclick="doSomething()" />');
		$('#row'+id2).append('<input type="button" class="delete" id="d'+id2+'" value="Delete" onclick="doSomething()"/>');
	});
	
	
	
	
	
});

function doSomething()
{
	$('.change').click(function(){
		
		alert("change");
		var newTopic= $('#t'+id2).val();
		alert("new topic: "+newTopic);
		obj=id2;
		alert("obj id: "+obj);
		$('#t').val(newTopic);
		$('#o').val(id2);
		alert("new topic: "+newTopics+" and this topic obj id: "+obj);
		$('#hide2').submit();
	});	

}

*/