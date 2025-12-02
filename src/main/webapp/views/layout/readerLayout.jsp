<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>ABC NEWS</title>

<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style>
body {
	font-family: "Segoe UI", Arial;
	background: #f4f6f8;
}

/* HEADER MENU */
.topbar {
	background: #1565c0;
	padding: 14px 25px;
	color: white;
	display: flex;
	justify-content: space-between;
	align-items: center;
	box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
}

.menu-item {
	color: white;
	font-weight: 700;
	margin-right: 35px;
	cursor: pointer;
	font-size: 22px; /* <== CHỈ CẦN SỬA DÒNG NÀY */
}

.menu-item:hover {
	color: #ffeb3b;
}

.menu-left {
	display: flex;
	align-items: center;
	gap: 35px;
}

/* ICON 3 GẠCH */
.menu-btn {
	font-size: 34px;
	cursor: pointer;
	padding: 5px 12px;
	border-radius: 6px;
	transition: 0.25s;
}

.menu-btn:hover {
	background: rgba(255, 255, 255, 0.2);
}

/* SIDEBAR CATEGORY */
.sidebar {
	position: fixed;
	top: 0;
	right: -350px;
	width: 350px;
	height: 100%;
	background: #fff;
	box-shadow: -4px 0 20px rgba(0, 0, 0, 0.15);
	padding: 25px;
	overflow-y: auto;
	transition: 0.4s ease;
	z-index: 999;
}

.sidebar.show {
	right: 0;
}

.category-block {
	margin-bottom: 35px;
}

.category-block h4 {
	font-size: 20px;
	margin-bottom: 10px;
	color: #1565c0;
	font-weight: 600;
	border-left: 4px solid #1565c0;
	padding-left: 10px;
}

.category-block a {
	display: block;
	color: #333;
	margin-bottom: 6px;
	font-size: 15px;
}

.category-block a:hover {
	color: #0d6efd;
	transform: translateX(3px);
}

/* MAIN CONTENT WRAPPER */
.content {
	padding: 28px;
	animation: fade 0.3s ease;
}

@
keyframes fade {from { opacity:0;
	transform: translateY(10px);
}

to {
	opacity: 1;
	transform: translateY(0);
}

}
.topbar {
	background: #1565c0;
	padding: 14px 25px;
	color: white;
	display: flex;
	justify-content: center; /* <= canh giữa */
	align-items: center;
	gap: 40px;
	box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
}

.logo-left {
	position: absolute;
	left: 25px;
}

.logo-title {
	font-size: 26px;
	font-weight: 800;
	color: white;
}

.right-box {
	position: absolute;
	right: 25px;
}

.logo-sub {
	font-size: 16px;
	font-weight: 500;
	color: #e8e8e8;
	white-space: nowrap;
}

/*bỏ rạch chân ABC NEWS*/
.logo-title {
	font-size: 26px;
	font-weight: 800;
	color: white;
	text-decoration: none; /* bỏ gạch chân */
}

.logo-title:hover {
	color: #ffeb3b; /* hover vàng cho đẹp */
}

/*bỏ gạch chân chữ*/
/* Bỏ gạch chân trong topbar */
.topbar a {
	text-decoration: none !important;
}

/* Giữ màu trắng */
.topbar a:link, .topbar a:visited {
	color: white;
}

/* Hover đổi vàng cho đẹp */
.topbar a:hover {
	color: #ffeb3b;
}

/*BỎ GẠCH CHÂN NỘI DUNG*/
.news-card a {
	text-decoration: none !important;
	color: #0d6efd; /* giữ màu xanh như hiện tại */
}

.news-card a:hover {
	text-decoration: none !important;
	color: #084298; /* xanh đậm hơn khi hover */
}
</style>

</head>

<body>

	<!-- 🔵 TOP NAVIGATION BAR -->
	<!-- 🔵 TOP NAVIGATION BAR -->
	<div class="topbar">

		<!-- LEFT: LOGO -->
		<div class="logo-left">
			<a href="${pageContext.request.contextPath}/home" class="logo-title">ABC
				NEWS</a>

		</div>

		<!-- CENTER MENU -->
		<div class="menu-left">
			<a class="menu-link"
				href="${pageContext.request.contextPath}/home?type=hot">🔥 Tin
				nổi bật</a> <a class="menu-link"
				href="${pageContext.request.contextPath}/home?type=newest">🆕
				Tin mới nhất</a>


			<div id="menu-btn" class="menu-btn">☰</div>
		</div>

		<!-- RIGHT: SLOGAN + ICON -->
		<div class="right-box d-flex align-items-center gap-4">
			<span class="logo-sub">Báo điện tử • Cập nhật liên tục</span>

		</div>

	</div>







	<!-- 📂 SIDEBAR CATEGORY -->
	<div id="sidebar" class="sidebar">

		<h3 class="mb-4 fw-bold">📂 Chuyên mục</h3>

		<c:forEach var="c" items="${categories}">
			<div class="category-block">
				<h4>${c.name}</h4>

				<a
					href="${pageContext.request.contextPath}/category/page?id=${c.id}">
					→ Xem bài viết </a>
			</div>
		</c:forEach>

	</div>

	<!-- 📰 MAIN CONTENT -->
	<div class="content">
		<jsp:include page="${contentPage}" />
	</div>

	<!-- 🔥 SCRIPT PHẢI ĐƯỢC ĐẶT Ở CUỐI BODY -->
	<script>
    const btn = document.getElementById("menu-btn");
    const sidebar = document.getElementById("sidebar");

    // Di chuột vào icon ☰ → mở menu
    btn.addEventListener("mouseenter", () => {
        sidebar.classList.add("show");
    });

    // Hover vào sidebar → giữ menu mở
    sidebar.addEventListener("mouseenter", () => {
        sidebar.classList.add("show");
    });

    // Rời sidebar → đóng menu
    sidebar.addEventListener("mouseleave", () => {
        sidebar.classList.remove("show");
    });

    // Rời icon → nếu không hover sidebar thì đóng
    btn.addEventListener("mouseleave", () => {
        setTimeout(() => {
            if (!sidebar.matches(":hover")) {
                sidebar.classList.remove("show");
            }
        }, 120);
    });
</script>

</body>
</html>
