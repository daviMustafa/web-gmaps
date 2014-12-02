<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css">
<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<title>Index Trixmaps_V2</title>
</head>
<body>

	<div id='cssmenu'>
		<ul>
			<li><a href='http://www.trixlog.com'><span>Home</span></a></li>
			<li class='active has-sub'><a href='#'><span>Location</span></a>
				<ul>
					<li><a href='htmlPages/location/create.html'><span>Create</span></a></li>
					<li class='last'><a href='htmlPages/location/list.html'><span>List</span></a></li>
				</ul>
			</li>	
			<li class='has-sub'><a href='#'><span>Tag</span></a>
				<ul>
					<li><a href='htmlPages/tag/create.html'><span>Create</span></a></li>
					<li class='last'><a href='htmlPages/tag/list.html'><span>List</span></a></li>
				</ul>
			</li>	
			<li class='has-sub'><a href='#'><span>GMaps</span></a>
				<ul>
					<li><a href='htmlPages/maps/maps.html'><span>Find Location</span></a></li>
				</ul>
			</li>
		</ul>
	</div>
	
	<div id="content">
		
	</div>

</body>
</html>