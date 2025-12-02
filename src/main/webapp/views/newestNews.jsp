<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<h2 class="mb-4">🆕 Tin mới nhất</h2>

<c:forEach var="n" items="${newest}">
    <div class="mb-4 p-3 border rounded">
        <h4>
            <a href="${pageContext.request.contextPath}/news/detail?id=${n.id}">
                ${n.title}
            </a>
        </h4>

        <div class="text-muted small">${n.postedDate}</div>
    </div>
</c:forEach>
