<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style><%@include file="/WEB-INF/resources/css/QuanLy_CSS/QuanLySinhVien.css"%></style>
<script src="https://kit.fontawesome.com/e70d1e2fed.js"></script>
</head>
<body>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/WEB-INF/views/includes/menu.jsp"%>
<div class="main_content">
	<h1 class="title">Quản Lý Sinh Viên</h1>
    <table>
        <thead>
            <tr>
                <th scope="col">Mã sinh viên <a href="#" data-column="MASV" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
				<th scope="col">Họ </th>
				<th scope="col">Tên <a href="#" data-column="TEN" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
				<th scope="col">Giới tính <a href="#" data-column="GIOITINH" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
				<th scope="col">Ngày sinh <a href="#" data-column="NGAYSINH" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
                <th scope="col">Địa chỉ</th>
                <th scope="col">SDT</th>
                <th scope="col">Vi phạm</th>
                <th scope="col">Người quản lý</th>
                <th scope="col">Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="sinhVien" items="${ListSV}">
                <tr>
                    <td>${sinhVien.getMaSV()}</td>
                    <td>${sinhVien.getHo()}</td>
                    <td>${sinhVien.getTen()}</td>
                    <td>${sinhVien.getGioiTinh()}</td>
                    <td>${sinhVien.getNgaySinh()}</td>
                    <td>${sinhVien.getDiaChi()}</td>
                    <td>${sinhVien.getSdt()}</td>
                    <td>Nội dung vi phạm</td>
                    <td>${sinhVien.getMaQL()}</td>
                    <td>
                        <a href="../quanly/QLSinhVien/${sinhVien.getMaSV()}/edit" class="btn button_action button_edit">Sửa</a>
                        <a href="" class="btn button_action button_delete">Xoá</a>
                    </td>                   
                </tr>
            </c:forEach>
        </tbody>
    </table> 
    
   
    
   <!--  form xác nhận xoá -->
    <div class="modal1">
	    <div class="confirmDelete">
	
	
	        <form name="delete-user-form" method="POST">
	            <div class="desc">Thông báo</div>
	            <div class="close2">
	                <span class="icon-close">
	                   <span class="fa fa-close"></span>
	                </span>
	            </div>
	            <p>Bạn thật sự muốn xóa sản phẩm này ?</p>
	            <div class="action">
	                <button type="submit" id="btn-delete-user" class="btn">
	                    Đúng
	                </button>
	                <a class="btn denied"> Hủy </a>
	            </div>
	        </form>
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

        // Tải trạng thái sắp xếp đã lưu từ localStorage
        var savedMode = localStorage.getItem('sortMode-' + column);
        if (savedMode) {
            link.setAttribute('data-mode', savedMode);
            link.setAttribute('href', "http://localhost:8080/quan_ly_ktx/quanly/QLSinhVien/" + column + "/sort/" + savedMode);
        }

        icon.addEventListener("click", function(event) {
            var currentMode = link.getAttribute('data-mode');
            var newMode = currentMode === 'asc' ? 'desc' : 'asc';

            // Cập nhật data-mode và href trên thẻ a
            link.setAttribute('data-mode', newMode);
            var newHref = "http://localhost:8080/quan_ly_ktx/quanly/QLSinhVien/" + column + "/sort/" + newMode;
            link.setAttribute('href', newHref);

            // Lưu trạng thái mới vào localStorage
            localStorage.setItem('sortMode-' + column, newMode);

            window.location.href = newHref;  // Chuyển hướng người dùng
        });
    });
});




</script>
 
</body>
</html>