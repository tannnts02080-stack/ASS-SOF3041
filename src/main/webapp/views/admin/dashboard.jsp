<%@ page contentType="text/html;charset=UTF-8" %>

<h2 class="fw-bold">👋 Chào mừng Admin!</h2>
<p class="text-muted">Hãy chọn tính năng ở menu bên trái.</p>

<div class="row mt-4">
    <div class="col-md-4">
        <div class="card p-4">
            <h5><i class="bi bi-people text-primary"></i> Người dùng</h5>
            <p>Quản lý tài khoản nhân viên & admin.</p>
            <a href="${pageContext.request.contextPath}/user" class="btn btn-primary mt-2">Đi đến quản lý</a>
        </div>
    </div>

    <div class="col-md-4">
        <div class="card p-4">
            <h5><i class="bi bi-newspaper text-success"></i> Tin tức</h5>
            <p>Thêm & chỉnh sửa các bài viết.</p>
            <a href="${pageContext.request.contextPath}/news" class="btn btn-success mt-2">Đi đến quản lý</a>
        </div>
    </div>

    <div class="col-md-4">
        <div class="card p-4">
            <h5><i class="bi bi-envelope-open text-warning"></i> Newsletter</h5>
            <p>Xem danh sách người đăng ký.</p>
            <a href="${pageContext.request.contextPath}/newsletter" class="btn btn-warning mt-2">Đi đến quản lý</a>
        </div>
    </div>
</div>

