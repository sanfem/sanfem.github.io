<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="title" value="${code.title}" />
</jsp:include>

<div class="page-header">
    <h2><i class="fas fa-code"></i> 代码详情</h2>
    <a href="${pageContext.request.contextPath}/code" class="btn btn-secondary">
        <i class="fas fa-arrow-left"></i> 返回列表
    </a>
</div>

<div class="code-detail">
    <h2>${code.title}</h2>
    <div class="info">
        <span><i class="fas fa-code"></i> ${code.language}</span>
        <span><i class="far fa-clock"></i> <fmt:formatDate value="${code.createdAt}" pattern="yyyy-MM-dd HH:mm" /></span>
    </div>
    <c:if test="${not empty code.description}">
        <p style="color: #aaa; margin-bottom: 20px;">${code.description}</p>
    </c:if>
    <div class="code-block">
        <pre><c:out value="${code.content}" /></pre>
    </div>
    <div style="margin-top: 20px; display: flex; gap: 12px;">
        <a href="${pageContext.request.contextPath}/code?action=edit&id=${code.id}" class="btn btn-primary">
            <i class="fas fa-edit"></i> 编辑
        </a>
        <a href="${pageContext.request.contextPath}/code?action=delete&id=${code.id}" class="btn btn-danger btn-delete">
            <i class="fas fa-trash"></i> 删除
        </a>
    </div>
</div>

<jsp:include page="/jsp/footer.jsp" />
