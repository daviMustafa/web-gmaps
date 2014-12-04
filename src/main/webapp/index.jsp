<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" href="${contextPath}/resources/css/styles.css"
	type="text/css" />
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css"
	type="text/css" />

<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script src="${contextPath}/resources/js/jquery-2.1.1.js" type="text/javascript"></script>
<script src="${contextPath}/resources/js/jquery-ui.js" type="text/javascript"></script>
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
						<li><a
							href='${contextPath}/index.jsp?page=/pages/location/create.jsp'><span>Manage Locations</span></a></li>
<!-- 						<li class='last'><a -->
<%-- 							href='${contextPath}/index.jsp?page=/pages/location/list.jsp'><span>List</span></a></li> --%>
					</ul>
				</li>
				<li class='has-sub'><a href='#'><span>Tag</span></a>
					<ul>
						<li><a
							href='${contextPath}/index.jsp?page=/pages/tag/create.jsp'><span>Manage Tags</span></a></li>
<!-- 						<li class='last'><a -->
<%-- 							href='${contextPath}/index.jsp?page=/pages/tag/list.jsp'><span>List</span></a></li> --%>
					</ul>
				</li>
				<li class='has-sub'><a href='#'><span>GMaps</span></a>
					<ul>
						<li><a
							href='${contextPath}/index.jsp?page=/pages/map/map.jsp'><span>Find Location</span></a></li>
					</ul></li>
			</ul>
		</div>

		<c:set scope="application" var="pages" value="${param.page}" />

		<div class="container" style="text-align: center; margin: 0px auto">
			<c:choose>
				<c:when test="${not empty pages}">
					<jsp:include page="${pages}" flush="true" />
				</c:when>
				<c:otherwise>
					<jsp:include page="home.jsp" flush="true" />
				</c:otherwise>
			</c:choose>
		</div>
		
		<div style="text-align: center;margin-top: 400px">
			<c:import url="footer.jsp"/>
		</div>
</body>
</html>