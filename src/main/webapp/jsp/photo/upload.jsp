<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/jsp/header.jsp">
    <jsp:param name="title" value="上传照片" />
</jsp:include>

<div class="form-container">
    <h2><i class="fas fa-upload"></i> 上传照片</h2>
    <form method="post" action="${pageContext.request.contextPath}/photo" enctype="multipart/form-data">
        <div class="form-group">
            <label for="title">标题</label>
            <input type="text" id="title" name="title" required placeholder="请输入照片标题">
        </div>

        <div class="form-group">
            <label for="description">描述</label>
            <input type="text" id="description" name="description" placeholder="简单描述这张照片">
        </div>

        <div class="form-group">
            <label for="photoInput">选择照片</label>
            <input type="file" id="photoInput" name="photo" accept="image/*" required>
            <img id="photoPreview" style="display:none; max-width:100%; margin-top:16px; border-radius:8px;" alt="预览">
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">
                <i class="fas fa-upload"></i> 上传
            </button>
            <a href="${pageContext.request.contextPath}/photo" class="btn btn-secondary">
                <i class="fas fa-times"></i> 取消
            </a>
        </div>
    </form>
</div>

<jsp:include page="/jsp/footer.jsp" />
