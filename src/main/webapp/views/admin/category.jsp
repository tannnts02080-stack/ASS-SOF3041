<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý loại tin</title>

    <style>
        body { font-family: Arial; margin: 30px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        table, th, td { border: 1px solid #ccc; }
        th, td { padding: 10px; }
        input[type=text] { padding: 8px; width: 250px; }
        button { padding: 7px 14px; background:#007bff; border:none; color:white; cursor:pointer; }
        button:hover { background:#0056b3; }
        .btn-red{ background:#dc3545; } 
        .btn-red:hover{ background:#a71d2a; }
        .alert { padding:12px; background:#ffdddd; border-left:5px solid red; margin-bottom:20px; }
    </style>
</head>

<body>

<h2>Quản lý loại tin (Category)</h2>

<!-- THÔNG BÁO LỖI -->
<c:if test="${not empty error}">
    <div class="alert">${error}</div>
</c:if>

<!-- FORM -->
<form method="post" action="category">
    <input type="hidden" name="action"
           value="${category != null ? 'update' : 'insert'}">

    <label>Mã loại:</label><br>
    <input type="text" name="id"
           value="${category != null ? category.id : newId}"
           <c:if test="${category != null}">readonly</c:if> >
    <br><br>

    <label>Tên loại:</label><br>
    <input type="text" name="name"
           value="${category != null ? category.name : ''}">
    <br><br>

    <button type="submit">
        <c:choose>
            <c:when test="${category != null}">Cập nhật</c:when>
            <c:otherwise>Thêm mới</c:otherwise>
        </c:choose>
    </button>

    <c:if test="${category != null}">
        <button type="button" onclick="location.href='category'">Hủy</button>
    </c:if>
</form>

<hr>

<h3>Danh sách loại tin</h3>

<table>
    <tr>
        <th>Mã loại</th>
        <th>Tên loại</th>
        <th>Hành động</th>
    </tr>

    <c:forEach var="item" items="${categories}">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>
                <button onclick="location.href='category?action=edit&id=${item.id}'">Sửa</button>
                <button class="btn-red"
                        onclick="if(confirm('Xóa loại này?')) location.href='category?action=delete&id=${item.id}'">
                    Xóa
                </button>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
