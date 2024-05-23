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
<style><%@include file="/WEB-INF/resources/css/QuanLy_CSS/QuanLySinhVien.css"%></style>
<script src="https://kit.fontawesome.com/e70d1e2fed.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

</head>
<body>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/WEB-INF/views/includes/menu.jsp"%>
<div class="main_content">
	<h1 class="title">Quản Lý Sinh Viên</h1>
	<div class="searchContainer">
		<form method="GET" action="/quan_ly_ktx/quanly/QLSinhVien/searchSinhVien" class="searchSinhVien" id="searchForm">
			<input type="text" name="maSV" placeholder="Mã sinh viên" class="inputSearch" id="nameInput"/>
			<input type="text" name="ho" placeholder="Họ" class="inputSearch" id="nameInput"/>
			<input type="text" name="ten" placeholder="Tên" class="inputSearch" id="nameInput"/>
			<!-- <input type="text" name="gioiTinh" placeholder="Giới tính" class="inputSearch" id="nameInput"/> -->
			<select class="inputSearch"  id="nameInput" name="gioiTinh">
				<option>Giới tính</option>
               	<option value="Nam" >Nam</option>
               	<option value="Nữ" >Nữ</option>
            </select>
			<input type="date" name="ngaySinh" placeholder="Ngày sinh" class="inputSearch" id="nameInput"/>
			<input type="text" name="diaChi" placeholder="Địa chỉ" class="inputSearch" id="nameInput"/>
			<input type="text" name="lop" placeholder="Lớp" class="inputSearch" id="nameInput"/>
			<button type="submit" value="Search" class="buttonSearch"><i class="fa-solid fa-magnifying-glass" style="color: #ffffff;"></i></button>
		</form>
	</div>
	
    <table>
        <thead>
            <tr>
                <th scope="col">Mã sinh viên <a href="#" data-column="MASV" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
				<th scope="col">Họ </th>
				<th scope="col">Tên <a href="#" data-column="TEN" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
				<th scope="col">Giới tính <a href="#" data-column="GIOITINH" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
				<th scope="col">Ngày sinh <a href="#" data-column="NGAYSINH" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                <th scope="col">Lớp</th>
                <th scope="col">Địa chỉ</th>
                <th scope="col">SDT</th>
                <th scope="col">Người sửa cuối</th>
                <th scope="col">Xét điều kiện ở</th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
        	<c:choose>
	        	<c:when test="${not empty ListSV}">
		            <c:forEach var="sinhVien" items="${ListSV}">
		                <tr>
		                    <td>${sinhVien.getMaSV()}</td>
		                    <td>${sinhVien.getHo()}</td>
		                    <td>${sinhVien.getTen()}</td>
		                    <td>${sinhVien.getGioiTinh()}</td>
		                    <td>${sinhVien.getNgaySinh()}</td>
		                    <td>${sinhVien.getLop()}</td>
		                    <td>${sinhVien.getDiaChi()}</td>
		                    <td>${sinhVien.getSdt()}</td>
		                    <td>${sinhVien.getNguoiSuaDoiCuoi()}</td>
		                    <td style="text-align: center">
					            <c:set var="viPhamMucDo1" value="0"/>
					            <c:set var="viPhamMucDo2" value="0"/>
					            <c:set var="viPhamMucDo3" value="0"/>
					            <c:set var="checked" value="false"/>
					            <c:set var="tooltipText" value=""/>
					            <c:forEach var="viPham" items="${ListSV_CoViPham}">
					                <c:if test="${viPham.getMaSV() == sinhVien.getMaSV()}">
					                    <c:choose>
					                        <c:when test="${viPham.getMucDoViPham() == 1}">
					                        	<c:set var="viPhamMucDo1" value="${viPhamMucDo1 + 1}"/>
					                        </c:when>
					                        <c:when test="${viPham.getMucDoViPham() == 2}">
					                        	<c:set var="viPhamMucDo2" value="${viPhamMucDo2 + 1}"/>
					                        </c:when>
					                        <c:when test="${viPham.getMucDoViPham() == 3}">
					                        	<c:set var="viPhamMucDo3" value="${viPhamMucDo3 + 1}"/>
					                        </c:when>
					                    </c:choose>
					                </c:if>
					               
					            </c:forEach>
					            <!-- Ở vi phạm mức độ 3 nhân với 4 vì nếu vi phạm mức độ 3 1 lần là out lun -->
					             <c:if test = "${(viPhamMucDo1*1) + (viPhamMucDo2)*2 + (viPhamMucDo3)*4 > 3}">
					                	<c:set var="checked" value="true"/>
				                </c:if>
				                <c:set var="tooltipText" value="Vi phạm mức độ 1: ${viPhamMucDo1} lần, mức độ 2: ${viPhamMucDo2} lần, mức độ 3: ${viPhamMucDo3} lần" />
					            <input class="check" type="checkbox" ${checked ? '' : 'checked'}  title="${tooltipText}" disabled="disabled"/>
					        </td>
		                    <td>
			                    <a class="btn-link" href="/quan_ly_ktx/quanly/QLSinhVien/${sinhVien.getMaSV()}/edit"><i class="fa-solid fa-pen-to-square" style="color: #63E6BE;"></i></a>
			                    <a class="btn-link delete-link" href="/quan_ly_ktx/quanly/QLSinhVien/${sinhVien.getMaSV()}/delete"><i class="fa-solid fa-trash" style="color: #fa0000;"></i></a>
			                    <a href="">Chi tiết</a>
		                    </td>
		                </tr>
		            </c:forEach>
	            </c:when>
	            <c:otherwise>
			        <tr><td rowspan="10" style="text-align: center; color: red">Không có dữ liệu sinh viên</td></tr>
			    </c:otherwise>
	    	</c:choose>
        </tbody>
    </table> 
    <div class="create_SV">
	    <button type="button" class="btn btn-outline-primary button_createSV" onclick="toggleCreateSinhVienForm()">Thêm sinh viên</button>
	    <div id="createSinhVienForm" style="display: none">
		     <form:form action="/quan_ly_ktx/quanly/QLSinhVien/createSV" class="form" modelAttribute="newSinhVien">
		        <h2>Thêm Sinh Viên</h2>
		        <div class="form-group full">
		            <label for="maSV">Mã sinh viên</label>
		            <input class="edit_input" id="maSV" type="text" name="maSV" placeholder="Mã sinh viên" required="required"/>
		        </div>
		        <div class="form-group">
		            <div class="half">
		                <label for="ho">Họ và tên đệm</label>
		                <input class="edit_input" id="ho" type="text" name="ho" placeholder="Họ và tên đệm" required="required"/>
		            </div>
		            <div class="half">
		                <label for="ten">Tên</label>
		                <input class="edit_input" id="ten" type="text" name="ten" placeholder="Tên" required="required"/>
		            </div>
		        </div>
		        <div class="form-group">
		            <div class="half">
		                <label for="ngaySinh">Ngày sinh</label>
		                <input class="edit_input" id="ngaySinh" type="date" name="ngaySinh" placeholder="Ngày sinh" required="required"/>
		            </div>
		            <div class="half">
		                <label for="gioiTinh">Giới tính</label>
		                <select class="edit_input"  id="gioiTinh" name="gioiTinh" required="required">
		                	 <option value="Nam" >Nam</option>
		                	 <option value="Nữ" >Nữ</option>
		                </select>
		            </div>
		        </div>
		        <div class="form-group">
		            <div class="half">
		                <label for="lop">Lớp</label>
		                <input class="edit_input" id="lop" type="text" name="lop" placeholder="Lớp" required="required"/>
		            </div>
		             <div class="half">
		                <label for="diaChi">Địa chỉ</label>
		                <input class="edit_input" id="diaChi" type="text" name="diaChi" placeholder="Địa chỉ" required="required"/>
		            </div>
		        </div>
		        <div class="form-group">
		            <div class="half">
		                <label for="sdt">Số điện thoại</label>
		                <input class="edit_input" id="sdt" type="text" name="sdt" placeholder="Số điện thoại" required="required"/>
		            </div>
		            
		            <div class="half">
		                <label for="maQL">Người quản lý</label>
		                <!-- <input class="edit_input" id="maQL" type="text" name="maQL" placeholder="Mã quản lý"/> -->
		             	<select class="edit_input" id="maQL" name="maQL">
						    <c:forEach var="quanLySinhVien" items="${ListQuanLySinhVien}">
						        <option value="${quanLySinhVien}" 
						                ${quanLySinhVien ==  sessionScope.USERNAME ? 'selected' : ''}>
						            ${quanLySinhVien}
						        </option>
						    </c:forEach>
						</select>
		            </div>
		        </div>
		        <button type="submit">Thêm</button>
		    </form:form>
	    </div>
    </div>
