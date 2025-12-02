<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<title>Quản lý tin tức</title>

<style>
    body { font-family: Arial; margin: 20px; }
    table { width: 100%; border-collapse: collapse; margin-top: 20px; }
    table, th, td { border: 1px solid #ccc; }
    th, td { padding: 10px; text-align:left; }

    input[type=text], textarea, select {
        width: 100%;
        padding: 8px;
        margin-bottom: 10px;
    }

    textarea { height: 120px; }
    button {
        padding: 8px 14px;
        background: #007bff;
        color: white;
        border: none;
        cursor: pointer;
    }
    button:hover { background: #0056b3; }

    .btn-red { background: #dc3545; }
    .btn-red:hover { background: #b52a35; }

    .preview-img {
        height: 80px;
        margin-top: 5px;
        border: 1px solid #ccc;
    }

    .alert-error {
        padding: 12px;
        background: #ffe5e5;
        border-left: 5px solid red;
        margin-bottom: 20px;
    }
</style>

<h2>Quản lý tin tức (News)</h2>

<!-- ⭐ HIỂN THỊ LỖI KHI TRÙNG MÃ -->
<c:if test="${not empty error}">
    <div class="alert-error">${error}</div>
</c:if>

<form method="post" enctype="multipart/form-data"
      action="${pageContext.request.contextPath}/news">

    <input type="hidden" name="action"
           value="${news != null ? 'update' : 'insert'}">

    <label>Mã tin (Id):</label>
    <input type="text" name="id"
           value="${news != null ? news.id : ''}"
           ${news != null ? "readonly" : ""} />

    <label>Tiêu đề (Title):</label>
    <input type="text" name="title" value="${news.title}" />

    <label>Nội dung (Content):</label>
    <textarea name="content">${news.content}</textarea>

    <label>Ảnh (Image):</label>
    <input type="file" name="imageFile" accept="/assets/img/*" />

    <!-- Giữ ảnh cũ -->
    <c:if test="${news != null}">
        <input type="hidden" name="oldImage" value="${news.image}">
        <div>
            <small>Ảnh hiện tại:</small><br>
            <img src="${pageContext.request.contextPath}/assets/img/${news.image}"
                 class="preview-img">
        </div>
    </c:if>

    <label>Tác giả (Author):</label>
    <input type="text" name="author"
           value="${news != null ? news.author : sessionScope.user.id}" />

    <label>View Count:</label>
    <input type="text" name="viewCount"
           value="${news != null ? news.viewCount : 0}" />

    <label>Loại tin (CategoryId):</label>
    <select name="categoryId">
        <c:forEach var="c" items="${categories}">
            <option value="${c.id}"
                ${news != null && news.categoryId == c.id ? "selected" : ""}>
                ${c.name}
            </option>
        </c:forEach>
    </select>

    <label>
        <input type="checkbox" name="home"
               ${news != null && news.home ? "checked" : ""}>
        Tin nổi bật (Home)
    </label>

    <br><br>

    <button type="submit">
        ${news != null ? "Cập nhật" : "Thêm mới"}
    </button>

    <c:if test="${news != null}">
        <button type="button"
                onclick="location.href='${pageContext.request.contextPath}/news'">
            Hủy
        </button>
    </c:if>
</form>

<hr>

<h3>Danh sách tin tức</h3>

<table>
    <tr>
        <th>Mã tin</th>
        <th>Tiêu đề</th>
        <th>Loại tin</th>
        <th>Ảnh</th>
        <th>Ngày đăng</th>
        <th>Lượt xem</th>
        <th>Nổi bật</th>
        <th>Hành động</th>
    </tr>

    <c:forEach var="n" items="${list}">
        <tr>
            <td>${n.id}</td>
            <td>${n.title}</td>
            <td>${n.categoryId}</td>
            <td>
                <img src="${pageContext.request.contextPath}/assets/img/${n.image}"
                     class="preview-img">
            </td>
            <td>${n.postedDate}</td>
            <td>${n.viewCount}</td>
            <td>${n.home ? "✔" : ""}</td>

            <td>
                <button onclick="location.href='${pageContext.request.contextPath}/news/edit?id=${n.id}'">
                    Sửa
                </button>

                <button class="btn-red"
                        onclick="if(confirm('Xóa tin này?')) 
                                 location.href='${pageContext.request.contextPath}/news/delete?id=${n.id}'">
                    Xóa
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
