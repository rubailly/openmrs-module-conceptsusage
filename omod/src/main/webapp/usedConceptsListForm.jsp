<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<style>
.odd {
	background-color: #D3D6FF;
}

.even {
	background-color: #EAEBFF;
}
</style>
<table>
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Class</th>
		<th>Retired</th>
		<th>Type</th>
		<th>UUID</th>
	</tr>
	<c:forEach var="concept" items="${usedConcepts}" varStatus="count">
		<tr class="${count.index % 2 == 0 ? 'odd' : 'even'}">
			<td>${concept.conceptId}</td>
			<td>${concept.name}</td>
			<td>${concept.conceptClass.name}</td>
			<td>${concept.retired}</td>
			<td>${concept.datatype.name}</td>
			<td>${concept.uuid}</td>
		</tr>

	</c:forEach>
</table>

<%@ include file="/WEB-INF/template/footer.jsp"%>