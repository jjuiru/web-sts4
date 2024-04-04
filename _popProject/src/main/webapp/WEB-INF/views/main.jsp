<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MOHAJIPOP</title>
</head>
<body>
	<haeder>
	<article>
		<div>
			<h1>
				<a href="main">MOHAJIPOP!</a>
			</h1>
			<h3>서울 팝업 전시 모두 모여라</h3>
		</div>
	</article>
	<div>
	<c:choose>
	<c:when test="${loginC}">
		<a>${userId}님.로그인</a> 
		 <a href="myPage">마이페이지.(login)</a> 
		 <a href="logout">로그아웃.(login)</a>
		<h6>=(메뉴)</h6>
		</c:when>
		 <c:otherwise>
        <a href="loginPage">로그인</a>
        <a href="signUpPage">회원가입</a>        
        <h6>=(메뉴)</h6>
    </c:otherwise>
</c:choose>
	</div>
	</haeder>
	<!-- 여기서부터 내용, 슬라이스 이미지 들어가는 부분 -->
	<figure>
		<article>
			<a onclick="window.location.href='/view?popCode=a01';">(여기에 화살표로
				넘기는 이미지 들어갈거)</a>
		</article>

		<!-- 여기가 스크롤 내리면 팝업리스트 나오는곳 -->
		<article>
			<table>
				<c:forEach var="list" items="${artlist}">
					<tr onclick="window.location.href='/view?popCode=${list.popCode}';"
						style="cursor: pointer;">
						<th><img src="<c:url value='${list.rink}' />" alt="이미지 설명"></th>
						<td>${list.popName}</td>
						<td>${list.startDay}</td>
						<td>${list.endDay}</td>
					</tr>
				</c:forEach>
			</table>

		</article>
		<article> 
		<!-- 여기가 달력이랑 검색 있는 사이드 영역 -->
		<!-- 달력부분은 부트스트랩이나 여러가지 형태로 바꾸기 가능 -->
		<input type="date" id="findPop" name="findPop">
		<button type="submit" onclick="window.location.href='artlist'">날짜로검색</button><br>
		 <input type="text" id="findName" name="findName">
		 <button type="submit" onclick="window.location.href='artlist'">키워드검색</button>
		 </article>
	</figure>
	
	<!--  여기는 기업정보 나오는 부분 -->
	<footer>
		<div class="inner">
			<div class="upper">
				<h1>DCODELAB</h1>
				<ul>
					<li><a href="#">Plicy</a></li>
					<li><a href="#">Terms</a></li>
					<li><a href="#">Family Site</a></li>
					<li><a href="#">Sitemap</a></li>
				</ul>
			</div>

			<div class="lower">
				<address>
					Lorem ipsum dolor sit amet consectetur adipisicing elit. Molestias,
					voluptatem? <br> TEL: 010-7760-3956
				</address>
				<p>2020 DCODELAB uuuuummmmm</p>
			</div>
		</div>
		<!-- 기업정보 -->
	</footer>
	<script >
	window.onload = function() {
	    var signUp = "${signUp}";

	    if (signUp === "ok") {
	        // 회원가입 성공 메시지를 띄웁니다.
	        alert("회원가입이 완료되었습니다. 로그인 해주세요.");
	    }
	};
	</script>
</body>
</html>