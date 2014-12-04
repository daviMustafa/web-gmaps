<html>
<body>
	<h3>List of Tags</h3>

	<form action="tagController" method="get">
		<table>
			<tr>
				<td>ID</td>
				<td>Nome</td>
				<td>Excluir</td>
			</tr>
			<c:forEach var="tag" items="${tags}">
				<tr>
					<td><c:out value="${tag.id}" /></td>
					<td><c:out value="${tag.name}" /></td>
					<td><a
						href="#{contextPath}/TagController?acao=delete&id=${tag.id}">Excluir</a>
					</td>
				</tr>
			</c:forEach>
		</table>

	</form>


</body>
</html>