<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="title" value="首页" />
</jsp:include>

<section class="hero">
    <h1>欢迎来到我的个人空间</h1>
    <p>这里是我的代码集、相册和日记，记录学习与生活的点滴</p>
</section>

<section class="features">
    <a href="${pageContext.request.contextPath}/code" class="feature-card">
        <div class="icon">💻</div>
        <h3>代码集</h3>
        <p>收藏和展示我写过的代码片段，涵盖多种编程语言和算法实现</p>
    </a>
    <a href="${pageContext.request.contextPath}/photo" class="feature-card">
        <div class="icon">📸</div>
        <h3>相册</h3>
        <p>记录生活中的美好瞬间，用图片讲述故事</p>
    </a>
    <a href="${pageContext.request.contextPath}/diary" class="feature-card">
        <div class="icon">📖</div>
        <h3>日记</h3>
        <p>记录每天的所思所想，留下成长的足迹</p>
    </a>
</section>

<jsp:include page="/jsp/footer.jsp" />
