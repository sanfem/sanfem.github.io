<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="title" value="${empty diary ? '写日记' : '编辑日记'}" />
</jsp:include>

<div class="form-container">
    <h2><i class="fas fa-pen"></i> ${empty diary ? '写日记' : '编辑日记'}</h2>
    <form method="post" action="${pageContext.request.contextPath}/diary">
        <c:if test="${not empty diary}">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${diary.id}">
        </c:if>

        <div class="form-group">
            <label for="title">标题</label>
            <input type="text" id="title" name="title" value="${diary.title}" required
                   placeholder="今天的标题">
        </div>

        <div class="form-group">
            <label for="mood">心情</label>
            <select id="mood" name="mood">
                <option value="">请选择心情</option>
                <option value="开心" ${diary.mood == '开心' ? 'selected' : ''}>😊 开心</option>
                <option value="平静" ${diary.mood == '平静' ? 'selected' : ''}>😐 平静</option>
                <option value="充实" ${diary.mood == '充实' ? 'selected' : ''}>💪 充实</option>
                <option value="感动" ${diary.mood == '感动' ? 'selected' : ''}>🥺 感动</option>
                <option value="难过" ${diary.mood == '难过' ? 'selected' : ''}>😢 难过</option>
                <option value="疲惫" ${diary.mood == '疲惫' ? 'selected' : ''}>😴 疲惫</option>
            </select>
        </div>

        <div class="form-group">
            <label for="weather">天气</label>
            <select id="weather" name="weather">
                <option value="">请选择天气</option>
                <option value="晴天" ${diary.weather == '晴天' ? 'selected' : ''}>☀️ 晴天</option>
                <option value="多云" ${diary.weather == '多云' ? 'selected' : ''}>⛅ 多云</option>
                <option value="阴天" ${diary.weather == '阴天' ? 'selected' : ''}>☁️ 阴天</option>
                <option value="雨天" ${diary.weather == '雨天' ? 'selected' : ''}>🌧️ 雨天</option>
                <option value="雪天" ${diary.weather == '雪天' ? 'selected' : ''}>❄️ 雪天</option>
            </select>
        </div>

        <div class="form-group">
            <label for="content">内容</label>
            <textarea id="content" name="content" required
                      placeholder="记录今天的故事...">${diary.content}</textarea>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-save"></i> ${empty diary ? '保存' : '更新'}
            </button>
            <a href="${pageContext.request.contextPath}/diary" class="btn btn-secondary">
                <i class="fas fa-times"></i> 取消
            </a>
        </div>
    </form>
</div>

<jsp:include page="/jsp/footer.jsp" />
