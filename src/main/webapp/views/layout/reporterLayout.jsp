<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang phóng viên</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body { background: #f4f6f9; }
        .sidebar {
            width: 240px;
            height: 100vh;
            background: #1e1f26;
            position: fixed;
            left: 0; top: 0;
            padding-top: 20px;
        }
        .sidebar .menu-item {
            padding: 12px 20px;
            color: #ddd;
            display: flex;
            gap: 10px;
            text-decoration: none;
            font-size: 16px;
        }
        .sidebar .menu-item:hover {
            background: #343642;
            color: white;
        }
        .content {
            margin-left: 240px;
            padding: 40px;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <a class="menu-item" href="${pageContext.request.contextPath}/reporter/home">
        <i class="bi bi-person-video2"></i> Trang phóng viên
    </a>

    <a class="menu-item" href="${pageContext.request.contextPath}/reporter/news/new">
    <i class="bi bi-file-earmark-plus"></i> Đăng bài mới
</a>


    <a class="menu-item text-danger" href="${pageContext.request.contextPath}/logout">
        <i class="bi bi-box-arrow-right"></i> Đăng xuất
    </a>
</div>

<div class="content">
    <c:import url="${contentPage}" />

</div>

</body>
</html>