</div> 
 
<script>
document.addEventListener("DOMContentLoaded", function() {
    var tbody = document.querySelector("table tbody");
    var rows = tbody.querySelectorAll("tr").length;
    var maxRows = 12;

    for (var i = rows; i < maxRows; i++) {
        var tr = document.createElement("tr");
        tr.innerHTML = "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
        tbody.appendChild(tr);
    }
});

document.addEventListener("DOMContentLoaded", function() {
    var sortIcons = document.querySelectorAll(".fa-sort");
    sortIcons.forEach(function(icon) {
        var link = icon.closest('a');
        var column = link.getAttribute('data-column');

        // Kiểm tra và cập nhật trạng thái ban đầu dựa vào localStorage
        var storedMode = localStorage.getItem('sortMode-' + column);
        if (storedMode) {
            link.setAttribute('data-mode', storedMode);
        }

        icon.addEventListener("click", function(event) {
            event.preventDefault(); // Ngăn chặn hành vi mặc định

            // Lấy URL hiện tại và các tham số tìm kiếm
            var currentUrl = new URL(window.location);
            var searchParams = new URLSearchParams(currentUrl.search);

            // Lấy trạng thái hiện tại và cập nhật
            var currentMode = link.getAttribute('data-mode');
            var newMode = currentMode === 'asc' ? 'desc' : 'asc';

            // Cập nhật localStorage
            localStorage.setItem('sortMode-' + column, newMode);

            // Thiết lập hoặc cập nhật tham số 'sort' và 'direction'
            searchParams.set('sort', column);
            searchParams.set('direction', newMode);

            // Cập nhật URL với tham số mới và chuyển hướng
            currentUrl.search = searchParams.toString();
            window.location.href = currentUrl.toString();
        });
    });
});

/* Ẩn hiện form tạo sinh viên mới */
function toggleCreateSinhVienForm(event) {
	    var form = document.getElementById('createSinhVienForm');
	    if (form.style.display === 'none' || form.style.display === '') {
	        form.style.display = 'block';
	    } else {
	        form.style.display = 'none';
	    }
	}
/* Kết thúc */

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

/* Thông báo xác nhận muốn xoá sinh viên không */
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
/* Kết thúc */

</script>
 
</body>
</html>