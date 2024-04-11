<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
    .heart-btn {
        cursor: pointer;
        color: black; /* 기본적으로 검정으로 설정 */
    }
    .heart-btn.active {
        color: red; /* 클릭되었을 때 빨간색으로 변경 */
    }
    .fas.fa-star {
        cursor: pointer;
          color: white; /* 비활성화된 별의 색상 */
}
.fas.fa-star.active {
    color: yellow; /* 활성화된 별의 색상 */
}
    </style>
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
<figure>
    <img src="<c:url value='${pop.rink}' />" alt="콘텐츠 이미지">
    <!-- 제목 -->
    <h1>${pop.popName}</h1>
        <p>${count}</p>
    <button class="heart-btn ${onOff == 'on' ? 'active' : ''}" onclick="toggleHeart(this, '${pop.popCode}')">
        <i class="far fa-heart"></i> <!-- 빈 하트 아이콘 -->
    </button>
    <p>장소: ${pop.place}</p>
    <p>기간: ${pop.startDay} - ${pop.endDay}</p>
    <p>${pop.content}</p>
    <div id="map"></div>
    <a href="${rink}">${rink}</a>
    
<a  onclick="showboard()" style="cursor: pointer;">review</a>
    <div style="display: none;" id="showboard">
  <table>
        <c:forEach var="board" items="${popBoard}" >
            <tr onclick="fillForm(this)" style="cursor: pointer;"> <!-- 댓글을 클릭했을 때 fillForm 함수 호출 -->
                <td>
                    <div id="stars-${board.num}">
                        <script>
                            var starCount = parseInt("${board.star}");
                            var starHtml = '';
                            for (var i = 0; i < starCount; i++) {
                                starHtml += '<i class="fas fa-star active"></i>';
                            }
                            document.write(starHtml);
                        </script>
                    </div>
                </td>
                <td>${board.userId}</td>
                <td>${board.title}</td>
                <td>${board.content}</td>
                <td>${board.regtime}</td>
                <td hidden>${board.num}</td>
                <td hidden>${board.popCode}</td>
            </tr>
        </c:forEach>
    </table>
        
<form id="boardForm" action="insertPopbard" method="post">
    <input hidden type="text" id="num" name="num" >
    <input hidden type="text" id="userId" name="userId" >
    <input hidden type="text" id="popCode" name="popCode" >
    <label>작성자 ${userId}</label>
<div >
 <!--  별점시스템 들어갈 공간 -->
</div>
    제목<input type="text" id="title" name="title" ><br>
    내용<input type="text" id="content" name="content" ><br>
    <button type="submit">등록</button>
    <button type="button">삭제</button>
</form>
</div>		
</figure>
<!-- Font Awesome 스크립트 CDN 링크 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
<script>
// 댓글을 클릭할 때 호출되는 함수
function fillForm(row) {
    // 클릭한 댓글의 각 셀에서 값을 가져옵니다.
    var clickedUserId = row.cells[1].innerText;
    var title = row.cells[2].innerText;
    var content = row.cells[3].innerText;
    var num = row.cells[5].innerText;
    var popCode = row.cells[6].innerText;

    // 폼에 가져온 값을 채워 넣습니다.
    document.getElementById("userId").value = clickedUserId;
    document.getElementById("title").value = title;
    document.getElementById("content").value = content;
    document.getElementById("num").value = num;
    document.getElementById("popCode").value = popCode;
}
window.onload = function() {
    var textValue = "${text}";

    if (textValue === 'on') {
        alert('로그인 후 이용해 주세요.');
        window.location.href = '/loginPage';
    }
};
    function toggleHeart(button, popCode) {
        button.classList.toggle("active"); // 버튼에 active 클래스를 toggle하여 버튼의 색상을 변경합니다.
        
        // 버튼의 상태에 따라 리다이렉트
        if (button.classList.contains("active")) {
            // 버튼이 활성화된 상태일 때 (하트가 클릭된 상태)
           window.location.href = "like?status=on&popCode=" + popCode; // popCode 값을 파라미터로 추가합니다.
        } else {
            // 버튼이 비활성화된 상태일 때 (하트가 클릭되지 않은 상태)
             window.location.href = "like?status=off&popCode=" + popCode; // popCode 값을 파라미터로 추가합니다.           
        }
    }
    function showboard() {
        var showboardElement = document.getElementById("showboard");
        if (showboardElement.style.display === "none") {
            showboardElement.style.display = "block";
        } else {
            showboardElement.style.display = "none";
        }
        
    }
</script>
</body>
</html>