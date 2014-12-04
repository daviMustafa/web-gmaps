<html>

<script src="${contextPath}/resources/js/dialog_box_addTag.js"></script>
<script>
$(document).ready(function () {

	SetFloatEntry("latitude");
	SetFloatEntry("longitude");

});
</script>
<div style="text-align: center">
	
	<h3>Locations Manager</h3>
	</br>
	<form action="locationController" method="get">
	
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
		
		<c:if test="${locations ne null }">
		<table border="2" style="margin: 0px auto;margin-top: 50px">
			<tr>
				<td style="width: 200px; text-align: center">ID</td>
				<td style="width: 200px; text-align: center">Name</td>
				<td style="width: 200px; text-align: center">Latitude</td>
				<td style="width: 200px; text-align: center">Longitude</td>
				<td style="width: 200px; text-align: center">Delete</td>
				<td style="width: 200px; text-align: center">Edit</td>
			</tr>
				<c:forEach var="location" items="${locations}">
					<tr>
						<td style="text-align: center"><c:out value="${location.id}" /></td>
						<td style="text-align: center"><c:out value="${location.name}" /></td>
						<td style="text-align: center"><c:out value="${location.latitude}" /></td>
						<td style="text-align: center"><c:out value="${location.longitude}" /></td>
						
						<td style="text-align: center">
							<a href="/TagController?action=delete&id=${location.id}" title="Delete"></a>
						</td>
						<td style="text-align: center">
							<a href="/TagController?action=edit&id=${location.id}" title="Edit"></a>
						</td>
					</tr>
				</c:forEach>
		</table>
	</c:if>
	
	</form>
	
</div>

</html>