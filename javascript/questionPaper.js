

$(document).ready(function(){
	
	
	
	
	 size=parseInt($("#size").text(),10);
	for (var i=1;i<=size;i++)
		{
		index=i;
		$(".palette").append("<input type='button' name='xyz' style='width:35px'  value='"+i+"' class='paletteQue' id='btn"+i+"'/>");
		}
	
	$('.next').click(function(){
		
		
		var index= $('#hdn').val();// 1st:0
		var answer=$('#ans').val();
		
		
		
		
		if(index=="")
			{
			 id=$(this).attr("id");//1st,id=1|
			 id=parseInt(id)+1;//now, id=2|
			
			}
		else
			{id=index;
			//alert("id:"+id);
			
			
			}
		
		
		$
		.ajax({
		
			type : 'GET',
			url : 'http://localhost:8080/csjobs-exam/getNextQuestion.html?qNo='+id+'&ans='+answer,
			
			success : function(data)
			{
				
				if(data[1].length)
					{
					
					$('#ans').val(data[1]);
					}
				else
					{
				
				var ansField= $('#ans').val();
				
				if(ansField.length>0)
					{
					ansField='';
					$('#ans').val(ansField);
					}
					}
				
				var indexres=parseInt(id)+1; // 1st,indexres=3|
				
				$('#hdn').val(indexres); //1st, hdn=3|
				$('#lbl').val(parseInt(id));
				
				$('#textArea').empty();
				
				
				$('#textArea').val(id +") "+data[0]);
				
				$('#hdnCurrentQue').val(id);//1st, this:2|
				
			}
		});
		
	});
	
	$('.previous').click(function(){
		// assume returning to 1st que from 2nd
		var index=parseInt( $('#hdn').val())-1;// index=2
				
		$
		.ajax({
			
			type : 'GET',
			url : 'http://localhost:8080/csjobs-exam/getPreviousQuestion.html?qNo='+index,
			success : function(data)
			{
				var indexres=parseInt(index)-1; // indexres=1
			 // var data2=JSON.parse(data);
				//alert(data2);
				
					
				
				$('#textArea').empty();
				$('#textArea').val(indexres +") "+data[0]);
				$('#ans').val(data[1]);
				$('#hdn').val(index);// hdn=2
				$('#hdnCurrentQue').val(indexres);//this=1
				
			}
			
		});
		
	});
	
	$('.mark').click(function(){
		var id =$('#hdnCurrentQue').val();
		$('#btn'+id).css("background-color","red");
		
		
	});
	
	
	$('.unmark').click(function(){
		var id =$('#hdnCurrentQue').val();
		$('#btn'+id).css("background-color","");
		
		
	});
	
	
	
	
	
	
	$("input[name = 'xyz']").click(function(){
		
		var id3=$(this).attr("value");
		var index=$(this).val();
		var answer=$('#ans').val();
		
		
		
		$
		.ajax({
			
			type: 'GET',
			url:'http://localhost:8080/csjobs-exam/paletteQuestion.html?qNo='+index+'&ans='+answer,
			success:function(data)
			{
				
				
				var indexres=parseInt(index)-1;
				
				$('#textArea').empty();
				$('#textArea').val(index +") "+data[0]);
				$('#ans'+id).val(data[1]);
				$('#hdn').val(index);
				$('#hdnCurrentQue').val(index);
				
			}
			
		});
		
	});
	
	
});

/*$('.paletteQue').click(function(){
	
	var id3=$(this).attr("value");
	alert("id"+id3);
	var index=$(this).val();
	var answer=$('#ans').val();
	alert("inside palette: index "+index+" nsewr: "+answer);
	
	
	$.ajax({
		
		type: 'GET',
		url:'http://localhost:8080/csjobs-exam/paletteQuestion.html?qNo='+index+'&ans='+answer,
		success:function(data)
		{
			alert("Going in");
			
			var indexres=parseInt(index)-1;
			
			$('#textArea').empty();
			$('#textArea').val(index +") "+data[0]);
			$('#ans'+id).val(data[1]);
			$('#hdn').val(index);
			$('#hdnCurrentQue').val(index);
			
		}
		
	});
	
});
*/