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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<style><%@include file="/WEB-INF/resources/css/QuanLy_CSS/QuanLyHopDong.css"%></style>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://kit.fontawesome.com/e70d1e2fed.js"></script>
</head>
<body>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/WEB-INF/views/includes/menu.jsp"%>

<div class="main_content">
	<div class="requestHD-container">
		<button type="button" class="requestHD-btn" onclick="toggleListSinhVienChuaCoHopDong()">Sinh Viên chưa có hợp đồng</button>
		<span class="badge1"><strong>${soLuongSVChuaCoHD}</strong></span>
	</div>
	
	<div id="ListSinhVienChuaCoHopDong" class="container mt-4" style="display: none">
	    <c:choose>
		    <c:when test="${not empty ListSVChuaCoHD}">
		        <table class="table table-hover table-striped">
		            <thead class="table-info">
		                <tr>
		                    <th scope="col">Mã sinh viên</th>
		                    <th scope="col">Họ</th>
		                    <th scope="col">Tên</th>
		                    <th scope="col">Lớp</th>
		                    <th scope="col">Ngày sinh</th>
		                    <th scope="col">Điều kiện</th>
		                    <th scope="col">Hành động</th>
		                </tr>
		            </thead>
		            <tbody>
		                <c:forEach var="sinhVienChuaCoHD" items="${ListSVChuaCoHD}">
		                    <tr>
		                        <td>${sinhVienChuaCoHD.getMaSV()}</td>
		                        <td>${sinhVienChuaCoHD.getHo()}</td>
		                        <td>${sinhVienChuaCoHD.getTen()}</td>
		                        <td>${sinhVienChuaCoHD.getLop()}</td>
		                        <td>${sinhVienChuaCoHD.getNgaySinh()}</td>
		                        <td>
						            <c:set var="viPhamMucDo1" value="0"/>
						            <c:set var="viPhamMucDo2" value="0"/>
						            <c:set var="viPhamMucDo3" value="0"/>
						            <c:set var="checked" value="false"/>
						            <c:set var="tooltipText" value=""/>
						            <c:forEach var="viPham" items="${ListSV_CoViPham}">
						                <c:if test="${viPham.getMaSV() == sinhVienChuaCoHD.getMaSV()}">
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
		                            <a class="btn btn-primary btn-sm create-hopdong-btn" href="#" onclick="toggleCreateHopDongForm('${sinhVienChuaCoHD.getMaSV()}')">Tạo hợp đồng</a>
		                        </td>
		                    </tr>
		                </c:forEach>
		            </tbody>
		        </table>
		    </c:when>
		    <c:otherwise>
		        <h1 style="text-align: center; color: red">Không có dữ liệu sinh viên</h1>
		    </c:otherwise>
		</c:choose>
	</div>
	
	<h1 class="title">Quản Lý Hợp Đồng</h1>
    <table id="ListHopDong">
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
                    <td>
	                    <a class="btn-link" href="/quan_ly_ktx/quanly/QLHopDong/${hopDong.getMaHD()}/edit"><i class="fa-solid fa-pen-to-square" style="color: #63E6BE;"></i></a>
	                    <a class="btn-link delete-link" href="/quan_ly_ktx/quanly/QLHopDong/${hopDong.getMaHD()}/delete"><i class="fa-solid fa-trash" style="color: #fa0000;"></i></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table> 
	<div class="creatHD">
		<button type="button" class="btn btn-primary button_createHD" onclick="toggleCreateHopDongForm()">Hợp đồng mới</button>
			<div id="createHopDongForm" style="display: none">
		        <h2>Tạo hợp đồng mới</h2>
		        <form:form action="/quan_ly_ktx/quanly/QLHopDong/createHD" modelAttribute="hopDongMoi" method="post">
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
			           		<select class="edit_input" id="maPhong" name="maPhong" required="required">
			           			<c:forEach var="maPhong" items="${ListPhong}">
						        	<option value="${maPhong}">${maPhong }</option>
				    			</c:forEach>
			           		</select>
			            </div>
			        </div>
			        <div class="form-group">
			            <div class="half">
			                <label for="maSV">Mã sinh viên</label>
			                <input class="edit_input" id="maSV" type="text" name="maSV" placeholder="Mã sinh viên" required="required" readonly="readonly"/>
			            </div>
			            <div class="half">
			                <label for="maQL">Mã quản lý</label>
			                <!-- <input class="edit_input" id="maQL" type="text" name="maQL" placeholder="Mã quản lý"/> -->
			            </div>
			        </div>
			        <button type="submit">Tạo</button>
	        </form:form>
	    </div>
	</div>
</div>


<script type="text/javascript">
document.addEventListener("DOMContentLoaded", function() {
    // Chọn phần tử tbody của bảng bạn muốn thêm các hàng trống
    var tbody = document.querySelector("#ListHopDong tbody");

    // Kiểm tra xem phần tử tbody có tồn tại hay không
    if (tbody) {
        var rows = tbody.querySelectorAll("tr").length;
        var maxRows = 12;

        for (var i = rows; i < maxRows; i++) {
            var tr = document.createElement("tr");
            tr.innerHTML = "<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>";
            tbody.appendChild(tr);
        }
    }
});

	
	 document.addEventListener("DOMContentLoaded", function() {
	        var rows = document.querySelectorAll("#ListHopDong tbody tr"); // Lấy tất cả các dòng trong tbody
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
	 function toggleCreateHopDongForm(maSV) {
		    var form = document.getElementById('createHopDongForm');
		    document.getElementById('maSV').value = maSV;
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
	
	function toggleListSinhVienChuaCoHopDong() {
	    var table_SV = document.getElementById('ListSinhVienChuaCoHopDong');
	    var table_HD = document.getElementById('ListHopDong');
	    if (table_SV.style.display === 'none' || table_SV.style.display === '') {
	    	table_SV.style.display = 'block';
	    	table_HD.style.display = 'none';
	    } else {
	    	table_SV.style.display = 'none';
	    	table_HD.style.display = 'block';
	    }
	}
	
	document.addEventListener("DOMContentLoaded", function() {
	    var rows = document.querySelectorAll("#ListSinhVienChuaCoHopDong tbody tr"); // Lấy tất cả các dòng trong tbody

	    rows.forEach(function(row) {
	    	var checkbox = row.querySelector('.check'); // Tìm checkbox trong hàng này
	        if (!checkbox.checked) { // Kiểm tra nếu checkbox tồn tại và chưa được đánh dấu
                row.classList.add("table-danger"); // Thêm class 'expired' nếu chưa được đánh dấu và vi phạm mức 3
             // Vô hiệu hóa nút "Tạo hợp đồng" trong hàng này
                var createHDButton = row.querySelector('.create-hopdong-btn');
                if (createHDButton) {
                	createHDButton.removeAttribute('onclick');
                	createHDButton.classList.remove("btn-primary")
                	createHDButton.classList.add("btn-secondary")
                	console.log(createHDButton)
                }
	        }
	    });
	});
	 
</script>

</body>
</html>