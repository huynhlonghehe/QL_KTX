<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style><%@include file="/WEB-INF/resources/css/QuanLy_CSS/QuanLyTaiKhoan.css"%></style>

</head>
<body>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/WEB-INF/views/includes/menu.jsp"%>
<div class="main_content">
	<h1 class="title">Quản Lý Tài Khoản</h1>
    <table>
        <thead>
            <tr>
                <th scope="col">Tên Tài Khoản</th>
                <%--<th scope="col">Mật Khẩu</th>--%>
                <th scope="col">Tình Trạng</th>
                <th scope="col">Quyền</th>
                <th scope="col" colspan="2">Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="taikhoan" items="${ListTK}">
                <tr>
                    <td>${taikhoan.getTenDangNhap()}</td>
                    <%--<td>${taikhoan.getMatKhau()}</td>--%>
                    <td>${taikhoan.getTinhTrang()}</td>
                    <td>${taikhoan.getQuyen()}</td>
                    <td><a href="../quanly/QLTaiKhoan/${taikhoan.tenDangNhap}/edit"  class="button_action button_edit">Sửa</a></td>
                    <td><a href="../quanly/QLTaiKhoan/${taikhoan.tenDangNhap}/delete"  class="button_action button_delete">Xoá</a></td>
                    
                </tr>
            </c:forEach>
        </tbody>
    </table> 
    
    <button type="button" class="btn btn-primary button_createTK" onclick="toggleCreateAccountForm()">Tạo tài khoản</button>
	<div id="createAccountForm" style="display: none">
        <h2>Tạo Tài Khoản</h2>
        <form action="" method="post">
            <div class="form-group">
                <label for="username">Tên đăng nhập:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" required>
            </div>*/
            <div class="form-group">
                <label for="permissions">Quyền</label>
                <input type="text" id="permissions" name="permissions" required>
            </div>
            <div class="form-group">
                <button type="submit">Tạo Tài Khoản</button>
            </div>
        </form>
    </div>
</div>
<script>
	function toggleCreateAccountForm(event) {
	    var form = document.getElementById('createAccountForm');
	    if (form.style.display === 'none' || form.style.display === '') {
	        form.style.display = 'block';
	    } else {
	        form.style.display = 'none';
	    }
	}
	
	document.addEventListener('DOMContentLoaded', function() {
	    var form = document.getElementById('createAccountForm');
	    form.addEventListener('click', function(event) {
	        event.stopPropagation(); // Ngăn chặn sự kiện lan truyền lên cấp cha
	    });
	});
	

</script>
</body>
</html>
