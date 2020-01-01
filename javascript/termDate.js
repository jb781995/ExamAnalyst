$(document).ready(function(){
	
	$('.edit').click(function(){
		
		
		id = $(this).attr("id");
		
		$("#" + id).hide();
		$("#row" + id).append('<input  type="button" name="change" value="Commit change" id="change'+id+' " class="btn btn-success commit" onclick="changeDate()"/>');
		$('#sem'+id).removeAttr('disabled');
		$('#from'+id).removeAttr('disabled');
		$('#to'+id).removeAttr('disabled');
	});
	
	
});

function changeDate()
{
$('.commit').click(function(){
	
	var newSem = $("#sem" + id).val();
	var newFrom = $("#from" + id).val();
	var newTo = $("#to" + id).val();
	
	
	$("#semester").val(newSem);
	$("#from").val(newFrom);
	$("#to").val(newTo);
	$('#id').val(id);
	$("#hide").submit();
	
});	
}

