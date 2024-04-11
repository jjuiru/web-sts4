<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>응답 내용</title>
</head>
<body>
    <h2>응답 내용 : </h2>
<ul>
    <c:forEach var="data" items="${ans.dataList}" varStatus="sta">
        <li>${sta.index+1}번응답: ${data}</li>
    </c:forEach>
</ul>
    </form>
</body>
</html>
