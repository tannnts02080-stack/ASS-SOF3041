<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<title>Quản lý người dùng</title>

<style>
body {
	font-family: Arial;
	margin: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

table, th, td {
	border: 1px solid #ccc;
}

th, td {
	padding: 10px;
}

input, select {
	width: 100%;
	padding: 8px;
	margin-bottom: 10px;
}

button {
	padding: 8px 14px;
	background: #007bff;
	color: #fff;
	border: none;
	cursor: pointer;
}

button:hover {
	background: #0056b3;
}

.btn-red {
	background: #dc3545;
}

.btn-red:hover {
	background: #b52a35;
}
</style>

<h2>Quản lý tài khoản (Users)</h2>

<form method="post" action="${pageContext.request.contextPath}/user">

	<!-- Xác định hành động INSERT hay UPDATE -->
	<input type="hidden" name="action"
		value="${requestScope.user != null ? 'update' : 'insert'}"> <label>Mã
		người dùng (ID):</label> <input type="text" name="id"
		value="${requestScope.user != null ? requestScope.user.id : ''}"
		${requestScope.user != null ? "readonly" : ""} /> <label>Mật
		khẩu:</label> <input type="text" name="password"
		value="${requestScope.user != null ? requestScope.user.password : ''}" />

	<label>Họ tên:</label> <input type="text" name="fullname"
		value="${requestScope.user != null ? requestScope.user.fullname : ''}" />

	<label>Ngày sinh:</label> <input type="date" name="birthday"
		value="${requestScope.user != null ? requestScope.user.birthday : ''}" />

	<label>Số điện thoại:</label> <input type="text" name="phone"
		value="${requestScope.user != null ? requestScope.user.phone : ''}" />

	<label>Email:</label> <input type="text" name="email"
		value="${requestScope.user != null ? requestScope.user.email : ''}" />

	<label>Vai trò:</label> <select name="role">
		<option value="1"
			${requestScope.user != null && requestScope.user.role == 1 ? "selected" : ""}>
			Admin</option>

		<option value="0"
			${requestScope.user != null && requestScope.user.role == 0 ? "selected" : ""}>
			Phóng viên</option>
	</select>

	<!-- Nút Submit -->
	<button type="submit">${requestScope.user != null ? "Cập nhật" : "Thêm mới"}</button>

	<!-- Nút Hủy (chỉ hiện khi đang Edit) -->
	<c:if test="${requestScope.user != null}">
		<button type="button"
			onclick="location.href='${pageContext.request.contextPath}/user'">
			Hủy</button>
	</c:if>

</form>

<hr>

<h3>Danh sách người dùng</h3>

<table>
	<tr>
		<th>ID</th>
		<th>Họ tên</th>
		<th>Ngày sinh</th>
		<th>SĐT</th>
		<th>Email</th>
		<th>Vai trò</th>
		<th>Hành động</th>
	</tr>

	<c:forEach var="u" items="${list}">
		<tr>
			<td>${u.id}</td>
			<td>${u.fullname}</td>
			<td>${u.birthday}</td>
			<td>${u.phone}</td>
			<td>${u.email}</td>
			<td>${u.role == 1 ? "Admin" : "Phóng viên"}</td>

			<td>
				<button
					onclick="location.href='${pageContext.request.contextPath}/user/edit?id=${u.id}'">
					Sửa</button> <c:if test="${u.role != 1}">
					<button class="btn-red"
						onclick="if(confirm('Xóa người dùng này?')) location.href='${pageContext.request.contextPath}/user/delete?id=${u.id}'">
						Xóa</button>
				</c:if>

			</td>
		</tr>
	</c:forEach>

</table>
