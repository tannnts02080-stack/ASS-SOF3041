<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>


<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>ABC NEWS - Trang chủ</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
	
	

<style>
body {
	background: #f4f6f8;
	font-family: "Segoe UI", Arial;
}

/* --- TOP HEADER (TRẮNG GIỐNG VNEXPRESS) --- */
.vn-header {
	background: #ffffff;
	padding: 18px 0;
	border-bottom: 1px solid #ddd;
	margin-bottom: 10px;
}

.vn-logo {
	font-size: 28px;
	font-weight: 800;
	color: #1565c0;
}

/* --- MENU NGANG XANH --- */
.menu-bar {
	background: #1565c0;
	padding: 10px 0;
	margin-bottom: 30px;
}

.menu-link {
	color: white;
	margin-right: 30px;
	font-size: 16px;
	font-weight: 500;
}

.menu-link:hover {
	color: #ffeb3b;
}

/* --- NEWS HOT BLOCK --- */
.hot-img {
	border-radius: 12px;
	width: 100%;
	height: 340px;
	object-fit: cover;
}

.hot-title {
	font-size: 26px;
	font-weight: 700;
	color: #1565c0;
}

.hot-desc {
	font-size: 17px;
	line-height: 1.6;
	color: #444;
}

/* --- BANNER PHẢI --- */
.right-banner img {
	width: 100%;
	border-radius: 14px;
	box-shadow: 0 6px 18px rgba(0, 0, 0, 0.15);
}

/* --- CARD NEWS --- */
.news-card {
	background: white;
	padding: 30px;
	border-radius: 14px;
	border: 1px solid #ebebeb;
	transition: .25s;
	height: 100%;
}

.news-card:hover {
	box-shadow: 0 5px 20px rgba(0, 0, 0, 0.12);
	transform: translateY(-4px);
}

.section-title {
	font-size: 24px;
	font-weight: 700;
	margin: 35px 0 15px 0;
	color: #1565c0;
}

/*TRANG TRÍ TIN THEO CHUYÊN MỤC*/
/* Card tin theo chuyên mục */
.category-news-card {
    background: #ffffff;
    padding: 18px 22px;
    border-radius: 12px;
    border: 1px solid #e6e6e6;
    margin-bottom: 14px;
    transition: 0.25s ease;
}

.category-news-card:hover {
    transform: translateY(-3px);
    box-shadow: 0 6px 18px rgba(0,0,0,0.08);
}

/* Link trong card */
.category-news-card a {
    text-decoration: none !important;
    font-size: 18px;
    font-weight: 500;
    color: #0d6efd;
}

.category-news-card a:hover {
    color: #084298;
}

.container {
    max-width: 100% !important;
}

.container {
    max-width: 100% !important;
}


/*CHỈNH TIN THEO CHUYÊN MỤC*/




</style>
</head>

<body>

	
	

	<div class="container">

		<!-- 🟥 TIN NỔI BẬT (ẢNH TRÁI + TEXT PHẢI) -->
		<div class="row mb-5">

			<!-- LEFT: HOT IMG -->
			<div class="col-md-8">

				<c:if test="${not empty hotNews}">
					<c:set var="hn" value="${hotNews[0]}" />

					<img class="hot-img"
						src="${pageContext.request.contextPath}/assets/img/${hn.image}"
						alt="">

					<h2 class="hot-title mt-3">${hn.title}</h2>

					<div class="text-muted mb-2">${hn.postedDate} · 👁
						${hn.viewCount}</div>

					<p class="hot-desc">${fn:substring(hn.content, 0, 180)}...</p>


					<a
						href="${pageContext.request.contextPath}/news/detail?id=${hn.id}"
						class="text-primary fw-bold">Xem chi tiết →</a>
				</c:if>

			</div>

			<!-- RIGHT: BANNER -->
			<div class="col-md-4 right-banner">
				<img src="${pageContext.request.contextPath}/assets/img/vn-bg.jpg">
			</div>

		</div>


		<!-- 🟩 TIN MỚI NHẤT -->
		<div class="section-title">🆕 Tin mới nhất</div>

		<div class="row g-4">
			<c:forEach var="n" items="${newest}">
				<div class="col-md-4">
					<div class="news-card">
						<h5>
							<a
								href="${pageContext.request.contextPath}/news/detail?id=${n.id}"
								class="fw-bold"> ${n.title} </a>
						</h5>

						<div class="text-muted small mt-2">
							${n.postedDate}<br> Chuyên mục: <b>${n.categoryId}</b>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>


		<!-- 📂 THEO CHUYÊN MỤC -->
<div class="section-title">📂 Tin theo chuyên mục</div>

<c:forEach var="c" items="${categories}">

    <div class="category-section">

        <h3>
            <i class="bi bi-folder"></i> ${c.name}
        </h3>

        <div class="row g-3">
            <c:forEach var="n" items="${newest}">
                <c:if test="${n.categoryId == c.id}">
                    <div class="col-12">
                        <div class="category-news-card">
                            <a href="${pageContext.request.contextPath}/news/detail?id=${n.id}">
                                ${n.title}
                            </a>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>

    </div>

</c:forEach>


	</div>

</body>
</html>
