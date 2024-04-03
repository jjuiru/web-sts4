<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div>
		<form id="registerForm" action="signUp" method="post">
			<label for="userid">아이디</label> <input type="text" id="userId"
				name="userId" placeholder="아이디 입력">
			<button type="button" onclick="checkUsername()">중복확인</button><br> 
			<label id="errorLabel" style="color: red; display: none;">${error}</label>
			<label id="checkLabel" style="color: blue; display: none;">${check}</label>			
			<label id="checkLabel2" style="color: red; display: none;">아이디를 입력해 주세요</label>
			
			<label for="password">비밀번호</label> 
			<input type="password"id="pass" name="pass" placeholder="비밀번호 입력"><br> 
			<label for="confirm-password">비밀번호 확인</label> 
			<input type="password" id="confirm-pass" placeholder="비밀번호 확인"><br> 
			<label id="passNotEquals" style="color: red; display: none;">비밀번호가 일치하지 않습니다.</label> 
			
			<label for="uname">이름</label> <input type="text" id="uname" name="uname" placeholder="이름입력"><br> 
			
			<label for="email">이메일</label> <input type="email" id="email" name="email" placeholder="이메일 입력"><br> 
			
			<label for="phone">핸드폰번호</label> <input type="tel" id="phoneNum" name="phoneNum" placeholder="핸드폰번호 입력"><br> 
			
			<label for="birthdate">생년월일</label>
			<input type="date" id="birth" name="birth"><br> 
			
			<label>성별</label> 
			<input type="radio" id="male" name="gender" value="male"> 
			<label for="male">남자</label> <input type="radio" id="female" name="gender" value="female"> 
			<label for="female">여자</label><br> 
			
			<input type="checkbox" id="event-agreement" name="uevent"> 
			<label for="event-agreement">이벤트 수신 동의</label><br> 
			
			<label id="idNotCheck" style="color: red; display: none;">아이디 중복확인을 해주세요</label>
			<button type="button" onclick="register()">회원가입</button>
		</form>
	</div>

	<script>
	//-------------아이디 체크시 실행되는 액션------------------
		function checkUsername() {
			//userId의 value 에서 공백을 제거한 값
			var userId = document.getElementById("userId").value.trim();
			if (userId === "") {
				document.getElementById("checkLabel2").style.display = "block";
				document.getElementById("errorLabel").style.display = "none";
				document.getElementById("checkLabel").style.display = "none";
				document.getElementById("userId").focus();
				return false;
			}
			var pass = document.getElementById("pass").value.trim();
			var email = document.getElementById("email").value.trim();
			var uname = document.getElementById("uname").value.trim();
			var phone = document.getElementById("phoneNum").value.trim();
			var birth = document.getElementById("birth").value.trim();

			var queryString = "?userId=" + encodeURIComponent(userId)
					+ "&pass=" + encodeURIComponent(pass) 
					+ "&email="+ encodeURIComponent(email) 
					+ "&phoneNum=" + encodeURIComponent(phoneNum) 
					+ "&uname=" + encodeURIComponent(uname)
					+ "&birth="+ encodeURIComponent(birth);

			window.location.href = "userIdCheck" + queryString;
		}

		//---------회원가입 버튼을 눌렀을 경우 실행되는 코드-----------
		function register() {
			//패스워드가 같은지 확인후 같을 때만 회원가입 실행
			var password = document.getElementById("pass").value.trim();
			var confirmPassword = document.getElementById("confirm-pass").value.trim();
			if (password !== confirmPassword) {
				document.getElementById("passNotEquals").style.display = "block";
				return;
			} else {
				document.getElementById("passNotEquals").style.display = "none";
			}
			//아이디 체크를 하지 않았을 경우 리턴
			if (document.getElementById("checkLabel").style.display === "block") {
				document.getElementById("registerForm").submit(); // 폼 제출
			} else { // 위의 조건 모두 만족할 경우 submit
				document.getElementById("idNotCheck").style.display = "block";
				return;
			}
		}
		//----------------오류 표시--------------------------
		// 페이지 실행시 아이디 중복일 경우 에러코드를 받아온다면 아래와 같이 오류표시
		function displayErrorMessage(error) {
			if (error) {
				document.getElementById("errorLabel").innerText = error;
				document.getElementById("errorLabel").style.display = "block";
			}
		}
		displayErrorMessage('${error}');
		
	   //페이지 실행시 아이디가 중복이 아닐 경우 아래와 같이 체크 값을 받아 표시
		function displayCheckMessage(check) {
			if (check) {
				document.getElementById("checkLabel").innerText = check;
				document.getElementById("checkLabel").style.display = "block";
			}
		}
		displayCheckMessage('${check}');
		
		//----------------페이지 새로고침시 이전의 상태정보를 받아온다--------------------------
	  //페이지 실행시 해당 값들이 있다면 해당하는 id 의 input 칸에 받아온 value를 넣는다.
		window.onload = function() {
			var userIdValue = "${userId}";
			var passValue = "${pass}";
			var emailValue = "${email}";
			var phoneValue = "${phoneNum}";
			var birthValue = "${birth}";
			var unameValue = "${uname}";

			if (userIdValue) {
				document.getElementById("userId").value = userIdValue;
			}
			if (passValue) {
				document.getElementById("pass").value = passValue;
			}
			if (emailValue) {
				document.getElementById("email").value = emailValue;
			}
			if (phoneValue) {
				document.getElementById("phoneNum").value = phoneNumValue;
			}
			if (birthValue) {
				document.getElementById("birth").value = birthValue;
			}
			if (unameValue) {
				document.getElementById("uname").value = unameValue;
			}
		};
	</script>


</body>
</html>