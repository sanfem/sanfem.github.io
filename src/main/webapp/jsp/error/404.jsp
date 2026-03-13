<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="title" value="页面未找到" />
</jsp:include>

<div class="empty-state">
    <div class="icon">🔍</div>
    <h2 style="color: #fff; margin-bottom: 12px;">404 - 页面未找到</h2>
    <p>抱歉，您访问的页面不存在。</p>
    <a href="${pageContext.request.contextPath}/" class="btn btn-primary">
        <i class="fas fa-home"></i> 返回首页
    </a>
</div>

<jsp:include page="/jsp/footer.jsp" />
