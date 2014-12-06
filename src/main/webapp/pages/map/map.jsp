<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<style>
html, body, #map-canvas {
	height: 100%;
	margin: 0px;
	padding: 0px
}

#panel {
	position: absolute;
	top: 5px;
	left: 50%;
	margin-left: -180px;
	z-index: 5;
	background-color: #fff;
	padding: 5px;
	border: 1px solid #999;
}
</style>
	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
	<script type="text/javascript">
		function executarMapa(){
		    var map;
		    var mapOptions = {
		        zoom: 8,
		 	center: new google.maps.LatLng(document.getElementById('lat').value,document.getElementById('lng').value)
		    };
		    map = new google.maps.Map(document.getElementById('map-canvas'),
		    mapOptions);
		    var marker = new google.maps.Marker({
		      position: new google.maps.LatLng(document.getElementById('lat').value,document.getElementById('lng').value),
		      map: map,
		      title: document.getElementById("name").value
		     });
		}
	</script>
<form id="geomap">
	
	<div id="geoValues">
	
	</div>
	
	<div id="panel" style="margin-top: 80px">
		<label>Choose one Location: </label>
		<div id="comboLocations">  
    		<select id="locationId" name="locationSelected" style="width:263px;">  
        		<option value="0">Select one location</option>  
            	<c:forEach var="loc" items="${listLocations}">  
                	<option value="${loc.id}"> ${loc.name}</option>  
            	</c:forEach>  
    		</select>               
	</div>
		<button id="geocode" name="geocode">GeoCode</button>
	</div>
	<div id="map-canvas" style="height: 500px;margin-top: 120px"></div>
</form>

	
</html>