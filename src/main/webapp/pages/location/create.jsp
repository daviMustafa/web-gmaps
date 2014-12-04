<html>

<script src="${contextPath}/resources/js/dialog_box_addTag.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#form_location").validate({
			onfocusout : function(element) {
				console.log($(element).next("label").remove());
				$(element).valid();
			},
			// Define as regras
			rules : {
				name : {
					required : true,
					minlenght : 3
				},
				latitude : {
					required: true,
					number: true,
					digits: true,
					minlenght: 0
				},
				longitude: {
					required: true,
					number: true,
					digits: true,
					minlenght: 0
				}

			},
			// Define as mensagens de erro para cada regra
			messages : {
				name : {
					required : "Name required.",
					minlength : "Name requires at least 3 characters."
				},
				latitude: {
					required: "Set latitude.",
					number: "Numbers only.",
					minlength: "At least one number"
				},
				latitude: {
					required: "Set longitude.",
					number: "Numbers only.",
					minlength: "At least one number"
				}
				
			}
		});

	});
	function submitId(id){
		$("#id").val(id);
		$("#form_location_list").submit();
	}
</script></script>
<div style="text-align: center">
	
	<h3>Locations Manager</h3>
	</br>
	<form id="form_location" action="locationController" method="post">
		
		<tr>
			<td>
				<label>Name: </label>
			</td>
			<td>	
				<input type="text" name="name"/>
			</td></br>	
		</tr>
		<tr>
			<td>
				<label>Latitude: </label>
			</td>	
			<td>
				<input type="text" name="latitude"></br>
			</td>
		</tr>	
			<label>Longitude: </label>
			<input type="text" name="longitude"></br>
			<label>Select tags: </label>
			<input type="checkbox"></br>
				
				
			<input type="submit" value="Save"/>
	</form>
	
		<form id="form_location_list" action="locationController" method="post">
			<input type="hidden" value="" name="id" id="id"/>
			
			<c:if test="${not empty locations }">
				<table border="2" style="margin: 0px auto;margin-top: 50px">
					<tr>
						<td style="width: 200px; text-align: center">ID</td>
						<td style="width: 200px; text-align: center">Name</td>
						<td style="width: 200px; text-align: center">Latitude</td>
						<td style="width: 200px; text-align: center">Longitude</td>
						<td style="width: 200px; text-align: center">Tags</td>
						<td style="width: 200px; text-align: center">Delete</td>
						<td style="width: 200px; text-align: center">Edit</td>
					</tr>
						<c:forEach var="location" items="${locations}">
							<tr>
								<td style="text-align: center"><c:out value="${location.id}" /></td>
								<td style="text-align: center"><c:out value="${location.name}" /></td>
								<td style="text-align: center"><c:out value="${location.latitude}" /></td>
								<td style="text-align: center"><c:out value="${location.longitude}" /></td>
								
								<c:forEach var="tagLocations" items="${location.tags}">
									<td style="text-align: center"><c:out value="${tagLocations.name}" /></td>
								</c:forEach>
								
								<td style="text-align: center">
									<button value="delete" name="action" type="button" onclick="submitId(${location.id})">Delete</button>
								</td>
								<td style="text-align: center">
									<button value="edit" type="button" name="action" onclick="submitId(${location.id})">Edit</button>
								</td>
							</tr>
						</c:forEach>
				</table>
			</c:if>
		</form>
	
</div>

</html>