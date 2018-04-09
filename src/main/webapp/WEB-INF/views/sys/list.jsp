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
<body>

	<a href="goAdd">add</a>  
	<a href="<%=basePath %>permission/list">权限</a>
	<table style="min-width: 1100px;">

		<thead>
			<tr>
				<th style="min-width: 80px;">ID</th>
				<th style="min-width: 230px;">名称</th>
				<th style="min-width: 100px;">图片</th>
				<th style="min-width: 100px;">操作</th>
			</tr>
		</thead>
		<tbody id="mainTable">
			<c:forEach items="${list}" var="row">
				<tr id="${row.ID}">
					<td>${row.ID }</td>
					<td>${row.NAME }</td>
					<td>${row.IMAGEURL }</td>
					<td><a href="goEdit?id=${row.ID }">修改</a></td>
					<td><a href="del?id=${row.ID }">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>