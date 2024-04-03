<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<article>
			<table>
				<c:forEach var="list" items="${artlist}">
					<tr onclick="window.location.href='/view?popCode=${list.popCode}';"
						style="cursor: pointer;">
						<th><img src="#" alt="콘텐츠 이미지"></th>
						<td>${list.popName}</td>
						<td>${list.startDay}</td>
						<td>${list.endDay}</td>
					</tr>
				</c:forEach>
			</table>

		</article>
</body>
</html>