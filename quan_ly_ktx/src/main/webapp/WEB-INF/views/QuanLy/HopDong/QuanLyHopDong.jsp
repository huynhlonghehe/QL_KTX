<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@ page import="java.time.LocalDate" %>
<%
    LocalDate today = LocalDate.now();
    String todayStr = today.toString(); // Chuyển ngày hiện tại thành chuỗi
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style><%@include file="/WEB-INF/resources/css/QuanLy_CSS/QuanLyHopDong.css"%></style>
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10"> -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://kit.fontawesome.com/e70d1e2fed.js"></script>
</head>
<body>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/WEB-INF/views/includes/menu.jsp"%>

<div class="main_content">
	<h1 class="title">Quản Lý Hợp Đồng</h1>
    <table>
        <thead>
            <tr>
                <th scope="col">Mã hợp đồng <a href="#" data-column="MAHD" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
				<th scope="col">Ngày tạo <a href="#" data-column="NGAYTAO" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
				<th scope="col">Ngày hết hạn <a href="#" data-column="NGAYHETHAN" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
				<th scope="col">Số tiền <a href="#" data-column="SOTIEN" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
				<th scope="col">Năm học <a href="#" data-column="NAMHOC" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>
				<th scope="col">Học kỳ <a href="#" data-column="HOCKY" data-mode="asc"><i class="fa-solid fa-sort"></i></a></th>              
                <th scope="col">Mã phòng </th>
                <th scope="col">Mã sinh viên </th>
                <th scope="col">Mã quản lý </th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="hopDong" items="${ListHD}">
                <tr>
                    <td>${hopDong.getMaHD()}</td>
                    <td>${hopDong.getNgayTao()}</td>
                    <td>${hopDong.getNgayHetHan()}</td>
                    <td>${hopDong.getSoTien()}</td>
                    <td>${hopDong.getNamHoc()}</td>
                    <td>${hopDong.getHocKy()}</td>
                    <td>${hopDong.getMaPhong()}</td>
                    <td>${hopDong.getMaSV()}</td>
                    <td>${hopDong.getMaQL()}</td>
                    <td>
	                    <a href="../quanly/QLHopDong/${hopDong.getMaHD()}/edit">Sửa</a>
	                    <a href="">Xoá</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table> 
	<div class="creatHD">
		<button type="button" class="btn btn-primary button_createHD" onclick="toggleCreateHopDongForm()">Hợp đồng mới</button>
			<div id="createHopDongForm" style="display: none">
		        <h2>Tạo hợp đồng mới</h2>
		        <form:form action="../quanly/QLHopDong/createHD" modelAttribute="hopDongMoi" method="post">
		            <div class="form-group full">
			            <label for="maHD">Mã hợp đồng</label>
			            <input class="edit_input" id="maHD" type="text" name="maHD" placeholder="Mã hợp đồng" required="required"/>
			        </div>
			        <div class="form-group">
			            <div class="half">
			                <label for="ngayTao">Ngày tạo</label>
			                <input class="edit_input" id="ngayTao" type="date" name="ngayTao" placeholder="Ngày tạo" value="<%= todayStr %>" required="required" onchange="updateMinEndDate();"/>
			            </div>
			           <div class="half">
			                <label for="ngayHetHan">Ngày hết hạn</label>
			                <input class="edit_input" id="ngayHetHan" type="date" name="ngayHetHan" placeholder="Ngày hết hạn" required="required"/>
			            </div>
			        </div>
			        <div class="form-group">
			         	<div class="half">
			                <label for="soTien">Số tiền</label>
			                <input class="edit_input" id="soTien" type="number" min = 0 name="soTien" placeholder="Số tiền" required="required"/>
			            </div>
			            <div class="half">
			                <label for="namHoc">Năm học</label>
			                <%-- <input class="edit_input" id="namHoc" type="text" name="namHoc" placeholder="Năm học" value="${hopDong.getNamHoc()}"/> --%>
			                 <select class="edit_input" name="namHoc" id="namHoc" required="required">
							    <option value="2023-2024">2023-2024</option>
							    <option value="2024-2025">2024-2025</option>
							</select>
			            </div>
			            
			        </div>
			        <div class="form-group">
			           <div class="half">
			                <label for="hocKy">Học kỳ</label>
			                <select class="edit_input"  id="hocKy" name="hocKy" required="required">
			                	 <option value="1">1</option>
			                	 <option value="2">2</option>
			                	 <option value="3">3</option>	
			                </select>
			            </div>
			            <div class="half">
			                <label for="maPhong">Mã phòng</label>
			                <input class="edit_input" id="maPhong" type="text" name="maPhong" placeholder="Mã phòng" required="required"/>
			            </div>
			        </div>
			        <div class="form-group">
			            <div class="half">
			                <label for="maSV">Mã sinh viên</label>
			                <input class="edit_input" id="maSV" type="text" name="maSV" placeholder="Mã sinh viên" required="required"/>
			            </div>
			            <div class="half">
			                <label for="maQL">Mã quản lý</label>
			                <input class="edit_input" id="maQL" type="text" name="maQL" placeholder="Mã quản lý" required="required"/>
			            </div>
			        </div>
			        <button type="submit">Tạo</button>
	        </form:form>
	    </div>
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
	        var today = new Date().toISOString().slice(0, 10); // Ngày hiện tại ở dạng YYYY-MM-DD

	        rows.forEach(function(row) {
	            var ngayHetHan = row.cells[2].textContent; // Giả sử ngày hết hạn ở cột thứ 3
	            if (new Date(ngayHetHan) < new Date(today)) {
	                row.classList.add("expired"); // Thêm class 'expired' nếu đã quá hạn
	            }
	        });
	    });
	 
	 /* Thông báo */
	 document.addEventListener('DOMContentLoaded', function() {
		    var errorMessage = "${errorMessage}";
		    console.log(errorMessage)
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
	
	 /* Ẩn hiện form tạo hợp đồng */
	 function toggleCreateHopDongForm(event) {
		    var form = document.getElementById('createHopDongForm');
		    if (form.style.display === 'none' || form.style.display === '') {
		        form.style.display = 'block';
		    } else {
		        form.style.display = 'none';
		    }
		}
	 /* Kết thúc */
	 
	 /* Set giá trị ngày tạo là ngày hiện tại và cập nhật min cho ngày hết hạn */

		document.addEventListener('DOMContentLoaded', function() {
		    var ngayTao = document.getElementById('ngayTao');
		    var today = new Date().toISOString().slice(0, 10); // Lấy ngày hiện tại và chuyển đổi thành chuỗi YYYY-MM-DD
		    ngayTao.value = today; // Thiết lập giá trị ngày tạo
		    updateMinEndDate(); // Cập nhật giới hạn ngày hết hạn
		});

		function updateMinEndDate() {
		    var ngayTao = document.getElementById('ngayTao').value;
		    var ngayHetHan = document.getElementById('ngayHetHan');
		    ngayHetHan.min = ngayTao; // Cập nhật thuộc tính min của ngày hết hạn
		}
	 /* Kết thúc */
	 
</script>

</body>
</html>