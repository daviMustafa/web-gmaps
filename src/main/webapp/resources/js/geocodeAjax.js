$(function(){
	
	$('#geocode').button().on('click', function(source){
		loadMap(function(data){
			console.log(data);
		})
		source.preventDefault();
	});
	
	function loadMap(callbackhandler){
		$.ajax({
			url : "/trixmaps_v2/googleMapsController?method=loadMap",
			type : "GET",
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
				
				$('#geoValues').empty();
				
				inputName   = $('<input>', {id:'name',   value: name, name:'name', type:'hidden'});
				inputLat    = $('<input>', {id:'lat',    value: lat, name:'lat', type:'hidden'});
				inputLong   = $('<input>', {id:'lng',     value: lng, name:'lng', type:'hidden'});
				inputLatLng = $('<input>', {id:'latlng', value: latlng, name:'latlng', type:'hidden'});
				
				inputName.appendTo( "#geoValues" );
				inputLat.appendTo( "#geoValues" );
				inputLong.appendTo( "#geoValues" );
				executarMapa();
				
			},
			async: true
		});
	}
	
});