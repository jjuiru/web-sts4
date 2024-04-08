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
        color:red; /* 클릭되었을 때 빨간색으로 변경 */
    }
</style>
</head>
<body>
<figure>
    <img src="<c:url value='${pop.rink}' />" alt="콘텐츠 이미지">
    <!-- 제목 -->
    <h1>${pop.popName}</h1>
        <p>${count}</p>
    <button class="heart-btn ${onOff == 'on' ? 'active' : ''}" onclick="toggleHeart(this, '${pop.popCode}')">
        <i class="far fa-heart"></i> <!-- 빈 하트 아이콘 -->
    </button>
    <!-- 장소 -->
    <p>장소: ${pop.place}</p>
    <!-- 기간 -->
    <p>기간: ${pop.startDay} - ${pop.endDay}</p>
    <!-- 컨텐츠 내용 -->
    <p>${pop.content}</p>
    <!-- 지도 API -->
    <div id="map"></div>
    <!-- 링크 -->
    <a href="${rink}">${rink}</a>
</figure>
<!-- Font Awesome 스크립트 CDN 링크 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
<script>
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
</script>
</body>
</html>