<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>👨‍💼 Xin chào phóng viên: ${sessionScope.user.fullname}</h2>
<p class="text-muted">Dưới đây là các bài viết trên hệ thống.</p>

<div class="card card-custom p-4 mt-4">
    <h4 class="mb-3">📝 Bài viết</h4>

    <table class="table table-hover align-middle">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Tiêu đề</th>
                <th>Ngày đăng</th>
                <th>Lượt xem</th>
                <th>Hành động</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach var="n" items="${list}">
                <tr>
                    <td>${n.id}</td>
                    <td><b>${n.title}</b></td>
                    <td>${n.postedDate}</td>
                    <td>${n.viewCount}</td>

                    <td>
                        <c:choose>

                            <c:when test="${n.author == sessionScope.user.id}">
                                <!-- Nếu bài viết này do chính phóng viên đang đăng nhập tạo -->
                                <a class="btn btn-sm btn-primary"
                                   href="${pageContext.request.contextPath}/reporter/news/edit?id=${n.id}">
                                    Sửa
                                </a>

                                <a class="btn btn-sm btn-danger"
                                   href="${pageContext.request.contextPath}/reporter/news/delete?id=${n.id}"
                                   onclick="return confirm('Xóa bài này?')">
                                    Xóa
                                </a>
                            </c:when>

                            <c:otherwise>
                                <!-- Không phải bài của mình -->
                                <span class="text-muted">Không có quyền</span>
                            </c:otherwise>

                        </c:choose>
                    </td>

                </tr>
            </c:forEach>
        </tbody>

    </table>
</div>
