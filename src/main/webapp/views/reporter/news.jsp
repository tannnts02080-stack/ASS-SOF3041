<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="p-4">

    <!-- HIỆN LỖI -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger fw-bold">${error}</div>
    </c:if>

    <h3 class="fw-bold mb-4">
        <c:choose>
            <c:when test="${not empty news}">✏️ Sửa bài viết</c:when>
            <c:otherwise>📝 Đăng bài mới</c:otherwise>
        </c:choose>
    </h3>

<form action="${pageContext.request.contextPath}/reporter/news/save" 
      method="post" enctype="multipart/form-data">

    <!-- BÁO CHO SERVLET BIẾT LÀ ĐANG EDIT -->
    <c:if test="${not empty news}">
        <input type="hidden" name="editing" value="1">
    </c:if>

    <!-- ID -->
    <div class="mb-3">
        <label>Mã tin (Id):</label>
        <input type="text" name="id"
               class="form-control"
               value="${not empty news ? news.id : inputId}"
               <c:if test="${not empty news}">readonly</c:if> >
    </div>

    <!-- Title -->
    <div class="mb-3">
        <label>Tiêu đề:</label>
        <input type="text" name="title"
               class="form-control"
               value="${not empty news ? news.title : inputTitle}">
    </div>

    <!-- Content -->
    <div class="mb-3">
        <label>Nội dung:</label>
        <textarea name="content" rows="6" class="form-control">${not empty news ? news.content : inputContent}</textarea>
    </div>

    <!-- Image -->
    <div class="mb-3">
        <label>Ảnh:</label>
        <input type="file" name="imageFile" class="form-control">
    </div>

    <c:if test="${not empty news.image}">
        <div class="mb-3">
            <img src="${pageContext.request.contextPath}/assets/img/${news.image}" width="150"
                 style="border:1px solid #ccc; border-radius:6px;">
        </div>
    </c:if>

    <!-- Category -->
    <div class="mb-3">
        <label>Loại tin:</label>
        <select name="categoryId" class="form-select">
            <c:forEach var="c" items="${categories}">
                <option value="${c.id}"
                    <c:if test="${(not empty news && news.categoryId == c.id) || inputCate == c.id}">
                        selected
                    </c:if>>
                    ${c.name}
                </option>
            </c:forEach>
        </select>
    </div>

    <!-- Home -->
    <div class="form-check mb-3">
        <input type="checkbox" name="home" class="form-check-input"
               <c:if test="${(not empty news && news.home) || inputHome}">checked</c:if> >
        <label class="form-check-label">Tin nổi bật</label>
    </div>

    <!-- Buttons -->
    <button class="btn btn-primary">
        <c:choose>
            <c:when test="${not empty news}">Cập nhật</c:when>
            <c:otherwise>Đăng bài</c:otherwise>
        </c:choose>
    </button>

    <a href="${pageContext.request.contextPath}/reporter/home" class="btn btn-secondary">Hủy</a>

</form>

</div>
