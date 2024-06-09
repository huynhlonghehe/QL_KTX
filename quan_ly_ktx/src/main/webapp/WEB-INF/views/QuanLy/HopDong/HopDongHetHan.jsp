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
	<h1 class="title">Hợp Đồng Hết Hạn</h1>
	<div id="ListSinhVienHetHanHopDong" class="container mt-4">
		<c:choose>
		    <c:when test="${not empty ListHDHetHan}">
		        <table class="table table-hover table-striped table-bordered">
		            <thead class="table-info">
		                <tr>
		                	<th scope="col" style="width: 50px">STT</th>
		                    <th scope="col">Mã hợp đồng</th>
		                    <th scope="col">Ngày tạo</th>
		                    <th scope="col">Ngày hết hạn</th>
		                    <th scope="col">Năm học</th>
		                    <th scope="col">Học kỳ</th>
		                    <th scope="col">Mã phòng</th>
		                    <th scope="col">Mã sinh viên</th>
		                    <th scope="col" style="width: 60px"></th>
		                </tr>
		            </thead>
		            <tbody>
		                <c:forEach var="hopDongHetHan" items="${ListHDHetHan}" varStatus = "i">
		                    <tr>
		                    	<td style="width: 50px">${i.index + 1}</td>
		                        <td>${hopDongHetHan.getMaHD()}</td>
		                        <td>${hopDongHetHan.getNgayTao()}</td>
		                        <td>${hopDongHetHan.getNgayHetHan()}</td>
		                        <td>${hopDongHetHan.getNamHoc()}</td>
		                        <td>${hopDongHetHan.getHocKy()}</td>
		                        <td>${hopDongHetHan.getMaPhong()}</td>
		                        <td>${hopDongHetHan.getMaSV()}</td>
		                        <td>
		                            <a class="btn-link" href="#" onclick="toggleCreateHopDongForm('${hopDongHetHan.getMaSV()}', '${nextMaHD }')" title="Tạo hợp đồng mới"><i class="fa-solid fa-plus" style="color: 0d6efd"></i></a>
		                        	<a class="btn-link delete-link" href="/quan_ly_ktx/quanly/QLHopDong/${hopDongHetHan.getMaHD()}/delete" title="Sửa hợp đồng"><i class="fa-solid fa-trash" style="color: #fa0000;"></i></a>
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
	
	<div class="creatHD">
			<div id="createHopDongForm" style="display: none">
				<div><i id="closeForm" class="fa-solid fa-xmark close" style="color: white;"></i></div>
		        <h2>Tạo hợp đồng mới</h2>
		        <form:form action="/quan_ly_ktx/quanly/QLHopDong/createHD" modelAttribute="hopDongMoi" method="post">
		            <div class="form-group full">
			            <label for="maHD">Mã hợp đồng</label>
			            <input class="edit_input" id="createHD_maHD" type="text" name="maHD" placeholder="Mã hợp đồng" required="required"/>
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
    var tbody = document.querySelector("#ListSinhVienHetHanHopDong tbody");

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
	 function toggleCreateHopDongForm(maSV, nextMaHD) {
		    var form = document.getElementById('createHopDongForm');
		    document.getElementById('maSV').value = maSV;
		    document.getElementById('createHD_maHD').value = nextMaHD;
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
 
	 /* Đóng form khi nhấn vào biểu tượng close */
	document.getElementById('closeForm').addEventListener('click', function() {
	    var form = document.getElementById('createHopDongForm');
	    form.style.display = 'none';
	});
	/* Kết thúc */
</script>

</body>
</html>