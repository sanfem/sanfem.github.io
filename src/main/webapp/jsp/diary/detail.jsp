<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="title" value="${diary.title}" />
</jsp:include>

<div class="page-header">
    <h2><i class="fas fa-book-open"></i> 日记详情</h2>
    <a href="${pageContext.request.contextPath}/diary" class="btn btn-secondary">
        <i class="fas fa-arrow-left"></i> 返回列表
    </a>
</div>

<div class="diary-detail">
    <h2>${diary.title}</h2>
    <div class="info">
        <span><i class="far fa-clock"></i> <fmt:formatDate value="${diary.createdAt}" pattern="yyyy-MM-dd HH:mm" /></span>
        <c:if test="${not empty diary.mood}">
            <span><i class="far fa-smile"></i> ${diary.mood}</span>
        </c:if>
        <c:if test="${not empty diary.weather}">
            <span><i class="fas fa-cloud-sun"></i> ${diary.weather}</span>
        </c:if>
    </div>
    <div class="content"><c:out value="${diary.content}" /></div>
    <div style="margin-top: 30px; display: flex; gap: 12px;">
        <a href="${pageContext.request.contextPath}/diary?action=edit&id=${diary.id}" class="btn btn-primary">
            <i class="fas fa-edit"></i> 编辑
        </a>
        <a href="${pageContext.request.contextPath}/diary?action=delete&id=${diary.id}" class="btn btn-danger btn-delete">
            <i class="fas fa-trash"></i> 删除
        </a>
    </div>
</div>

<jsp:include page="/jsp/footer.jsp" />
