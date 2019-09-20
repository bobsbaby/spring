<%@page import="kr.or.ddit.user.model.User"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${userList }" var="user">
	<tr class="userTr" data-userId="${user.userId }">
		<input type="hidden" value="${user.userId }" />
		<td>${user.userId }</td>
		<td>${user.userNm }</td>
		<td>${user.alias }</td>
		<td>${user.reg_dt_fmt }</td>
	</tr>
</c:forEach>

##########!!!!!!!!!!

<ul class="pagination">
	<%-- 							이전페이지 가기 : 지금 있는 페이지에서 한 페이지 전으로
								단 1페이지인 경우는 li 태그에 class = "disabled"를 추가하고 이동경로는 차단  --%>
	<c:choose>
		<c:when test="${pageVo.page == 1 }">
			<li class="disabled"><a href="#" aria-label-hidden="true">&laquo;</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="javascript:getUserHatmlList(${pageVo.page-1 }, ${pageVo.pagesize});"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:otherwise>
	</c:choose>

	<%-- 								<li class="disabled"><a href="${cp }/userPagingList?page=${param.page-1 }&pagesize=10" aria-label="Previous"> --%>
	<!-- 										<span aria-hidden="true">&laquo;</span> -->
	<!-- 								</a></li> -->

	<c:forEach begin="1" end="${pagenationSize }" var="page">
		<c:choose>
			<c:when test="${page == pageVo.page }">
				<%-- 								<li <c:if test = "${page == param.page }"> class = "active" </c:if> --%>
				<%-- 								<li class = "active"><a href="${cp }/userPagingList?page=${page }&pagesize=10">${page }</a></li> --%>
				<li class="active"><span>${page }</span></li>
			</c:when>
			<c:otherwise>
				<li><a
					href="javascript:getUserHatmlList(${page }, ${pageVo.pagesize});">${page }</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:choose>
		<c:when test="${pageVo.page ==pagenationSize }">
			<li class="disabled"><a href="#" aria-label-hidden="true">&laquo;</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="javascript:getUserHatmlList(${pageVo.page+1 }, ${pageVo.pagesize});"
				aria-label="Next"><span aria-hidden="true">&raquo;</span> </a></li>
		</c:otherwise>
	</c:choose>

</ul>