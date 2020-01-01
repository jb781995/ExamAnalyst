$(document).ready(function(){
	
	$('.moveThis').click(function(){
		alert("inside moveThis");
		var id=$(this).attr("id");
		var from =$('#from'+id).val();
		var to=$('#to'+id).val();
		alert("from: "+from+"   to : "+to);
		
		$('#f').val(from);
		$('#t').val(to);
		$('#i').val(id);
		$('#moveForm').submit();
		
	});
})