<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body { background: #f4f6f9; }

        .sidebar {
            width: 240px;
            height: 100vh;
            position: fixed;
            left: 0;
            top: 0;
            background: #1e1f26;
            padding-top: 20px;
        }

        .sidebar .menu-item {
            padding: 12px 20px;
            color: #ddd;
            display: flex;
            align-items: center;
            gap: 10px;
            text-decoration: none;
        }

        .sidebar .menu-item:hover { background: #343642; color: white; }

        .content {
            margin-left: 240px;
            padding: 40px;
        }
    </style>
</head>
<body>

<div class="sidebar">
    <a class="menu-item" href="${pageContext.request.contextPath}/admin">
        <i class="bi bi-speedometer2"></i> Bảng điều khiển
    </a>

    <a class="menu-item" href="${pageContext.request.contextPath}/user">
        <i class="bi bi-people"></i> Quản lý người dùng
    </a>

    <a class="menu-item" href="${pageContext.request.contextPath}/category">
        <i class="bi bi-list-ul"></i> Quản lý danh mục
    </a>

    <a class="menu-item" href="${pageContext.request.contextPath}/news">
        <i class="bi bi-newspaper"></i> Quản lý tin tức
    </a>

    <a class="menu-item" href="${pageContext.request.contextPath}/newsletter">
        <i class="bi bi-envelope-paper"></i> Đăng ký nhận tin
    </a>

    <a class="menu-item text-danger" href="${pageContext.request.contextPath}/logout">
        <i class="bi bi-box-arrow-right"></i> Đăng xuất
    </a>
</div>

<!-- CHỈ NHÚNG TRANG CON -->
<div class="content">
    <jsp:include page="${contentPage}" />
</div>

</body>
</html>
