<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="title" value="${empty code ? '添加代码' : '编辑代码'}" />
</jsp:include>

<div class="form-container">
    <h2><i class="fas fa-code"></i> ${empty code ? '添加代码' : '编辑代码'}</h2>
    <form method="post" action="${pageContext.request.contextPath}/code">
        <c:if test="${not empty code}">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${code.id}">
        </c:if>

        <div class="form-group">
            <label for="title">标题</label>
            <input type="text" id="title" name="title" value="${code.title}" required
                   placeholder="请输入代码标题">
        </div>

        <div class="form-group">
            <label for="language">编程语言</label>
            <select id="language" name="language" required>
                <option value="">请选择语言</option>
                <option value="Java" ${code.language == 'Java' ? 'selected' : ''}>Java</option>
                <option value="Python" ${code.language == 'Python' ? 'selected' : ''}>Python</option>
                <option value="JavaScript" ${code.language == 'JavaScript' ? 'selected' : ''}>JavaScript</option>
                <option value="C" ${code.language == 'C' ? 'selected' : ''}>C</option>
                <option value="C++" ${code.language == 'C++' ? 'selected' : ''}>C++</option>
                <option value="HTML" ${code.language == 'HTML' ? 'selected' : ''}>HTML</option>
                <option value="CSS" ${code.language == 'CSS' ? 'selected' : ''}>CSS</option>
                <option value="SQL" ${code.language == 'SQL' ? 'selected' : ''}>SQL</option>
                <option value="Other" ${code.language == 'Other' ? 'selected' : ''}>其他</option>
            </select>
        </div>

        <div class="form-group">
            <label for="description">描述</label>
            <input type="text" id="description" name="description" value="${code.description}"
                   placeholder="简单描述这段代码的功能">
        </div>

        <div class="form-group">
            <label for="content">代码内容</label>
            <textarea id="content" name="content" class="code-textarea" required
                      placeholder="在此粘贴或编写代码...">${code.content}</textarea>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-save"></i> ${empty code ? '保存' : '更新'}
            </button>
            <a href="${pageContext.request.contextPath}/code" class="btn btn-secondary">
                <i class="fas fa-times"></i> 取消
            </a>
        </div>
    </form>
</div>

<jsp:include page="/jsp/footer.jsp" />
