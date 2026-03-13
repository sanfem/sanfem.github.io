<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="title" value="代码集" />
</jsp:include>

<div class="page-header">
    <h2><i class="fas fa-laptop-code"></i> 代码集</h2>
    <a href="${pageContext.request.contextPath}/code?action=add" class="btn btn-primary">
        <i class="fas fa-plus"></i> 添加代码
    </a>
</div>

<c:choose>
    <c:when test="${empty codes}">
        <div class="empty-state">
            <div class="icon">💻</div>
            <p>还没有代码，快来添加第一段代码吧！</p>
            <a href="${pageContext.request.contextPath}/code?action=add" class="btn btn-primary">添加代码</a>
        </div>
    </c:when>
    <c:otherwise>
        <div class="code-list">
            <c:forEach var="code" items="${codes}">
                <div class="code-item">
                    <div class="code-header">
                        <h3>
                            <a href="${pageContext.request.contextPath}/code?action=detail&id=${code.id}">
                                ${code.title}
                            </a>
                        </h3>
                        <span class="language-tag">${code.language}</span>
                    </div>
                    <p class="description">${code.description}</p>
                    <div class="meta">
                        <span><i class="far fa-clock"></i>
                            <fmt:formatDate value="${code.createdAt}" pattern="yyyy-MM-dd HH:mm" />
                        </span>
                        <div class="actions">
                            <a href="${pageContext.request.contextPath}/code?action=edit&id=${code.id}"
                               class="btn btn-secondary btn-sm">
                                <i class="fas fa-edit"></i> 编辑
                            </a>
                            <a href="${pageContext.request.contextPath}/code?action=delete&id=${code.id}"
                               class="btn btn-danger btn-sm btn-delete">
                                <i class="fas fa-trash"></i> 删除
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>

<jsp:include page="/jsp/footer.jsp" />
