<html>

<script src="${contextPath}/resources/js/dialog_box_addTag.js"></script>


<div style="text-align: center">
	
	<h3>Create new Location</h3>
	</br>
	<form action="createLocation" method="get">
		
		<label>Name: </label>
		<input type="text" name="name"/>
		<label>Latitude: </label>
		<input type="text" name="latitude">
		<label>Longitude: </label>
		<input type="text" name="longitude">
		<label>Select tags: </label>
		<input type="checkbox">
			
			
		<input type="submit" value="Save"/>
	
	
	</form>
	
</div>

</html>