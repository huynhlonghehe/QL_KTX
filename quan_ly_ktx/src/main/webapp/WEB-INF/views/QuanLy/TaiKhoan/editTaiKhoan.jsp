<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa Thông Tin Tài Khoản</title>
    <style>
       <%@include file="/WEB-INF/resources/css/QuanLy_CSS/editTaiKhoan.css" %>
    </style>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    
</head>
<body>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/WEB-INF/views/includes/menu.jsp"%>
<div class="main_content">
	<form:form class="edit_form"  modelAttribute="taiKhoan" action="../${taiKhoan.getTenDangNhap()}/update" method="POST">
        <h2>Sửa Tài Khoản</h2>
        <input class = "edit_input" type="text" name="tenDangNhap" placeholder="Tên đăng nhập" value="${taiKhoan.getTenDangNhap()}"/>
        <input class = "edit_input" type="text" name="matKhau" placeholder="Mật khẩu" value="${taiKhoan.getMatKhau()}"/>
      	<select class="edit_input" name="tinhTrang">
        	<option value="0" ${taiKhoan.tinhTrang != 'Đã ngưng hoạt động' ? 'selected' : ''}>Đã ngưng hoạt động</option>
		    <option value="1" ${taiKhoan.tinhTrang == 'Còn hoạt động' ? 'selected' : ''}>Còn hoạt động</option> 
		</select>
        <select class="edit_input" name="quyen">
		    <option value="ADMIN" ${taiKhoan.getQuyen() == 'ADMIN' ? 'selected' : ''} ${taiKhoan.getQuyen() != 'ADMIN' ? 'disabled' : ''}>ADMIN</option>
		    <option value="QUANLY" ${taiKhoan.getQuyen() == 'Quản lý' ? 'selected' : ''} ${taiKhoan.getQuyen() == 'Sinh viên' ? 'disabled' : ''}>Quản lý</option>
		    <option value="QUANLY" ${taiKhoan.getQuyen() == 'Quản lý tài khoản' ? 'selected' : ''} ${taiKhoan.getQuyen() == 'Sinh viên' ? 'disabled' : ''}>Quản lý tài khoản</option>
		    <option value="QUANLY" ${taiKhoan.getQuyen() == 'Quản lý sinh viên' ? 'selected' : ''} ${taiKhoan.getQuyen() == 'Sinh viên' ? 'disabled' : ''}>Quản lý sinh viên</option>
		    <option value="QUANLY" ${taiKhoan.getQuyen() == 'Quản lý hợp đồng' ? 'selected' : ''} ${taiKhoan.getQuyen() == 'Sinh viên' ? 'disabled' : ''}>Quản lý hợp đồng</option>
		    <option value="QUANLY" ${taiKhoan.getQuyen() == 'Quản lý phòng' ? 'selected' : ''} ${taiKhoan.getQuyen() == 'Sinh viên' ? 'disabled' : ''}>Quản lý phòng</option>
		    <option value="QUANLY" ${taiKhoan.getQuyen() == 'Quản lý vật tư' ? 'selected' : ''} ${taiKhoan.getQuyen() == 'Sinh viên' ? 'disabled' : ''}>Quản lý vật tư</option>
		    <option value="QUANLY" ${taiKhoan.getQuyen() == 'Quản lý hoá đơn điện' ? 'selected' : ''} ${taiKhoan.getQuyen() == 'Sinh viên' ? 'disabled' : ''}>Quản lý hoá đơn điện</option>
		    <option value="SINHVIEN" ${taiKhoan.getQuyen() == 'Sinh viên' ? 'selected' : ''}>Sinh viên</option>
		</select>
        <button class="edit_button" type="button" onclick="confirmSave()">Lưu</button>
    </form:form>
</div>

<!-- Modal -->
<!-- <div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeModal()">&times;</span>
        <p>Bạn có chắc chắn muốn lưu các thay đổi không?</p>
        <div class="button_group">
            <button type="button" class="edit_button" onclick="submitForm()">Xác nhận</button>
            <button type="button" class="edit_button" style="background-color: #f44336;" onclick="closeModal()">Hủy</button>
        </div>
    </div>
</div> -->
<script>

function confirmSave() {
    Swal.fire({
        title: 'Bạn có chắc chắn?',
        text: "Bạn có muốn lưu các thay đổi này không?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Lưu',
        cancelButtonText: 'Hủy'
    }).then((result) => {
        if (result.isConfirmed) {
            // Thực hiện submit form nếu người dùng xác nhận
            document.getElementsByClassName('edit_form')[0].submit();
        }
    });
}

</script>
</body>
</html>
