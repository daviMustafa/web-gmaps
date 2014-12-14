<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
		$("#idTag").val(id);
	}
</script>
<div style="text-align: center">
	
	<h3>Tags Manager</h3>
	</br>
	<form id="form_tag" action="tagController" method="post">
		<label>Name: </label> <input type="text" name="name" /></br>
		<label for="name" style="color: red"></label> 
		<input type="hidden" value="create" name="save"/>
		<input type="submit" value="Save" />
		<input type="reset" value="Reset" /> </br>
	</form>

		<input type="hidden" value="" name="id" id="idTag"/>
			
		<c:if test="${not empty tags }">
			<table  style="margin: 0px auto; margin-top: 50px" id="tablelist">
				<thead>
					<td style="width: 50px; text-align: center">ID</td>
					<td style="width: 200px; text-align: center">Name</td>
					<td style="width: 100px; text-align: center">Created</td>
					<td style="width: 40px; text-align: center">Delete</td>
				</thead>
				<div id="divResult">
					<c:forEach var="tag" items="${tags}">
						<tr id="${tag.id}">
							<td style="text-align: center"><c:out value="${tag.id}"/></td>
							<td style="text-align: center"><c:out value="${tag.name}" /></td>
							<td style="text-align: center"><fmt:formatDate value="${tag.created}" pattern="dd/MM/yyyy"/></td>
							<td style="text-align: center">
								<div>
									<button title="Delete" class="delTag" onclick="submitId(${tag.id})">
										<img src="resources/img/delete.png" width="15px" height="15px"/>
									</button>
								</div>
							</td>
						</tr>
					</c:forEach>
				</div>
			</table>
		</c:if>
</div>
</html>