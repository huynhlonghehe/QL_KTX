<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style><%@include file="/WEB-INF/resources/css/page_login.css"%></style>
</head>
<body>
    <div class="main-container">
        <div class="overlay"></div>
        <h1 class="main-container-title">Học Viện Công Nghệ Bưu Chính Viễn Thông cơ sở TPHCM</h1>
        <span id="currentDateTime"></span>
    </div>

    <div class="login-container">
        <form class="login-form" action="#" method="post">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAqRmZSXWlKqfobe50ulZH45nIrmkuxhWjUQX6skmxbw&s" alt="Icon" class="login-icon">
            <h2>Chào mừng tới</h2>
            <h1>Hệ thống quản lý KTX</h1>
            <h4>Hãy điền thông tin tài khoản để đăng nhập</h4>
            <div class="input-group">
                <label for="username">Mã số sinh viên</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="input-group">
                <label for="password">Mật khẩu</label>
                <input type="password" id="password" name="password" required>
            </div>
            <a href="#">Quên mật khẩu?</a>
            <button type="submit">Đăng nhập</button>
        </form>
    </div>

    <script>
        function updateTime() {
            var now = new Date();
            var dateTimeString = now.toLocaleString('vi-VN'); // Định dạng ngày giờ cho tiếng Việt
            document.getElementById('currentDateTime').textContent = dateTimeString;
        }

        // Cập nhật ngày giờ mỗi giây
        setInterval(updateTime, 1000);
    </script>
</body>


</html>
