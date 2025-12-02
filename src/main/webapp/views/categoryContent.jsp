<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<style>
    .news-card {
        background: white;
        padding: 18px;
        border-radius: 10px;
        border: 1px solid #ddd;
        transition: 0.2s;
    }

    .news-card:hover {
        transform: translateY(-3px);
        box-shadow: 0 4px 10px rgba(0,0,0,0.1);
    }

    .news-card a {
        text-decoration: none;
        color: #0d6efd;
        font-weight: 600;
    }

    .news-card a:hover {
        color: #084298;
    }
</style>

<h2 class="fw-bold mb-4">📂 Chuyên mục: ${category.name}</h2>

<c:forEach var="n" items="${list}">
    <div class="news-card mb-3">
        <h4>
            <a href="${pageContext.request.contextPath}/news/detail?id=${n.id}">
                ${n.title}
            </a>
        </h4>

        <div class="text-muted small mt-2">
            📅 ${n.postedDate} 
            &nbsp; | &nbsp; 
            👁 ${n.viewCount}
        </div>
    </div>
</c:forEach>

<a href="${pageContext.request.contextPath}/home" class="btn btn-secondary mt-3">
    ⬅ Quay lại
</a>
