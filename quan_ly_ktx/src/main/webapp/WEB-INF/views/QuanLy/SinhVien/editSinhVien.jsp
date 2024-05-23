<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa Tài Khoản Sinh Viên</title>
<style><%@include file="/WEB-INF/resources/css/QuanLy_CSS/editSinhVien.css"%></style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
</head>
<body>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/WEB-INF/views/includes/menu.jsp"%>
<div class="main_content">
    <form:form action="../${sinhVien.getMaSV()}/update" class="form" id="editForm" modelAttribute="sinhVienEdit">
        <h2>Sửa Tài Khoản Sinh Viên ${sinhVien.getMaSV()}</h2>
        <div class="form-group full">
            <label for="maSV">Mã sinh viên</label>
            <!-- Mã sinh viên mới nếu có sửa mã sinh viên -->
            <input class="edit_input" id="maSV" type="text" name="maSV" placeholder="Mã sinh viên" value="${sinhVien.getMaSV()}" required="required"/>  
        </div>
        <div class="form-group">
            <div class="half">
                <label for="ho">Họ</label>
                <input class="edit_input" id="ho" type="text" name="ho" placeholder="Họ" value="${sinhVien.getHo()}" required="required"/>
            </div>
            <div class="half">
                <label for="ten">Tên</label>
                <input class="edit_input" id="ten" type="text" name="ten" placeholder="Tên" value="${sinhVien.getTen()}" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <div class="half">
                <label for="ngaySinh">Ngày sinh</label>
                <input class="edit_input" id="ngaySinh" type="date" name="ngaySinh" placeholder="Ngày sinh" value="${sinhVien.getNgaySinh()}" required="required"/>
            </div>
            <div class="half">
                <label for="gioiTinh">Giới tính</label>
                <select class="edit_input"  id="gioiTinh" name="gioiTinh" required="required">
                	 <option value="Nam" ${sinhVien.getGioiTinh() == 'Nam' ? 'selected' : ''}>Nam</option>
                	 <option value="Nữ" ${sinhVien.getGioiTinh() == 'Nữ' ? 'selected' : ''}>Nữ</option>
                </select>
            </div>
        </div>
        <div class="form-group">
        	<div class="half">
		                <label for="lop">Lớp</label>
		                <input class="edit_input" id="lop" type="text" name="lop" placeholder="Lớp" value="${sinhVien.getLop()}" required="required"/>
		            </div>
            <div class="half">
                <label for="diaChi">Địa chỉ</label>
                <input class="edit_input" id="diaChi" type="text" name="diaChi" placeholder="Địa chỉ" value="${sinhVien.getDiaChi()}" required="required"/>
            </div>
        </div>
        <div class="form-group">
            <div class="half">
                <label for="sdt">Số điện thoại</label>
                <input class="edit_input" id="sdt" type="text" name="sdt" placeholder="Số điện thoại" value="${sinhVien.getSdt()}" required="required"/>
            </div>
            <div class="half">
                <label for="username">Tên tài khoản</label>
                <input class="edit_input" id="username" type="text" name="username" disabled="disabled" placeholder="Tên tài khoản" value="${sinhVien.getUsername()}" required="required"/>
            </div>
        </div>
        <input type="hidden" id="nguoiSuaDoiCuoi" name="nguoiSuaDoiCuoi" value = "${sessionScope.USERNAME}">
        <button class="update-btn" type="submit">Cập nhật</button>
    </form:form>
</div>

<script type="text/javascript">
/* Thông báo lỗi và kết quả*/
document.addEventListener('DOMContentLoaded', function() {
    var errorMessage = "${errorMessage}";
    var successMessage = "${successMessage}";

    if (errorMessage) {
        Swal.fire({
            title: 'Error!',
            text: errorMessage,
            icon: 'error',
            confirmButtonText: 'OK'
        });
    } else if (successMessage) {
        Swal.fire({
            title: 'Success!',
            text: successMessage,
            icon: 'success',
            confirmButtonText: 'OK'
        });
    }
});
/* Kết thúc */
 
 
 /* Thông báo xác nhận muốn sửa không */
document.addEventListener('DOMContentLoaded', function() {
    // Tìm tất cả các nút có class là 'update-btn'
    var deleteButtons = document.querySelectorAll('.update-btn');

    deleteButtons.forEach(function(button) {
        button.addEventListener('click', function(event) {
            event.preventDefault(); // Ngăn chặn hành vi mặc định của nút

            Swal.fire({
                title: 'Bạn có chắc chắn muốn sửa?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sửa',
                cancelButtonText: 'Huỷ'
            }).then((result) => {
                if (result.isConfirmed) {
                    // Gửi form nếu người dùng xác nhận
                	document.getElementById('editForm').submit();
                }
            });
        });
    });
});

/* Kết thúc */

</script>

</body>
</html>
