$(document).ready(function(){
		
	
	 $('.setDate').click(function(){
		  
			//date = $(".examDate").val();
			alert("inside set link");
			var id=$(this).attr("id");
			
			var date2=$("#sub"+id).val();
			
//			 textFieldValue=$(this).siblings("text").val();
			
			
			if (date2.length==0) {
				alert("sorry...You need to set date first  ");

				return false;
			}
			
			else
				{
				//dateForThisSub=$('.examDate').val();
				dateForThisSub=$("#sub"+id).val();
				alert("you set:"+dateForThisSub);
				var thisHref = $("#form"+id).attr("action");
				alert("form's action:"+thisHref);
				$("#form"+id).attr("action",thisHref + "&date="+dateForThisSub);
				
				$(this).attr("href");
				alert("after attr");
				$('#hiddenDateTextField').val(dateForThisSub);
				$('#hideDate').submit();
				return true;
				}
		});
	 
	 $('.setDate2').click(function(){
	
		 var id=$(this).attr("id");
		 var date2=$("#date"+id).val();
		 if (date2.length==0) {
				alert("sorry...You need to set date first  ");

				return false;
			}
		 else
			 {
		 var date= $("#date"+id).val();
		 alert("got date:"+date);
		 var subId=$("#subject"+id).text();
		 alert("got SubId:"+subId);
		 $(".selection").each(function(subject,index){
		 if($(this).is(':checked'))
			 {
			  type=$(this).val();
			 alert("i selected:"+type);
			 }
		 });
		 
		 $("#hiddenDate").val(date);
		 $("#hiddenSubId").val(subId);
		 $("#hiddenExamType").val(type);
		 $("#hide").submit();
			 }
	 });
	 
	 
	});