<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>팝업페이지</title>
</head>
<body>
    <h1>팝업페이지</h1>
    <ul>
        <li><a href="myPage">내 정보</a></li>
        <li><a href="myPage">나의 팝업</a></li>
    </ul>
    <!-- 나의 팝업 메뉴 클릭시 -->
    <div id="myPopUp">
        <h2>나의 팝업</h2>
        <!-- 여기에 좋아요를 눌렀던 팝업 리스트를 출력하는 코ㄴ드를 작성예정 -->
        <!-- 예시로 팝업 제목과 내용을 리스트 형태로 출력하는 코드를 작성 -->
        		<article>
			<table>
				<c:forEach var="myList" items="${myList}">
					<tr onclick="window.location.href='#?popCode=${myList.popCode}';"
						style="cursor: pointer;">
						<th><img src="#" alt="콘텐츠 이미지"></th>
						<td>${myList.popName}</td>
						<td>${myList.startDay}</td>
						<td>${myList.endDay}</td>
					</tr>
				</c:forEach>
			</table>

		</article>
        <ul>
        </ul>
    </div>
   
</body>
</html>