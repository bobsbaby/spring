<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>hello.jsp</h2>
	<%-- model ��ü�� �߰��� �Ӽ� �ΰ��� nowDt, msg --%>
	
	<%=request.getAttribute("nowDt") %> <br>
	<%=request.getAttribute("msg") %> <br>
	${nowDt }<br>
	${msg }<br>
	
</body>
</html>