<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="title" value="服务器错误" />
</jsp:include>

<div class="empty-state">
    <div class="icon">⚠️</div>
    <h2 style="color: #fff; margin-bottom: 12px;">500 - 服务器错误</h2>
    <p>抱歉，服务器遇到了一些问题，请稍后再试。</p>
    <a href="${pageContext.request.contextPath}/" class="btn btn-primary">
        <i class="fas fa-home"></i> 返回首页
    </a>
</div>

<jsp:include page="/jsp/footer.jsp" />
