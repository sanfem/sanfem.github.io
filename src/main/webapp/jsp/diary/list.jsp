<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="title" value="日记" />
</jsp:include>

<div class="page-header">
    <h2><i class="fas fa-book"></i> 日记</h2>
    <a href="${pageContext.request.contextPath}/diary?action=add" class="btn btn-primary">
        <i class="fas fa-plus"></i> 写日记
    </a>
</div>

<c:choose>
    <c:when test="${empty diaries}">
        <div class="empty-state">
            <div class="icon">📖</div>
            <p>还没有日记，开始记录你的故事吧！</p>
            <a href="${pageContext.request.contextPath}/diary?action=add" class="btn btn-primary">写日记</a>
        </div>
    </c:when>
    <c:otherwise>
        <div class="diary-list">
            <c:forEach var="diary" items="${diaries}">
                <div class="diary-item">
                    <div class="diary-header">
                        <h3>
                            <a href="${pageContext.request.contextPath}/diary?action=detail&id=${diary.id}">
                                ${diary.title}
                            </a>
                        </h3>
                        <div class="diary-tags">
                            <c:if test="${not empty diary.mood}">
                                <span class="diary-tag">${diary.mood}</span>
                            </c:if>
                            <c:if test="${not empty diary.weather}">
                                <span class="diary-tag">${diary.weather}</span>
                            </c:if>
                        </div>
                    </div>
                    <p class="preview">${diary.content}</p>
                    <div class="meta">
                        <span><i class="far fa-clock"></i>
                            <fmt:formatDate value="${diary.createdAt}" pattern="yyyy-MM-dd HH:mm" />
                        </span>
                        <div class="actions">
                            <a href="${pageContext.request.contextPath}/diary?action=edit&id=${diary.id}"
                               class="btn btn-secondary btn-sm">
                                <i class="fas fa-edit"></i> 编辑
                            </a>
                            <a href="${pageContext.request.contextPath}/diary?action=delete&id=${diary.id}"
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
