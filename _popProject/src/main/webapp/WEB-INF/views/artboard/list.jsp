<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<article>
		<div>
			<h1>
				<a href="main">MOHAJIPOP!</a>
			</h1>
			<h3>서울 팝업 전시 모두 모여라</h3>
		</div>
	</article>
			<article> 
		<!-- 여기가 달력이랑 검색 있는 사이드 영역 -->
		<!-- 달력부분은 부트스트랩이나 여러가지 형태로 바꾸기 가능 -->
		<form action="selectDate" method="GET">
    <input type="date" id="findPop" name="date">
    <button type="submit">날짜로 검색</button>
</form>
<br>
<form action="keyword" method="GET">
    <input type="text" id="findName" name="keyword">
    <button type="submit">키워드 검색</button>
</form>

<button onclick="window.location.href='selectAllList';">전체 팝업 보기</button>
		 </article>
<article>
<div>${text}</div>
			<table>
				<c:forEach var="list" items="${list}">
					<tr onclick="window.location.href='/view?popCode=${list.popCode}';"
						style="cursor: pointer;">
						<th><img src="<c:url value='${list.rink}' />" alt="콘텐츠 이미지"></th>
						<td>${list.popName}</td>
						<td>${list.startDay}</td>
						<td>${list.endDay}</td>
					</tr>
				</c:forEach>
			</table>

		</article>
</body>
</html>