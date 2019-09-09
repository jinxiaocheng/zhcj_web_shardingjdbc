<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>loading</title>
    <link rel="stylesheet" href="">
    <style>
            #loading{
                position: absolute;
                top: 40%;
                left: 50%;
                margin-left: -164px;

            }
    </style>
</head>
<body>
    <img id="loading" src="${ctx}/resources/static/images/loading.gif" alt="">
</body>
</html>