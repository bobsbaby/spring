<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="${cp }/js/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function(){
		//pathBtn�� Ŭ�� �ɶ� �̺�Ʈ �ڵ鷯
		$("#pathBtn").on("click", function(){
			var subpath = $("input[name=path]:checked").val();
			$("#frm").attr("action", "${cp}/mvc/path/" + subpath );
			$("#frm").submit();
		})
	})
</script>
</head>
<body>
	<h2>mvc/view.jsp</h2>
	<h3>requestParam</h3>
	<form action = "${cp }/mvc/requestParam" method ="post">
	 userId : <input type = "text" name = "userId" value = "sally"/><br>
	 <input type = "submit" value = "����" />
	</form>
	
	<h3>pathvariavle</h3>
	<form id = "frm">
		brown <input type ="radio" name = "path" value = "brown" checked="checked"> <br>
		sally <input type ="radio" name = "path" value = "sally"> <br>
		<input type = "button" id = "pathBtn" value = "����"/>
	</form>
	
</body>
</html>