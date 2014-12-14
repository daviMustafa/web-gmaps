<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="shortcut icon" href="resources/img/favicon.png" type="image/png" />

<link rel="stylesheet" href="${contextPath}/resources/css/styles.css"        type="text/css" />
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css"     type="text/css" />
<link rel="stylesheet" href="${contextPath}/resources/css/jquery-ui.min.css" type="text/css" />
<link rel="stylesheet" href="${contextPath}/resources/css/table.css" 		 type="text/css" />
	

<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script src="${contextPath}/resources/js/jquery-2.1.1.js" type="text/javascript"></script>
<script src="${contextPath}/resources/js/jquery-ui.js"    type="text/javascript"></script>
<script src="${contextPath}/resources/js/jquery.validate.min.js" type="text/javascript"></script>
	
<script src="${contextPath}/resources/js/menu.js"></script>

<title>Index Trixmaps_V2</title>
</head>
<body>
	<div class="container" style="height: 100px; width: 100%">
		<div id='cssmenu'>
			<ul>
				<li class='active'><a href='http://www.trixlog.com'><span>Home</span></a></li>
				<li class='has-sub'><a href='#'><span>Location</span></a>
					<ul>
						<li><a href='${contextPath}/locationController'><span>Manage Locations</span></a></li>
 						<!-- <li class='last'><a -->
						<%-- href='${contextPath}/index.jsp?page=/pages/location/list.jsp'><span>List</span></a></li> --%>
					</ul></li>
				<li class='has-sub'><a href='#'><span>Tag</span></a>
					<ul>
						<li><a href='${contextPath}/tagController'><span>Manage	Tags</span></a></li>
					</ul></li>
				<li class='has-sub'><a href='#'><span>GMaps</span></a>
					<ul>
						<li><a
							href='${contextPath}/googleMapsController'><span>Find
									Location</span></a></li>
					</ul></li>
			</ul>
		</div>

		<c:set scope="application" var="pages" value="${param.page}" />

		<div class="container" style="text-align: center; margin: 0px auto; height: 100%">

			<c:if test="${ not empty msg}">
				<div id="divMsg" class="ui-widget vanish">
					<div class="ui-state-highlight ui-corner-all"
						style="padding: 0 .7em;">
						<p>
							<span class="ui-icon ui-icon-info"></span> 
							<strong id="msgSucesso">Sucesso: </strong> ${msg}
							<script type="text/javascript">
								$('#divMsg').fadeOut(3000);
							</script>
						</p>
					</div>
				</div>
			</c:if>

			<c:if test="${ not empty errorMsg}">
				<div id="divErroMsg" class="ui-widget vanish">
					<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
						<p>
							<span class="ui-icon ui-icon-info"></span> 
							<strong id="msgErro">Erro: </strong> ${errorMsg}
							<script type="text/javascript">
								$('#divErroMsg').fadeOut(3000);
							</script>
						</p>
					</div>
				</div>
			</c:if>

			<c:choose>
				<c:when test="${not empty pages}">
					<jsp:include page="${pages}" flush="true" />
				</c:when>
				<c:otherwise>
					<jsp:include page="home.jsp" flush="true" />
				</c:otherwise>
			</c:choose>
		</div>

		<div style="text-align: center; margin-top: 700px">
			<c:import url="footer.jsp" />
		</div>
</body>
<script src="${contextPath}/resources/js/deleteTagAjax.js" type="text/javascript"></script>
<!-- Dialog Tag  -->	
<link rel="stylesheet" href="${contextPath}/resources/css/dialog_box_addTag.css" type="text/css" />	
<script src="${contextPath}/resources/js/dialog_box_addTag.js" type="text/javascript"></script>
<!-- Dialog Tag -->

<script src="${contextPath}/resources/js/geocodeAjax.js" type="text/javascript"></script>
</html>