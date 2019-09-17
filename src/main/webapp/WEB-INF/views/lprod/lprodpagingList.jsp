<%@page import="kr.or.ddit.lprod.model.LprodVo"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Jsp-basicLib</title>


<%@ include file="/WEB-INF/views/commonJsp/basicLib.jsp"%>
<script>
	//문서 로딩이 완료된 후 작업 진행 
	$(document).ready(function() {
		//사용자 정보 클릭시 이벤트 핸들러 
		$('.lprodTr').on('click', function() {
			var data = $(this).data("lprodGu");
			$("#lprod_gu").val(data);
			console.log("serialize : " + $('#frm').serialize());

			$('#frm').submit();
		})
	});
</script>
</head>
<body>
	<form id="frm" action="${cp }/prodList" method="get">
		<input type="hidden" id="lprod_gu" name="lprod_gu" />
	</form>
	<!-- header -->
	header
	<%@include file="/WEB-INF/views/commonJsp/header.jsp"%>
	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">

				<%@ include file="/WEB-INF/views/commonJsp/left.jsp"%>

			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">사용자</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>ID</th>
									<th>GU</th>
									<th>NM</th>
								</tr>


								<%--루프 태그 for (USer user : userList) --%>
								<c:forEach items="${lprodList}" var="lprod">
									<tr class="lprodTr" data-lprodGu="${lprod.lprod_gu }">
										<td>${lprod.lprod_id}</td>
										<td>${lprod.lprod_gu}</td>
										<td>${lprod.lprod_nm}</td>
									</tr>
								</c:forEach>
							</table>
						</div>

						<a class="btn btn-default pull-right">사용자 등록</a>

						<div class="text-center">
							<ul class="pagination">

								<c:choose>
									<c:when test="${pageVo.page == 1 }">
										<li class="disabled"><a href="#" aria-label-hidden="true">&laquo;</a></li>
									</c:when>
									<c:otherwise>
										<li><a
											href="${cp }/lprodPagingList?page=${pageVo.page-1 }" aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
										</a></li>
									</c:otherwise>
								</c:choose>

								<c:forEach begin="1" end="${paginationSize }" var="page">
									<c:choose>
										<c:when test="${page == pageVo.page }">
											<li class="active"><span>${page }</span></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${cp }/lprodPagingList?page=${page }&pagesize=${pageVo.pagesize }">${page }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${pageVo.page ==paginationSize }">
										<li class="disabled"><a href="#" aria-label-hidden="true">&laquo;</a></li>
									</c:when>
									<c:otherwise>
										<li><a
											href="${cp }/lprodPagingList?page=${pageVo.page+1 }" aria-label="Next"><span aria-hidden="true">&raquo;</span>
										</a></li>
									</c:otherwise>
								</c:choose>

							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>