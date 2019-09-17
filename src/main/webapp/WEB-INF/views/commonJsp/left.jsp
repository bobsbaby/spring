<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<ul class="nav nav-sidebar">
									<!-- a tag: get method -->
		<li class="active"><a href="${cp }/user/userList"><span class="glyphicon glyphicon-user"></span> 유저리스트<span class="sr-only"></span></a></li>
		<li class="active"><a href="${cp }/user/userListOnlyHalf"><span class="glyphicon glyphicon-user"></span> 사용자리스트(onlyHals)<span class="sr-only"></span></a></li>
		<li class="active"><a href="${cp }/user/userPagingList?page=0&pagesize=0"><span class="glyphicon glyphicon-user"></span> 사용자 페이징 리스트<span class="sr-only">(current)</span></a></li>
		<li class="active"><a href="${cp }/lprodList"><span class = "glyphicon glyphicon-bed"></span> 제품그룹리스트<span class="sr-only">(current)</span></a></li>
		<li class="active"><a href="${cp }/lprodPagingList?page=0&pagesize=0"><span class = "glyphicon glyphicon-bed"></span> 제품 페이징 리스트<span class="sr-only"></span></a></li>
	</ul>