<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập hệ thống</title>

    <style>
        body {
            margin: 0;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden;
            font-family: 'Segoe UI', sans-serif;

            /* Gradient nền */
            background: linear-gradient(135deg, #4cc9f0, #4361ee);
        }

        /* ICON RƠI */
        .icon {
            position: absolute;
            top: -50px;
            color: rgba(255, 255, 255, 0.4);
            font-size: 25px;
            animation: fall linear infinite;
        }

        @keyframes fall {
            to { transform: translateY(120vh); }
        }

        /* Form */
        .login-box {
            width: 380px;
            padding: 30px;
            background: #fff;
            border-radius: 18px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.15);
            text-align: center;
            position: relative;
            z-index: 10;
        }

        .login-box h2 {
            margin-bottom: 20px;
            font-size: 24px;
            font-weight: 700;
        }

        label {
            float: left;
            margin-bottom: 6px;
            font-weight: 600;
        }

        input {
            width: 100%;
            padding: 10px;
            border-radius: 8px;
            border: 1px solid #ccc;
            margin-bottom: 18px;
            font-size: 15px;
            background: #f1f5ff;
        }

        button {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 8px;
            background: linear-gradient(45deg, #4361ee, #3a0ca3);
            color: white;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: 0.25s;
        }

        button:hover {
            transform: scale(1.04);
            background: linear-gradient(45deg, #3a0ca3, #4361ee);
        }
    </style>
</head>

<body>

    <!-- ICON animation tự rơi -->
    <script>
        const icons = ["★", "✦", "✧", "❉", "✺", "✹", "☼"];
        function createIcon() {
            const icon = document.createElement("div");
            icon.classList.add("icon");
            icon.innerText = icons[Math.floor(Math.random() * icons.length)];
            icon.style.left = Math.random() * 100 + "vw";
            icon.style.animationDuration = (3 + Math.random() * 5) + "s";
            document.body.appendChild(icon);

            // Xóa icon sau khi rơi xong
            setTimeout(() => icon.remove(), 8000);
        }
        setInterval(createIcon, 300);
    </script>

    <!-- FORM -->
    <div class="login-box">
        <h2>Đăng nhập hệ thống</h2>

        <form action="${pageContext.request.contextPath}/login" method="post">

            <label>Tài khoản:</label>
            <input type="text" name="id" value="${id}">

            <label>Mật khẩu:</label>
            <input type="password" name="password">

            <button type="submit">Đăng nhập</button>

        </form>
    </div>

</body>
</html>
