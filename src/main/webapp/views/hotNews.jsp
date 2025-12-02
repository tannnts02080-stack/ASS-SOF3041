<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>

<h2 class="mb-4">🔥 Tin nổi bật</h2>

<c:if test="${empty hotNews}">
    <p class="text-muted">Không có tin nổi bật nào.</p>
</c:if>

<c:forEach var="n" items="${hotNews}">
    <div class="mb-5">
        <img src="${pageContext.request.contextPath}/assets/img/${n.image}"
             style="width:100%; border-radius:12px; height:350px; object-fit:cover">

        <h3 class="mt-3">
            <a href="${pageContext.request.contextPath}/news/detail?id=${n.id}"
               class="text-primary fw-bold">${n.title}</a>
        </h3>

        <div class="text-muted">${n.postedDate} · 👁 ${n.viewCount}</div>

        <p>${fn:substring(n.content, 0, 150)}...</p>

        <a href="${pageContext.request.contextPath}/news/detail?id=${n.id}">
            Xem chi tiết →
        </a>
    </div>
</c:forEach>
