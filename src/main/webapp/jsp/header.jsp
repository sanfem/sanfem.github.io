<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${param.title} - 我的个人网站</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
</head>
<body>
    <header class="navbar">
        <a href="${pageContext.request.contextPath}/" class="logo">
            <i class="fas fa-code"></i> <span>SanFem</span>
        </a>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/"><i class="fas fa-home"></i> 首页</a></li>
                <li><a href="${pageContext.request.contextPath}/code"><i class="fas fa-laptop-code"></i> 代码集</a></li>
                <li><a href="${pageContext.request.contextPath}/photo"><i class="fas fa-images"></i> 相册</a></li>
                <li><a href="${pageContext.request.contextPath}/diary"><i class="fas fa-book"></i> 日记</a></li>
            </ul>
        </nav>
    </header>
    <main class="container">
