<html>
<script type="text/javascript">
	$(document).ready(function() {
		$("#form_tag").validate({
			onfocusout : function(element) {
				console.log($(element).next("label").remove());
				$(element).valid();
			},
			// Define as regras
			rules : {
				name : {
					required : true,
					minlenght : 3
				}

			},
			// Define as mensagens de erro para cada regra
			messages : {
				name : {
					required : "Name required.",
					minlength : "Name requires at least 3 characters."
				}
			}
		});

	});
</script>
<div style="text-align: center">

	<h3>Create new Tag</h3>
	</br>
	<form id="form_tag" action="tagController" method="get">
		<label>Name: </label> 
		<input type="text" name="name" /></br>
		<input type="hidden" value="save" name="acao" /> 
		<input type="submit" value="Save" /> 
		<input type="reset" value="Reset" />
	</form>
</div>
</html>