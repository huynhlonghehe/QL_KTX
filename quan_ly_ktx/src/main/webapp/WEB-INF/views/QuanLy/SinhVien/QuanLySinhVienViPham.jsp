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
<style><%@include file="/WEB-INF/resources/css/QuanLy_CSS/QuanLySinhVienViPham.css"%></style>
<script src="https://kit.fontawesome.com/e70d1e2fed.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@latest"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
</head>
<body>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/WEB-INF/views/includes/menu.jsp"%>

<div class="main_content">
	<h1 class="title">Quản Lý Sinh Viên Vi Phạm Nội Quy</h1>
	<div class="createReport">
		<button type="button" class="btn btn-primary">Lập biên bản</button>
	</div>
	
	<div class="createRule">
		<button type="button" class="btn btn-success">Thêm quy tắc mới</button>
	</div>
	
	<div id="ListSinhVienViPhamNoiQuy" >
	    <%-- <c:choose>
		    <c:when test="${not empty ListSV_ChuaCoTK}"> --%>
        <table>
	        <thead>
	            <tr>
	                <th scope="col">Mã sinh viên <a href="#" data-column="MASV" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
					<th scope="col">Họ </th>
					<th scope="col">Tên <a href="#" data-column="TEN" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
					<th scope="col">Giới tính <a href="#" data-column="GIOITINH" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
					<th scope="col">Ngày sinh <a href="#" data-column="NGAYSINH" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
	                <th scope="col">Nội dung vi phạm</th>
	                <th scope="col">Mức độ vi phạm</th>
	                <th scope="col">Thời gian vi phạm</th>
	            </tr>
	        </thead>
	        <tbody>
	        <c:forEach var="sinhVienVP" items="${ListSV_ViPhamNoiQuy}">
		        	<tr>
		        		<td>${sinhVienVP.getMaSV()}</td>
		        		<td>${sinhVienVP.getHo() }</td>
		        		<td>${sinhVienVP.getTen() }</td>
		        		<td>${sinhVienVP.getGioiTinh() }</td>
		        		<td>${sinhVienVP.getNgaySinh() }</td>
	        			<td>${sinhVienVP.getTenQuyTac() }</td>
	        			<td style="text-align: center">${sinhVienVP.getMucDoViPham() }</td>
	        			<td>${sinhVienVP.getThoiGianViPham() }</td>
		        	</tr>
	        	</c:forEach>
	        </tbody>
	    </table> 
		  <%--   </c:when>
		    <c:otherwise>
		        <h1 style="text-align: center; color: red">Không có dữ liệu sinh viên</h1>
		    </c:otherwise>
		</c:choose> --%>
	</div>
</div>

<script type="text/javascript">
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
    var rows = document.querySelectorAll("tbody tr"); // Lấy tất cả các dòng trong tbody

    rows.forEach(function(row) {
        if (row.cells[6].textContent == 3) {
            row.classList.add("serious"); // Thêm class 'expired' nếu vi phạm mức 3
        }
    });
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

</script>
</body>
</html>