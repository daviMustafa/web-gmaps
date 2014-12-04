<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	function submitId(id){
		$("#id").val(id);
		$("#form_tag_list").submit();
	}
</script>
<div style="text-align: center">
	
	<h3>Tags Manager</h3>
	</br>
	<form id="form_tag" action="tagController" method="post">
		<label>Name: </label> <input type="text" name="name" /></br>
		<label for="name" style="color: red"></label> 
		<input type="hidden" value="save" name="action" /> 
		<input type="submit" value="Save" />
		<input type="reset" value="Reset" /> </br>
	</form>

	<form id="form_tag_list" action="tagController" method="post">
		<input type="hidden" value="" name="id" id="id"/>
			
		<c:if test="${not empty tags }">
			<table  style="margin: 0px auto; margin-top: 50px">
				<thead>
					<td style="width: 200px; text-align: center">ID</td>
					<td style="width: 200px; text-align: center">Name</td>
					<td style="width: 200px; text-align: center">Created</td>
					<td style="width: 200px; text-align: center">Delete</td>
				</thead>
				<c:forEach var="tag" items="${tags}">
					<tr>
						<td style="text-align: center"><c:out value="${tag.id}"/></td>
						<td style="text-align: center"><c:out value="${tag.name}" /></td>
						<td style="text-align: center"><c:out value="${tag.created}" /></td>
						<td style="text-align: center">
							<div>
								<button value="Delete" type="button" onclick="submitId(${tag.id})">Delete</button>
								<input type="hidden" value="delete" name="action" />
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</form>
</div>
</html>