
$(document)
		.ready(
				function() {
					var testid = "";

					$(".edit")
							.click(
									function() {

										id = $(this).attr("id");
										
										$("#" + id).hide();
										$("#date" + id).removeAttr('disabled');
										$("#type" + id).removeAttr('disabled');

										$("#row" + id)
												.append(
														'<input  type="button" name="change" value="Commit change" id="change'
																+ id
																+ '" class="btn btn-success commit" onclick="deleteDate()"/>&nbsp;&nbsp;');
										$("#row" + id)
												.append(
														'<input  type="button" name="Delete" value="Delete" id="del'
																+ id
																+ '" class="btn btn-success delete" onclick="deleteDate()"/>');

									});

					
				});

function deleteDate() {

	$(".delete").click(function() {

		$("#Id").val(id);
		$("#hide2").submit()

		/* var id2=parseInt($("#number"+id).text(),10); */

	});
	$(".commit").click(function() {
		alert("inside edit");
		var newDate = $("#date" + id).val();
		var newType = $("#type" + id).val();
		
		$("#hiddenDate").val(newDate);
		$("#hiddenExamType").val(newType);
		$("#hiddenId").val(id);
		$("#hide").submit();

	});


}

