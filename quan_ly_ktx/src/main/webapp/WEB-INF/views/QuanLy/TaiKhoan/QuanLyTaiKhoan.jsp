<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style><%@include file="/WEB-INF/resources/css/QuanLy_CSS/QuanLyTaiKhoan.css"%></style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

</head>
<body>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/WEB-INF/views/includes/menu.jsp"%>
<div class="main_content">
	<h1 class="title">Quản Lý Tài Khoản</h1>
	<!-- Này là phần về yêu cầu tạo tài khoản cho sinh viên chưa có tài khoản -->
	<div class="requestTK-container">
		<button type="button" class="requestTK-btn" onclick="toggleListSinhVienDontHaveAccount()">Yêu Cầu</button>
		<span class="badge1"><strong>${SoLuongSV_ChuaCoTk}</strong></span>
	</div>
	
	<div id="ListSinhVienDontHaveAccount" class="container mt-4" style="display: none">
        <table class="table table-hover table-striped">
            <thead class="table-info">
                <tr>
               		<th scope="col" style="width: 50px">STT</th>
                    <th scope="col">Mã sinh viên</th>
                    <th scope="col">Họ</th>
                    <th scope="col">Tên</th>
                    <th scope="col">Lớp</th>
                    <th scope="col">Ngày sinh</th>
                    <th scope="col">Hành động</th>
                </tr>
            </thead>
            <tbody>
            <c:choose>
   				 <c:when test="${not empty ListSV_ChuaCoTK}">
                <c:forEach var="sinhVienChuaCoTK" items="${ListSV_ChuaCoTK}" varStatus = "i">
                    <tr>
                    	<td style="width: 50px">${i.index + 1}</td>
                        <td>${sinhVienChuaCoTK.getMaSV()}</td>
                        <td>${sinhVienChuaCoTK.getHo()}</td>
                        <td>${sinhVienChuaCoTK.getTen()}</td>
                        <td>${sinhVienChuaCoTK.getLop()}</td>
                        <td>${sinhVienChuaCoTK.getNgaySinh()}</td>
                        <td>
                            <a class="btn btn-primary btn-sm" href="#" onclick="toggleCreateAccountForm('${sinhVienChuaCoTK.getMaSV()}')">Tạo tài khoản</a>
                        </td>
                    </tr>
                </c:forEach>
                </c:when>
			    <c:otherwise>
			         <tr><td colspan="7" style="text-align: center; color: red">Không có dữ liệu tài khoản</td></tr>
			    </c:otherwise>
			</c:choose>
            </tbody>
        </table>
	</div>

	<!-- Kết thúc phần yêu cầu tạo tài khoản -->
	
	<!-- Phần này là phần danh sách các tài khoản -->
    <div id="ListAccount" class="container mt-4">
        <table class="table table-hover table-striped table-bordered">
            <thead class="table-info">
                <tr>
                	<th scope="col" style="width: 25px">STT</th>
	                <th scope="col">Tên Tài Khoản</th>
	                <th scope="col">Mật Khẩu</th>
	                <th scope="col">Tình Trạng</th>
	                <th scope="col">Quyền</th>
	                <th scope="col" style="width: 50px">Hành động </th>
                </tr>
            </thead>
            <tbody>
            	<c:choose>
    				<c:when test="${not empty ListTK}">
	                 	<c:forEach var="taikhoan" items="${ListTK}" varStatus = "i">
			                <tr>
			                	<td style="width: 50px">${i.index + 1}</td>
			                    <td>${taikhoan.getTenDangNhap()}</td>
			                    <td>${taikhoan.getMatKhau()}</td>
			                    <td>${taikhoan.getTinhTrang()}</td>
			                    <td>${taikhoan.getQuyen()}</td>
			                    <td>
			                    	<a class="btn-link" href="../quanly/QLTaiKhoan/${taikhoan.getTenDangNhap()}/edit" title="Sửa thông tin tài khoản"><i class="fa-solid fa-pen-to-square" style="color: #63E6BE;"></i></a>
			                    	<a class="btn-link delete-link"  href="../quanly/QLTaiKhoan/${taikhoan.getTenDangNhap()}/delete" title="Xoá tài khoản"><i class="fa-solid fa-trash" style="color: #fa0000;"></i></a>
			                    </td>
			                </tr>
			            </c:forEach>
			         </c:when>
		            <c:otherwise>
				        <tr><td rowspan="6" style="text-align: center; color: red">Không có dữ liệu sinh viên</td></tr>
				    </c:otherwise>
		    	</c:choose>
            </tbody>
        </table>
		    
	</div>
   <!-- Kết thúc phần danh sách các tài khoản -->
   
   <!--  <button type="button" class="btn btn-primary button_createTK" onclick="toggleCreateAccountForm()">Tạo tài khoản</button> -->
	<div id="createAccountForm" style="display: none">
		<div><i id="closeForm" class="fa-solid fa-xmark close" style="color: white;"></i></div>
        <h2>Tạo Tài Khoản</h2>
        <form:form action="/quan_ly_ktx/quanly/QLTaiKhoan/createTK" method="post">
            <div class="form-group">
                <label for="username">Tên đăng nhập:</label>
                <input type="text" id="tenDangNhap" name="tenDangNhap" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="password" id="matKhau" name="matKhau" required>
            </div>
            <div class="form-group">
                <label for="permissions">Quyền</label>
                <input type="text" id="quyen" name="quyen" value="Sinh viên" required>
            </div>
            <input type="hidden" id="tinhTrang" name="tinhTrang" value="Còn hoạt động">
            <div class="form-group">
                <button type="submit">Tạo Tài Khoản</button>
            </div>
        </form:form>
    </div>
