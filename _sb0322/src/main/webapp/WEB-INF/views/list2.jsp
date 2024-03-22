<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<title>top page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style>
h1 {
	font-size: 18pt;
	font-weight: bold;
	color: gray;
}

body {
	font-size: 13pt;
	color: gray;
	margin: 5px 25px;
}

tr {
	margin: 5px;
}

th {
	padding: 5px;
	color: white;
	background: darkgray;
}

td {
	padding: 5px;
	color: black;
	background:#BDF3FF ;
}

.err {
	color: red;
}
</style>
</head>
<body>
	<h1>Hello page</h1>

	<table>
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>연령</th>
			<th>메일</th>
			<th>메모</th>
			<th>Action</th>
		</tr>
		<c:forEach var="mal" items="${memberAllList}">
			<tr>
				<td><a href="updateForm?id=${mal.id}">${mal.id}</a></td>
				<td>${mal.name}</td>
				<td>${mal.age}</td>
				<td>${mal.email}</td>
				<td>${mal.meno}</td>
				<td>
					<form method="post" action="delete" style="display: inline-block;">
						<input type="hidden" name="id" value="${mal.id}" /> <input
							type="submit" value="Delete" />
					</form>
					<form method="get" action="updateForm"
						style="display: inline-block;">
						<input type="hidden" name="id" value="${mal.id}" /> <input
							type="submit" value="Edit" />
					</form>
				</td>
		</c:forEach>
		</tr>
	</table>
</body>
</html>