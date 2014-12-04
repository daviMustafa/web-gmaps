<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				longitude: {
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
</script>
<div style="text-align: center">
	
	<h3>Locations Manager</h3>
	</br>
	<table style="margin: 0 auto">
		<form id="form_location" action="locationController" method="post">
		
			<tbody style="width: 100%">
				<tr width="100%">
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
				
				<tr>
					<td>	
						<label>Longitude: </label>
					</td>
					<td>	
						<input type="text" name="longitude">
					</td></br>
				</tr>		
				
				<tr>
					<td>
						<label>Select tags: </label>
					</td>
					<td>	
						<c:forEach var="tag" items="${tags}">
							<input type="checkbox">${tag.name}</br>
						</c:forEach>
					</td>	
				</tr>		
				<tr>
					<td>
						<span></span>
					</td>
					<td colspan="2" align="right">
						<input type="submit" value="Save"/>
					</td>
				</tr>		
			</tbody>	
		</form>
	</table>		
		
	<hr width="85%" align="center" style="border-style: inset; border-width: 1px">
		<form id="form_location_list" action="locationController" method="post">
			<input type="hidden" value="" name="id" id="id"/>
			
			<c:if test="${not empty locations }">
				<table style="margin: 0px auto; margin-top: 50px">
					<thead style="background-color: ">
						<td style="width: 200px; text-align: center">ID</td>
						<td style="width: 200px; text-align: center">Name</td>
						<td style="width: 200px; text-align: center">Latitude</td>
						<td style="width: 200px; text-align: center">Longitude</td>
						<td style="width: 200px; text-align: center">Tags</td>
						<td style="width: 200px; text-align: center">Created</td>
						<td style="width: 200px; text-align: center">Delete</td>
						<td style="width: 200px; text-align: center">Edit</td>
					</thead>
					<c:forEach var="location" items="${locations}">
							<tr>
								<td style="text-align: center"><c:out value="${location.id}" /></td>
								<td style="text-align: center"><c:out value="${location.name}" /></td>
								<td style="text-align: center"><c:out value="${location.latitude}" /></td>
								<td style="text-align: center"><c:out value="${location.longitude}" /></td>
								
								<td style="text-align: center">
									<c:forEach var="tagLocations" items="${location.tags}">
										<div>	
											<c:out value="${tagLocations.name}" />
										</div>
									</c:forEach>
								</td>
								
								<td style="text-align: center"><c:out value="${location.created}" /></td>
								
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