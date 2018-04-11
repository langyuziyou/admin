<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>用户列表</title>
		<script src="<%=basePath %>js/jquery.min.js" type="text/javascript"></script>
		<script>
					var basePath = '<%=basePath%>';
		</script>
	</head>
<body>

<%-- 	<a href="goAdd">add</a>  
	<a href="<%=basePath %>permission/list">权限</a> --%>
	
		<form name="" id="mainForm" action="<%=basePath%>user/list" method="post">
									<input type="hidden" value="${page}" id="page" name="page" /> 
									<input type="hidden" value="${pageCount}" id="pageCount" name="pageCount" /> 
									<input type="hidden" value="${pageSize}" id="pageSize" name="pageSize" />
									
									 <input type="text" value="${name}" id="name" name="name"/>
									 <input type="submit" value="查询"/>
	<table style="min-width: 1100px;">

		<thead>
			<tr>
				<th style="min-width: 80px;">ID</th>
				<th style="min-width: 230px;">名称</th>
			</tr>
		</thead>
		<tbody id="mainTable">
			<c:forEach items="${list}" var="row">
				<tr id="${row.USER_ID}">
					<td>${row.USER_ID }</td>
					<td>${row.USER_ACCOUNT }</td>
					<td><a href="goEdit?id=${row.USER_ID }">修改</a></td>
					<td><a href="del?id=${row.USER_ID }">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
									<!-- 分页  -->
								
								<%@ include file="/WEB-INF/views/common/pageSplit.jsp"%> 
								</form>
</body>
</html>