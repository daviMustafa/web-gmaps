$(document).ready(function(){
	
	$('.delTag').button().on('click', function(source){
		deleteTag(function(data){
			console.log(data);
			$('tr#'+$("#idTag").val()).fadeOut(1000, function(item){$(item).remove});;
			
		})
		source.preventDefault();
	});
	
	function deleteTag(callbackhandler){
		$.ajax({
			url : "/trixmaps_v2/tagController?id="+$("#idTag").val(),
			data: {} ,
			type: "DELETE",
			success: callbackhandler,
				
//				location.href = "/trixmaps_v2/tagController";
				
			
			async: true
		});
	}
});	