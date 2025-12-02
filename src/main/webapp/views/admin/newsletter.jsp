<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<h2>Đăng ký nhận tin</h2>

<form method="post" action="${pageContext.request.contextPath}/newsletter">
    <input type="text" name="email" placeholder="Nhập email..." style="width:300px;padding:8px;">
    <button type="submit">Đăng ký</button>
</form>

<hr>

<h3>Danh sách đã đăng ký</h3>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Email</th>
        <th>Ngày đăng ký</th>
        <th>Hành động</th>
    </tr>

    <c:forEach var="n" items="${list}">
        <tr>
            <td>${n.id}</td>
            <td>${n.email}</td>
            <td>${n.subscribedDate}</td>
            <td>
                <button onclick="location.href='${pageContext.request.contextPath}/newsletter/delete?id=${n.id}'">Xóa</button>
            </td>
        </tr>
    </c:forEach>
</table>
