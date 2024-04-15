<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>마이페이지</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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
    <h1>마이페이지</h1>
    <ul>
        <li><a  onclick="showUserInfo()">내 정보</a></li>
        <li><a  onclick="showMyPopups()">나의 팝업</a></li>
        <li><a  onclick="showMyBoard()">리뷰관리</a></li>
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
    </div>
    
    <div style="display: none;" id="myBoard">
    <h3>내가 쓴 리뷰</h3>
  <table>
        <c:forEach var="board" items="${popBoard}" >
            <tr>
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
    <td><button onclick="window.location.href='/delMyPopboard?num=${board.num}&popCode=${popCode}'">삭제</button></td>
</c:if>
            </tr>
        </c:forEach>
    </table>
    
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
        if (popDelValue === "off") { // popDel 값이 "on"인 경우
        	showMyBoard(); // showMyPopups() 함수 실행
        }
    };

    function showUserInfo() {
        document.getElementById("userInfo").style.display = "block";
        document.getElementById("myPopups").style.display = "none";
        document.getElementById("myBoard").style.display = "none";
    }

    function showMyPopups() {
        document.getElementById("userInfo").style.display = "none";
        document.getElementById("myPopups").style.display = "block";
        document.getElementById("myBoard").style.display = "none";
    }
    
    function showMyBoard() {
    	document.getElementById("userInfo").style.display = "none";
        document.getElementById("myPopups").style.display = "none";
        document.getElementById("myBoard").style.display = "block";
    }
	</script>
</body>
</html>