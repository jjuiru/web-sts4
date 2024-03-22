<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
	<title>top page</title>
	<meta http-equiv="Content-Type" 
		content="text/html; charset=UTF-8" />
	<style>
	h1 { font-size:18pt; font-weight:bold; color:gray; }
	body { font-size:13pt; color:gray; margin:5px 25px; }
	tr { margin:5px; }
	th { padding:5px; color:white; background:darkgray; }
	td { padding:5px; color:black; background:#e0e0ff; }
	.err { color:red; }
	</style>
</head>
<body>
	<h1>Hello page</h1>
	<p></p>
	<table>
	<form method="post" action="input">
		<tr><td><label for="name">이름</label></td>
			<td><input type="text" name="name" value="${member.name}" /></td></tr>
		<tr><td><label for="age">나이</label></td>
			<td><input type="text" name="age" value="${member.age}" /></td></tr>
		<tr><td><label for="email">메일</label></td>
			<td><input type="text" name="email" value="${member.email}" /></td></tr>
		<tr><td><label for="meno">메모</label></td>
			<td><textarea name="meno" cols="20" rows="5">${member.meno}</textarea></td></tr>
		<tr><td></td><td><input type="submit" value="Input User" /></td></tr>
	</form>
	</table>
	<hr/>
	<table>
	<tr><th>ID</th><th>이름</th><th>연령</th><th>메일</th><th>메모</th><th>Action</th></tr>
		<c:forEach var="mal" items="${memberAllList}">
	<tr>
		<td><a href="updateForm?id=${mal.id}">${mal.id}</a></td>
		<td>${mal.name}</td>
		<td>${mal.age}</td>
		<td>${mal.email}</td>
		<td>${mal.meno}</td>
		<td>           <form method="post" action="delete">
                <input type="hidden" name="id" value="${mal.id}" />
                <input type="submit" value="Delete" />
            </form>
            <form method="get" action="updateForm">
                <input type="hidden" name="id" value="${mal.id}" />
                <input type="submit" value="Edit" />
            </form></td>
	</c:forEach>
	</tr>
	</table>
</body>
</html>