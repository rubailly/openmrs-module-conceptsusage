<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<openmrs:htmlInclude
	file="/moduleResources/conceptcleanup/dataTables/media/css/demo_page.css" />

<openmrs:htmlInclude
	file="/moduleResources/conceptcleanup/dataTables/media/css/demo_table.css" />

<openmrs:htmlInclude
	file="/moduleResources/conceptcleanup/dataTables/media/js/jquery.js" />
<openmrs:htmlInclude
	file="/moduleResources/conceptcleanup/dataTables/media/js/jquery.dataTables.js" />

<script>
	var currentTab = 0;

	function openTab(clickedTab) {
		var thisTab = $(".tabbed-box .tabs a").index(clickedTab);
		$(".tabbed-box .tabs li a").removeClass("active");
		$(".tabbed-box .tabs li a:eq(" + thisTab + ")").addClass("active");
		$(".tabbed-box .tabbed-content").hide();
		$(".tabbed-box .tabbed-content:eq(" + thisTab + ")").show();
		currentTab = thisTab;
	}
	$(document).ready(function() {
		$(".tabs li:eq(0) a").css("border-left", "none");

		$('#example1').dataTable();
		$('#example2').dataTable();
		$('#example3').dataTable();
		$('#example4').dataTable();

		$(".tabbed-box .tabs li a").click(function() {
			openTab($(this));
			return false;
		});

		$(".tabbed-box .tabs li a:eq(" + currentTab + ")").click()
	});
</script>
<style>
.tabbed-box {
	width: 100%;
	background: #fff url(tabbed-body-bg.jpg) repeat-x bottom;
	border: 1px solid #ddd;
}

.tabbed-box .tabs li {
	width: 20%;
	list-style: none;
	float: left;
}

.tabbed-box .tabs li a {
	display: block;
	width: 100%;
	padding: 5px 0;
	font-weight: bold;
	text-align: center;
	text-decoration: none;
	color: #888;
	background: #fff url(tabbed-tab-bg.jpg) repeat-x bottom;
	border-left: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
}

.tabbed-box .tabs li:first-child a {
	border-left: none;
}

.tabbed-box .tabs li a:hover {
	color: #333;
}

.tabbed-box .tabs li a:focus {
	outline: none;
}

.odd {
	background-color: #D3D6FF;
}

.even {
	background-color: #EAEBFF;
}

.tabbed-box .tabs li a.active {
	background: #fff;
	color: #333;
	border-bottom: 1px solid #fff;
}

.tabbed-content {
	font-family: "Times New Roman", Times, serif;
}
</style>
<br />

<body>
	<div class="tabbed-box">
		<ul class="tabs">
			<li><a href="#">All Concepts</a></li>
			<li><a href="#">Concepts in Forms</a></li>
			<li><a href="#">Concepts in database Tables</a></li>
			<li><a href="#">Unused Concepts</a></li>
		</ul>
		<div class="tabbed-content">
			<body id="dt_example">
				<div id="container" style="width: 80%">

					<div id="demo">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="example1" width="100%">
							<thead>
								<tr>
									<th>Id</th>
									<th>Name</th>
									<th>Class</th>
									<th>Retired</th>
									<th>Type</th>
									<th>UUID</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="concept" items="${allConcepts}"
									varStatus="count">
									<tr class="${count.index % 2 == 0 ? 'odd' : 'even'}">
										<td>${concept.conceptId}</td>
										<td>${concept.name}</td>
										<td>${concept.conceptClass.name}</td>
										<td>${concept.retired}</td>
										<td>${concept.datatype.name}</td>
										<td>${concept.uuid}</td>
									</tr>

								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<th>Id</th>
									<th>Name</th>
									<th>Class</th>
									<th>Retired</th>
									<th>Type</th>
									<th>UUID</th>
								</tr>
							</tfoot>
						</table>
					</div>
					<div class="spacer"></div>

				</div>
			</body>
		</div>
		<div class="tabbed-content">
			<body id="dt_example">
				<div id="container" style="width: 80%">

					<div id="demo">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="example2" width="100%">
							<thead>
								<tr>
									<th>Id</th>
									<th>Name</th>
									<th>Class</th>
									<th>Retired</th>
									<th>Type</th>
									<th>UUID</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="concept" items="${formConcepts}"
									varStatus="count">
									<tr class="${count.index % 2 == 0 ? 'odd' : 'even'}">
										<td>${concept.conceptId}</td>
										<td>${concept.name}</td>
										<td>${concept.conceptClass.name}</td>
										<td>${concept.retired}</td>
										<td>${concept.datatype.name}</td>
										<td>${concept.uuid}</td>
									</tr>

								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<th>Id</th>
									<th>Name</th>
									<th>Class</th>
									<th>Retired</th>
									<th>Type</th>
									<th>UUID</th>
								</tr>
							</tfoot>
						</table>
					</div>
					<div class="spacer"></div>

				</div>
			</body>
		</div>
		<div class="tabbed-content">
			<body id="dt_example">
				<div id="container" style="width: 80%">

					<div id="demo">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="example3" width="100%">
							<thead>
								<tr>
									<th>Id</th>
									<th>Name</th>
									<th>Class</th>
									<th>Retired</th>
									<th>Type</th>
									<th>UUID</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="concept" items="${tableConcepts}"
									varStatus="count">
									<tr class="${count.index % 2 == 0 ? 'odd' : 'even'}">
										<td>${concept.conceptId}</td>
										<td>${concept.name}</td>
										<td>${concept.conceptClass.name}</td>
										<td>${concept.retired}</td>
										<td>${concept.datatype.name}</td>
										<td>${concept.uuid}</td>
									</tr>

								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<th>Id</th>
									<th>Name</th>
									<th>Class</th>
									<th>Retired</th>
									<th>Type</th>
									<th>UUID</th>
								</tr>
							</tfoot>
						</table>
					</div>
					<div class="spacer"></div>

				</div>
			</body>
		</div>
		<div class="tabbed-content">
			<body id="dt_example">
				<div id="container" style="width: 80%">

					<div id="demo">
						<table cellpadding="0" cellspacing="0" border="0" class="display"
							id="example4" width="100%">
							<thead>
								<tr>
									<th>Id</th>
									<th>Name</th>
									<th>Class</th>
									<th>Retired</th>
									<th>Type</th>
									<th>UUID</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="concept" items="${unusedConcepts}"
									varStatus="count">
									<tr class="${count.index % 2 == 0 ? 'odd' : 'even'}">
										<td>${concept.conceptId}</td>
										<td>${concept.name}</td>
										<td>${concept.conceptClass.name}</td>
										<td>${concept.retired}</td>
										<td>${concept.datatype.name}</td>
										<td>${concept.uuid}</td>
									</tr>

								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<th>Id</th>
									<th>Name</th>
									<th>Class</th>
									<th>Retired</th>
									<th>Type</th>
									<th>UUID</th>
								</tr>
							</tfoot>
						</table>
					</div>
					<div class="spacer"></div>

				</div>
			</body>
		</div>
	</div>
</body>

<%@ include file="/WEB-INF/template/footer.jsp"%>
