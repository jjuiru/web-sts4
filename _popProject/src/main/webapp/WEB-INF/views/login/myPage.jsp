<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
</head>
<body>
    <h1>마이페이지</h1>
    <ul>
        <li><a  onclick="showUserInfo()">내 정보</a></li>
        <li><a  onclick="showMyPopups()">나의 팝업</a></li>
         <li><a  href="date">나의 팝업</a></li>
    </ul>

    <!-- 내 정보 -->
    <div id="userInfo">
        <h2>내 정보</h2>
	<div>
		<form id="registerForm" action="userUpdate" method="post">
			<label for="userid">아이디</label><input type="text" id="userId" name="userId" readonly><br>
			<label for="password">비밀번호</label> 
			<input type="password"id="pass" name="pass" placeholder="비밀번호 입력"><br> 
			<label for="confirm-password">비밀번호 확인</label> 
			<input type="password" id="confirm-pass" placeholder="비밀번호 확인"><br> 
			<label id="passNotEquals" style="color: red; display: none;">비밀번호가 일치하지 않습니다.</label> 
			
			<label for="uname">이름</label> <input type="text" id="uname" name="uname" placeholder="이름입력"><br> 
			
			<label for="email">이메일</label> <input type="email" id="email" name="email" placeholder="이메일 입력"><br> 
			
			<label for="phoneNum">핸드폰번호</label> <input type="tel" id="phoneNum" name="phoneNum" placeholder="핸드폰번호 입력"><br> 
			
			<label for="birthdate">생년월일</label>
			<input type="text" id="birth" name="birth"><br> 
			
			<input type="checkbox" id="uevent" name="uevent"> 
			<label for="uevent">이벤트 수신 동의</label><br> 
			
			<label id="idNotCheck" style="color: red; display: none;">아이디 중복확인을 해주세요</label>
			<button type="button" onclick="register()">수정</button>
		</form>
	</div>
    </div>

    <!-- 나의 팝업 메뉴 클릭시 -->
    <div id="myPopups">
        <h2>나의 팝업</h2>
        <!-- 여기에 좋아요를 눌렀던 팝업 리스트를 출력하는 코드를 작성예정 -->
        <!-- 예시로 팝업 제목과 내용을 리스트 형태로 출력하는 코드를 작성 -->
        		<article>
			<table>
				<c:forEach var="myList" items="${myList}">
					<tr onclick="window.location.href='/view?popCode=${myList.popCode}';"
						style="cursor: pointer;">
						<th><img src="<c:url value='${myList.rink}' />" alt="콘텐츠 이미지"></th>
						<td>${myList.popName}</td>
						<td>${myList.startDay}</td>
						<td>${myList.endDay}</td>
						<td>
					<form action="likeDelete" method="get">
                    <input type="hidden" name="popCode" value="${myList.popCode}">
                    <input type="hidden" name="userId" value="${userId}">
                    <input type="submit" value="삭제">
                </form>
						</td>
					</tr>					
				</c:forEach>
			</table>

		</article>
        <ul>
        </ul>
    </div>
    <script>
		//---------회원가입 버튼을 눌렀을 경우 실행되는 코드-----------
		function register() {
			//패스워드가 같은지 확인후 같을 때만 업데이트 실행
			var password = document.getElementById("pass").value.trim();
			var confirmPassword = document.getElementById("confirm-pass").value.trim();
			if (password !== confirmPassword) {
				document.getElementById("passNotEquals").style.display = "block";
				return;
			} else {
				document.getElementById("passNotEquals").style.display = "none";
			}
			if (confirm("정말로 수정하시겠습니까?")) {
	            document.getElementById("registerForm").submit(); // 폼 제출
	        }
			}
		
		//----------------페이지 새로고침시 이전의 상태정보를 받아온다--------------------------
	  //페이지 실행시 해당 값들이 있다면 해당하는 id 의 input 칸에 받아온 value를 넣는다.
		    window.onload = function() {
		    showUserInfo();
			
		var userIdValue = "${user.userId}";
        var passValue = "${user.pass}";
        var emailValue = "${user.email}";
        var unameValue = "${user.uname}";
        var phoneValue = "${user.phoneNum}";
        var birthValue = "${user.birth}";
        var ueventValue = "${user.uevent}";

        if (userIdValue) {
            document.getElementById("userId").value = userIdValue;
        }
        if (ueventValue) {
            document.getElementById("uevent").checked = true;
        }
        if (unameValue) {
            document.getElementById("uname").value = unameValue;
        }
        if (passValue) {
            document.getElementById("pass").value = passValue;
        }
        if (emailValue) {
            document.getElementById("email").value = emailValue;
        }
        if (phoneValue) {
            document.getElementById("phoneNum").value = phoneValue;
        }
        if (birthValue) {
            document.getElementById("birth").value = birthValue;
        }
        var popDelValue = "${popDel}"; // popDel 값 받아오기
        if (popDelValue === "on") { // popDel 값이 "on"인 경우
            showMyPopups(); // showMyPopups() 함수 실행
        }
    };

    function showUserInfo() {
        document.getElementById("userInfo").style.display = "block";
        document.getElementById("myPopups").style.display = "none";
    }

    function showMyPopups() {
        document.getElementById("userInfo").style.display = "none";
        document.getElementById("myPopups").style.display = "block";
    }
	</script>
</body>
</html>