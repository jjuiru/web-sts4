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
	<form action="login" method="post" onsubmit="return validateForm()">
		<h1 class="h3 mb-3 fw-normal">로그인</h1>
		<div class="form-floating">
			<input style="margin-bottom: 10px; border-radius: 10px;" type="text"
				class="form-control" id="userId" name="userId" placeholder="id">
			<label for="userId">아이디</label>
			<label id="userIdError" style="color: red; display: none;">아이디를 입력해주세요.</label>
		</div>
		<div class="form-floating">
			<input style="border-radius: 10px;" type="text" class="form-control"
				id="pass" name="pass" placeholder="pass"> <label for="pass">패스워드</label>
			<label id="passError" style="color: red; display: none;">비밀번호를
				입력해주세요.</label> <label id="loginError" style="color: red; display: none;">아이디나
				비밀번호가 올바르지 않습니다.</label> <label id="errorLabel"
				style="color: red; display: none;">${error}</label>
		</div>

		<div class="form-check text-start my-3">
			<input class="form-check-input" type="checkbox" id="check"
				name="check" checked> <label class="form-check-label"
				for="check"> 아이디를 저장합니다. </label>
		</div>
		<div>
			<button style="margin-bottom: 10px; border-radius: 10px;"
				class="btn btn-sm btn-outline-secondary w-100 py-2" type="submit">login</button>
		</div>
	</form>
	<div>
		<button style="margin-bottom: 10px; border-radius: 10px;"
			class="btn btn-sm btn-outline-secondary w-100 py-2"
			onclick="location.href='signUpPage'">Sign Up</button>
	</div>

	<div>
		<button style="border-radius: 10px;"
			class="btn btn-sm btn-outline-secondary w-100 py-2"
			onclick="location.href='main'">main</button>
	</div>
	<p class="mt-5 mb-3 text-body-secondary">&copy; 2024–2027</p>

	<script>
		function validateForm() {
			var userId = document.getElementById("userId").value;
			var pass = document.getElementById("pass").value;

			if (userId.trim() === "") {
				document.getElementById("userIdError").style.display = "block";
				document.getElementById("userId").focus();
				return false;
			} else {
				document.getElementById("userIdError").style.display = "none";
			}

			if (pass.trim() === "") {
				document.getElementById("passError").style.display = "block";
				document.getElementById("pass").focus();
				return false;
			} else {
				document.getElementById("passError").style.display = "none";
			}
			return true;
		}
	</script>
	<script>
		/* 서버로부터 받은 에러 메시지를 확인하여 표시하는 함수 */
		function displayErrorMessage(error) {
			// 에러 메시지가 존재하는 경우
			if (error) {
				// 에러 메시지를 표시하는 요소를 찾아서 에러 메시지를 설정하고, display 속성을 변경하여 표시합니다.
				document.getElementById("errorLabel").innerText = error;
				document.getElementById("errorLabel").style.display = "block";
			}
		}

		// 서버로부터 받은 에러 메시지를 확인하여 표시합니다.
		displayErrorMessage('${error}');
	</script>
</body>
</html>