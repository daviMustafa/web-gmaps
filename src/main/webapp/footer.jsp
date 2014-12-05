<!-- Inicio do Box Area-->
<body class="create_tag_body">
<div id="dialog-form-tag" title="Associação de Tags da Location">
  <p class="validateTips"></p>
 
  <form id="dialog-box-addTag">
    <fieldset class="create_tag_fieldset">
	<!-- Inicio do Box -->
	<div style="width: 100%; margin: 0 auto;">
		<div style="width: 90%; margin: 0 auto;">
			<!-- Lista de Tags -->
			<div style="float: left;">
				<p><b>Tags existentes: </b></p>
				<ul id="tags-sortable1" class="droptrue" style="width: 160px; height: 280px; overflow-y: scroll; overflow-x: scroll;">
				</ul>
			</div>

			<div style="float: left; padding-left: 20px; padding-right: 20px;">
				<p><b>&nbsp;</b></p>
				<ul id="sortable2" class="" style="width: 36px; height: 280px; background-color: #fff;">
					<!-- Forma Incorreta de por espaçamento vertical -->
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li style="width: 0px; margin: 0px auto; padding: 0px;">
						<button type="button" name="addAllTags" id="addAllTags">&gt;&gt;</button>
					</li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li style="width: 0px; margin: 0px auto; padding: 0px;">
						<button type="button" name="removeAllArea" id="removeAllTags">&lt;&lt;</button>
					</li>
					<li></li>
				</ul>
			</div>

			<!-- Lista de Tags Associadas a Location -->
			<div style="float: right;">
				<p><b>Tags associadas: </b></p>
				<ul id="tags-sortable3" class="droptrue" style="width: 160px; height: 280px; overflow-y: scroll; overflow-x: scroll;">
				  <!-- <li class="ui-state-highlight">Item 5</li> -->
				</ul>
			</div>

			<br style="clear:both">
		</div>
	</div>

      <!-- Allow form submission with keyboard without duplicating the dialog button -->
      <input type="submit" class="create_tag_label_input" tabindex="-1" style="position:absolute; top:-1000px">
    </fieldset>
  </form>
</div>
<body>
<!-- Fim do Box -->


<label>Copyright 2014 - Todos direitos reservados TrixMaps - @Trixmaps</label>


