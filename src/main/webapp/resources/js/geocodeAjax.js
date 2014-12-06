$(function(){
	
	$('#geocode').button().on('click', function(source){
		loadMap(function(data){
			console.log(data);
		})
	});
	
	function loadMap(callbackhandler){
		$.ajax({
			url : "/trixmaps_v2/googleMapsController?method=loadMap",
			type : "POST",
			data: {locationId : $("#locationId").val()},
			success : function(data){
				var lng = data['longitude'];
				var lat = data['latitude'];
				var name = data['name'];
				var latlng = data['latlng'];
				var inputName = "";
				var inputLat = "";
				var inputLong ="";
				var inputLatlng = "";
				
				inputName   = $('<input>', {value: name, name:'name', type:'hidden'});
				inputLat    = $('<input>', {value: lat, name:'lat', type:'hidden'});
				inputLong   = $('<input>', {value: lng, name:'lng', type:'hidden'});
				inputLatLng = $('<input>', {value: latlng, name:'latlng', type:'hidden'});
				
				inputName.appendTo( "#geoValues" );
				inputLat.appendTo( "#geoValues" );
				inputLong.appendTo( "#geoValues" );
				console.log(inputName, inputLat, inputLong);
				
			},
			async: true
		});
	}
	
});