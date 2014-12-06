$(function() {
	var 
      dialog = undefined;
	
    $( "#showBoxAddTag" ).button().on( "click", function(source) {
    	loadData(function (data) {
        	console.log(data);
        });
    	source.preventDefault();
    	dialog.dialog( "open" );
    });
    
    /*Configuração da Caixa de Diálogo (Dialog)*/
    dialog = $( "#dialog-form-tag" ).dialog({
      resizable: false,
      autoOpen: false,
      height: 480,
      width: 550,
      modal: true,
      buttons: {
      "Associar Tag": addLocationTags,
      "Cancelar": function() {
    	  $( "#tags-sortable1" ).append( $( "#tags-sortable3 li" ));
    	  	dialog.dialog( "close" );
        }
      },
      close: function() {
    	$( "#tags-sortable1" ).append( $( "#tags-sortable3 li" ));
    		dialog.dialog( "close" );
      }
     });
    
    // Função para carregamento das Tags
    function loadData(callbackhandler) {
        $.ajax({
        	url: "/trixmaps_v2/locationActionAjax?method=loadTags",
        	type: "POST",
        	data: {locationId : $("#locationId").val()},
        	success : function(data){
        		var allTags = data['allTags'];
        		var locationTags = data['locationTags'];

        		if(locationTags != undefined){
        			removeDuplication(allTags, locationTags);
        		}

        		addItensTo(allTags, "#tags-sortable1", "ui-state-highlight");
        		addItensTo(locationTags, "#tags-sortable3", "ui-state-default");
			
        	},
        	async: true
        });
    }
    
    
    function addLocationTags() {
      /*Chamada e tratamento da resposta do envio do POST ao servidor*/
    	var listTags = getSelectedTags();
    	var inputs = "";
    	
    	$('#locTag').empty();
    	
    	for(var i = 0; i < listTags.length; i++){
    		inputs = $('<input>', {value: listTags[i].id, name:'tagsSelecionadas', type: 'hidden'});
    		inputs.appendTo("#locTag");
    	}
    	$( "#dialog-form-tag" ).dialog( "close" );
    }
    
    /*get selected tags and put it all on an array*/
    function getSelectedTags() {
      var tags = [];
      var a = undefined;

      $( "#tags-sortable3 li" ).each(function( index ) {
        var id = $( this ).val();
        var name = $( this ).text();
        a = {
          'id':id,
          'name':name
        };
        tags.push(a);
      });

      return tags;
    }

    function removeDuplication(allArray, usrArray) {
	  var allTags = allArray;
  	  var myTags = usrArray;
  	  var id = undefined;
  	  
  	  for (var i=0; i<allTags.length; i++) {
  		  var removed = false;
  		  id = allTags[i]['id'];
  		  for (var j=0; j<myTags.length && !removed; j++) {
  			  if (id == myTags[j]['id']) {
  				  delete allTags[i];
  				  removed = true;
  			  }
  		  }
  	  }
  	  
    }
    
    function addItensTo(arrayItens, UIComponent, UIClassState) {
		var items = arrayItens;
		var strItens = '';
		if(items.length>0) {
			strItens = '';
			for(var i=0;i<items.length;i++) {
				if ( items[i] != undefined ) {
					var id = items[i]['id'];
					var name = items[i]['name'];
					strItens += '<li class="'+ UIClassState +'" value="' + id + '">' + name + '</li>';
				}
			 }
		}
		
		$(UIComponent).html(strItens);
    }
    
  $("#addAllTags").click(function() {
    $( "#tags-sortable3" ).append( $( "#tags-sortable1 li" ));
  });

  $("#removeAllTags").click(function() {
    $( "#tags-sortable1" ).append( $( "#tags-sortable3 li" ));
  });

  $( "ul.droptrue" ).sortable({
	  connectWith: "ul"
  });
 
  $( "ul.dropfalse" ).sortable({
	connectWith: "ul",
    dropOnEmpty: false
  });
 
  $( "#tags-sortable1, #sortable2, #tags-sortable3" ).disableSelection();
  
});