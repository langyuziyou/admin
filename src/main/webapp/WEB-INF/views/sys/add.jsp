<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	System.out.println(basePath);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>新增</title>
</head>
<body>
add
	 <form name="" action="<%=basePath %>/category/add">
	 	<c:if test="${model == null}">
	 		 <input type="hidden" name="id" id="id" value="0"/>
	 	</c:if>
	 	<c:if test="${model != null}">
	 		 <input type="hidden" name="id" id="id" value="${model.id}"/>
	 	</c:if>
		
		 名称：<input type="text" id="name" name="name" value="${model.name}">
		imageUrl：<input type="text" id="imageUrl" name="imageUrl" value="${model.imageUrl}">
		<input type="submit" value="提交"/>
	</form>
</body>
</html>