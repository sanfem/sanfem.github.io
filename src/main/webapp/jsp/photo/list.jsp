<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="title" value="相册" />
</jsp:include>

<div class="page-header">
    <h2><i class="fas fa-images"></i> 相册</h2>
    <a href="${pageContext.request.contextPath}/photo?action=upload" class="btn btn-primary">
        <i class="fas fa-upload"></i> 上传照片
    </a>
</div>

<c:choose>
    <c:when test="${empty photos}">
        <div class="empty-state">
            <div class="icon">📸</div>
            <p>相册还是空的，上传第一张照片吧！</p>
            <a href="${pageContext.request.contextPath}/photo?action=upload" class="btn btn-primary">上传照片</a>
        </div>
    </c:when>
    <c:otherwise>
        <div class="photo-grid">
            <c:forEach var="photo" items="${photos}">
                <div class="photo-card">
                    <img src="${pageContext.request.contextPath}/${photo.filePath}" alt="${photo.title}">
                    <div class="photo-info">
                        <h3>${photo.title}</h3>
                        <c:if test="${not empty photo.description}">
                            <p>${photo.description}</p>
                        </c:if>
                        <div class="photo-actions">
                            <span><i class="far fa-clock"></i>
                                <fmt:formatDate value="${photo.createdAt}" pattern="yyyy-MM-dd" />
                            </span>
                            <a href="${pageContext.request.contextPath}/photo?action=delete&id=${photo.id}"
                               class="btn btn-danger btn-sm btn-delete">
                                <i class="fas fa-trash"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>

<jsp:include page="/jsp/footer.jsp" />
