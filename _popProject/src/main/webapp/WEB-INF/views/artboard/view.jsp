<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=2ko63nwjlc&submodules=geocoder"></script>
<style>
    .heart-btn {
        cursor: pointer;
        color: black; /* 기본적으로 검정으로 설정 */
    }
    .heart-btn.active {
        color: red; /* 클릭되었을 때 빨간색으로 변경 */
    }
    </style>
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
	

<figure>

    <img src="<c:url value='${pop.rink}' />" alt="콘텐츠 이미지">
    <!-- 제목 -->
    <h1>${pop.popName}</h1>
        <p>${count}</p>
    <button class="heart-btn ${onOff == 'on' ? 'active' : ''}" onclick="toggleHeart(this, '${pop.popCode}')">
        <i class="far fa-heart"></i> <!-- 빈 하트 아이콘 -->
    </button> 
    <a onclick="scrollToBoardForm();" style="cursor: pointer;">review</a>
    <p>장소: ${pop.place}</p>
    <p>기간: ${pop.startDay} - ${pop.endDay}</p>
    <p>${pop.content}</p>
    <a href="${pop.weblink}">${pop.weblink}</a><br>
    

        <!-- 지도api -->
<div id="map" style="width:40%;height:400px;"></div>


    <div id="showboard">
  <table>
                 <lable>평균 ${val} 점</lable>
        <c:forEach var="board" items="${popBoard}" >
            <tr style="cursor: pointer;"> <!-- 댓글을 클릭했을 때 fillForm 함수 호출 -->
                <td>
                    <div id="stars-${board.num}" style="color: yellow">
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
                <c:if test="${sessionScope.userId eq board.userId}">
    <td><button onclick="window.location.href='/deletePopboard?num=${board.num}&popCode=${popCode}&page=${currentPage}'">삭제</button></td>
    <td><button >수정</button></td>
</c:if>
            </tr>
</c:forEach>
</table>    

<c:set var="currentPage" value="${currentPage}" /> <!-- 현재 페이지 번호를 가져와 변수에 저장합니다. -->

<c:if test="${totalPages > 0}">
    <c:if test="${currentPage > 0}">
        <a href="view" onclick="getPage(${currentPage - 1})">이전</a>
    </c:if>

    <c:forEach var="i" begin="0" end="${totalPages - 1}">
        <a href="/view?page=${i+1}&popCode=${popCode}">${i + 1}</a>
    </c:forEach>

    <c:if test="${currentPage < totalPages - 1}">
        <a href="view" onclick="getPage(${currentPage + 1})">다음</a>
    </c:if>
</c:if>
        
<form id="boardForm" action="insertPopboard?page=${currentPage}" method="post" onsubmit="return checkUserId()">
    <input hidden type="text" id="num" name="num" >
    <input hidden type="text" id="userId" name="userId" value="${userId}">
    <input hidden type="text" id="popCode" name="popCode" value="${popCode}">
    <label>작성자 ${userId}</label>
    <div id="stars">
        <input type="radio" id="star1" name="star" value="1">
        <label for="star1">1</label>
        <input type="radio" id="star2" name="star" value="2">
        <label for="star2">2</label>
        <input type="radio" id="star3" name="star" value="3">
        <label for="star3">3</label>
        <input type="radio" id="star4" name="star" value="4">
        <label for="star4">4</label>
        <input type="radio" id="star5" name="star" value="5">
        <label for="star5">5</label>
    </div>
    
    <input type="hidden" id="selectedStar" name="selectedStar">
    <input type="hidden" id="star" name="star">
    제목<input type="text" id="title" name="title" ><br>
    내용<input type="text" id="content" name="content" ><br>
    <button type="submit">등록</button>
</form>
</div>		
</figure>
<!-- Font Awesome 스크립트 CDN 링크 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
<script>
        var address = "${pop.place}";
        
        // 입력받은 주소로 좌표 변환 요청
        naver.maps.Service.geocode({ address: address }, function(status, response) {
            if (status === naver.maps.Service.Status.OK) {
                var coords = response.result.items[0].point;
                var mapOptions = {
                    center: new naver.maps.LatLng(coords.y, coords.x),
                    zoom: 17
                };

                // 지도를 생성하고 표시합니다.
                var map = new naver.maps.Map('map', mapOptions);
                
                // 지도에 마커를 추가합니다.
                var marker = new naver.maps.Marker({
                    position: new naver.maps.LatLng(coords.y, coords.x),
                    map: map
                });
            } else {
                alert('주소를 찾을 수 없습니다.');
            }
        });
    </script>
<script>
    const stars = document.querySelectorAll('input[name="star"]');

    stars.forEach((star) => {
        star.addEventListener('click', function() {
            document.getElementById('selectedStar').value = this.value;
        });
    });
</script>
<script>
//댓글등록시 실행되는 함수
function checkUserId() {
    var userId = document.getElementById('userId').value;
    if (userId === null || userId === '') {
        alert('로그인 후 이용해주세요.');
        return false; // 서브밋을 막습니다.
    }
    return true; // 폼을 제출합니다.
}
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
    var showboard = "${save}"
    var showboardElement = document.getElementById("showboard");
    if (textValue === 'on') {
        alert('로그인 후 이용해 주세요.');
        window.location.href = '/loginPage';
    }
    
    if (showboard === 'on') {
        showboardElement.style.display = "block"; // showboard 요소를 화면에 표시합니다.
        showboardElement.scrollIntoView(); // showboard 요소로 스크롤 이동
    }
};

function scrollToBoardForm() {
    var showboardElement = document.getElementById("showboard");
    if (showboardElement) {
        showboardElement.style.display = "block"; // showboard 요소를 화면에 표시합니다.
        showboardElement.scrollIntoView({ behavior: 'smooth' }); // showboard 요소로 스크롤 이동
    }
}
    function toggleHeart(button, popCode) {
    	var page ="${currentPage}";
        button.classList.toggle("active"); // 버튼에 active 클래스를 toggle하여 버튼의 색상을 변경합니다.
        
        // 버튼의 상태에 따라 리다이렉트
        if (button.classList.contains("active")) {
            // 버튼이 활성화된 상태일 때 (하트가 클릭된 상태)
           window.location.href = "like?status=on&popCode=" + popCode +"&page=" + page; // popCode 값을 파라미터로 추가합니다.
        } else {
            // 버튼이 비활성화된 상태일 때 (하트가 클릭되지 않은 상태)
             window.location.href = "like?status=off&popCode="  + popCode +"&page=" + page; // popCode 값을 파라미터로 추가합니다.           
        }
    }
</script>
</body>
</html>