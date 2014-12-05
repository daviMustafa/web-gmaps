<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
					minlenght: 0
				},
				longitude: {
					required: true,
					number: true,
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
	function submitId(id, action){
		$("#id").val(id);
		$("#action").val(action);
		$("#form_location_list").submit();
	}
</script>
<div style="text-align: center">
	
	<h3>Locations Manager</h3>
	</br>
	<form id="form_location" action="locationController" method="post">
		<table style="margin: 0 auto">
			<tbody style="width: 100%">
				<tr width="100%">
					<td>
						<label>Name: </label>
					</td>
					<td>	
					<c:choose>
						<c:when test="${location.id ne null}">
							<input type="hidden" id="locationId" name="locationId" value="${location.id}"/>
							<input type="text" name="name" value="${location.name}"/>
						</c:when>
						<c:otherwise>
							<input type="text" name="name"/>
						</c:otherwise>
					</c:choose>
						
					</td></br>	
				</tr>
				
				<tr>
					<td>
						<label>Latitude: </label>
					</td>	
					<td>
					<c:choose>
						<c:when test="${location.id ne null}">
							<input type="text" name="latitude" value="${location.latitude}"/>
						</c:when>
						<c:otherwise>
							<input type="text" name="latitude"/>
						</c:otherwise>
					</c:choose>
					</td></br>
				</tr>
				
				<tr>
					<td>	
						<label>Longitude: </label>
					</td>
					<td>	
						<c:choose>
						<c:when test="${location.id ne null}">
							<input type="text" name="longitude" value="${location.longitude}"/>
						</c:when>
						<c:otherwise>
							<input type="text" name="longitude"/>
						</c:otherwise>
					</c:choose>
					</td></br>
				</tr>		
				
				<tr>
					<td>
						<label>Associate Tags: </label>
					</td>
					<td>	
						<%-- <c:forEach var="tag" items="${tags}">
							<input type="checkbox">${tag.name}</br>
						</c:forEach> --%>
						<button id="showBoxAddTag" value="">Add Tags</button>
					</td>	
				</tr>		
				<tr>
					<td>
						<span></span>
					</td>
					<td colspan="2" align="right">
					<c:choose>
						<c:when test="${location.id ne null}">
							<input type="submit" value="Update"/>
							<input type="hidden" name="action" value="update"/>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Save"/>
							<input type="hidden" name="action" value="create"/>
						</c:otherwise>
					</c:choose>
					</td>
				</tr>		
			</tbody>
		</table>
		<div id="locTag">
				
		</div>			
	</form>
		
	<hr width="85%" align="center" style="border-style: inset; border-width: 1px">
		<form id="form_location_list" action="locationController" method="post">
			<input type="hidden" value="" name="id" id="id"/>
			<input type="hidden" value="" name="action" id="action"/>
			
			<c:if test="${not empty locations }">
				<table id="tableList">
					<thead style="background-color: ">
						<td style="width: 40px; text-align: center">ID</td>
						<td style="width: 200px; text-align: center">Name</td>
						<td style="width: 100px; text-align: center">Latitude</td>
						<td style="width: 100px; text-align: center">Longitude</td>
						<td style="width: 200px; text-align: center">Tags</td>
						<td style="width: 100px; text-align: center">Created</td>
						<td style="width: 40px; text-align: center">Delete</td>
						<td style="width: 40px; text-align: center">Edit</td>
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
								
								<td style="text-align: center"><fmt:formatDate value="${location.created}" pattern="dd/MM/yyyy"/> </td>
								
								<td style="text-align: center">
									<button title="Delete" id="del" value="del" type="button" onclick="submitId(${location.id}, document.getElementById('del').value);">
										<img src="resources/img/delete.png" width="15px" height="15px"/>
									</button>
								</td>
								<td style="text-align: center">
									<button title="Edit" id="edit" value="edit" type="button" onclick="submitId(${location.id}, document.getElementById('edit').value);">
										<img src="resources/img/edit.png" width="15px" height="15px"
									</button>
								</td>
							</tr>
					</c:forEach>
				</table>
			</c:if>
		</form>
	
</div>

</html>