</div>
<script>
	document.addEventListener("DOMContentLoaded", function() {
	    var tbody = document.querySelector("table tbody");
	    var rows = tbody.querySelectorAll("tr").length;
	    var maxRows = 12;
	
	    for (var i = rows; i < maxRows; i++) {
	        var tr = document.createElement("tr");
	        tr.innerHTML = "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
	        tbody.appendChild(tr);
	    }
	});
	

	function toggleCreateAccountForm(maSV) {
	    var form = document.getElementById('createAccountForm');
	    document.getElementById('tenDangNhap').value = maSV;
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
	
	document.addEventListener('DOMContentLoaded', function() {
	    // Tìm tất cả các thẻ a có class là 'delete-link'
	    var deleteLinks = document.querySelectorAll('.delete-link');

	    deleteLinks.forEach(function(link) {
	        link.addEventListener('click', function(event) {
	            event.preventDefault(); // Ngăn thẻ a chuyển hướng trực tiếp
	            var href = this.href; // Lưu lại đường dẫn href của thẻ a

	            Swal.fire({
	                title: 'Bạn có chắc chắn muốn xoá?',
	                text: "Bạn sẽ không thể hoàn tác điều này!",
	                icon: 'warning',
	                showCancelButton: true,
	                confirmButtonColor: '#3085d6',
	                cancelButtonColor: '#d33',
	                confirmButtonText: 'Xoá',
	                cancelButtonText: 'Huỷ'
	            }).then((result) => {
	                if (result.isConfirmed) {
	                    // Chuyển hướng đến href nếu người dùng xác nhận
	                    window.location.href = href;
	                }
	            });
	        });
	    });
	});
	
	function toggleListSinhVienDontHaveAccount() {
	    var table_SV = document.getElementById('ListSinhVienDontHaveAccount');
	    var table_TK = document.getElementById('ListAccount');
	    if (table_SV.style.display === 'none' || table_SV.style.display === '') {
	    	table_SV.style.display = 'block';
	    	table_TK.style.display = 'none';
	    } else {
	    	table_SV.style.display = 'none';
	    	table_TK.style.display = 'block';
	    }
	}

	 /* Đóng form khi nhấn vào biểu tượng close */
	document.getElementById('closeForm').addEventListener('click', function() {
	    var form = document.getElementById('createAccountForm');
	    form.style.display = 'none';
	});
	/* Kết thúc */
</script>
</body>
</html>
