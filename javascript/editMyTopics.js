$(document).ready(function()
		{
$('.editt').click(function(){
	
	 id2=$(this).attr('id');
	$('#t'+id2).removeAttr('disabled');
	$('#'+id2).hide();
	$('#row'+id2).append('<input type="button" class="btn btn-success change" id="c'+id2+'" value="Commit Change" onclick="doSomething()" />');
	$('#row'+id2).append('<input type="button" class="btn btn-success delete" id="d'+id2+'" value="Delete" onclick="doSomething()"/>');
});
		});

function doSomething()
{
$('.change').click(function(){
	
	
 
	var newTopic= $('#t'+id2).val();

	obj=id2;
	$('#topics').val(newTopic);
	$('#obj').val(id2);
	
	$('#editForm').submit();
});

$('.delete').click(function(){
	alert("delete");
	$('#delObj').val(id2);
	$('#h2').submit();
		});
	
}