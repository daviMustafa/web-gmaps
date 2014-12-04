/**
 * 
 */

// função para carregamento das Tags
    function loadData(callbackhandler) {
        $.ajax({
        	url: "/trixmaps_v2/LocationActionAjax?method=loadTags",
        	type: "POST",
        	data: {
        		location : $("#location_add_tags").val() 
    		},
        	success : function(data){
        		var all = data['all'];
        		var usr = data['usr'];

        		removeDuplication(all, usr);

        		addItensTo(all, "#tag-sortable1", "ui-state-highlight");
        		addItensTo(usr, "#tag-sortable3", "ui-state-default");
			},
        	async: true
        });
    }