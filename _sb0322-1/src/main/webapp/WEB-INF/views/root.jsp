<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<head>
<title>top page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style>
h1 {
	font-size: 18pt;
	font-weight: bold;
	color: gray;
}

body {
	font-size: 13pt;
	color: gray;
	margin: 5px 25px;
}

tr {
	margin: 5px;
}

th {
	padding: 5px;
	color: white;
	background: darkgray;
}

td {
	padding: 5px;
	color: black;
	background: #e0e0ff;
}

.err {
	color: red;
}
</style>
</head>
<body>
	<h1>부서-사원</h1>
	<hr />
	<table>
		<tr>
			<th>부서번호</th>
			<th>부서이름</th>
			<th>부서위치</th>
		</tr>
		<c:forEach var="dept" items="${list}">
			<tr>
				<td><a href="selectEmp?deptno=${dept.deptno}">${dept.deptno}</a></td>
				<td>${dept.dname}</td>
				<td>${dept.loc}</td>
			</tr>
		</c:forEach>
	</table>
	<hr />
	
	<table>
		<tr>
			<th>사번</th>
			<th>이름</th>
			<th>직무</th>
			<th>상급자사번</th>
			<th>입사일</th>
			<th>급여</th>
			<th>보너스</th>
		</tr>
		<c:forEach var="emp" items="${list2}">
			<tr>
				<td><a href="updateForm1?empno=${emp.empno}">${emp.empno}</a></td>
				<td>${emp.ename}</td>
				<td>${emp.job}</td>
				<td>${emp.mgr}</td>
				<td>${emp.hiredate}</td>
				<td>${emp.sal}</td>
				<td>${emp.comm}</td>
			</tr>
		</c:forEach>
	</table>
	<hr />
<form method="post" action="input">
    <table>
        <tr>
            <td><label for="empno">사번</label></td>
            <td><input type="text" name="empno" value="${list3.empno}"  /></td>
        </tr>
        <tr>
            <td><label for="ename">이름</label></td>
            <td><input type="text" name="ename" value="${list3.ename}" /></td>
        </tr>
        <tr>
            <td><label for="job">직무</label></td>
            <td><input type="text" name="job" value="${list3.job}" /></td>
        </tr>
        <tr>
            <td><label for="mgr">상급자 사번</label></td>
            <td><input type="text" name="mgr" value="${list3.mgr}" /></td>
        </tr>
        <tr>
            <td><label for="sal">월급</label></td>
            <td><input type="text" name="sal" value="${list3.sal}" /></td>
        </tr>
        <tr>
            <td><label for="comm">보너스</label></td>
            <td><input type="text" name="comm" value="${list3.comm}" /></td>
        </tr>
        <tr>
            <td><label for="deptno">부서번호</label></td>
            <td>
                <!-- 선택된 값에 따라 텍스트 박스 표시/숨김 -->
                <select id="selectBox" name="deptno" onchange="toggleInput(this)">
                    <option value="${list3.deptno}" selected>${list3.deptno}</option>
                    <option value="10">10</option>
                    <option value="20">20</option>
                    <option value="30">30</option>
                    <option value="40">40</option>
                </select>
</td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="input" />
                <input type="button" value="update" onclick="submitForm('update')" />
                <input type="button" value="delete" onclick="submitForm('delete')" />
            </td>
        </tr>
    </table>
</form>

<script>
function toggleInput(selectElement) {
    var selectedValue = selectElement.value;
    var deptnoInput = document.getElementById('deptnoInput');
    var deptnoText = document.getElementById('deptnoText');
    
    // 선택된 값이 '10', '20', '30', '40'인 경우
    if (selectedValue === '10' || selectedValue === '20' || selectedValue === '30' || selectedValue === '40') {
        deptnoInput.style.display = 'block';
        // 선택된 값으로 텍스트 박스 업데이트
        deptnoInput.value = selectedValue;
        // 선택된 값으로 텍스트 업데이트
        deptnoText.innerText = getDeptName(selectedValue);
    } else {
        deptnoInput.style.display = 'none';
        deptnoInput.value = '';
        deptnoText.innerText = '';
    }
}

function submitForm(action) {
    document.getElementById("myForm").submit();
}
</script>
<script>
    function submitForm(action) {
        document.forms[0].action = action;
        document.forms[0].submit();
    }
</script>



</body>
</html>