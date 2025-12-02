<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div class="container py-5">

    <!-- Title -->
    <h1 class="fw-bold mb-3 text-primary">${news.title}</h1>

    <!-- Meta info -->
    <div class="text-muted mb-4">
        <span>📅 Ngày đăng: ${news.postedDate}</span> |
        <span>👁️ Lượt xem: ${news.viewCount}</span> |
        <span>✍️ Tác giả: ${news.author}</span>
    </div>

    <!-- Image -->
    <div class="text-center mb-4">
        <img src="${pageContext.request.contextPath}/assets/img/${news.image}"
             class="img-fluid rounded shadow"
             style="max-height: 450px; object-fit: cover;">
    </div>

    <!-- Content -->
    <div class="fs-5 lh-lg">
        ${news.content}
    </div>

    <!-- Back button -->
    <div class="mt-4">
        <a href="${pageContext.request.contextPath}/home" 
           class="btn btn-outline-primary px-4">
            ← Quay về trang chủ
        </a>
    </div>

</div>
